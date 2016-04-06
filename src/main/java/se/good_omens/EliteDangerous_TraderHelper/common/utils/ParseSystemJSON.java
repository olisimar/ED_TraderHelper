package se.good_omens.EliteDangerous_TraderHelper.common.utils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.Position;
import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.StarSystem;

public class ParseSystemJSON {

	private final String									originalData;
	private TreeMap<Integer, StarSystem>	systems			= new TreeMap<Integer, StarSystem>();

	private static HashSet<String>				power				= new HashSet<String>();
	private static HashSet<String>				powerState	= new HashSet<String>();
	private static HashSet<String>				allegiance	= new HashSet<String>();
	private static HashSet<String>				economyType	= new HashSet<String>();
	private static HashSet<String>				secRating		= new HashSet<String>();
	private static HashSet<String>				government	= new HashSet<String>();

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
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("POWERS:");
		for (String item : power) {
			System.out.println(", " + item.toUpperCase().replaceAll(" ", "_") + "(\"" + item + ", ALLEGIANCE.");
		}

		System.out.println("\nPOWER_STATE:");
		for (String item : powerState) {
			if (item == null) {
				System.out.println("NULL");
			} else {
				System.out.println(", " + item.toUpperCase());
			}
		}
		System.out.println("\nALLEGIANCE:");
		for (String item : allegiance) {
			if (item == null) {
				System.out.println("NULL");
			} else {
				System.out.println(", " + item.toUpperCase());
			}
		}
		System.out.println("\nECONOMY_TYPE:");
		for (String item : economyType) {
			if (item == null) {
				System.out.println("NULL");
			} else {
				System.out.println(", " + item.toUpperCase());
			}
		}
		System.out.println("\nGOVERNMENT:");
		for (String item : government) {
			if (item == null) {
				System.out.println("NULL");
			} else {
				System.out.println(", " + item.toUpperCase());
			}
		}
		System.out.println("\nSECURITY:");
		for (String item : powerState) {
			if (item == null) {
				System.out.println("NULL");
			} else {
				System.out.println(", " + item.toUpperCase());
			}
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
			power.add((String) obj.get("power"));
			powerState.add((String) obj.get("power_state"));
			allegiance.add((String) obj.get("allegiance"));
			economyType.add((String) obj.get("primary_economy"));
			secRating.add((String) obj.get("security"));
			government.add((String) obj.get("government"));
			return current;
		}
		return null;
	}
}
