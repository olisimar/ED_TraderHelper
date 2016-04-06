package se.good_omens.EliteDangerous_TraderHelper.tests.utils;

import org.testng.annotations.Test;

import se.good_omens.EliteDangerous_TraderHelper.common.enums.COMMODITY_CATEGORY;
import se.good_omens.EliteDangerous_TraderHelper.common.utils.JSONParserToXml;
import se.good_omens.EliteDangerous_TraderHelper.common.utils.PARSE_TYPE;
import se.good_omens.xmlModel.XmlNode;

public class BasicFileTests {

	public static String protocol = "file:///";
	public static String filePath = "C:/OWN/LunaWorkspace/ED_TraderHelper/rawData/";


	@Test(enabled = false)
	public void test_fileReadCategories() {
		JSONParserToXml parser = new JSONParserToXml(filePath +"commodities.json", PARSE_TYPE.CATEGORIES);
		System.out.println(parser.getNodeData().xmlPrint());
	}

	@Test(enabled = true)
	public void test_fileReadCommodities() {
		JSONParserToXml parser = new JSONParserToXml(filePath +"commodities.json", PARSE_TYPE.COMMIDITIES);
		XmlNode result = parser.getNodeData();
		System.out.println(parser.getNodeData().xmlPrint());

		if(false) {
			for(XmlNode node : parser.getNodeData().getChildren()) {
				String name = node.getChildByName("name").getTextValue().replace(" ", "_").replace("-", "_").replace(".", "").toUpperCase();
				int price = new Integer(node.getChildByName("averagePrice").getTextValue()).intValue();
				System.out.println(
						", "+ name +"("+ node.getAttributeValue("id")
						+", \""+ node.getChildByName("name").getTextValue() +"\""
						+", "+ node.getChildByName("averagePrice").getTextValue()
						+", COMMODITY_CATEGORY."+ COMMODITY_CATEGORY.fromInt( new Integer(node.getChildByName("category").getAttributeValue("id")).intValue()).name()
						+")");
			}
		}
	}
	@Test(enabled = false)
	public void test_fileReadSystems() {
		JSONParserToXml parser = new JSONParserToXml(filePath +"systems.json", PARSE_TYPE.SYSTEMS);
		System.out.println(parser.getNodeData().xmlPrint());
	}
	@Test(enabled = false)
	public void test_fileReadStationsLite() {
		JSONParserToXml parser = new JSONParserToXml(filePath +"stations.json", PARSE_TYPE.STATIONS_LITE);
		System.out.println(parser.getNodeData().xmlPrint());
	}
	@Test(enabled = false)
	public void test_fileReadStations() {
		JSONParserToXml parser = new JSONParserToXml(filePath +"stations.json", PARSE_TYPE.STATIONS);
		System.out.println(parser.getNodeData().xmlPrint());
	}
}
