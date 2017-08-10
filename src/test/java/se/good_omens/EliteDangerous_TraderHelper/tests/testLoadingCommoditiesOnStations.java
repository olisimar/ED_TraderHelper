package se.good_omens.EliteDangerous_TraderHelper.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.TreeMap;

import org.testng.annotations.Test;
import org.testng.reporters.Files;

import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.Station;
import se.good_omens.EliteDangerous_TraderHelper.common.parsers.ParseListings;
import se.good_omens.EliteDangerous_TraderHelper.common.parsers.ParseModules;
import se.good_omens.EliteDangerous_TraderHelper.common.parsers.ParseStationJSON;

public class testLoadingCommoditiesOnStations {
	public static String	protocol	= "file:///";
	public static String	filePath	= "C:/Users/TuX/workspace/ED_TraderHelper/rawData/";

	@Test
	public void load() {
		try {
			ParseModules mParser = new ParseModules(Files.readFile(new File(filePath + "modules.json")));
			ParseStationJSON sParser = new ParseStationJSON(Files.readFile(new File(filePath + "stations.json")), mParser);
			sParser.parseStationJSON();
			TreeMap<Long, Station> stations = sParser.getStations();
			String data = Files.readFile(new File(filePath + "listings.csv"));
			ParseListings listings = new ParseListings();
			listings.parseListings(data, stations);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
