package se.good_omens.EliteDangerous_TraderHelper.common.utils;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import se.good_omens.EliteDangerous_TraderHelper.common.enums.COMMODITY_CATEGORY;
import se.good_omens.xmlModel.XmlNode;
/**
 * Parses a file that contains json data into a XmlModel
 *
 * @author Werner
 * @date Jun 8, 2015
 */
public class JSONParserToXml {

	private String	rawData;
	private XmlNode	rawNode;
	private FileReader	reader;

	public JSONParserToXml(String file, PARSE_TYPE type) {
		try {
			this.reader = new FileReader(file);
			JSONParser jsonParser = new JSONParser();
			this.rawNode = this.parseJSON(jsonParser, type);
		} catch (Exception e) {
			System.out.println(" ------ Type: "+ e.getClass().getSimpleName());
			System.out.println(" --- Message: "+ e.getMessage());
			e.printStackTrace();
			System.out.println(" -----------------------------");
		}
	}

	private XmlNode parseJSON(JSONParser jsonParser, PARSE_TYPE type) {
		switch (type) {
			case CATEGORIES:
				return this.parseCategories(jsonParser);
			case COMMIDITIES:
				return this.parseCommiditiesBaseData(jsonParser);
			case SYSTEMS:
				return this.parseSystemFile(jsonParser);
			case STATIONS_LITE:
				return this.parseStationLite(jsonParser);
			case STATIONS:
				return this.parseStation(jsonParser);
			default:
				throw new IllegalArgumentException("You did not provide a proper option. You provided: "+ type);
		}
	}

