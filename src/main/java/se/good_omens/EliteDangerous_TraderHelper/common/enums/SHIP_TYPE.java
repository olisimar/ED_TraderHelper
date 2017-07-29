package se.good_omens.EliteDangerous_TraderHelper.common.enums;

public enum SHIP_TYPE {

	  SIDEWINDER_MK_1("Sidewinder Mk. I", SHIP_SIZE.SMALL)
	, EAGLE_MK_II("Eagle Mk. II", SHIP_SIZE.SMALL)
	, HAULER("Hauler", SHIP_SIZE.SMALL)
	, ADDER("Adder", SHIP_SIZE.SMALL)
	, VIPER_MK_III("Viper Mk III", SHIP_SIZE.SMALL)
	, COBRA_MK_III("Cobra Mk. III", SHIP_SIZE.SMALL)
	, TYPE_6_TRANSPORTER("Type-6 Transporter", SHIP_SIZE.SMALL)
	, TYPE_7_TRANSPORTER("Type-7 Transporter", SHIP_SIZE.LARGE)
	, ASP_EXPLORER("Asp Explorer", SHIP_SIZE.SMALL)
	, VULTURE("Vulture", SHIP_SIZE.SMALL)
	, IMPERIAL_CLIPPER("Imperial Clipper", SHIP_SIZE.LARGE)
	, FEDERAL_DROPSHIP("Federal Dropship", SHIP_SIZE.LARGE)
	, ORCA("Orca", SHIP_SIZE.LARGE)
	, TYPE_9_HEAVY("Type-9 Heavy", SHIP_SIZE.LARGE)
	, PYTHON("Python", SHIP_SIZE.LARGE)
	, FER_DE_LANCE("Fer-de-Lance", SHIP_SIZE.SMALL)
	, ANACONDA("Anaconda", SHIP_SIZE.LARGE)
	
	, NONE("none", SHIP_SIZE.NONE)
	, UNKNOWN("unknown", SHIP_SIZE.NONE);
	
	private final String usedName;
	private final SHIP_SIZE size;
	
	private SHIP_TYPE(String usedName, SHIP_SIZE size) {
		this.usedName = usedName;
		this.size = size;
	}
	
	public String getUsedName() {
		return this.usedName;
	}
	
	public SHIP_SIZE getSize() {
		return this.size;
	}

	public static SHIP_TYPE fromString(String in) {
		if(in == null || in.trim().isEmpty()) {
			return SHIP_TYPE.NONE;
		}
		for(SHIP_TYPE type : SHIP_TYPE.values()) {
			if(in.equalsIgnoreCase(type.getUsedName()) || in.equalsIgnoreCase(type.name())) {
				return type;
			}
		}
		
		return SHIP_TYPE.UNKNOWN;
	}	
}
