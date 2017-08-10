package se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers;

import se.good_omens.EliteDangerous_TraderHelper.common.enums.MODULE_PLACEMENT_SLOT;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.MODULE_WEAPON_MODE;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.SHIP_TYPE;

/**
 * Denotes a ship module in terms of Station commodity.
 */
public class ShipModule {
// {"id":738,"group_id":50,"class":1,"rating":"I","price":null,"weapon_mode":null,"missile_type":null,"name":null,"belongs_to":null,
//"ed_id":128049250,"ed_symbol":"SideWinder_Armour_Grade1","ship":"Sidewinder Mk. I",
//"group":{"id":50,"category_id":40,"name":"Lightweight Alloy","category":"Bulkhead"}}

// {"id":739,"group_id":51,"class":1,"rating":"I","price":25600,"weapon_mode":null,"missile_type":null,"name":null,"belongs_to":null,
//"ed_id":128049251,"ed_symbol":"SideWinder_Armour_Grade2","mass":2,"ship":"Sidewinder Mk. I",
//"group":{"id":51,"category_id":40,"name":"Reinforced Alloy","category":"Bulkhead"}}
	
	private final Integer id;
	private MODULE_PLACEMENT_SLOT slotType;
	private SHIP_TYPE shipType = SHIP_TYPE.NONE;
	private String name = "None";
	
	private String modRating = "none";
	private int modClass = 0;
	private int price = 0;

	public ShipModule(Integer id) {
		this.id = id;
	}

	public Integer getId() { return id; }

	public MODULE_PLACEMENT_SLOT getSlotType() {
		return slotType;
	}

	public void setSlotType(MODULE_PLACEMENT_SLOT slotType) {
		this.slotType = slotType;
	}

	public SHIP_TYPE getShipType() {
		return shipType;
	}

	public void setShipType(SHIP_TYPE shipType) {
		this.shipType = shipType;
	}

	public String getRating() {
		return modRating;
	}

	public void setRating(String rating) {
		this.modRating = rating;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return new String(id +":"+ shipType.getUsedName() +" > "+ name +" ["+ modRating +":"+ modClass +"] ("+ slotType.name()+")");
	}

	public int getModClass() {
		return modClass;
	}

	public void setModClass(int modClass) {
		this.modClass = modClass;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ShipModule) {
			ShipModule comp = (ShipModule) obj;
			if(this.id.equals(comp.getId())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.id;
	}	
}
