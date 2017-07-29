package se.good_omens.EliteDangerous_TraderHelper.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map.Entry;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import org.testng.reporters.Files;

import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.ShipModule;
import se.good_omens.EliteDangerous_TraderHelper.common.parsers.ParseModules;

public class testParsingModules {
	public static String	protocol	= "file:///";
	public static String	filePath	= "C:/Users/TuX/workspace/ED_TraderHelper/rawData/";

	@Test
	public void printAll() throws ParseException {
		try {
			ParseModules sParser = new ParseModules(Files.readFile(new File(filePath + "modules.json")));
			sParser.parseCommodities();
			for(Entry<Integer, ShipModule> mod : sParser.getShipModules().entrySet()) {
				System.out.println(mod.getValue());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
