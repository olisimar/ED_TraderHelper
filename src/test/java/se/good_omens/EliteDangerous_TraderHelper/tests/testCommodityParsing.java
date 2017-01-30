package se.good_omens.EliteDangerous_TraderHelper.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import org.testng.reporters.Files;

import se.good_omens.EliteDangerous_TraderHelper.common.parsers.ParseCommoditiesJSON;

public class testCommodityParsing {
	public static String	protocol	= "file:///";
	public static String	filePath	= "C:/OWN/LunaWorkspace/ED_TraderHelper/rawData/";

	@Test
	public void printAll() throws ParseException {
		try {
			ParseCommoditiesJSON sParser = new ParseCommoditiesJSON(Files.readFile(new File(filePath + "commodities.json")));
			sParser.parseCommoditiesJSON();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
