package se.good_omens.EliteDangerous_TraderHelper.common.enums;

public enum MODULE_PLACEMENT_SLOT {

	  BULKHEAD("Bulkhead")
	, UTILITY_MOUNT("Utility Mount")
	, ESSENTIAL_EQUIPMENT("Essential Equipment")
	, INTERNAL_COMPARTMENT("Internal Compartment")
	, WEAPON_HARDPOINT("Weapon Hardpoint")
	  
	, NONE("none")
	, UNKNOWN("unknown")
	;
	
	private final String usedName;
	
	private MODULE_PLACEMENT_SLOT(String usedName) {
		this.usedName = usedName;
	}
	
	public static MODULE_PLACEMENT_SLOT fromString(String in) {
		if((in == null) || in.trim().isEmpty()) {
			return MODULE_PLACEMENT_SLOT.NONE;
		}
		for(MODULE_PLACEMENT_SLOT slot : MODULE_PLACEMENT_SLOT.values()) {
			if(slot.name().equalsIgnoreCase(in.trim()) || in.equalsIgnoreCase(slot.getUsedName())) {
				return slot;
			}
		}
		return MODULE_PLACEMENT_SLOT.UNKNOWN;
	}

	private String getUsedName() {
		return this.usedName;
	}

	public static MODULE_PLACEMENT_SLOT fromInt(int id) {
		return null;
	}
}
