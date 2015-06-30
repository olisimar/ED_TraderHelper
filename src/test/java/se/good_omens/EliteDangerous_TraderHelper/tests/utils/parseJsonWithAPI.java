package se.good_omens.EliteDangerous_TraderHelper.tests.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Iterator;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.Test;

public class parseJsonWithAPI {
	public static String	protocol	= "file:///";
	public static String	filePath	= "C:/OWN/LunaWorkspace/ED_TraderHelper/rawData/";

	@Test(enabled = false)
	public void test_useAPI() {
		try {
			// String data = FileReader.readFile(filePath + "commodities.json");
			File file = new File(filePath + "commodities.json");
			InputStream is = new FileInputStream(file);
			JsonParser parser = Json.createParser(is);
			while (parser.hasNext()) {
				Event next = parser.next();
				System.out.println(next);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Test(enabled = false)
	public void testCrap() {
		try {
			FileReader reader = new FileReader(filePath + "test.json");
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

			// get a String from the JSON object

			String firstName = (String) jsonObject.get("firstname");
			System.out.println("The first name is: " + firstName);

			// get a number from the JSON object
			long id = (Long) jsonObject.get("id");
			System.out.println("The id is: " + id);

			// get an array from the JSON object
			JSONArray lang = (JSONArray) jsonObject.get("languages");

			// take the elements of the json array
			for (int i = 0; i < lang.size(); i++) {
				System.out
				.println("The " + i + " element of the array: " + lang.get(i));
			}
			Iterator i = lang.iterator();

			// take each value from the json array separately
			while (i.hasNext()) {
				JSONObject innerObj = (JSONObject) i.next();
				System.out.println("language " + innerObj.get("lang") + " with level "
						+ innerObj.get("knowledge"));
			}
			// handle a structure into the json object
			JSONObject structure = (JSONObject) jsonObject.get("job");
			System.out.println("Into job structure, name: " + structure.get("name"));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test(enabled = true)
	public void parseCommoditiesJSON() {
		try {
			FileReader reader = new FileReader(filePath + "systems.json");
			JSONParser jsonParser = new JSONParser();
			Object commodities = jsonParser.parse(reader);
			if(commodities instanceof JSONArray) {
				JSONArray jCommodities = (JSONArray) commodities;
				System.out.println("has elements: "+ jCommodities.size());
				Iterator iter = jCommodities.iterator();
				while(iter.hasNext()) {
					Object item = iter.next();
					if(item instanceof JSONObject) {
						JSONObject jItem = (JSONObject) item;
						//						System.out.println(jItem.toJSONString());
						Long tmp = (Long) jItem.getOrDefault("needs_permit", 0);
						Boolean permit = false;
						if((tmp == null) || (tmp == 0)) {
							permit = false;
						} else {
							permit = true;
						}
						String name = jItem.getOrDefault("name", "Nameless system").toString();
						if(permit == null) {
							System.out.println("Permit logic gives: "+ permit +" for system '"+ name +"'.");
						} else if (permit != false) {
							System.out.println("Permit logic gives: "+ permit +" for system '"+ name +"'.");
						}
					}
				}
				System.out.println("Size: "+ jCommodities.size());
			} else if(commodities instanceof JSONObject) {

			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
