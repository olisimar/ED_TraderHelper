package se.good_omens.EliteDangerous_TraderHelper.common.parsers;

import java.util.Iterator;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.ShipModule;
import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.Station;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.ALLEGIANCE;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.COMMODITY_DATA;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.ECONOMY_TYPE;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.GOVERMENT_TYPE;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.SHIP_SIZE;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.SHIP_TYPE;

public class ParseStationJSON implements Runnable {

/*
{"id":5,"name":"Reilly Hub","system_id":396,"updated_at":1501117497,"max_landing_pad_size":"L","distance_to_star":171,"government_id":64,"government":"Corporate"
,"allegiance_id":3,"allegiance":"Federation","state_id":16,"state":"Boom","type_id":8,"type":"Orbis Starport","has_blackmarket":false,"has_market":true,"has_refuel":true
,"has_repair":true,"has_rearm":true,"has_outfitting":true,"has_shipyard":true,"has_docking":true,"has_commodities":true
,"import_commodities":["Pesticides","Aquaponic Systems","Biowaste"]
,"export_commodities":["Mineral Oil","Fruit and Vegetables","Grain"]
,"prohibited_commodities":["Narcotics","Tobacco","Combat Stabilisers","Imperial Slaves","Slaves","Personal Weapons","Battle Weapons","Toxic Waste","Wuthielo Ku Froth","Bootleg Liquor","Landmines"]
,"economies":["Agriculture"],"shipyard_updated_at":1501109193,"outfitting_updated_at":1501109188,"market_updated_at":1501109188,"is_planetary":false
,"selling_ships":["Adder","Eagle Mk. II","Hauler","Sidewinder Mk. I","Viper Mk III"]
,"selling_modules":[738,739,740,743,744,745,748,749,750,753,754,755,756,757,758,759,760,761,762,824,826,830,831,835,837,843,850,851,876,877,878,879,880,882,883,885,886,891,892,893,894,895,896,897,898,899,900,928,933,934,936,937,938,941,942,943,946,948,961,962,963,965,966,967,968,969,970,1000,1004,1005,1010,1011,1012,1013,1016,1017,1018,1021,1022,1023,1032,1036,1037,1038,1041,1042,1043,1046,1047,1048,1066,1071,1072,1116,1119,1120,1123,1124,1125,1128,1133,1137,1138,1182,1187,1191,1192,1193,1194,1195,1196,1199,1200,1201,1202,1203,1204,1207,1208,1209,1212,1213,1214,1242,1243,1245,1246,1286,1306,1307,1310,1311,1316,1317,1320,1321,1324,1326,1373,1375,1377,1379,1381,1421,1425,1429,1523,1524,1525,1526,1527,1528,1529,1530,1531,1532,1533,1534,1535,1540,1544,1549,1577,1579,1581,1583,1585,1587]
,"settlement_size_id":null,"settlement_size":null,"settlement_security_id":null,"settlement_security":null,"body_id":7086578,"controlling_minor_faction_id":13925}
	 
{"id":173,"name":"Lichtenberg Port","system_id":2328,"updated_at":1500584678,"max_landing_pad_size":"L","distance_to_star":371,"government_id":64,"government":"Corporate"
,"allegiance_id":4,"allegiance":"Independent","state_id":80,"state":"None","type_id":3,"type":"Coriolis Starport","has_blackmarket":false,"has_market":true,"has_refuel":true
,"has_repair":true,"has_rearm":true,"has_outfitting":true,"has_shipyard":true,"has_docking":true,"has_commodities":true
,"import_commodities":[]
,"export_commodities":[]
,"prohibited_commodities":[]
,"economies":["Terraforming"],"shipyard_updated_at":1489154447,"outfitting_updated_at":1489154442,"market_updated_at":1489154441,"is_planetary":false
,"selling_ships":["Adder","Eagle Mk. II","Hauler","Sidewinder Mk. I","Type-7 Transporter"]
,"selling_modules":[738,739,740,742,743,744,745,746,747,748,749,750,751,752,753,754,755,773,774,775,776,777,852,854,862,868,870,873,876,877,878,879,880,881,882,883,884,886,888,890,972,973,976,977,978,981,982,983,997,998,1003,1007,1008,1037,1038,1039,1042,1043,1044,1047,1048,1049,1052,1053,1056,1057,1061,1062,1077,1078,1082,1083,1084,1086,1087,1088,1089,1091,1092,1093,1096,1097,1101,1102,1103,1106,1111,1112,1116,1117,1118,1121,1122,1123,1141,1142,1146,1147,1168,1172,1177,1178,1191,1192,1193,1194,1195,1196,1197,1207,1232,1233,1234,1242,1246,1266,1267,1273,1274,1281,1282,1286,1306,1326,1327,1335,1341,1380,1381,1382,1416,1421,1425,1435,1436,1439,1440,1441,1444,1445,1523,1524,1525,1528,1529,1530,1531,1532,1541,1582,1583,1584,1585,1586]
,"settlement_size_id":null,"settlement_size":null,"settlement_security_id":null,"settlement_security":null,"body_id":null,"controlling_minor_faction_id":516}

{"id":2477,"name":"Kraepelin Orbital","system_id":3008,"updated_at":1501111377,"max_landing_pad_size":"L","distance_to_star":227,"government_id":64,"government":"Corporate"
,"allegiance_id":3,"allegiance":"Federation","state_id":65,"state":"Election","type_id":8,"type":"Orbis Starport","has_blackmarket":false,"has_market":true,"has_refuel":true
,"has_repair":true,"has_rearm":true,"has_outfitting":true,"has_shipyard":true,"has_docking":true,"has_commodities":true
,"import_commodities":[]
,"export_commodities":[]
,"prohibited_commodities":["Narcotics","Tobacco","Combat Stabilisers","Imperial Slaves","Slaves","Personal Weapons","Battle Weapons","Toxic Waste","Wuthielo Ku Froth","Bootleg Liquor","Landmines"]
,"economies":["Industrial"],"shipyard_updated_at":1500260889,"outfitting_updated_at":1500260883,"market_updated_at":1500260882,"is_planetary":false
,"selling_ships":["Diamondback Explorer","Diamondback Scout","Eagle Mk. II","Federal Assault Ship","Federal Dropship","Hauler","Sidewinder Mk. I","Type-7 Transporter","Type-9 Heavy"]
,"selling_modules":[738,739,740,741,742,743,744,745,748,749,750,773,774,775,776,777,793,794,795,796,797,803,804,805,806,807,826,827,828,830,831,834,835,839,840,843,845,846,850,851,852,854,855,857,858,860,861,862,863,864,865,867,868,869,870,871,873,874,876,878,879,880,882,883,884,885,888,889,890,891,892,893,895,896,897,898,899,900,904,909,913,914,916,917,918,921,922,923,926,927,928,930,931,932,933,934,935,938,939,943,947,948,949,951,952,956,957,961,962,963,964,966,967,968,969,973,974,977,978,979,981,982,984,986,987,991,992,993,996,997,998,1000,1001,1002,1003,1006,1007,1008,1009,1010,1019,1024,1026,1027,1028,1031,1032,1036,1037,1038,1039,1041,1042,1043,1044,1046,1047,1048,1049,1066,1067,1068,1071,1073,1076,1077,1078,1079,1081,1082,1083,1084,1086,1087,1088,1089,1106,1107,1108,1111,1113,1116,1117,1118,1120,1121,1122,1123,1124,1128,1129,1132,1137,1138,1141,1146,1147,1148,1151,1152,1153,1154,1156,1157,1158,1159,1161,1162,1163,1164,1181,1182,1183,1186,1187,1188,1191,1192,1193,1194,1195,1196,1197,1198,1199,1200,1201,1202,1203,1204,1205,1206,1207,1208,1209,1210,1212,1213,1214,1215,1217,1218,1219,1220,1222,1223,1224,1227,1229,1230,1232,1233,1234,1235,1236,1238,1240,1242,1243,1244,1245,1246,1248,1252,1253,1254,1255,1256,1260,1261,1262,1263,1264,1268,1269,1270,1271,1272,1276,1277,1278,1279,1286,1287,1288,1289,1290,1291,1292,1293,1294,1295,1296,1297,1298,1300,1303,1306,1307,1308,1309,1310,1311,1313,1314,1316,1317,1321,1325,1326,1327,1330,1331,1332,1333,1334,1335,1337,1338,1339,1340,1341,1342,1343,1344,1345,1346,1349,1350,1351,1352,1353,1357,1358,1359,1365,1366,1368,1369,1370,1371,1372,1375,1377,1379,1381,1382,1384,1385,1386,1387,1388,1394,1395,1396,1397,1398,1399,1400,1401,1402,1403,1404,1405,1406,1407,1408,1409,1410,1411,1412,1417,1418,1421,1423,1425,1426,1429,1430,1431,1432,1433,1434,1435,1436,1437,1438,1439,1440,1441,1442,1443,1444,1445,1446,1447,1449,1450,1451,1452,1456,1457,1458,1459,1460,1466,1467,1468,1469,1470,1523,1524,1525,1527,1528,1529,1530,1531,1532,1533,1534,1535,1536,1537,1540,1541,1544,1545,1561,1562,1567,1571,1574,1581,1585,1586,1587]
,"settlement_size_id":null,"settlement_size":null,"settlement_security_id":null,"settlement_security":null,"body_id":null,"controlling_minor_faction_id":20750}

*/
	
