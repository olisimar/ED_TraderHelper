package se.good_omens.EliteDangerous_TraderHelper.common.enums;

public enum SECURITY_RATING {
	NONE
	, UNKNOWN
	, LOW
	, MEDIUM
	, HIGH
	;

	public static SECURITY_RATING fromString(String in) {
		if((in == null) || in.trim().isEmpty()) {
			return SECURITY_RATING.NONE;
		}
		for(SECURITY_RATING rating : SECURITY_RATING.values()) {
			if(rating.name().equalsIgnoreCase(in)) {
				return rating;
			}
		}
		return SECURITY_RATING.UNKNOWN;
	}
}
