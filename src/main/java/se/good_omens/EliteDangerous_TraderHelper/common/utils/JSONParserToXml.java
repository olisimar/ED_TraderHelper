package se.good_omens.EliteDangerous_TraderHelper.common.utils;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import se.good_omens.EliteDangerous_TraderHelper.common.enums.COMMODITY_CATEGORY;
import se.good_omens.xmlModel.XmlNode;

import com.codesnippets4all.json.parsers.JSONParser;
import com.codesnippets4all.json.parsers.JsonParserFactory;

/**
 * Parses a file that contains json data into a XmlModel
 *
 * @author Werner
 * @date Jun 8, 2015
 */
public class JSONParserToXml {

	private String	rawData;
	private XmlNode	rawNode;

	public JSONParserToXml(String file) {
		try {
			this.rawData = FileReader.readFile(file);
			this.rawNode = this.parseJSON(rawData, PARSE_TYPE.COMMIDITIES);
			this.rawNode = this.parseJSON(rawData, PARSE_TYPE.SYSTEMS);
			this.rawNode = this.parseJSON(rawData, PARSE_TYPE.STATIONS_LITE);
			this.rawNode = this.parseJSON(rawData, PARSE_TYPE.STATIONS);
		} catch(SocketException e) {
			System.out.println(" --- Message: "+ e.getMessage());
			e.printStackTrace();
			System.out.println(" -----------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private XmlNode parseJSON(String data, PARSE_TYPE type) {
		XmlNode toReturn = null;
		switch (type) {

			case COMMIDITIES:
				break;
			case SYSTEMS:
				break;
			case STATIONS_LITE:
				break;
			case STATIONS:
			default:
		}
		JSONParser parser = JsonParserFactory.getInstance().newJsonParser();
		TreeMap<Integer, String> categories = new TreeMap<Integer, String>();
		TreeMap<Integer, TreeMap<String, String>> commodity = new TreeMap<Integer, TreeMap<String,String>>();

		Map rootMap = parser.parseJson(data);
		ArrayList mailElements = (ArrayList) rootMap.get("root");
		for(Object item : mailElements) {
			if(item instanceof HashMap) {
				HashMap mItem = (HashMap) item;
				HashMap category = (HashMap) mItem.get("category");
				categories.put(new Integer((String) category.get("id")), category.get("name").toString());

				// {"id":59,"name":"Imperial Slaves","category_id":10,"average_price":16058,"category":{"id":10,"name":"Slavery"}},
				TreeMap<String, String> tmp = new TreeMap<String, String>();
				tmp.put("id", mItem.get("id").toString());
				tmp.put("name", mItem.get("name").toString());
				tmp.put("categoryId", mItem.get("category_id").toString());
				tmp.put("averagePrice", mItem.get("average_price").toString());

				commodity.put(new Integer(mItem.get("id").toString()), tmp);
			}
		}

		System.out.println(" --------------- ");
		for(Entry<Integer, String> item : categories.entrySet()) {
			System.out.println(", "+ item.getValue().toUpperCase() +"(\""+ item.getValue() +"\", "+ item.getKey() +")");
		}
		System.out.println(" --------------- ");
		for(Entry<Integer, TreeMap<String, String>> item : commodity.entrySet()) {
			TreeMap<String, String> comData = item.getValue();
			String enumName = comData.get("name").toUpperCase().replaceAll(" ", "_").replaceAll("\\.", "").replaceAll("-", "_");
			Integer price =  0;
			if((comData.get("averagePrice") != null) && ((comData.get("averagePrice").toString() != null) && !comData.get("averagePrice").toString().equalsIgnoreCase("null"))) {
				new Integer(comData.get("averagePrice").toString());
			}
			COMMODITY_CATEGORY current = COMMODITY_CATEGORY.fromInt(new Integer(comData.get("categoryId").toString()).intValue());
			System.out.println(", "+ enumName +"("+ comData.get("id") +", \""+ comData.get("name") +"\", "+ price +", COMMODITY_CATEGORY."+ current.name() +")");

		}
		System.out.println(" --------------- ");

		return toReturn;
	}

	public XmlNode parseSystemFile(String data) {
		/* ,{
		 *    "id":2,"name":"1 Geminorum","x":19.78125,"y":3.5625,"z":-153.8125,"faction":"1 Geminorum Liberals","population":141727,
		 *    "government":"Democracy","allegiance":"Federation","state":"None","security":"Medium","primary_economy":"Terraforming",
		 *    "needs_permit":0,"updated_at":1430931879
		 *  }
		 */
		return new XmlNode("Nutting");
	}

	public XmlNode parseStationLite(String data) {
		/* ,{
		 * 		"id":1,"name":"Bain Colony","system_id":18370,"max_landing_pad_size":"L","distance_to_star":16253,
		 * 		"faction":"","government":null,"allegiance":null,"state":null,"type":"Unknown Starport","has_blackmarket":0,
		 * 		"has_commodities":1,"has_refuel":null,"has_repair":null,"has_rearm":null,"has_outfitting":null,"has_shipyard":null,
		 * 		"import_commodities":[],"export_commodities":[],"prohibited_commodities":[],"economies":[],
		 * 		"updated_at":1429408824
		 * }
		 */
		return new XmlNode("Nutting");
	}

	public XmlNode parseStation(String data) {
		/* ,{
		 * 		"id":1,"name":"Bain Colony","system_id":18370,"max_landing_pad_size":"L","distance_to_star":16253,"faction":"",
		 * 		"government":null,"allegiance":null,"state":null,"type":"Unknown Starport","has_blackmarket":0,"has_commodities":1,
		 * 		"has_refuel":null,"has_repair":null,"has_rearm":null,"has_outfitting":null,"has_shipyard":null,"import_commodities":[],
		 * 		"export_commodities":[],"prohibited_commodities":[],"economies":[],"updated_at":1429408824,"listings":[
		 * 				{"id":1,"station_id":1, "commodity_id":5,"supply":0,"buy_price":0,"sell_price":378,"demand":1137,"collected_at":1429359631,"update_count":"2"}
		 * 			, {"id":2,"station_id":1,"commodity_id":6,"supply":0,"buy_price":0,"sell_price":6882,"demand":38,"collected_at":1429359631,"update_count":"2"}
		 * 			,{"id":3,"station_id":1,"commodity_id":7,"supply":0,"buy_price":0,"sell_price":534,"demand":318,"collected_at":1429359631,"update_count":"2"},{"id":4,"station_id":1,"commodity_id":14,"supply":0,"buy_price":0,"sell_price":1451,"demand":139,"collected_at":1429359632,"update_count":"2"},{"id":5,"station_id":1,"commodity_id":15,"supply":0,"buy_price":0,"sell_price":1431,"demand":375,"collected_at":1429359632,"update_count":"2"},{"id":6,"station_id":1,"commodity_id":16,"supply":0,"buy_price":0,"sell_price":472,"demand":528,"collected_at":1429359633,"update_count":"2"},{"id":7,"station_id":1,"commodity_id":17,"supply":0,"buy_price":0,"sell_price":176,"demand":18,"collected_at":1429359633,"update_count":"2"},{"id":8,"station_id":1,"commodity_id":18,"supply":0,"buy_price":0,"sell_price":421,"demand":1626,"collected_at":1429359634,"update_count":"2"},{"id":9,"station_id":1,"commodity_id":19,"supply":0,"buy_price":0,"sell_price":257,"demand":923,"collected_at":1429359634,"update_count":"2"},{"id":10,"station_id":1,"commodity_id":20,"supply":0,"buy_price":0,"sell_price":292,"demand":48,"collected_at":1429359635,"update_count":"2"},{"id":11,"station_id":1,"commodity_id":21,"supply":0,"buy_price":0,"sell_price":1638,"demand":165,"collected_at":1429359635,"update_count":"2"},{"id":12,"station_id":1,"commodity_id":8,"supply":0,"buy_price":0,"sell_price":281,"demand":7114,"collected_at":1429359636,"update_count":"2"},{"id":13,"station_id":1,"commodity_id":9,"supply":0,"buy_price":0,"sell_price":762,"demand":950,"collected_at":1429359636,"update_count":"2"},{"id":14,"station_id":1,"commodity_id":12,"supply":0,"buy_price":0,"sell_price":306,"demand":667,"collected_at":1429359636,"update_count":"2"},{"id":15,"station_id":1,"commodity_id":30,"supply":0,"buy_price":0,"sell_price":534,"demand":127,"collected_at":1429359637,"update_count":"2"},{"id":16,"station_id":1,"commodity_id":31,"supply":0,"buy_price":0,"sell_price":304,"demand":225,"collected_at":1429359637,"update_count":"2"},{"id":17,"station_id":1,"commodity_id":33,"supply":0,"buy_price":0,"sell_price":320,"demand":341,"collected_at":1429359638,"update_count":"2"},{"id":18,"station_id":1,"commodity_id":36,"supply":0,"buy_price":0,"sell_price":6882,"demand":5,"collected_at":1429359638,"update_count":"2"},{"id":19,"station_id":1,"commodity_id":42,"supply":0,"buy_price":0,"sell_price":9281,"demand":1264,"collected_at":1429359639,"update_count":"2"},{"id":20,"station_id":1,"commodity_id":45,"supply":0,"buy_price":0,"sell_price":13678,"demand":2435,"collected_at":1429359639,"update_count":"2"},{"id":21,"station_id":1,"commodity_id":46,"supply":0,"buy_price":0,"sell_price":19943,"demand":3039,"collected_at":1429359640,"update_count":"2"},{"id":22,"station_id":1,"commodity_id":47,"supply":0,"buy_price":0,"sell_price":4765,"demand":2115,"collected_at":1429359640,"update_count":"2"},{"id":23,"station_id":1,"commodity_id":52,"supply":0,"buy_price":2124,"sell_price":2122,"demand":0,"collected_at":1420167769,"update_count":"1"},{"id":24,"station_id":1,"commodity_id":75,"supply":736,"buy_price":20,"sell_price":15,"demand":0,"collected_at":1429359641,"update_count":"2"},{"id":25,"station_id":1,"commodity_id":78,"supply":0,"buy_price":0,"sell_price":1789,"demand":135,"collected_at":1429359641,"update_count":"2"},{"id":26,"station_id":1,"commodity_id":80,"supply":0,"buy_price":0,"sell_price":2149,"demand":88,"collected_at":1429359642,"update_count":"2"},{"id":659902,"station_id":1,"commodity_id":2,"supply":4036,"buy_price":112,"sell_price":107,"demand":0,"collected_at":1429359630,"update_count":"1"},{"id":659903,"station_id":1,"commodity_id":83,"supply":0,"buy_price":0,"sell_price":36459,"demand":1217,"collected_at":1429359641,"update_count":"1"}
		 * 			]
		 * 	}
		 *
		 */
		return new XmlNode("Nutting");
	}

	public XmlNode getNodeData() {
		return rawNode;
	}
}
