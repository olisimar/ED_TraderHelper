package se.good_omens.EliteDangerous_TraderHelper.common.parsers;

import java.util.Map;
import java.util.TreeMap;

import se.good_omens.EliteDangerous_TraderHelper.common.utils.RuntimeProperties;

public class ParseInitialFile {

	private final String originalData;
	private final RuntimeProperties props;
	
	public ParseInitialFile(String originalData) {
		this.originalData = originalData;
		this.props = RuntimeProperties.of(this.parseOriginalDataToMap());
	}
	
	private Map<String, String> parseOriginalDataToMap() {
		TreeMap<String, String> props = new TreeMap<>();
		String[] data = this.originalData.split("\n");
		for(String item : data) {
			item = item.trim();
			if(item.contains("\n") | item.contains("\r")) {
				throw new RuntimeException("Found BAD init file data. Please correct: "+ item);
			}
			if(item != null && !item.isEmpty()) {
				String[] keyValue = item.split(":");
				if(keyValue.length == 2) {
					props.put(keyValue[0].trim(), keyValue[1].trim());
				}
			}
		}
		return props;
	}

	public RuntimeProperties getRuntimeProperties() {
		return this.props;
	}
	
	public String getOriginalData() {
		return this.originalData;
	}
}
