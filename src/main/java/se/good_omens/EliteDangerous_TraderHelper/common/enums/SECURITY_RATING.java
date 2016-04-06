package se.good_omens.EliteDangerous_TraderHelper.common.enums;

public enum SECURITY_RATING {
	NONE
	, LOW
	, MEDIUM
	, HIGH
	, UNKNOWN
	, NULL
	;

	public SECURITY_RATING fromString(String in) {
		for(SECURITY_RATING rating : SECURITY_RATING.values()) {
			if(rating.name().equalsIgnoreCase(in)) {
				return rating;
			}
		}
		return SECURITY_RATING.UNKNOWN;
	}
}
