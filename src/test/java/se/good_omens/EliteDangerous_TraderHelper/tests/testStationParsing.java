package se.good_omens.EliteDangerous_TraderHelper.tests;

import java.util.Set;
import java.util.TreeMap;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.Station;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.SHIP_TYPE;
import se.good_omens.EliteDangerous_TraderHelper.common.exceptions.FileMissingException;
import se.good_omens.EliteDangerous_TraderHelper.common.parsers.ParseListings;
import se.good_omens.EliteDangerous_TraderHelper.common.parsers.ParseModules;
import se.good_omens.EliteDangerous_TraderHelper.common.parsers.ParseStationJSON;
import se.good_omens.EliteDangerous_TraderHelper.common.utils.FileHandler;
import se.good_omens.EliteDangerous_TraderHelper.common.utils.SystemData;

public class testStationParsing {
	private static SystemData systemData = new SystemData();

	@Test
	public void testJSONParsingOfFileContent() throws ParseException {
		try {
			ParseModules mParser = new ParseModules(FileHandler.readFile(systemData.getWorkingDirectory() + systemData.getDirectoryDelimiter() + "rawData"+ systemData.getDirectoryDelimiter(), "modules.json"));
			ParseStationJSON sParser = new ParseStationJSON(FileHandler.readFile(systemData.getWorkingDirectory() + systemData.getDirectoryDelimiter() + "rawData"+ systemData.getDirectoryDelimiter(), "stations.json"), mParser);
			sParser.parseStationJSON();
		} catch (FileMissingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCommoditiesLoadedOntoStation() {
		try {
			ParseModules mParser = new ParseModules(FileHandler.readFile(systemData.getWorkingDirectory() + systemData.getDirectoryDelimiter() + "rawData"+ systemData.getDirectoryDelimiter(), "modules.json"));
			ParseStationJSON sParser = new ParseStationJSON(FileHandler.readFile(systemData.getWorkingDirectory() + systemData.getDirectoryDelimiter() + "rawData"+ systemData.getDirectoryDelimiter(), "stations.json"), mParser);
			sParser.parseStationJSON();
			TreeMap<Long, Station> stations = sParser.getStations();
			
			String data = FileHandler.readFile(systemData.getWorkingDirectory() + systemData.getDirectoryDelimiter() + "rawData"+ systemData.getDirectoryDelimiter(), "listings.csv");
			ParseListings listings = new ParseListings();
			listings.parseListings(data, stations);
		} catch (FileMissingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testShipsLoadedOntoStation() {
		try {
			ParseModules mParser = new ParseModules(FileHandler.readFile(systemData.getWorkingDirectory() + systemData.getDirectoryDelimiter() + "rawData"+ systemData.getDirectoryDelimiter(), "modules.json"));
			ParseStationJSON sParser = new ParseStationJSON(FileHandler.readFile(systemData.getWorkingDirectory() + systemData.getDirectoryDelimiter() + "rawData"+ systemData.getDirectoryDelimiter(), "stations.json"), mParser);
			sParser.parseStationJSON();
			TreeMap<Long, Station> stations = sParser.getStations();
			Station station = stations.get(173l);
			Set<SHIP_TYPE> soldShips = station.getSoldShips();
			Assert.assertFalse(soldShips.isEmpty());
			Assert.assertTrue(soldShips.size() == 5);
			Assert.assertTrue(soldShips.contains(SHIP_TYPE.ADDER));
		} catch (FileMissingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testModulesLoadedOntoStation() {
		try {
			ParseModules mParser = new ParseModules(FileHandler.readFile(systemData.getWorkingDirectory() + systemData.getDirectoryDelimiter() + "rawData"+ systemData.getDirectoryDelimiter(), "modules.json"));
			ParseStationJSON sParser = new ParseStationJSON(FileHandler.readFile(systemData.getWorkingDirectory() + systemData.getDirectoryDelimiter() + "rawData"+ systemData.getDirectoryDelimiter(), "stations.json"), mParser);
			sParser.parseStationJSON();
			TreeMap<Long, Station> stations = sParser.getStations();
			Station station = stations.get(173l);
			
			Assert.assertEquals(station.getBaseName(), "Lichtenberg Port");
			Assert.assertFalse(station.getSoldModules().isEmpty());
			Assert.assertEquals(station.getSoldModules().size(), 154);
			Assert.assertNotEquals(station.getSpecificShipModule(738), null);
		} catch (FileMissingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
