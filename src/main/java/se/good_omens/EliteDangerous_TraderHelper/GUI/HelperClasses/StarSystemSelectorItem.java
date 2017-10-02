package se.good_omens.EliteDangerous_TraderHelper.GUI.HelperClasses;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.StarSystem;

public class StarSystemSelectorItem {

	private final StarSystem system;
	
	public static ArrayList<StarSystemSelectorItem> of(TreeMap<Long, StarSystem> systems) {
		ArrayList<StarSystemSelectorItem> toReturn = new ArrayList<>();
		for(Entry<Long, StarSystem> item : systems.entrySet()) {
			toReturn.add(new StarSystemSelectorItem(item.getValue()));
		}
		return toReturn;
	}
	
	public StarSystemSelectorItem(StarSystem item) {
		this.system = item;
	}
	
	public String toString() {
		return this.system.getName();
	}
	
	public long getId() {
		return system.getId();
	}
	
	public String getName() {
		return this.system.getName();
	}
	
	public StarSystem getSystem() {
		return this.system;
	}
}
