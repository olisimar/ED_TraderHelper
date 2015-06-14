package se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers;

import se.good_omens.EliteDangerous_TraderHelper.common.enums.COMMODITY_CATEGORY;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.COMMODITY_DATA;
import se.good_omens.EliteDangerous_TraderHelper.common.interfaces.Commodity;
import se.good_omens.xmlModel.XmlNode;

public class BaseCommodity implements Commodity {


	private int	id;
	private COMMODITY_DATA	commodity;
	private COMMODITY_CATEGORY	category;
	private String	name;
	private int	averagePrice;
	private Boolean	sold;
	private Boolean	bought = true;
	private Boolean	illegal = false;
	private int	sellingPrice;
	private int	buyingPrice;

	public BaseCommodity(int id) {
		this.commodity = COMMODITY_DATA.fromInt(id);
	}

	public BaseCommodity(COMMODITY_DATA data) {
		this.commodity = data;
		this.category = data.getCategory();
		this.id = data.getId();
		this.name = data.getName();
		this.averagePrice = data.getAveragePrice();

	}

	public int getId() {
		return this.id;
	}

	public COMMODITY_DATA getCommodity() {
		return this.commodity;
	}

	public String getName() {
		return this.name;
	}

	public Boolean isSold() {
		return this.sold;
	}

	public Boolean isBought() {
		return this.bought;
	}

	public Boolean isIllegal() {
		return this.illegal;
	}

	public int getAveragePrice() {
		return this.averagePrice;
	}

	public int getSellingPrice() {
		return this.sellingPrice;
	}

	public int getBuyingPrice() {
		return this.buyingPrice;
	}

	public XmlNode generateXmlRepresentation() {
		XmlNode toReturn = new XmlNode("commodity");
		toReturn.addAttribute("id", new Integer(this.getCommodity().getId()).toString());
		toReturn.addChildNode(new XmlNode("name").setTextValue(this.getName()));
		toReturn.addChildNode(new XmlNode("sellingPrice").addAttribute("sold", this.isSold().toString()).setTextValue(new Integer(this.getSellingPrice()).toString()));
		toReturn.addChildNode(new XmlNode("buyingPrice").addAttribute("bought", this.isSold().toString()).setTextValue(new Integer(this.getBuyingPrice()).toString()));
		toReturn.addAttribute("illegal", this.isIllegal().toString());

		return null;
	}
}
