package se.good_omens.EliteDangerous_TraderHelper.common.parsers;

import java.util.Iterator;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.ShipModule;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.MODULE_PLACEMENT_SLOT;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.SHIP_TYPE;

public class ParseModules {

	private final String orginalData;
	private TreeMap<Integer, ShipModule> shipModules = new TreeMap<>();

	public ParseModules(String originalData) {
		this.orginalData = originalData;
	}
	
	@SuppressWarnings("unchecked")
	public void parseModules() {
		try {
			JSONParser parser = new JSONParser();
			Object data = parser.parse(orginalData);
			if (data.getClass() == JSONObject.class) {

			} else if (data.getClass() == JSONArray.class) {
				Iterator<JSONObject> iter = ((JSONArray)data).iterator();
				while(iter.hasNext()) {
					this.parseSingleModuleJSON(iter.next());
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}


	private void parseSingleModuleJSON(JSONObject next) {
		ShipModule shipModule = new ShipModule(Integer.parseInt(next.get("id").toString()));
		if(next.get("ship") != null && !next.get("ship").toString().isEmpty()) {
			shipModule.setShipType(SHIP_TYPE.fromString(next.get("ship").toString()));
		}
		
		if(next.get("rating") != null) {
			shipModule.setRating(next.get("rating").toString());
		}
		if(next.get("class") != null) {
			shipModule.setModClass(Integer.parseInt(next.get("class").toString()));
		}
		if(next.get("price") != null) {
			shipModule.setPrice(Integer.parseInt(next.get("price").toString()));
		}

		JSONObject groupData = (JSONObject) next.get("group");
		shipModule.setName(groupData.get("name").toString());
		shipModule.setSlotType(MODULE_PLACEMENT_SLOT.fromString(groupData.get("category").toString()));
		
		shipModules.put(shipModule.getId(), shipModule);
	}

	public  TreeMap<Integer, ShipModule> getShipModules() {
		return this.shipModules;
	}
}
