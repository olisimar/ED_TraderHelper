package se.good_omens.EliteDangerous_TraderHelper.common.enums;

public enum COMMODITY_CATEGORY {

	CHEMICALS("Chemicals", 1)
	, CONSUMER_ITEMS("Consumer Items", 2)
	, LEGAL_DRUGS("Legal Drugs", 3)
	, FOODS("Foods", 4)
	, INDUSTRIAL_MATERIALS("Industrial Materials", 5)
	, MACHINERY("Machinery", 6)
	, MEDICINES("Medicines", 7)
	, METALS("Metals", 8)
	, MINERALS("Minerals", 9)
	, SLAVERY("Slavery", 10)
	, TECHNOLOGY("Technology", 11)
	, TEXTILES("Textiles", 12)
	, WASTE("Waste", 13)
	, WEAPONS("Weapons", 14)
	, UNKNOWN("Unknown", 15)
	, SALVAGE("Salvage", 16)

	, NOT_DEFINED("Not Defined", 0)
	;

	private String name;
	private int	index;

	private COMMODITY_CATEGORY(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public int getIndex() {
		return this.index;
	}

	public String getName() {
		return this.name;
	}

	public static COMMODITY_CATEGORY fromString(String sought) {
		if((sought != null) || !sought.trim().isEmpty()) {
			for(COMMODITY_CATEGORY item : COMMODITY_CATEGORY.values()) {
				if(item.name().equalsIgnoreCase(sought) || item.getName().equalsIgnoreCase(sought)) {
					return item;
				}
			}
		}
		return COMMODITY_CATEGORY.NOT_DEFINED;
	}

	public static COMMODITY_CATEGORY fromInt(int sought) {
		if(sought >= 0) {
			for(COMMODITY_CATEGORY item : COMMODITY_CATEGORY.values()) {
				if(item.getIndex() == sought) {
					return item;
				}
			}
		}
		return COMMODITY_CATEGORY.NOT_DEFINED;
	}
}
