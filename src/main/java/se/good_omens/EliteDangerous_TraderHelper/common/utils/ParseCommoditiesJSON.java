package se.good_omens.EliteDangerous_TraderHelper.common.utils;

import java.util.Iterator;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.BaseCommodity;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.COMMODITY_CATEGORY;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.COMMODITY_DATA;

public class ParseCommoditiesJSON {
	private final String orginalData;
	private final TreeMap<Long, BaseCommodity> baseCommidities = new TreeMap<Long, BaseCommodity>();

	private final TreeMap<Long, String> commodities = new TreeMap<Long, String>();
	private final TreeMap<Long, String> catergories = new TreeMap<Long, String>();


	public ParseCommoditiesJSON(String data) {
		this.orginalData = data;
	}

	public void parseCommoditiesJSON() {
		try {
			JSONParser parser = new JSONParser();
			Object data = parser.parse(orginalData);
			if (data.getClass() == JSONObject.class) {

			} else if (data.getClass() == JSONArray.class) {
				Iterator<JSONObject> iter = ((JSONArray)data).iterator();
				while(iter.hasNext()) {
					this.parseSingleCommodityJSON(iter.next());
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void parseSingleCommodityJSON(JSONObject next) {
		// 	, ANTIQUITIES(96, "Antiquities", 120365, COMMODITY_CATEGORY.SALVAGE)
		String toPrint = ", "+ next.get("name").toString().replaceAll(" ", "_").replaceAll("-", "_").replaceAll("\\.", "").toUpperCase() +"(";
		toPrint = toPrint + next.get("id") +", \"";
		toPrint = toPrint + next.get("name") + "\", ";
		toPrint = toPrint + next.get("average_price") + " ,";
		if(new Long(next.get("is_rare").toString()) == 1) {
			toPrint = toPrint + "true, ";
		} else {
			toPrint = toPrint + "false, ";
		}
		COMMODITY_CATEGORY cat = COMMODITY_CATEGORY.fromInt(new Integer(next.get("category_id").toString()));
		if(cat == COMMODITY_CATEGORY.NOT_DEFINED) {
			throw new IllegalStateException("COMMODITY_CATEGORY was NOT_DEFINED. Add: "+ next.toString());
		}
		toPrint = toPrint + "COMMODITY_CATEGORY." + cat.name() + ")";
		COMMODITY_DATA data = COMMODITY_DATA.fromInt(new Integer(next.get("id").toString()).intValue());
		if(data == COMMODITY_DATA.NOT_DEFINED) {
			throw new IllegalStateException("Found new Commodity, add: "+ toPrint);
		}
	}

	public TreeMap<Long, BaseCommodity> getBaseCommidities() {
		return baseCommidities;
	}
}
