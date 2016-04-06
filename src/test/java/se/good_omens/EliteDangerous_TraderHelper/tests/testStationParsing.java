package se.good_omens.EliteDangerous_TraderHelper.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import org.testng.reporters.Files;

import se.good_omens.EliteDangerous_TraderHelper.common.utils.ParseStationJSON;

public class testStationParsing {
	public static String	protocol	= "file:///";
	public static String	filePath	= "C:/OWN/LunaWorkspace/ED_TraderHelper/rawData/";

	@Test
	public void printAll() throws ParseException {
		try {
			ParseStationJSON sParser = new ParseStationJSON(Files.readFile(new File(filePath + "stations.json")));
			sParser.parseStationJSON();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}