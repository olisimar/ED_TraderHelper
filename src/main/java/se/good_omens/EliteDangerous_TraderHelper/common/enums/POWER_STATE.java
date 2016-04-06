package se.good_omens.EliteDangerous_TraderHelper.common.enums;

public enum POWER_STATE {
	NONE
	, UNKNOWN
	;

	public POWER_STATE fromString(String in) {
		if((in == null) || in.trim().isEmpty()) {
			throw new RuntimeException("Null or Empty String found when trying to detemine POWER_STATE");
		}
		for(POWER_STATE state : POWER_STATE.values()) {
			if(state.name().equalsIgnoreCase(in.trim())) {
				return state;
			}
		}
		return POWER_STATE.UNKNOWN;
	}
}
