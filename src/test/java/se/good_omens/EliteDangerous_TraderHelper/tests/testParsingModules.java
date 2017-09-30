package se.good_omens.EliteDangerous_TraderHelper.tests;

import java.util.Map.Entry;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.ShipModule;
import se.good_omens.EliteDangerous_TraderHelper.common.exceptions.FileMissingException;
import se.good_omens.EliteDangerous_TraderHelper.common.parsers.ParseModules;
import se.good_omens.EliteDangerous_TraderHelper.common.utils.FileHandler;
import se.good_omens.EliteDangerous_TraderHelper.common.utils.SystemData;

public class testParsingModules {
	private static SystemData systemData = new SystemData();

	@Test
	public void testJSONParsingOfFileContent() throws ParseException {
		try {
			ParseModules mParser = new ParseModules(FileHandler.readFile(systemData.getWorkingDirectory() + systemData.getDirectoryDelimiter() + "rawData"+ systemData.getDirectoryDelimiter(), "modules.json"));
			mParser.parseModules();
			for(Entry<Integer, ShipModule> mod : mParser.getShipModules().entrySet()) {
				System.out.println(mod.getValue());
			}
		} catch (FileMissingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
