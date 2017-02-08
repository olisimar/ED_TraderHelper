package se.good_omens.EliteDangerous_TraderHelper.common.parsers;

import java.util.Date;
import java.util.Iterator;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.Position;
import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.StarSystem;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.ALLEGIANCE;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.ECONOMY_TYPE;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.GOVERMENT_TYPE;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.POWER;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.POWER_STATE;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.SECURITY_RATING;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.SYSTEM_STATE;

public class ParseSystemJSON {

	private final String									originalData;
	private TreeMap<Long, StarSystem>	systems			= new TreeMap<Long, StarSystem>();


	public ParseSystemJSON(String data) {
		this.originalData = data;
	}

	public void parseSystemJSON() {
		try {
			JSONParser parser = new JSONParser();
			parser.parse(originalData);
			Object data = parser.parse(this.originalData);
			if (data.getClass() == JSONObject.class) {

			} else if (data.getClass() == JSONArray.class) {
				Iterator iter = ((JSONArray) data).iterator();
				while (iter.hasNext()) {
					StarSystem current = parseSingleSystem(iter.next());
					if(current != null) {
						systems.put(current.getId(), current);
					}
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private StarSystem parseSingleSystem(Object next) {
		if (next instanceof JSONObject) {
			JSONObject obj = (JSONObject) next;
			if ((obj.get("id") == null) || (obj.get("name") == null)) {
				return null;
			}
			StarSystem current = new StarSystem((Long) obj.get("id"), (String) obj.get("name"));
			Double x = new Double(obj.get("x").toString());
			Double y = new Double(obj.get("y").toString());
			Double z = new Double(obj.get("z").toString());

			current.setPosition(new Position(x, y, z));
			current.setFaction((String) obj.get("faction"));
			current.setPower(POWER.fromString((String)obj.get("power")));
			current.setPowerState(POWER_STATE.fromString((String) obj.get("power_state")));
			current.setAllegiance(ALLEGIANCE.fromString((String) obj.get("allegiance")));
			current.setPrimaryEconomy(ECONOMY_TYPE.fromString((String) obj.get("primary_economy")));
			current.setSecRating(SECURITY_RATING.fromString((String) obj.get("security")));
			current.setGovernment(GOVERMENT_TYPE.fromString((String) obj.get("government")));
			current.setState(SYSTEM_STATE.fromString((String) obj.get("state")));
			if(obj.get("population") != null) {
				current.setPopulation((Long)obj.get("population"));
			}
			current.setUpdatedAt(new Date((Long)obj.get("updated_at")));
			if((obj.get("needs_permit") != null) && ((Long)obj.get("needs_permit") != 0)) {
				current.setNeedsPermit(true);
			}
			current.setSimbadRef((String)obj.get("simbad_ref"));
			return current;
		}
		return null;
	}
}
