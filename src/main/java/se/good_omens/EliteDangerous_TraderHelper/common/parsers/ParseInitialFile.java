package se.good_omens.EliteDangerous_TraderHelper.common.parsers;

import java.util.Map;
import java.util.TreeMap;

import se.good_omens.EliteDangerous_TraderHelper.common.utils.RuntimeProperties;

public class ParseInitialFile {

	private final String originalData;
	private final RuntimeProperties props;
	
	public ParseInitialFile(String originalData) {
		this.originalData = originalData;
		this.props = RuntimeProperties.of(this.getOriginalDataMap());
	}
	
	private Map<String, String> getOriginalDataMap() {
		TreeMap<String, String> props = new TreeMap<>();
		String[] data = this.originalData.split("\n");
		for(String item : data) {
			if(item != null && !item.isEmpty()) {
				String[] keyValue = item.split(":");
				if(keyValue.length == 2) {
					props.put(keyValue[0], keyValue[1]);
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
