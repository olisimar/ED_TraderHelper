package se.good_omens.EliteDangerous_TraderHelper.GUI;

import java.util.TreeMap;

import se.good_omens.EliteDangerous_TraderHelper.DataStore;
import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.StarSystem;
import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.UserData;
import se.good_omens.EliteDangerous_TraderHelper.common.utils.RuntimeProperties;

/**
 * This will work as the central hub of base knowledge for the whole GUI section.
 * @author TuX
 *
 */
public class BaseWindowLoader {

	private final DataStore dataStore;

	public BaseWindowLoader(DataStore dataStore) {
		this.dataStore = dataStore;
	}
	
	public void begin() {
		PersonalInfoFrame frame = new PersonalInfoFrame(dataStore);
		
		
	}
}
