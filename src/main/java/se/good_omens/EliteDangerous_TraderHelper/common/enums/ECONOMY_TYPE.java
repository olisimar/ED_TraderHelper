package se.good_omens.EliteDangerous_TraderHelper.common.enums;

public enum ECONOMY_TYPE {
	NONE
	, UNKNOWN
	, TOURISM
	, INDUSTRIAL
	, SERVICE
	, COLONY
	, EXTRACTION
	, AGRICULTURE
	, REFINERY
	, HIGH_TECH
	, TERRAFORMING
	, MILITARY
	;

	public static ECONOMY_TYPE fromString(String in) {
		if((in == null) || in.trim().isEmpty()) {
			return ECONOMY_TYPE.NONE;
		}
		for(ECONOMY_TYPE type : ECONOMY_TYPE.values()) {
			if(type.name().equalsIgnoreCase(in.replace(" ", "_"))) {
				return type;
			}
		}
		return ECONOMY_TYPE.UNKNOWN;
	}
}
