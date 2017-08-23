package se.good_omens.EliteDangerous_TraderHelper.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.reporters.Files;

import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.BaseCommodity;
import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.StarSystem;
import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.Station;
import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.TradeCalculator;
import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.UserData;
import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.TradeCalculator.TradeResult;
import se.good_omens.EliteDangerous_TraderHelper.common.parsers.ParseInitialFile;
import se.good_omens.EliteDangerous_TraderHelper.common.parsers.ParseListings;
import se.good_omens.EliteDangerous_TraderHelper.common.parsers.ParseModules;
import se.good_omens.EliteDangerous_TraderHelper.common.parsers.ParseStationJSON;
import se.good_omens.EliteDangerous_TraderHelper.common.parsers.ParseSystemJSON;
import se.good_omens.EliteDangerous_TraderHelper.common.utils.RuntimeProperties;
import se.good_omens.EliteDangerous_TraderHelper.common.utils.SystemData;

public class testFindBestTradeBasedOnInitialLoad {
	public static String			protocol		= "file:///";
	public static String			filePath		= "C:/Users/TuX/workspace/ED_TraderHelper/rawData/";
	private static SystemData	systemData	= new SystemData();

	@Test
	public void verifyDataIsAvailable() {
		try {
			System.out.println("Loading files...");
			ParseModules mParser = new ParseModules(
			    Files.readFile(new File(systemData.getWorkingDirectory() + "\\rawData\\modules.json")));
			ParseStationJSON sParser = new ParseStationJSON(
			    Files.readFile(new File(systemData.getWorkingDirectory() + "\\rawData\\stations.json")), mParser);
			ParseSystemJSON sysParser = new ParseSystemJSON(
			    Files.readFile(new File(systemData.getWorkingDirectory() + "\\rawData\\systems_populated.json")));
			System.out.println("Parsing files...");
			sParser.parseStationJSON();
			sysParser.parseSystemJSON();
			TreeMap<Long, Station> stations = sParser.getStations();
			stations = new ParseListings().parseListings(
			    Files.readFile(new File(systemData.getWorkingDirectory() + "\\rawData\\listings.csv")), stations);
			TreeMap<Long, StarSystem> systems = sysParser.getSystems();

			System.out.println("Loading systems onto their systems");
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
			System.out.println("Systems, stations, modules and listings loaded...");

			RuntimeProperties props = new ParseInitialFile(
			    Files.readFile(new File(systemData.getWorkingDirectory() + "\\tradehelper.ini"))).getRuntimeProperties();
			System.out.println("\nInitial properties:");
			for (String key : props.listKeys()) {
				System.out.println(key + " - " + props.getEntry(key));
			}
			System.out.println("\t");

			UserData userData = new UserData(props, systems);

			Assert.assertNotEquals(systems.size(), userData.getCurrentBubble().size());

			/*
			 * for( Entry<Long, StarSystem> item :
			 * userData.getCurrentBubble().entrySet()) {
			 * System.out.println(item.getKey() +" : "+ item.getValue().getName()
			 * +" [No# Stations: "+ item.getValue().getStations().size()+
			 * "] with the distance of "+
			 * userData.getCurrentSystem().getPosition().distanceTo(item.getValue().
			 * getPosition()) ); }
			 * 
			 * for( BaseCommodity item:
			 * userData.getCurrentStation().getExportCommodities()) {
			 * System.out.println(item.getName() +" : B:"+ item.getBuyingPrice()
			 * +"\tS:"+ item.getSellingPrice()); }
			 * System.out.println("  Sold items:"); for( BaseCommodity item :
			 * userData.getCurrentStation().getSoldCommodities()) {
			 * System.out.println(item.getName() +" :  B:"+ item.getBuyingPrice()
			 * +" S:"+ item.getSellingPrice()); }
			 * System.out.println("\n  Bought items:"); for( BaseCommodity item :
			 * userData.getCurrentStation().getBoughtCommodities()) {
			 * System.out.println(item.getName() + " :  B:"+ item.getBuyingPrice()
			 * +"  S:"+ item.getSellingPrice()); }
			 */
			System.out.println(userData.getCurrentBubble().size() + " in the current bubble.");

			System.out.println("TradeCalculation is has started.");
			TradeCalculator calc = new TradeCalculator(userData);
			calc.run();
			System.out.println(calc.getTotalNumberOfStationsLookedAt() + " stations looked at for trade.");
			System.out.println(calc.getPossibleTrades().size() + " possible trades.");

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
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
