package se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers;

import se.good_omens.EliteDangerous_TraderHelper.common.enums.COMMODITY_CATEGORY;
import se.good_omens.EliteDangerous_TraderHelper.common.interfaces.CommodityCategory;
import se.good_omens.xmlModel.XmlNode;

public class Category implements CommodityCategory {

	private COMMODITY_CATEGORY category;
	private int id;
	private String name;

	public Category(String name) {
		this(COMMODITY_CATEGORY.fromString(name));
	}

	public Category(int id) {
		this(COMMODITY_CATEGORY.fromInt(id));
	}

	public Category(COMMODITY_CATEGORY category) {
		this.category = category;
		this.name = this.category.getName();
		this.id = this.category.getIndex();
	}

	public Category(int id, String name) {
		COMMODITY_CATEGORY catId = COMMODITY_CATEGORY.fromInt(id);
		COMMODITY_CATEGORY catName = COMMODITY_CATEGORY.fromString(name);
		if((catId != COMMODITY_CATEGORY.NOT_DEFINED) || (catName != COMMODITY_CATEGORY.NOT_DEFINED)) {
			if(catId != COMMODITY_CATEGORY.NOT_DEFINED) {
				this.category = catId;
				this.id = catId.getIndex();
				this.name = catName.getName();
			} else if(catName != COMMODITY_CATEGORY.NOT_DEFINED) {
				this.category = catName;
				this.name = catName.getName();
				this.id = catName.getIndex();
			}
		}
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return this.category.getIndex();
	}

	public String getName() {
		return this.category.getName();
	}

	public XmlNode generateXmlRepresentation() {
		return new XmlNode("commodities", "category").addAttribute("id", new Integer(this.id).toString()).setTextValue(this.getName());
	}
}
