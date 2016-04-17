package se.good_omens.EliteDangerous_TraderHelper.common.enums;

public enum STATION_TYPE {
	UNKNOWN(0)
	,CIVILIAN_OUTPOST(1)
	,COMMERCIAL_OUTPOST(2)
	,CORIOLIS_STARPORT(3)
	,INDUSTRIAL_OUTPOST(4)
	,MILITARY_OUTPOST(5)
	,MINING_OUTPOST(6)
	,OCELLUS_STARPORT(7)
	,ORBIS_STARPORT(8)
	,SCIENTIFIC_OUTPOST(9)
	,UNSANCTIONED_OUTPOST(10)
	,UNKNOWN_OUTPOST(11)
	,UNKNOWN_STARPORT(12)
	,PLANETARY_OUTPOST(13)
	,PLANETARY_PORT(14)
	,UNKNOWN_PLANETARY(15)
	;

	private final int id;

	private STATION_TYPE(int id) {
		this.id = id;
	}

	public static STATION_TYPE fromInt(int in) {
		for(STATION_TYPE type : STATION_TYPE.values()) {
			if(type.getId() == in) {
				return type;
			}
		}
		return STATION_TYPE.UNKNOWN;
	}

	public static STATION_TYPE fromString(String in) {
		if((in == null) || !in.trim().isEmpty()) {
			for(STATION_TYPE type : STATION_TYPE.values()) {
				if(type.name().equalsIgnoreCase(in.trim().replaceAll(" ", "_"))) {
					return type;
				}
			}
		}
		return STATION_TYPE.UNKNOWN;
	}

	private int getId() {
		return this.id;
	}
}
