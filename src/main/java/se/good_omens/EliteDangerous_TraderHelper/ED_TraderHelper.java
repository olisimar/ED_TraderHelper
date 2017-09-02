package se.good_omens.EliteDangerous_TraderHelper;

import java.util.HashMap;
import java.util.Map;

import se.good_omens.EliteDangerous_TraderHelper.common.exceptions.FileMissingException;
import se.good_omens.EliteDangerous_TraderHelper.common.utils.FileHandler;
import se.good_omens.EliteDangerous_TraderHelper.common.utils.SystemData;

public class ED_TraderHelper {

	public static void main(String[] args) {
		SystemData sysData = new SystemData();
		System.out.println(sysData.getWorkingDirectory());
		System.out.println(sysData.getScreenWidth());
		System.out.println(sysData.getScreenHeight());
		Map<String,String> properties = getProperties(sysData);
		System.out.println(properties);
		
		saveProperties(properties, sysData);
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
	}
}
