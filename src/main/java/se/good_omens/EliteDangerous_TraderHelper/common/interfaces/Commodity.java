package se.good_omens.EliteDangerous_TraderHelper.common.interfaces;

import se.good_omens.EliteDangerous_TraderHelper.common.enums.COMMODITY_DATA;

/**
 * Base definition of a commodity found at a station.
 *
 * @author Werner
 * @date Jun 9, 2015
 */
public interface Commodity extends XmlEntity {

	public COMMODITY_DATA getCommodity();
	public String getName();
	public Boolean isSold();
	public Boolean isBought();
	public Boolean isIllegal();

	public int getAveragePrice();
	public int getSellingPrice();
	public int getBuyingPrice();
}