	private XmlNode parseCategories(JSONParser jsonParser) {
		XmlNode categories = new XmlNode("categories");
		TreeMap<Integer, XmlNode> tmp = new TreeMap<Integer, XmlNode>();
		try {
			Object commodities = jsonParser.parse(reader);
			if(commodities instanceof JSONArray) {
				Iterator iter = ((JSONArray)commodities).iterator();
				while(iter.hasNext()) {
					XmlNode cat = createCategory(iter.next());
					if(cat != null) {
						tmp.put(new Integer(cat.getAttributeValue("id")), cat);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(Entry<Integer, XmlNode> item : tmp.entrySet()) {
			categories.addChildNode(item.getValue());
		}
		return categories;
	}

	private XmlNode createCategory(Object next) {
		if(next instanceof JSONObject) {
			JSONObject jCategory = (JSONObject) next;
			if(next instanceof JSONObject) {
				JSONObject jCat = (JSONObject) next;
				if(jCat.get("category") instanceof JSONObject) {
					JSONObject cat = (JSONObject) jCat.get("category");
					Long id = (Long) cat.get("id");
					if(id != null) {
						COMMODITY_CATEGORY eCat = COMMODITY_CATEGORY.fromInt(id.intValue());
						return new XmlNode("category").addAttribute("id", eCat.getStringIndex()).setTextValue(eCat.getName());
					}
				}
			}
		}
		return null;
	}

	private XmlNode parseCommiditiesBaseData(JSONParser jsonParser) {

		return null;
	}

	private XmlNode parseSystemFile(JSONParser jsonParser) {
		// TODO Auto-generated method stub
		return null;
	}

	private XmlNode parseStationLite(JSONParser jsonParser) {
		// TODO Auto-generated method stub
		return null;
	}

	private XmlNode parseStation(JSONParser jsonParser) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * DEFUNCT!!! REFerEnCE ONLY
	 * /




	private XmlNode parseJSON(String data, PARSE_TYPE type) {
		JSONParser parser = JsonParserFactory.getInstance().newJsonParser();
		TreeMap<Integer, String> categories = new TreeMap<Integer, String>();
		TreeMap<Integer, TreeMap<String, String>> commodity = new TreeMap<Integer, TreeMap<String,String>>();
		switch (type) {
			case CATEGORIES:
				return this.parseCategories(parser, data, categories);
			case COMMIDITIES:
				return this.parseCommiditiesBaseData(parser, data, commodity);
			case SYSTEMS:
				return this.parseSystemFile(parser, data);
			case STATIONS_LITE:
				return this.parseStationLite(parser, data);
			case STATIONS:
				return this.parseStation(parser, data);
			default:
				throw new IllegalArgumentException("You did not provide a proper option. You provided: "+ type);
		}
	}

	private XmlNode parseCategories(JSONParser parser, String data, TreeMap<Integer, String> categories) {
		XmlNode toReturn = new XmlNode("categories");
		Map rootMap = parser.parseJson(data);
		ArrayList mailElements = (ArrayList) rootMap.get("root");
		for(Object item : mailElements) {
			if(item instanceof HashMap) {
				HashMap mItem = (HashMap) item;
				HashMap category = (HashMap) mItem.get("category");
				categories.put(new Integer((String) category.get("id")), category.get("name").toString());
			}
		}
		for(Entry<Integer, String> item : categories.entrySet()) {
			toReturn.addChildNode(new XmlNode("category").addAttribute("id", new Integer(item.getKey()).toString()).setTextValue(item.getValue()));
		}
		return toReturn;
	}


	/**
	 * Returns a very limited XmlNode representation of the commodities. This is
	 * merely definitions of the commodities present in the game, not all. See
	 * the full Station parsing for sales information.
	 * @param parser
	 * @param data
	 * @param categories
	 * @param commodity
	 * @return
	 * /
	private XmlNode parseCommiditiesBaseData(JSONParser parser, String data, TreeMap<Integer, TreeMap<String, String>> commodity) {
		XmlNode toReturn = new XmlNode("commodities");
		Map rootMap = parser.parseJson(data);
		ArrayList mailElements = (ArrayList) rootMap.get("root");
		// {"id":59,"name":"Imperial Slaves","category_id":10,"average_price":16058,"category":{"id":10,"name":"Slavery"}},
		//				TreeMap<String, String> tmp = new TreeMap<String, String>();
		//				tmp.put("id", mItem.get("id").toString());
		//				tmp.put("name", mItem.get("name").toString());
		//				tmp.put("categoryId", mItem.get("category_id").toString());
		//				tmp.put("averagePrice", mItem.get("average_price").toString());
		//
		//				commodity.put(new Integer(mItem.get("id").toString()), tmp);
		for(Object item : mailElements) {
			if(item instanceof HashMap) {
				HashMap mItem = (HashMap) item;

				//				TreeMap<String, String> tmp = new TreeMap<String, String>();
				XmlNode comm = new XmlNode("commodity").addAttribute("id", mItem.get("id").toString());
				comm.addChildNode(new XmlNode("name").setTextValue(mItem.get("name").toString()));
				Integer avPrice = new Integer(0);
				try {
					avPrice = new Integer(mItem.get("average_price").toString()).intValue();
				} catch(Exception e) {}
				XmlNode price = new XmlNode("averagePrice").setTextValue(avPrice.toString());
				comm.addChildNode(price);
				XmlNode category = new XmlNode("category").addAttribute("id", mItem.get("category_id").toString());
				comm.addChildNode(category);
				toReturn.addChildNode(comm);
			}
		}

		return toReturn;
	}


	public XmlNode parseSystemFile(JSONParser parser, String data) {
		XmlNode toReturn = new XmlNode("systems");
		/* ,{
	 *    "id":2,"name":"1 Geminorum","x":19.78125,"y":3.5625,"z":-153.8125,"faction":"1 Geminorum Liberals","population":141727,
	 *    "government":"Democracy","allegiance":"Federation","state":"None","security":"Medium","primary_economy":"Terraforming",
	 *    "needs_permit":0,"updated_at":1430931879
	 *  }
	 * /
		Map rootMap = new HashMap();
		try {
			rootMap = parser.parseJson(data);
		} catch(JSONParsingException e) {
			System.out.println(" --------- ");
			System.out.println(e.getLocalizedMessage());
			System.out.println(" --------- ");
		}
		ArrayList mailElements = (ArrayList) rootMap.get("root");
		for(Object item : mailElements) {
			if(item instanceof HashMap) {
				HashMap mItem = (HashMap) item;
				XmlNode system = new XmlNode("system");
				system.addChildNode(new XmlNode("id").setTextValue(mItem.get("id").toString()).addAttribute("updated", mItem.get("updated_at").toString()));
				system.addChildNode(new XmlNode("name").setTextValue(mItem.get("name").toString()).addAttribute("needsPermit", new Boolean(mItem.get("needs_permit").toString()).toString()));
				XmlNode coords = new XmlNode("coordinates").addAttribute("x", mItem.get("x").toString()).addAttribute("y", mItem.get("y").toString()).addAttribute("z", mItem.get("z").toString());
				system.addChildNode(coords);
				XmlNode governance = new XmlNode("governance");
				try {
					governance.addChildNode(new XmlNode("faction").setTextValue(mItem.get("faction").toString()));
				} catch(JSONParsingException e) {
					governance.addChildNode(new XmlNode("faction").setTextValue("Unknown - Not Provided"));
				}
				try {
					governance.addChildNode(new XmlNode("government").setTextValue(mItem.get("government").toString()));
				} catch(JSONParsingException e) {
					governance.addChildNode(new XmlNode("government").setTextValue("Unknown - Not Provided"));
				}
				try {
					governance.addChildNode(new XmlNode("allegiance").setTextValue(mItem.get("allegiance").toString()));
				} catch(JSONParsingException e) {
					governance.addChildNode(new XmlNode("allegiance").setTextValue("Unknown - Not Provided"));
				}
				try {
					governance.addChildNode(new XmlNode("state").setTextValue(mItem.get("state").toString()));
				} catch(JSONParsingException e) {
					governance.addChildNode(new XmlNode("state").setTextValue("Unknown - Not Provided"));
				}
				system.addChildNode(governance);

				try {
					system.addChildNode(new XmlNode("security").setTextValue(mItem.get("security").toString()));
				} catch(JSONParsingException e) {
					system.addChildNode(new XmlNode("security").setTextValue("Unknown - Not Provided"));
				}
				try {
					system.addChildNode(new XmlNode("primaryEconomy").setTextValue(mItem.get("primary_economy").toString()));
				} catch(JSONParsingException e) {
					system.addChildNode(new XmlNode("primaryEconomy").setTextValue("Unknown - Not Provided"));
				}
				toReturn.addChildNode(system);
			}
		}

		return toReturn;
	}

	public XmlNode parseStationLite(JSONParser parser, String data) {
		/* ,{
	 * 		"id":1,"name":"Bain Colony","system_id":18370,"max_landing_pad_size":"L","distance_to_star":16253,
	 * 		"faction":"","government":null,"allegiance":null,"state":null,"type":"Unknown Starport","has_blackmarket":0,
	 * 		"has_commodities":1,"has_refuel":null,"has_repair":null,"has_rearm":null,"has_outfitting":null,"has_shipyard":null,
	 * 		"import_commodities":[],"export_commodities":[],"prohibited_commodities":[],"economies":[],
	 * 		"updated_at":1429408824
	 * }
	 * /
		return new XmlNode("Nutting");
	}

	public XmlNode parseStation(JSONParser parser, String data) {
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
	 * /
	return new XmlNode("Nutting");
}
	/**
	 * Fnu
	 */
	public XmlNode getNodeData() {
		return rawNode;
	}
}
