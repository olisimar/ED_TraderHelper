package se.good_omens.EliteDangerous_TraderHelper.common.enums;

public enum SHIP_SIZE {

	NONE("NONE")
	, UNKNOWN("Unknown")
	, S("Small")
	,	M("Medium")
	,	L("Large")
	;


	private final String representation;

	private SHIP_SIZE(String name) {
		this.representation = name;
	}

	public String getRepresenation() {
		return this.representation;
	}

	public static SHIP_SIZE fromString(String in) {
		if((in == null) || in.trim().isEmpty()) {
			return SHIP_SIZE.NONE;
		}
		for(SHIP_SIZE size : SHIP_SIZE.values()) {
			if(in.equalsIgnoreCase(size.getRepresenation()) || in.equalsIgnoreCase(size.name())) {
				return size;
			}
		}
		return SHIP_SIZE.UNKNOWN;
	}
}
