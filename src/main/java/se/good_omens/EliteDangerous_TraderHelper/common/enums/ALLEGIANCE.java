package se.good_omens.EliteDangerous_TraderHelper.common.enums;

public enum ALLEGIANCE {
	UNKNOWN
	, INDEPENDENT
	, FEDERATION
	, EMPIRE
	, ALLIANCE
	, NONE
	;


	public static ALLEGIANCE fromString(String in) {
		if( (in == null) || in.trim().isEmpty()) {
			return ALLEGIANCE.UNKNOWN;
		}
		for(ALLEGIANCE allegiance : ALLEGIANCE.values()) {
			if(allegiance.name().equalsIgnoreCase(in)) {
				return allegiance;
			}
		}
		return ALLIANCE.NONE;
	}
}
