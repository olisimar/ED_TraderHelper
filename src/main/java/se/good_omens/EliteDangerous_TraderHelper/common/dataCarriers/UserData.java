package se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import se.good_omens.EliteDangerous_TraderHelper.common.enums.SHIP_TYPE;
import se.good_omens.EliteDangerous_TraderHelper.common.utils.RuntimeProperties;

public class UserData {

	private RuntimeProperties			properties;
	private StarSystem						currentSystem;
	private Station								currentStation;
	private Map<Long, StarSystem>	currentBubble;
	private Map<Long, StarSystem>	galaxy;
	private double								loadedJumpRange		= 0;
	private int										maxJumps					= 0;
	private boolean								planetaryLanding	= true;
	private SHIP_TYPE							shipType					= SHIP_TYPE.SIDEWINDER_MK_1;
	private int										creditAvailable		= 0;
	private int										maxCargoHold			= 0;

	public UserData(RuntimeProperties properties, Map<Long, StarSystem> galaxy) {
		this.galaxy = galaxy;
		this.currentSystem = galaxy.get(new Long(properties.getEntry("system")).longValue());
		this.loadedJumpRange = new Double(properties.getEntry("jumpRangeLoaded")).doubleValue();
		this.maxJumps = new Integer(properties.getEntry("maxJumps")).intValue();
		this.currentSystem = galaxy.get(new Long(properties.getEntry("system")).longValue());
		this.planetaryLanding = (Boolean.parseBoolean(properties.getEntry("planetaryLanding")));
		this.shipType = SHIP_TYPE.fromString(properties.getEntry("shipType"));
		this.maxCargoHold = Integer.parseInt(properties.getEntry("cargoHoldSize"));
		this.creditAvailable = Integer.parseInt(properties.getEntry("creditAvailable"));

		setStationBasedOnId(properties);
		calculateCurrentBubble();
	}

	public Map<Long, StarSystem> getCurrentBubble() {
		if (currentBubble == null) {
			System.out.println("buble not calculated...");
			calculateCurrentBubble();
		}
		return currentBubble;
	}

	public Station getCurrentStation() {
		return this.currentStation;
	}

	public StarSystem getCurrentSystem() {
		return this.currentSystem;
	}

	private void calculateCurrentBubble() {
		if (maxJumps * loadedJumpRange >= 0) {
			double maxDistance = loadedJumpRange * maxJumps;
			TreeMap<Long, StarSystem> firstSelection = new TreeMap<>();
			for (Entry<Long, StarSystem> item : galaxy.entrySet()) {
				if (currentSystem.getPosition().distanceTo(item.getValue().getPosition()) <= maxDistance) {
					if (item.getValue().getStations().size() != 0) {
						firstSelection.put(item.getKey(), item.getValue());
					}
				}
			}

			this.currentBubble = new TreeMap<>();
			currentBubble.put(this.getCurrentSystem().getId(), this.getCurrentSystem());
			for (int jump = 1; jump <= maxJumps; jump++) {
				TreeMap<Long, StarSystem> inProgress = new TreeMap<>();
				for (Entry<Long, StarSystem> origin : firstSelection.entrySet()) {
					inProgress.putAll(findClosestNeighbours(origin.getValue(), firstSelection));
				}
				currentBubble.putAll(inProgress);
			}
			System.out.println("Second run has been completed, bubble now has " + currentBubble.size());
		}
	}

	public TreeMap<Long, StarSystem> findClosestNeighbours(StarSystem origin, TreeMap<Long, StarSystem> systems) {
		TreeMap<Long, StarSystem> toReturn = new TreeMap<>();
		for (Entry<Long, StarSystem> system : systems.entrySet()) {
			if (inReachFromCurrentSystem(origin, system.getValue())) {
				if (!currentBubble.containsKey(system.getValue().getId())) {
					if ((origin.getId() != system.getValue().getId())) {
						currentBubble.put(system.getKey(), system.getValue());
					}
				}
			}
		}
		return toReturn;
	}

	private boolean inReachFromCurrentSystem(StarSystem origin, StarSystem destination) {
		if (origin.getPosition().distanceTo(destination.getPosition()) <= loadedJumpRange) {
			return true;
		}
		return false;
	}

	public double getLoadedJumpRange() {
		return loadedJumpRange;
	}

	private void setStationBasedOnId(RuntimeProperties properties) {
		for (Station station : currentSystem.getStations()) {
			if (station.getId() == new Long(properties.getEntry("station")).longValue()) {
				currentStation = station;
			}
		}
	}

	public boolean isPlanetaryLanding() {
		return planetaryLanding;
	}

	public SHIP_TYPE getShipType() {
		return shipType;
	}

	public void setShipType(SHIP_TYPE shipType) {
		this.shipType = shipType;
	}

	public int getCreditAvailable() {
		return creditAvailable;
	}

	public void setCreditAvailable(int creditAvailable) {
		this.creditAvailable = creditAvailable;
	}

	public int getMaxCargoHold() {
		return maxCargoHold;
	}

	public void setMaxCargoHold(int maxCargoHold) {
		this.maxCargoHold = maxCargoHold;
	}
}
