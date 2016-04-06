package se.good_omens.EliteDangerous_TraderHelper.common.enums;

public enum POWER {
	UNKNOWN("Unknown", ALLEGIANCE.UNKNOWN)
	,	NONE("None", ALLEGIANCE.NONE)
	, ARCHON_DELAINE("Archon Delaine", ALLEGIANCE.INDEPENDENT)
	, ZACHARY_HUDSON("Zachary Hudson", ALLEGIANCE.FEDERATION)
	, EDMUND_MAHON("Edmund Mahon", ALLEGIANCE.ALLIANCE)
	, FELICIA_WINTERS("Felicia Winters", ALLEGIANCE.FEDERATION)
	, PRANAV_ANTAL("Pranav Antal", ALLEGIANCE.INDEPENDENT)
	, DENTON_PATREUS("Denton Patreus", ALLEGIANCE.EMPIRE)
	, LI_YONG_RUI("Li Yong-Rui", ALLEGIANCE.INDEPENDENT)
	, ARISSA_LAVIGNY_DUVAL("Arissa Lavigny-Duval", ALLEGIANCE.EMPIRE)
	, AISLING_DUVAL("Aisling Duval", ALLEGIANCE.EMPIRE)
	;

	private final String powerName;
	private final ALLEGIANCE allegiance;

	private POWER(String name, ALLEGIANCE allegiance) {
		this.powerName = name;
		this.allegiance = allegiance;
	}

	public static POWER fromString(String in) {
		if((in == null) || in.trim().isEmpty()) {
			return POWER.NONE;
		}
		for(POWER item : POWER.values()) {
			if(item.getPublicName().equalsIgnoreCase(in.trim())) {
				return item;
			}
		}
		return POWER.UNKNOWN;
	}

	public String getPublicName() {
		return this.powerName;
	}

	public ALLEGIANCE getAllegiance() {
		return this.allegiance;
	}
}
