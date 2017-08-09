package se.good_omens.EliteDangerous_TraderHelper.common.enums;

public enum SHIP_TYPE {

	  SIDEWINDER_MK_1("Sidewinder Mk. I", SHIP_SIZE.SMALL)
	, DIAMONDBACK_SCOUT("Diamondback Scout", SHIP_SIZE.SMALL)
	, IMPERIAL_EAGLE("Imperial Eagle", SHIP_SIZE.SMALL)
	, IMPERIAL_COURIER("Imperial Courier", SHIP_SIZE.SMALL)
	, DIAMONDBACK_EXPLORER("Diamondback Explorer", SHIP_SIZE.SMALL)
	, EAGLE_MK_II("Eagle Mk. II", SHIP_SIZE.SMALL)
	, HAULER("Hauler", SHIP_SIZE.SMALL)
	, ADDER("Adder", SHIP_SIZE.SMALL)
	, VIPER_MK_III("Viper Mk III", SHIP_SIZE.SMALL)
	, VIPER_MK_IV("Viper MK IV", SHIP_SIZE.SMALL)
	, COBRA_MK_III("Cobra Mk. III", SHIP_SIZE.SMALL)
	, COBRA_MK_IV("Cobra MK IV", SHIP_SIZE.SMALL)
	, VULTURE("Vulture", SHIP_SIZE.SMALL)
	, DOLPHIN("Dolphin", SHIP_SIZE.SMALL)

	, TYPE_6_TRANSPORTER("Type-6 Transporter", SHIP_SIZE.MEDIUM)
	, ASP_SCOUT("Asp Scout", SHIP_SIZE.MEDIUM)
	, ASP_EXPLORER("Asp Explorer", SHIP_SIZE.MEDIUM)
	, KEELBACK("Keelback", SHIP_SIZE.MEDIUM)
	, PYTHON("Python", SHIP_SIZE.MEDIUM)
	, FER_DE_LANCE("Fer-de-Lance", SHIP_SIZE.MEDIUM)
	, FEDERAL_DROPSHIP("Federal Dropship", SHIP_SIZE.MEDIUM)
	, FEDERAL_ASSAULT_SHIP("Federal Assault Ship", SHIP_SIZE.MEDIUM)
	, FEDERAL_GUNSHIP("Federal Gunship", SHIP_SIZE.MEDIUM)
	
	, TYPE_7_TRANSPORTER("Type-7 Transporter", SHIP_SIZE.LARGE)
	, IMPERIAL_CLIPPER("Imperial Clipper", SHIP_SIZE.LARGE)
	, ORCA("Orca", SHIP_SIZE.LARGE)
	, TYPE_9_HEAVY("Type-9 Heavy", SHIP_SIZE.LARGE)
	, ANACONDA("Anaconda", SHIP_SIZE.LARGE)
	, IMPERIAL_CUTTER("Imperial Cutter", SHIP_SIZE.LARGE)
	, FEDERAL_CORVETTE("Federal Corvette", SHIP_SIZE.LARGE)
	, BELUGA_LINER("Beluga Liner", SHIP_SIZE.LARGE)
	
	, NONE("", SHIP_SIZE.NONE)
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
