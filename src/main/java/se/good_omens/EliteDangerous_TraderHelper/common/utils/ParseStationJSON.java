package se.good_omens.EliteDangerous_TraderHelper.common.utils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.Station;

public class ParseStationJSON {

	private final String orginalData;
	private final TreeMap<Long, Station> stations = new TreeMap<Long, Station>();

	private HashSet<String> station_state = new HashSet<String>();
	private HashSet<String> commodities = new HashSet<String>();
	private HashSet<String> categories = new HashSet<String>();

	public ParseStationJSON(String data) {
		this.orginalData = data;
	}

	public void parseStationJSON() {
		try {
			JSONParser parser = new JSONParser();
			Object data = parser.parse(orginalData);
			if (data.getClass() == JSONObject.class) {

			} else if (data.getClass() == JSONArray.class) {
				Iterator iter = ((JSONArray)data).iterator();
				while(iter.hasNext()) {
					Station current = this.parseSingleStationJSON(iter.next());
					stations.put(current.getId(), current);
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Station parseSingleStationJSON(Object next) {
		return null;
	}
}
