package se.good_omens.EliteDangerous_TraderHelper.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
			System.out.println(systemData.getWorkingDirectory());
			System.out.print("Loading of files: ");
			ParseModules mParser = new ParseModules(FileHandler.readFile(systemData.getWorkingDirectory() + systemData.getDirectoryDelimiter() + "rawData"+ systemData.getDirectoryDelimiter(), "modules.json"));
			ParseStationJSON sParser = new ParseStationJSON(FileHandler.readFile(systemData.getWorkingDirectory() + systemData.getDirectoryDelimiter() + "rawData"+ systemData.getDirectoryDelimiter(), "stations.json"), mParser);
			ParseSystemJSON sysParser = new ParseSystemJSON(FileHandler.readFile(systemData.getWorkingDirectory() + systemData.getDirectoryDelimiter() + "rawData"+ systemData.getDirectoryDelimiter(), "systems_populated.json"));
			System.out.println((System.currentTimeMillis() - startTime) +"ms");
			System.out.print("Parsing of files: ");
			
			sParser.run();
			sysParser.parseSystemJSON();
			
			TreeMap<Long, Station> stations = sParser.getStations();
			System.out.println((System.currentTimeMillis() - startTime) +"ms");
			
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

			System.out.println(userData.getCurrentBubble().size() + " systems in the current bubble.");
			int noStations = 0;
			for(StarSystem starSystem : userData.getCurrentBubble().values()) {
				noStations += starSystem.getStations().size();
			}
			System.out.println(noStations + " stations in the current bubble.");

			System.out.println("TradeCalculation has started.");
			long calcTime = System.currentTimeMillis();

			TradeCalculator calc = new TradeCalculator(userData);
			Thread calcThread = new Thread(calc);
			calcThread.run();
			
			System.out.println("Calculation took: "+ (System.currentTimeMillis() - calcTime) +"ms or "+ ((System.currentTimeMillis() - calcTime) / 1000) +"s" );
			System.out.println(calc.getTotalNumberOfStationsLookedAt() + " stations looked at for trade. "+ calc.getPossibleTrades().size() + " possible trades.");
			

			int counter = 1;
			int noToPrint = 30;
			
			TreeMap<Integer, List<TradeResult>> bestTradesBubble = new TreeMap<>();
			TreeMap<Integer, List<TradeResult>> bestTradesStation = new TreeMap<>();
			
			for(TradeResult trade : calc.getPossibleTrades()) {
				TradeResult returnTrade = new TreeSet<TradeResult>(calc.findPossibleTrades(trade.getToStation(), trade.getFromStation())).pollFirst();
				if(returnTrade != null) {
					int netGain = trade.getExpectedProfit() + returnTrade.getExpectedProfit();
					ArrayList<TradeResult> trades = new ArrayList<>();
					trades.add(trade); trades.add(returnTrade);
					bestTradesBubble.put(netGain, trades);
					if(trade.getFromStation() == userData.getCurrentStation()) {
						bestTradesStation.put(netGain, trades);
					}
				}
			}
			if(bestTradesBubble.size() < 31) {
				noToPrint = calc.getPossibleTrades().size();
			}
			
			/* Printing the best Bubble here */
			System.out.println("\n> Best trades in bubble.");
			while (counter <= noToPrint) {
				Entry<Integer, List<TradeResult>> trade = bestTradesBubble.pollLastEntry();
				TradeResult result = trade.getValue().get(0);
				TradeResult returnTrade = trade.getValue().get(1);
				if (counter < 10) {
					System.out.println("[0" + counter +" - "+ trade.getKey() +"]  " + result + " <-> " + returnTrade);
				} else {
					System.out.println("[" + counter +" - "+ trade.getKey() +"]  " + result + " <-> " + returnTrade);
				}
				counter++;
			}

			/* Printing the best Bubble here */
			System.out.println("\n> Best trade for Station "+ userData.getCurrentStation().getBaseName() +".");
			counter = 1;
			while (counter <= 10) {
				Entry<Integer, List<TradeResult>> trade = bestTradesStation.pollLastEntry();
				TradeResult result = trade.getValue().get(0);
				TradeResult returnTrade = trade.getValue().get(1);
				if (counter < 10) {
					System.out.println("[0" + counter +" - "+ trade.getKey() +"]  " + result + " <-> " + returnTrade);
				} else {
					System.out.println("[" + counter +" - "+ trade.getKey() +"]  " + result + " <-> " + returnTrade);
				}
				counter++;
			}
/*
			
			
			calc.run();
			counter = 1;
//			System.out.println("\n Specific base : "+ userData.getCurrentStation().getBaseName() +" {"+ userData.getCurrentSystem().getName() +"}");
			
			while (counter <= calc.getPossibleTrades().size()) {
				TradeResult result = calc.getPossibleTrades().pollFirst();
				if (result != null && result.getFromStation().getId() == userData.getCurrentStation().getId()) {
					TreeSet<TradeResult> returnTrade = new TreeSet<TradeResult>(
					    calc.findPossibleTrades(result.getToStation(), result.getFromStation()));
					if (counter < 10) {
						//System.out.println("[0" + counter + "]  " + result + " <-> " + returnTrade.pollFirst());
					} else {
						//System.out.println("[" + counter + "]  " + result + " <-> " + returnTrade.pollFirst());
					}
					int netGain = result.getExpectedProfit() + returnTrade.pollFirst().getExpectedProfit();
					ArrayList<TradeResult> trades = new ArrayList<>();
					trades.add(result); trades.add(returnTrade.pollFirst());
					bestTrades.put(netGain, trades);
				}
				counter++;
			}
			
			counter = 1;
			if(!bestTrades.isEmpty()) {
				while(counter < 11) {
					Entry<Integer, List<TradeResult>> item = bestTrades.lastEntry();
					System.out.print(counter +"> Profit: "+ item.getKey() +" ### ");
					System.out.println(item.getValue().get(0) +" <--> "+ item.getValue().get(1));
					counter++;
					bestTrades.remove(item.getKey());
				}
			} else {
				System.out.println("No trades for choosen station, "+ userData.getCurrentStation().getBaseName() +".");
			}
			*/	
			/**/
		} catch (FileMissingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
