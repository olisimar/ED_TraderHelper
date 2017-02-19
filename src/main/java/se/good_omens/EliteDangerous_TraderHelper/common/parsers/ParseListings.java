package se.good_omens.EliteDangerous_TraderHelper.common.parsers;

import java.util.TreeMap;

import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.BaseCommodity;
import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.Station;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.COMMODITY_DATA;

public class ParseListings {

	private long statCommoditiesTotal = 0l;
	private long statCommoditiesBought = 0l;
	private long statCommoditiesSold = 0l;

	/**
	 * Actually parses a listing and adds them to the related station in question.
	 * @param inData
	 * @param stations
	 * @return stations
	 */
	public TreeMap<Long, Station> parseListings(String inData, TreeMap<Long, Station> stations) {
		if((inData == null) || inData.trim().isEmpty()) {
			throw new IllegalArgumentException("Listing data was null/empty.");
		}
		String[] listings = inData.split("\n");
		for(String listing : listings) {
			addListingToStations(listing, stations);
		}

		System.out.println(" Total: "+ statCommoditiesTotal +"\nBought: "+ statCommoditiesBought +"\n  Sold: "+ statCommoditiesSold);
		return stations;
	}

	private void addListingToStations(String listing, TreeMap<Long, Station> stations) {
		String[] item = listing.split(",");
		if(!item[0].equals("id")) {
			// 0 id, 1 station id, 2 commodity id, 3 supply, 4 buy_price, 5 sell_price, 6 demand
			Station station = stations.get(new Long(item[1]).longValue());
			if(station != null) {
				COMMODITY_DATA data = COMMODITY_DATA.fromInt(new Long(item[2]).intValue());
				BaseCommodity com = new BaseCommodity(data);
				statCommoditiesTotal++;
				if(new Long(item[3]).longValue() != 0) {
					com.setSold(true);
					com.setSellingPrice(new Long(item[5]).intValue());
					station.addExportCommodity(com);
					statCommoditiesSold++;
				}
				if(new Long(item[6]).longValue() != 0) {
					com.setBought(true);
					com.setBuyingPrice(new Long(item[4]).intValue());
					station.addImportCommodity(com);
					statCommoditiesBought++;
				}
			} else {
				System.out.println("Failed to find requested station from provided list, concerns stationId: "+ item[1]);
			}
		}
	}
}
