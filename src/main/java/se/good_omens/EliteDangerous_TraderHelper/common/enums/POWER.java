package se.good_omens.EliteDangerous_TraderHelper.common.enums;

public enum POWER {
	ARISSA("Arissa Lavigny-Duval", ALLEGIANCE.FEDERATION)
	, NONE("None", ALLEGIANCE.NONE);
	;

	private final String name;
	private final ALLEGIANCE allegiance;

	private POWER(String name, ALLEGIANCE allegiance) {
		this.name = name;
		this.allegiance = allegiance;
	}

	public String getPublicName() {
		return this.name;
	}

	public ALLEGIANCE getAllegiance() {
		return this.allegiance;
	}
}
