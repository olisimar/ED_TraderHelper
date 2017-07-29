package se.good_omens.EliteDangerous_TraderHelper.common.enums;

public enum MODULE_WEAPON_MODE {

	  FIXED
	, GIMBAL
	, TURRET
	
	, NONE
	, UNKNOWN
	;
	
	public MODULE_WEAPON_MODE fromString(String mode) {
		if((mode == null) || mode.trim().isEmpty()) {
			return MODULE_WEAPON_MODE.NONE;
		}
		for(MODULE_WEAPON_MODE state : MODULE_WEAPON_MODE.values()) {
			if(state.name().equalsIgnoreCase(mode.trim())) {
				return state;
			}
		}
		return MODULE_WEAPON_MODE.UNKNOWN;
	}
}
