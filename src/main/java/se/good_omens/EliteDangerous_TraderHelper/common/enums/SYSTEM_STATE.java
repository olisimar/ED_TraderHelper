package se.good_omens.EliteDangerous_TraderHelper.common.enums;

public enum SYSTEM_STATE {

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

	public static SYSTEM_STATE fromString(String in) {
		if((in == null) || in.trim().isEmpty()) {
			return SYSTEM_STATE.NONE;
		}
		for(SYSTEM_STATE item : SYSTEM_STATE.values()) {
			if(item.name().equalsIgnoreCase(in.trim().replaceAll(" ", "_"))) {
				return item;
			}
		}
		return SYSTEM_STATE.UNKNOWN;
	}
}
