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
	public long getId();
	public String getName();
	public Boolean isSold();
	public Boolean isBought();
	public Boolean isIllegal();
	public Boolean isRare();

	public long getAveragePrice();
	public long getSellingPrice();
	public long getBuyingPrice();
}
