package se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Constructs a matrix of possible trades.
 * @author TuX
 *
 */
public class TradeCalculator extends Thread implements Runnable {
	
	private final UserData userData;
	private final List<StarSystem> reviewedSystems;
	private final List<Station> stationsWithinBubble;
	private volatile int noCalculatedStations = 0;
	private TreeSet<TradeResult> tradeResults;

	public TradeCalculator(UserData userData) {
		this(userData, userData.getCurrentStation(), new ArrayList<>(userData.getCurrentBubble().values()));
	}
	
	public TradeCalculator(UserData userData, Station currentStation) {
		this(userData, currentStation, new ArrayList<>(userData.getCurrentBubble().values()));
	}
	
	public TradeCalculator(UserData userData, Station currentStation, List<StarSystem> usedBubble) {
		this.userData = userData;
		this.reviewedSystems = usedBubble;
		ArrayList<Station> tmp = new ArrayList<>();
		
		if(usedBubble == null) {
			usedBubble = (List<StarSystem>) userData.getCurrentBubble().values();
		}
		
		for(StarSystem system : usedBubble) {
			for(Station station : system.getStations()) {
				if(isStationValidForTrade(station)) {
					tmp.add(station);
				}
			}
		}
		this.stationsWithinBubble = tmp;
	}
	
	private boolean isStationValidForTrade(Station station) {
		if(station.getBoughtCommodities().isEmpty()) {
			return false;
		}
		if(!station.getMaxLandingPadSize().sizeAppropiate(userData.getShipType().getSize())) {
			return false;
		}
		if(!userData.isPlanetaryLanding()) {
			if(station.isPlanetary()) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void run() {
		this.tradeResults = new TreeSet<>();
		noCalculatedStations = 0;
		for(Station evalStation: this.stationsWithinBubble) {
			if(this.isInterrupted()) {
				break;
			}
			noCalculatedStations++;
			for(Station compStation : this.stationsWithinBubble) {
				for(TradeResult result : this.findPossibleTrades(evalStation, compStation)) {
					if(result.getExpectedProfit() > 0) {
						tradeResults.add(result);
					}				
				}
			}
		}
	}
	
	public List<TradeResult> findPossibleTrades(Station curStation, Station evalStation) {
		List<TradeResult> toReturn = new ArrayList<>();
		if(curStation != null & evalStation != null) {
			for(BaseCommodity comm : curStation.getSoldCommodities()) {
				for(BaseCommodity evalComm : evalStation.getBoughtCommodities()) {
					if(comm.getCommodity() == evalComm.getCommodity()) {
						int expectedProfit = (int) (calculateMaxBuyingAmount(comm) * (evalComm.getSellingPrice() - comm.getBuyingPrice()));
						toReturn.add(new TradeResult(comm, curStation, evalStation, expectedProfit));
					}
				}
			}
		}
		return toReturn;
	}

	private int calculateMaxBuyingAmount(BaseCommodity comm) {
		int maxBuy = new Double(userData.getCreditAvailable() / comm.getSellingPrice()).intValue();
		if(comm.getSupply() < maxBuy) {
			maxBuy = comm.getSupply();
		}
		if(userData.getMaxCargoHold() < maxBuy) {
			maxBuy = userData.getMaxCargoHold();
		}
		return maxBuy;
	}
	
	public int getNumberCalculatedStations() {
		return this.noCalculatedStations;
	}
	
	public int getTotalNumberOfStationsLookedAt() {
		return this.stationsWithinBubble.size();
	}
	
	public TreeSet<TradeResult> getPossibleTrades() {
		return this.tradeResults;
	}
	
	
	/*********************************************************************
	 * Compares only on expectedProfits for easy ordering of TradeResults.
	 * @author TuX
	 *
	 */
	public class TradeResult implements Comparable<TradeResult> {

		private final BaseCommodity commodity;
		private final Station fromStation;
		private final Station toStation;
		private final int expectedProfit;
		
		private StarSystem fromSystem;
		private StarSystem toSystem;
		
		public TradeResult(BaseCommodity commodity, Station fromStation, Station toStation, int expectedProfit) {
			this.commodity = commodity;
			this.fromStation = fromStation;
			this.toStation = toStation;
			this.expectedProfit = expectedProfit;
			for(StarSystem system : reviewedSystems) {
				if(system.getId() == fromStation.getSystemId()) {
					this.fromSystem = system;
				}
				if(system.getId() == toStation.getSystemId()) {
					this.toSystem = system;
				}
			}
		}
		
		@Override
		public int compareTo(TradeResult in) {
			if(in == null) {
				throw new NullPointerException("TradeResult to be compared with can't be null. WAKE UP!");
			}

			if(this.expectedProfit < in.getExpectedProfit()) {
				return 1;
			} 
			if(this.expectedProfit > in.getExpectedProfit()) {
				return -1;
			}
			return 0; // Default					
		}

		public BaseCommodity getCommodity() {
			return commodity;
		}

		public Station getFromStation() {
			return fromStation;
		}

		public Station getToStation() {
			return toStation;
		}

		public int getExpectedProfit() {
			return expectedProfit;
		}
		
		public StarSystem getFromSystem() {
			return fromSystem;
		}
		
		public StarSystem getToSystem() {
			return toSystem;
		}
		
		public int getJumps() {
			int jumps = 0;
			double jumpDistance = fromSystem.getPosition().distanceTo(toSystem.getPosition());
			while(jumpDistance > 0) {
				jumps++;
				jumpDistance = jumpDistance - userData.getLoadedJumpRange();
			}
			return jumps;
		}
		
		public String toString() {
			String distance = new Double(fromSystem.getPosition().distanceTo(toSystem.getPosition())).toString();
			if(distance.length() > 5) {
				distance = distance.substring(0, 5);
			}
			String planetary = " (S) ";
			if(toStation.isPlanetary()) {
				planetary = " (P) ";
			}
			int buyPrice = 0;
			for(BaseCommodity comm : toStation.getBoughtCommodities()) {
				if(comm.getCommodity() == commodity.getCommodity()) {
					buyPrice = comm.getSellingPrice();
				}
			}
			return commodity.getName() +"["+commodity.getBuyingPrice()+"|"+buyPrice+"] : {"+ fromSystem.getName()+"} "+ fromStation.getBaseName() +" -> {"+ toSystem.getName()+"} "+ toStation.getBaseName() + planetary +" [Profit: "+ expectedProfit +" | "+ distance +" {jumps:"+ getJumps() +"}]"; 
		}
	}	
}
