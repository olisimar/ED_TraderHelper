package se.good_omens.EliteDangerous_TraderHelper.common.enums;

public enum ECONOMY_TYPE {
	NONE
	, INDUSTRIAL
	, REFINERY
	, MINING
	, UNKNOWN
	;

	public ECONOMY_TYPE fromString(String in) {
		if((in == null) || in.trim().isEmpty()) {
			return ECONOMY_TYPE.UNKNOWN;
		}
		for(ECONOMY_TYPE type : ECONOMY_TYPE.values()) {
			if(type.name().equalsIgnoreCase(in)) {
				return type;
			}
		}
		return ECONOMY_TYPE.UNKNOWN;
	}
}
