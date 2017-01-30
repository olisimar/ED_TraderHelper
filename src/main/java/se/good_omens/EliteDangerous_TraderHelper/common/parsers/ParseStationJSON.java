package se.good_omens.EliteDangerous_TraderHelper.common.parsers;

import java.util.Iterator;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.Station;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.ALLEGIANCE;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.ECONOMY_TYPE;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.GOVERMENT_TYPE;

public class ParseStationJSON {

	private final String											orginalData;
	private final TreeMap<Long, Station>			stations			= new TreeMap<Long, Station>();

	public ParseStationJSON(String data) {
		this.orginalData = data;
	}

	public void parseStationJSON() {
		try {
			JSONParser parser = new JSONParser();
			Object data = parser.parse(orginalData);
			if (data.getClass() == JSONObject.class) {

			} else if (data.getClass() == JSONArray.class) {
				Iterator iter = ((JSONArray) data).iterator();
				while (iter.hasNext()) {
					Station current = this.parseSingleStationJSON(iter.next());
					stations.put(current.getId(), current);
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
				if ((data.get("economies") != null) && (data.get("economies") instanceof JSONArray)) {
					JSONArray economies = (JSONArray) data.get("economies");
					Iterator<?> iter = economies.iterator();
					while (iter.hasNext()) {
						current.addEconomy(ECONOMY_TYPE.fromString(iter.next().toString()));
					}
				}

				if (data.get("distance_to_star") != null) { // might be null
					current.setDistanceToStar(new Long(data.get("distance_to_star").toString()).longValue());
				}

				return current;
			}
		}
		throw new IllegalArgumentException("Not an expected JSONObject, got a: " + next.getClass().getSimpleName());
	}

	public TreeMap<Long, Station> getStations() {
		return this.stations;
	}
}
