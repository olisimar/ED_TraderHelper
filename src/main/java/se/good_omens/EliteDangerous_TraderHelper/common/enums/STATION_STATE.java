package se.good_omens.EliteDangerous_TraderHelper.common.enums;

public enum STATION_STATE {
	NONE
	,UNKNOWN
	,LOCKDOWN
	,OUTBREAK
	,BOOM
	,CIVIL_UNREST
	,CIVIL_WAR
	,WAR
	,BUST
	,EXPANSION
	,ELECTION
	;

	public static STATION_STATE fromString(String in) {
		if((in != null) || !in.trim().isEmpty()) {
			for(STATION_STATE state : STATION_STATE.values()) {
				if(state.name().equalsIgnoreCase(in.trim().replaceAll(" ", "_"))) {
					return state;
				}
			}
		}
		return STATION_STATE.UNKNOWN;
	}
}
