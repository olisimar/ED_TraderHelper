package se.good_omens.EliteDangerous_TraderHelper.common.enums;

public enum POWER_STATE {
	NONE
	, UNKNOWN
	;

	public POWER_STATE fromString(String in) {
		if((in == null) || in.trim().isEmpty()) {
			return POWER_STATE.NONE;
		}
		for(POWER_STATE state : POWER_STATE.values()) {
			if(state.name().equalsIgnoreCase(in.trim())) {
				return state;
			}
		}
		return POWER_STATE.UNKNOWN;
	}
}
