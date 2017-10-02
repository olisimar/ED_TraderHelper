package se.good_omens.EliteDangerous_TraderHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import se.good_omens.EliteDangerous_TraderHelper.GUI.BaseWindowLoader;
import se.good_omens.EliteDangerous_TraderHelper.common.exceptions.FileMissingException;
import se.good_omens.EliteDangerous_TraderHelper.common.utils.FileHandler;
import se.good_omens.EliteDangerous_TraderHelper.common.utils.RuntimeProperties;
import se.good_omens.EliteDangerous_TraderHelper.common.utils.SystemData;

public class ED_TraderHelper {

	public static void main(String[] args) {
		SystemData sysData = new SystemData();
		System.out.println(sysData.getWorkingDirectory());
		System.out.println(sysData.getScreenWidth());
		System.out.println(sysData.getScreenHeight());
		Map<String,String> properties = getProperties(sysData);
		
		System.out.println(" ---");
		for(Entry<String, String> prop : properties.entrySet()) {
			System.out.println(prop.getKey() +":"+ prop.getValue());
		}
		System.out.println(" ---");
		
		try {
			DataStore dataStore = new DataStore();
			dataStore.loadFiles();
			BaseWindowLoader loader = new BaseWindowLoader(dataStore);
			loader.begin();
		}		
		finally {
			saveProperties(properties, sysData);
		}
	}

	private static Map<String, String> getProperties(SystemData sysData) {
		Map<String, String> properties = new HashMap<>();
		try {
			String data = FileHandler.readFile(sysData.getWorkingDirectory(), "tradehelper.ini");
			for(String item : data.split("\\n")) {
				String[] items = item.split(":");
				properties.put(items[0], items[1]);
			}
		} catch (FileMissingException e) {
			System.out.println(e.getMessage());
		}
		
		return properties;
	}

	private static void saveProperties(Map<String, String> properties, SystemData sysData) {
		String data = "";
		String correctPathAndFileName = sysData.getWorkingDirectory() + sysData.getDirectoryDelimiter() + "tradehelper.ini";
		
		for(Entry<String, String> prop : properties.entrySet()) {
			data = data + prop.getKey().trim() +":"+ prop.getValue().trim() + System.getProperty("line.separator");
		}
		
		FileHandler.writeFile(correctPathAndFileName, data);
	}
}
