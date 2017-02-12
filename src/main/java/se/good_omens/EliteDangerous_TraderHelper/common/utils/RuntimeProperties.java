package se.good_omens.EliteDangerous_TraderHelper.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class RuntimeProperties {

	private HashMap<String,String> properties;

	private RuntimeProperties() {
		this.properties = new HashMap<>();
	}

	/*
	 * Deep clone creation, probably not necessary.
	 */
	private RuntimeProperties(Map<String, String> properties) {
		this.properties = new HashMap<>();
		for(Entry<String,String> item : properties.entrySet()) {
			this.properties.put( new String(item.getKey()), new String(item.getValue()) );
		}
	}

	public static RuntimeProperties of(Map<String,String> properties) {
		if((properties != null) && !properties.isEmpty()) {
			return new RuntimeProperties(properties);
		}
		return new RuntimeProperties();
	}

	public void addNewEntry(String key, String value) {
		if(this.properties.containsKey(key)) {
			throw new IllegalArgumentException("Key already present in RuntimeProperties. Key in question: "+ key);
		}
		this.properties.put(key, value);
	}

	public String getEntry(String key) {
		return properties.get(key);
	}

	public List<String> listKeys() {
		return new ArrayList<>(properties.keySet());
	}
}
