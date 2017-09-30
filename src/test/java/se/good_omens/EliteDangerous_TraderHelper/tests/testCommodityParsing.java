package se.good_omens.EliteDangerous_TraderHelper.tests;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import se.good_omens.EliteDangerous_TraderHelper.common.exceptions.FileMissingException;
import se.good_omens.EliteDangerous_TraderHelper.common.parsers.ParseCommoditiesJSON;
import se.good_omens.EliteDangerous_TraderHelper.common.utils.FileHandler;
import se.good_omens.EliteDangerous_TraderHelper.common.utils.SystemData;

public class testCommodityParsing {
	public static String	protocol	= "file:///";
	private static SystemData systemData = new SystemData();
	public static String	filePath	= "C:/Users/TuX/workspace/ED_TraderHelper/rawData/";
	
	@Test
	public void testParsingCommoditiesJsonWithoutErrors() throws ParseException {
		try {
			ParseCommoditiesJSON sParser = new ParseCommoditiesJSON(FileHandler.readFile(systemData.getWorkingDirectory().trim() + systemData.getDirectoryDelimiter() +"rawData"+ systemData.getDirectoryDelimiter(), "commodities.json"));
			sParser.parseCommoditiesJSON();
		} catch (FileMissingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
