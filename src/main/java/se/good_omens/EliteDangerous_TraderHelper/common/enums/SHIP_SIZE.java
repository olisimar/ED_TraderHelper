package se.good_omens.EliteDangerous_TraderHelper.common.enums;

public enum SHIP_SIZE {

	  SMALL("S")
	,	MEDIUM("M")
	,	LARGE("L")
  , NONE("none")
  , UNKNOWN("unknown")
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
	
	/**
	 * Returns true if the padSize passed in is smaller or equal to this.
	 * Use: station.getMaxLandingPadSize().sizeAppropiate(ship.getShipSize())
	 * @param padSize
	 * @return
	 */
	public boolean sizeAppropiate(SHIP_SIZE padSize) {
		return (padSize.ordinal() <= this.ordinal());
	}
}
