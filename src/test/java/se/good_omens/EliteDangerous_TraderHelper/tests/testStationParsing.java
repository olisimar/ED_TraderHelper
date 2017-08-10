package se.good_omens.EliteDangerous_TraderHelper.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;
import java.util.TreeMap;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.reporters.Files;

import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.Station;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.SHIP_TYPE;
import se.good_omens.EliteDangerous_TraderHelper.common.parsers.ParseListings;
import se.good_omens.EliteDangerous_TraderHelper.common.parsers.ParseModules;
import se.good_omens.EliteDangerous_TraderHelper.common.parsers.ParseStationJSON;

public class testStationParsing {
	public static String	protocol	= "file:///";
	public static String	filePath	= "C:/Users/TuX/workspace/ED_TraderHelper/rawData/";

	@Test
	public void printAll() throws ParseException {
		try {
			ParseModules mParser = new ParseModules(Files.readFile(new File(filePath + "modules.json")));
			ParseStationJSON sParser = new ParseStationJSON(Files.readFile(new File(filePath + "stations.json")), mParser);
			sParser.parseStationJSON();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCommoditiesLoadedOntoStation() {
		try {
			ParseModules mParser = new ParseModules(Files.readFile(new File(filePath + "modules.json")));
			ParseStationJSON sParser = new ParseStationJSON(Files.readFile(new File(filePath + "stations.json")), mParser);
			sParser.parseStationJSON();
			TreeMap<Long, Station> stations = sParser.getStations();
			String data = Files.readFile(new File(filePath + "listings.csv"));
			ParseListings listings = new ParseListings();
			listings.parseListings(data, stations);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testShipsLoadedOntoStation() {
		try {
			ParseModules mParser = new ParseModules(Files.readFile(new File(filePath + "modules.json")));
			ParseStationJSON sParser = new ParseStationJSON(Files.readFile(new File(filePath + "stations.json")), mParser);
			sParser.parseStationJSON();
			TreeMap<Long, Station> stations = sParser.getStations();
			Station station = stations.get(173l);
			Set<SHIP_TYPE> soldShips = station.getSoldShips();
			Assert.assertFalse(soldShips.isEmpty());
			Assert.assertTrue(soldShips.size() == 5);
			Assert.assertTrue(soldShips.contains(SHIP_TYPE.ADDER));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testModulesLoadedOntoStation() {
		try {
			ParseModules mParser = new ParseModules(Files.readFile(new File(filePath + "modules.json")));
			ParseStationJSON sParser = new ParseStationJSON(Files.readFile(new File(filePath + "stations.json")), mParser);
			sParser.parseStationJSON();
			TreeMap<Long, Station> stations = sParser.getStations();
			Station station = stations.get(173l);
			
			Assert.assertEquals(station.getBaseName(), "Lichtenberg Port");
			Assert.assertFalse(station.getSoldModules().isEmpty());
			Assert.assertEquals(station.getSoldModules().size(), 154);
			Assert.assertNotEquals(station.getSpecificShipModule(738), null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
