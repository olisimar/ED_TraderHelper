package se.good_omens.EliteDangerous_TraderHelper.common.enums;

public enum SHIP_SIZE {

	S("Small")
	,	M("Medium")
	,	L("Large")
	, UNKNOWN("Unknown")
	;


	private final String representation;

	private SHIP_SIZE(String name) {
		this.representation = name;
	}

	public String getRepresenation() {
		return this.representation;
	}

	public SHIP_SIZE fromString(String in) {
		for(SHIP_SIZE size : SHIP_SIZE.values()) {
			if(in.equalsIgnoreCase(size.getRepresenation()) || in.equalsIgnoreCase(size.name())) {
				return size;
			}
		}
		return UNKNOWN;
	}
}
