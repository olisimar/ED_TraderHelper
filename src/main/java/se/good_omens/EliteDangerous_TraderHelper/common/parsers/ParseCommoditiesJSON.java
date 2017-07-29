package se.good_omens.EliteDangerous_TraderHelper.common.parsers;

import java.util.Iterator;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.BaseCommodity;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.COMMODITY_CATEGORY;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.COMMODITY_DATA;


/**
 * Parser for the commodities JSON.
 * It does not in fact parse it for commodities as enums are used for this purpose. It does however check for new
 * categories as well as commodities to be introduced into it. Will need to be refactored to deal with unknown items
 * if released to other people. 
 * @author TuX
 *
 */
public class ParseCommoditiesJSON {
	private final String orginalData;
	private final TreeMap<Long, BaseCommodity> baseCommidities = new TreeMap<Long, BaseCommodity>();

	private final TreeMap<Long, String> commodities = new TreeMap<Long, String>();
	private final TreeMap<Long, String> catergories = new TreeMap<Long, String>();


	public ParseCommoditiesJSON(String data) {
		this.orginalData = data;
	}

	@SuppressWarnings("unchecked")
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
			//System.out.println(toPrint); // Use when entering new items.
			throw new IllegalStateException("Found new Commodity, add: "+ toPrint);
		}
	}

	public TreeMap<Long, BaseCommodity> getBaseCommidities() {
		return baseCommidities;
	}
}
