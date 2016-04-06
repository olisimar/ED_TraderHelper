package se.good_omens.EliteDangerous_TraderHelper.common.utils;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.Station;

public class ParseStationJSON {

	private final String orginalData;
	private final ArrayList<Station> stations = new ArrayList<Station>();

	public ParseStationJSON(String json) {
		this.orginalData = json;
		try {
			JSONParser parser = new JSONParser();
			Object data = parser.parse(orginalData);
			if (data.getClass() == JSONObject.class) {

			} else if (data.getClass() == JSONArray.class) {
				Iterator iter = ((JSONArray)data).iterator();
				while(iter.hasNext()) {

				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
