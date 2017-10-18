package se.good_omens.EliteDangerous_TraderHelper.common.enums;

public enum ALLEGIANCE {
	UNKNOWN
	, INDEPENDENT
	, FEDERATION
	, EMPIRE
	, ALLIANCE
	, PILOTS_FEDERATION
	, NONE
	;


	public static ALLEGIANCE fromString(String in) {
		if( (in == null) || in.trim().isEmpty()) {
			return ALLEGIANCE.UNKNOWN;
		}
		for(ALLEGIANCE allegiance : ALLEGIANCE.values()) {
			if(allegiance.name().replaceAll("_", " ").equalsIgnoreCase(in)) {
				return allegiance;
			}
		}
		System.out.println(in);
		return ALLIANCE.NONE;
	}
}
