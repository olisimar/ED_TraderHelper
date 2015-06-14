package se.good_omens.EliteDangerous_TraderHelper.tests.utils;

import org.testng.annotations.Test;

import se.good_omens.EliteDangerous_TraderHelper.common.utils.JSONParserToXml;

public class BasicFileTests {

	public static String filePath = "file:///C:/OWN/LunaWorkspace/ED_TraderHelper/rawData/";


	@Test
	public void test_fileRead() {
		JSONParserToXml parser = new JSONParserToXml(filePath +"commodities.json");
		parser.getNodeData();
	}
}
