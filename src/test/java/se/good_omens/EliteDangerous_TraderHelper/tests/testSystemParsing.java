package se.good_omens.EliteDangerous_TraderHelper.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import org.testng.reporters.Files;

import se.good_omens.EliteDangerous_TraderHelper.common.parsers.ParseSystemJSON;

public class testSystemParsing {

	public static String	protocol	= "file:///";
	public static String	filePath	= "C:/Users/TuX/workspace/ED_TraderHelper/rawData/";

	@Test
	public void printAll() throws ParseException {
		try {
			ParseSystemJSON sysParser = new ParseSystemJSON(Files.readFile(new File(filePath + "systems_populated.json")));
			sysParser.parseSystemJSON();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
