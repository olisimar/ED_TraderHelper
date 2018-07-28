package se.good_omens.EliteDangerous_TraderHelper.common.parsers;

import java.util.TreeMap;

import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.BaseCommodity;
import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.Station;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.COMMODITY_DATA;

public class ParseListings {

	private long	statCommoditiesTotal	= 0l;
	private long	statCommoditiesBought	= 0l;
	private long	statCommoditiesSold		= 0l;

	/**
	 * Actually parses a listing and adds them to the related station in question.
	 * 
	 * @param inData
	 * @param stations
	 * @return stations
	 */
	public TreeMap<Long, Station> parseListings(String inData, TreeMap<Long, Station> stations) {
		if ((inData == null) || inData.trim().isEmpty()) {
			throw new IllegalArgumentException("Listing data was null/empty.");
		}
		String[] listings = inData.split("\n");
		for (String listing : listings) {
			addListingToStations(listing, stations);
		}

		System.out.println(
		    " Total: " + statCommoditiesTotal + "\nBought: " + statCommoditiesBought + "\n  Sold: " + statCommoditiesSold);
		return stations;
	}

	private TreeMap<Long, Station> addListingToStations(String listing, TreeMap<Long, Station> stations) {
		String[] item = listing.split(",");
		if (!item[0].equals("id")) {
// 0:id	1:station_id	2:commodity_id	3:supply	4:supply_bracket	5:buy_price	6:sell_price	7:demand	8:demand_bracket	9:collected_at
// 1	1	5	0	0	0	504	1526	2	1532605876

			// 0:id, 1:station id, 2:commodity id, 3:supply, 4:buy_price, 5:sell_price, 6:demand
			// id,station_id,commodity_id,supply,buy_price,sell_price,demand,collected_at
			// 1,1,5,0,0,476,1065,1500915774
			Station station = stations.get(new Long(item[1]).longValue());
			if (station != null) {
				COMMODITY_DATA data = COMMODITY_DATA.fromInt(new Integer(item[2]).intValue());
				BaseCommodity com = new BaseCommodity(data);
				com.setSupply(new Integer(item[3]).intValue());
				com.setSellingPrice(new Integer(item[6]).intValue());

				try {com.setBuyingPrice(new Integer(item[5]).intValue());} catch(NumberFormatException nfe) {com.setBuyingPrice(0);}
				try {com.setDemand(new Integer(item[7]).intValue());} catch(NumberFormatException nfe) {com.setDemand(0);}
				statCommoditiesTotal++;
				if (com.getSupply() != 0) {
					com.setSold(true);
					station.addSoldCommodity(com);
					statCommoditiesSold++;
				}
				if (com.getDemand() != 0) {
					com.setBought(true);
					station.addBoughtCommodity(com);
					statCommoditiesBought++;
				}
			} else {
				System.out.println("Failed to find requested station from provided list, concerns stationId: " + item[1]);
			}
		}
		return stations;
	}
}
