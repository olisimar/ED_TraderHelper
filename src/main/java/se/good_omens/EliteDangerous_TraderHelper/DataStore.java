package se.good_omens.EliteDangerous_TraderHelper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observer;
import java.util.TreeMap;

import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.StarSystem;
import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.Station;
import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.UserData;
import se.good_omens.EliteDangerous_TraderHelper.common.exceptions.FileMissingException;
import se.good_omens.EliteDangerous_TraderHelper.common.interfaces.Observable;
import se.good_omens.EliteDangerous_TraderHelper.common.parsers.ParseInitialFile;
import se.good_omens.EliteDangerous_TraderHelper.common.parsers.ParseListings;
import se.good_omens.EliteDangerous_TraderHelper.common.parsers.ParseModules;
import se.good_omens.EliteDangerous_TraderHelper.common.parsers.ParseStationJSON;
import se.good_omens.EliteDangerous_TraderHelper.common.parsers.ParseSystemJSON;
import se.good_omens.EliteDangerous_TraderHelper.common.utils.FileHandler;
import se.good_omens.EliteDangerous_TraderHelper.common.utils.RuntimeProperties;
import se.good_omens.EliteDangerous_TraderHelper.common.utils.SystemData;

/**
 * DataStore of all the parsed files and system data.
 * @author TuX
 */
public class DataStore implements Observable {

	private UserData userData = null;
	private SystemData systemData = null;
	private RuntimeProperties runtimeProperties = null;
	private ParseModules modulesParser = null;
	private ParseStationJSON stationsParser = null;
	private ParseSystemJSON systemsParser = null;

	public DataStore() {
		systemData = new SystemData();
	}
	
	public void loadFiles() {
		try {
			String rawDataPath = systemData.getWorkingDirectory() + systemData.getDirectoryDelimiter() +"rawData"+ systemData.getDirectoryDelimiter();
			this.runtimeProperties = RuntimeProperties.of(this.getProperties(systemData));
			
			modulesParser = new ParseModules(FileHandler.readFile(rawDataPath, "modules.json"));
			stationsParser = new ParseStationJSON(FileHandler.readFile(rawDataPath, "stations.json"), modulesParser);
			systemsParser = new ParseSystemJSON(FileHandler.readFile(rawDataPath, "systems_populated.json"));
			stationsParser.parseStationJSON();
			systemsParser.parseSystemJSON();
			TreeMap<Long, Station> stations = new ParseListings().parseListings(FileHandler.readFile(rawDataPath, "listings.csv"), stationsParser.getStations());
			TreeMap<Long, StarSystem> systems = systemsParser.getSystems();

			for (Entry<Long, Station> item : stations.entrySet()) {
				StarSystem system = systems.get(item.getValue().getSystemId());
				if (system != null) {
					system.addStation(item.getValue());
				}
			}
			
			RuntimeProperties props = new ParseInitialFile(FileHandler.readFile(systemData.getWorkingDirectory() + systemData.getDirectoryDelimiter(), "tradehelper.ini")).getRuntimeProperties();
			userData = new UserData(props, systems);
		} catch (FileMissingException e) {
			e.printStackTrace();
		}
	}
	
	public ParseModules getModuleParser() {
		return this.modulesParser;
	}
	
	public ParseStationJSON getStationsParser() {
		return this.stationsParser;
	}
	
	public ParseSystemJSON getSystemParser() {
		return this.systemsParser;
	}
	
	public UserData getUserData() {
		return this.userData;
	}
	
	public SystemData getSystemData() {
		return this.systemData;
	}
	
	public RuntimeProperties getRuntimeProperties() {
		return this.runtimeProperties;
	}
	
	private Map<String, String> getProperties(SystemData sysData) {
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

	/**
	 * Saves the current set of personal properties.
	 * @param properties
	 */
	public void saveProperties(Map<String, String> properties) {
		String data = "";
		String correctPathAndFileName = systemData.getWorkingDirectory() + systemData.getDirectoryDelimiter() + "tradehelper.ini";
		
		for(Entry<String, String> prop : properties.entrySet()) {
			data = data + prop.getKey().trim() +":"+ prop.getValue().trim() + System.getProperty("line.separator");
		}
		
		FileHandler.writeFile(correctPathAndFileName, data);
	}

	@Override
	public void addObserver(Observer observer) {
	}

	@Override
	public void removeObserver(Observer observer) {
	}

	@Override
	public void notifyObservers() {
	}
}
