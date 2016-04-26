package se.good_omens.EliteDangerous_TraderHelper.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.TreeMap;

import org.testng.annotations.Test;
import org.testng.reporters.Files;

import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.Station;
import se.good_omens.EliteDangerous_TraderHelper.common.utils.ParseListings;
import se.good_omens.EliteDangerous_TraderHelper.common.utils.ParseStationJSON;

public class testLoadingCommoditiesOnStations {
	public static String	protocol	= "file:///";
	public static String	filePath	= "C:/OWN/LunaWorkspace/ED_TraderHelper/rawData/";

	@Test
	public void load() {
		try {
			ParseStationJSON sParser = new ParseStationJSON(Files.readFile(new File(filePath + "stations.json")));
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