	private final String											orginalData;
	private final TreeMap<Long, Station>			stations			= new TreeMap<Long, Station>();
	private final ParseModules 								parserModules;
	private volatile int parsedStations = 0;
	private volatile int totalStations = 0;

	public ParseStationJSON(String data, ParseModules parserModules) {
		this.orginalData = data.trim();
		this.parserModules = parserModules;
		parserModules.parseModules();
	}
	
	@Override
	public void run() {
		this.parseStationJSON();
	}

	@SuppressWarnings("rawtypes")
	public void parseStationJSON() {
		try {
			JSONParser parser = new JSONParser();
			Object data = parser.parse(orginalData);
			if (data.getClass() == JSONObject.class) {

			} else if (data.getClass() == JSONArray.class) {
				Iterator iter = ((JSONArray) data).iterator();
				this.totalStations = ((JSONArray) data).size();
				while (iter.hasNext()) {
					Station current = this.parseSingleStationJSON(iter.next());
					stations.put(current.getId(), current);
					this.parsedStations++;					
				}
			}
		} catch (ParseException e) {
			System.out.println("Parsed so far: " + stations.size());
			e.printStackTrace();
		}
	}

	private Station parseSingleStationJSON(Object next) {
		if (next instanceof JSONObject) {
			JSONObject data = (JSONObject) next;
			Station current = new Station();
			if (next instanceof JSONObject) {
				current.setId(new Long(data.get("id").toString()).longValue());
				current.setBaseName(data.get("name").toString());
				current.setSystemId(new Long(data.get("system_id").toString()).longValue());
				if (data.get("allegiance") != null) {
					current.setAllegiance(ALLEGIANCE.fromString(data.get("allegiance").toString()));
				} else {
					current.setAllegiance(ALLEGIANCE.NONE);
				}
				if (data.get("government") != null) {
					current.setGoverment(GOVERMENT_TYPE.fromString(data.get("government").toString()));
				} else {
					current.setGoverment(GOVERMENT_TYPE.UNKNOWN);
				}
				if (data.get("faction") != null) {
					current.setLocalFaction(data.get("faction").toString());
				}
				if(data.get("max_landing_pad_size") != null) {
					current.setMaxLandingPadSize(SHIP_SIZE.fromString((String) data.get("max_landing_pad_size")));
				}				
				if (data.get("type_id") != null) {
					current.setTypeId(new Long(data.get("type_id").toString()));
				}
				if (data.get("has_blackmarket") != null) {
					current.setHasBlackMarket(new Boolean(data.get("has_blackmarket").toString()));
				}
				if (data.get("has_commodities") != null) {
					current.setHasCommodities(new Boolean(data.get("has_commodities").toString()));
				}
				if (data.get("has_outfitting") != null) {
					current.setHasOutfitting(new Boolean(data.get("has_outfitting").toString()));
				}
				if (data.get("has_shipyard") != null) {
					current.setHasShipyard(new Boolean(data.get("has_shipyard").toString()));
				}
				if (data.get("has_repair") != null) {
					current.setHasRepair(new Boolean(data.get("has_repair").toString()));
				}
				if (data.get("has_refuel") != null) {
					current.setHasRefuel(new Boolean(data.get("has_refuel").toString()));
				}
				if (data.get("has_rearm") != null) {
					current.setHasReArm(new Boolean(data.get("has_rearm").toString()));
				}
				if(data.get("is_planetary") != null) {
					current.setPlanetary(Boolean.parseBoolean(data.get("is_planetary").toString()));
				}
				if ((data.get("economies") != null) && (data.get("economies") instanceof JSONArray)) {
					JSONArray economies = (JSONArray) data.get("economies");
					Iterator<?> iter = economies.iterator();
					while (iter.hasNext()) {
						current.addEconomy(ECONOMY_TYPE.fromString(iter.next().toString()));
					}
				}
				if ((data.get("prohibited_commodities") != null && data.get("prohibited_commodities") instanceof JSONArray )) {
					JSONArray prohibited = (JSONArray) data.get("prohibited_commodities");
					Iterator<?> iter = prohibited.iterator();
					while (iter.hasNext()) {
						current.addProhibitedCommodities(COMMODITY_DATA.fromString(iter.next().toString()));
					}
				}

				if (data.get("distance_to_star") != null) { // might be null
					current.setDistanceToStar(new Long(data.get("distance_to_star").toString()).longValue());
				}
				
				// Parsing ships into the stations data
				if(data.get("selling_ships") != null && data.get("selling_ships") instanceof JSONArray) {
					JSONArray soldShips = (JSONArray) data.get("selling_ships");
					Iterator<?> iter = soldShips.iterator();
					while(iter.hasNext()) {
						SHIP_TYPE type = SHIP_TYPE.fromString((String) iter.next());
						if(type != SHIP_TYPE.UNKNOWN || type != SHIP_TYPE.NONE) {
							current.addSoldShip(type);
						}
					}
				}

				// Parsing ship modules into the stations data
				if(data.get("selling_modules") != null && data.get("selling_modules") instanceof JSONArray) {
					JSONArray soldModules = (JSONArray) data.get("selling_modules");
					Iterator<?> iter = soldModules.iterator();
					while(iter.hasNext()) {
						int id = new Long((Long) iter.next()).intValue();
						ShipModule module = this.parserModules.getShipModules().get( id );
						if(module != null) {
							current.addSoldModules(module);
						} else {
							System.out.println("Failed to find "+ id);
						}
					}
				}

				return current;
			}
		}
		throw new IllegalArgumentException("Not an expected JSONObject, got a: " + next.getClass().getSimpleName());
	}

	public TreeMap<Long, Station> getStations() {
		return this.stations;
	}

	public int getTotalStations() {
		return totalStations;
	}

	public int getParsedStations() {
		return parsedStations;
	}

}
