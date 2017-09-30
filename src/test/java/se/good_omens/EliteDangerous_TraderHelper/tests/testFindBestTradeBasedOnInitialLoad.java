package se.good_omens.EliteDangerous_TraderHelper.tests;

import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

import org.testng.Assert;
import org.testng.annotations.Test;

import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.StarSystem;
import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.Station;
import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.TradeCalculator;
import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.TradeCalculator.TradeResult;
import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.UserData;
import se.good_omens.EliteDangerous_TraderHelper.common.exceptions.FileMissingException;
import se.good_omens.EliteDangerous_TraderHelper.common.parsers.ParseInitialFile;
import se.good_omens.EliteDangerous_TraderHelper.common.parsers.ParseListings;
import se.good_omens.EliteDangerous_TraderHelper.common.parsers.ParseModules;
import se.good_omens.EliteDangerous_TraderHelper.common.parsers.ParseStationJSON;
import se.good_omens.EliteDangerous_TraderHelper.common.parsers.ParseSystemJSON;
import se.good_omens.EliteDangerous_TraderHelper.common.utils.FileHandler;
import se.good_omens.EliteDangerous_TraderHelper.common.utils.RuntimeProperties;
import se.good_omens.EliteDangerous_TraderHelper.common.utils.SystemData;

public class testFindBestTradeBasedOnInitialLoad {
	private static SystemData	systemData	= new SystemData();

	@Test
	public void verifyDataIsAvailable() {
		try {
			long startTime = System.currentTimeMillis();
			System.out.print("Loading of files: ");
			ParseModules mParser = new ParseModules(FileHandler.readFile(systemData.getWorkingDirectory() + systemData.getDirectoryDelimiter() + "rawData"+ systemData.getDirectoryDelimiter(), "modules.json"));
			ParseStationJSON sParser = new ParseStationJSON(FileHandler.readFile(systemData.getWorkingDirectory() + systemData.getDirectoryDelimiter() + "rawData"+ systemData.getDirectoryDelimiter(), "stations.json"), mParser);
			ParseSystemJSON sysParser = new ParseSystemJSON(FileHandler.readFile(systemData.getWorkingDirectory() + systemData.getDirectoryDelimiter() + "rawData"+ systemData.getDirectoryDelimiter(), "systems_populated.json"));
			System.out.println((System.currentTimeMillis() - startTime) +"ms");
			System.out.print("Parsing of files: ");
			sParser.parseStationJSON();
			sysParser.parseSystemJSON();
			TreeMap<Long, Station> stations = sParser.getStations();
			System.out.println((System.currentTimeMillis() - startTime) +"ms");
			
			System.out.print("Listings loaded onto stations: ");
			stations = new ParseListings().parseListings(FileHandler.readFile(systemData.getWorkingDirectory() + systemData.getDirectoryDelimiter() + "rawData"+ systemData.getDirectoryDelimiter(), "listings.csv"), stations);
			System.out.println((System.currentTimeMillis() - startTime) +"ms");
			
			TreeMap<Long, StarSystem> systems = sysParser.getSystems();

			System.out.print("Loading stations onto their systems: ");
			long numStations = 0l;
			long numFailedLoads = 0l;
			for (Entry<Long, Station> item : stations.entrySet()) {
				numStations++;
				StarSystem system = systems.get(item.getValue().getSystemId());
				if (system != null) {
					system.addStation(item.getValue());
				} else {
					numFailedLoads++;
					System.out.println(item.getValue().getBaseName() + "[" + item.getValue().getId()
					    + "] did not find it's system id of " + item.getValue().getSystemId());
				}
			}
			System.out.println(numStations + " in total, failed to find system in " + numFailedLoads);
			System.out.println((System.currentTimeMillis() - startTime) +"ms");
			System.out.println("Systems, stations, modules and listings loaded...");

			RuntimeProperties props = new ParseInitialFile(FileHandler.readFile(systemData.getWorkingDirectory() + systemData.getDirectoryDelimiter(), "tradehelper.ini")).getRuntimeProperties();
			System.out.println("\nInitial properties:");
			for (String key : props.listKeys()) {
				System.out.println(key + " - " + props.getEntry(key));
			}
			System.out.println("Total loadtime is: "+ (System.currentTimeMillis() - startTime) +"ns or "+ ((System.currentTimeMillis() - startTime)/1000) +"s" );
			System.out.println("   ---------");

			UserData userData = new UserData(props, systems);

			Assert.assertNotEquals(systems.size(), userData.getCurrentBubble().size());

			System.out.println(userData.getCurrentBubble().size() + " in the current bubble.");

			System.out.println("TradeCalculation has started.");
			TradeCalculator calc = new TradeCalculator(userData);
			long calcTime = System.currentTimeMillis();
			Thread calcThread = new Thread(calc);
			calcThread.run();

			
			System.out.println("Calculation took: "+ (System.currentTimeMillis() - calcTime) +"ms or "+ ((System.currentTimeMillis() - calcTime) / 1000) +"s" );
			System.out.println(calc.getTotalNumberOfStationsLookedAt() + " stations looked at for trade. "+ calc.getPossibleTrades().size() + " possible trades.");
			

			int counter = 1;
			/**/
			while (counter <= 30) {
				TradeResult result = calc.getPossibleTrades().pollFirst();
				if (result != null) {
					TreeSet<TradeResult> returnTrade = new TreeSet<TradeResult>(
					    calc.findPossibleTrades(result.getToStation(), result.getFromStation()));
					if (counter < 10) {
						System.out.println("[0" + counter + "]  " + result + " <-> " + returnTrade.pollFirst());
					} else {
						System.out.println("[" + counter + "]  " + result + " <-> " + returnTrade.pollFirst());
					}
				} else {
					counter = 30;
				}
				counter++;
			}

			calc.run();
			counter = 1;
			System.out.println("\n Specific base : "+ userData.getCurrentStation().getBaseName() +" {"+ userData.getCurrentSystem().getName() +"}");
			while (counter <= 99) {
				TradeResult result = calc.getPossibleTrades().pollFirst();
				if (result != null && result.getFromStation().getId() == userData.getCurrentStation().getId()) {
					TreeSet<TradeResult> returnTrade = new TreeSet<TradeResult>(
					    calc.findPossibleTrades(result.getToStation(), result.getFromStation()));
					if (counter < 10) {
						System.out.println("[0" + counter + "]  " + result + " <-> " + returnTrade.pollFirst());
					} else {
						System.out.println("[" + counter + "]  " + result + " <-> " + returnTrade.pollFirst());
					}
				}
				counter++;
			}
			/**/
		} catch (FileMissingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
