package se.good_omens.EliteDangerous_TraderHelper.common.enums;

public enum GOVERMENT_TYPE {
	UNKNOWN
	, NONE
	, FEUDAL
	, ANARCHY
	, COOPERATIVE
	, CORPORATE
	, COMMUNISM
	, PATRONAGE
	, CONFEDERACY
	, DEMOCRACY
	, DICTATORSHIP
	, THEOCRACY
	, PRISON_COLONY
	;

	public static GOVERMENT_TYPE fromString(String in) {
		if((in == null) || in.trim().isEmpty()) {
			return GOVERMENT_TYPE.NONE;
		}
		for(GOVERMENT_TYPE item : GOVERMENT_TYPE.values()) {
			if(item.name().equalsIgnoreCase(in.trim().replaceAll(" ", "_"))) {
				return item;
			}
		}
		return GOVERMENT_TYPE.UNKNOWN;
	}
}
