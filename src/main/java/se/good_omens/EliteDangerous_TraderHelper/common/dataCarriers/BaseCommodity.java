package se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers;

import se.good_omens.EliteDangerous_TraderHelper.common.enums.COMMODITY_CATEGORY;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.COMMODITY_DATA;
import se.good_omens.EliteDangerous_TraderHelper.common.interfaces.Commodity;
import se.good_omens.xmlModel.XmlNode;

/**
 * Describes a commodity used by a station. These stats should be considered as
 * a finding of wares or oppurtunities but not actual gains.
 *
 * @author Werner
 * @date Jun 15, 2015
 */
public class BaseCommodity implements Commodity {

	private long								id;
	private COMMODITY_DATA			commodity;
	private COMMODITY_CATEGORY	category;
	private String							name;
	private int								averagePrice;
	private Boolean							sold					= false;
	private Boolean							bought				= true;
	private Boolean							illegal				= false;
	
	private int									sellingPrice	= 0;
	private int									buyingPrice		= 0;
	private Boolean							rare					= false;
	private int 								supply				= 0;
	private int 								demand				= 0;

	public BaseCommodity(int id) {
		this.commodity = COMMODITY_DATA.fromInt(id);
	}

	public BaseCommodity(COMMODITY_DATA data) {
		this.commodity = data;
		this.setCategory(data.getCategory());
		this.id = data.getId();
		this.name = data.getName();
		this.averagePrice = data.getAveragePrice();
	}

	protected void setId(int id) {
		this.id = id;
	}

	public long getId() {
		return this.id;
	}

	public void setCommodity(COMMODITY_DATA commodity) {
		this.commodity = commodity;
	}

	public COMMODITY_DATA getCommodity() {
		return this.commodity;
	}

	public COMMODITY_CATEGORY getCategory() {
		return category;
	}

	public void setCategory(COMMODITY_CATEGORY category) {
		this.category = category;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setSold(Boolean sold) {
		this.sold = sold;
	}

	public Boolean isSold() {
		return this.sold;
	}

	public void setBought(Boolean bought) {
		this.bought = bought;
	}

	public Boolean isBought() {
		return this.bought;
	}

	protected void setIllegal(Boolean illegal) {
		this.illegal = illegal;
	}

	public Boolean isIllegal() {
		return this.illegal;
	}

	public int getAveragePrice() {
		return this.averagePrice;
	}

	/**
	 * This is the price the station will pay for the ware you are looking to sell to.
	 * @param sellingPrice
	 */
	public void setSellingPrice(int sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	/**
	 * This is the price the station will pay for the ware you are looking to sell to.
	 * @param sellingPrice
	 */
	public int getSellingPrice() {
		return this.sellingPrice;
	}

	/**
	 * This is the price the station will pay for the ware you are looking to buy from.
	 * @param buyingPrice
	 */
	public void setBuyingPrice(int buyingPrice) {
		this.buyingPrice = buyingPrice;
	}

	/**
	 * This is the price the station will pay for the ware you are looking to buy from.
	 * @param buyingPrice
	 */
	public int getBuyingPrice() {
//		System.out.println("Buying price: "+ buyingPrice);
		return this.buyingPrice;
	}

	public void setRare(boolean rare) {
		this.rare = rare;
	}

	public Boolean isRare() {
		return rare;
	}

	public XmlNode generateXmlRepresentation() {
		XmlNode toReturn = new XmlNode("commodity");

		toReturn.addAttribute("id", new Integer(this.getCommodity().getId()).toString());
		toReturn
		    .addChildNode(new XmlNode("category").addAttribute("id", new Integer(this.getCategory().getIndex()).toString())
		        .setTextValue(this.getCategory().getName()));
		toReturn.addChildNode(new XmlNode("name").setTextValue(this.getName()));
		toReturn.addChildNode(new XmlNode("averagePrice").setTextValue(new Long(this.getAveragePrice()).toString()));
		toReturn.addChildNode(new XmlNode("sellingPrice").addAttribute("sold", this.isSold().toString())
		    .setTextValue(new Long(this.getSellingPrice()).toString()));
		toReturn.addChildNode(new XmlNode("buyingPrice").addAttribute("bought", this.isSold().toString())
		    .setTextValue(new Long(this.getBuyingPrice()).toString()));
		toReturn.addAttribute("illegal", this.isIllegal().toString());
		toReturn.addAttribute("rare", rare.toString());

		return null;
	}

	/**
	 * Returns a fully realized trading commodity for a base. Please note that it
	 * is only a best effort attempted, it will throw exceptions
	 *
	 * @param node
	 * @return
	 */
	public static BaseCommodity generateFromXmlNode(XmlNode node) {
		if (!node.getName().equalsIgnoreCase("commodity")) {
			throw new IllegalArgumentException("Node was not a commodity - verify logic.");
		}

		BaseCommodity toReturn = new BaseCommodity(new Integer(node.getAttributeValue("id")).intValue());
		if (toReturn.getCommodity() != COMMODITY_DATA.NOT_DEFINED) {
			if (!node.getChildByName("name").getTextValue().equalsIgnoreCase(toReturn.getName())) {
				throw new IllegalArgumentException("Node did not match in name. Got '"
				    + node.getChildByName("name").getTextValue() + "', expected '" + toReturn.getName() + "'.");
			}
		}
		toReturn.setBought(new Boolean(node.getChildByName("buyingPrice").getAttributeValue("bought")));
		toReturn.setBuyingPrice(new Integer(node.getChildByName("buyingPrice").getTextValue()).intValue());
		toReturn.setSold(new Boolean(node.getChildByName("sellingPrice").getAttributeValue("sold")));
		toReturn.setSellingPrice(new Integer(node.getChildByName("sellingPrice").getTextValue()).intValue());
		toReturn.setIllegal(new Boolean(node.getAttributeValue("illegal")));
		toReturn.setRare(new Boolean(node.getAttributeValue("rare")));

		return toReturn;
	}

	public void setSupply(int supplyValue) {
		this.supply= supplyValue;		
	}
	public int getSupply() {
		return this.supply;
	}

	public void setDemand(int demandValue) {
		this.demand = demandValue;
	}
	public int getDemand() {
		return this.demand;
	}
}
