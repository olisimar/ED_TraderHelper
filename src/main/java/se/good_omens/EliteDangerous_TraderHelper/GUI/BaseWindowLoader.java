package se.good_omens.EliteDangerous_TraderHelper.GUI;

import se.good_omens.EliteDangerous_TraderHelper.common.utils.RuntimeProperties;

/**
 * This will work as the central hub of base knowledge for the whole GUI section.
 * @author TuX
 *
 */
public class BaseWindowLoader {

	private final RuntimeProperties properties;

	public BaseWindowLoader(RuntimeProperties properties) {
		this.properties = properties;
	}
	
	public void begin() {
		PersonalInfoFrame frame = new PersonalInfoFrame(properties);
		
		
	}
}
