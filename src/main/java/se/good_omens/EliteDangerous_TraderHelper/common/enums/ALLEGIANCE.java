package se.good_omens.EliteDangerous_TraderHelper.common.enums;

public enum ALLEGIANCE {
	NONE
	, FEDERATION
	, EMPIRE
	, ALLIANCE
	;


	public ALLEGIANCE fromString(String in) {
		if( (in == null) || in.trim().isEmpty()) {
			return ALLEGIANCE.NONE;
		}
		for(ALLEGIANCE allegiance : ALLEGIANCE.values()) {
			if(allegiance.name().equalsIgnoreCase(in)) {
				return allegiance;
			}
		}
		return ALLIANCE.NONE;
	}
}
