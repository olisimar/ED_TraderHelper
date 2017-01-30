package se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import se.good_omens.EliteDangerous_TraderHelper.common.enums.ALLEGIANCE;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.ECONOMY_TYPE;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.GOVERMENT_TYPE;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.SHIP_SIZE;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.STATION_STATE;
import se.good_omens.EliteDangerous_TraderHelper.common.interfaces.Commodity;

public class Station {

	private long							id;
	private String						baseName;
	private long							systemId;
	private SHIP_SIZE					maxLandingPadSize			= SHIP_SIZE.M;
	private long							distanceToStar				= -1L;
	private String						localFaction					= "";
	private GOVERMENT_TYPE		goverment							= GOVERMENT_TYPE.NONE;
	private ALLEGIANCE				allegiance						= ALLEGIANCE.NONE;
	private STATION_STATE			state									= STATION_STATE.NONE;
	private long							typeId								= 0;
	private SHIP_SIZE					maxSize;

	private boolean						isPlanetary						= false;
	private boolean						hasBlackMarket				= false;
	private boolean						hasRefuel							= false;
	private boolean						hasRepair							= false;
	private boolean						hasReArm							= false;
	private boolean						hasOutfitting					= false;
	private boolean						hasShipyard						= false;
	private boolean						hasCommodities				= false;

	private Set<Commodity>		importCommodities			= new HashSet<Commodity>();
	private Set<Commodity>		exportCommodities			= new HashSet<Commodity>();
	private Set<Commodity>		prohibitedCommodities	= new HashSet<Commodity>();

	private Set<ECONOMY_TYPE>	economies							= new HashSet<ECONOMY_TYPE>();
	private Date							stationUpdatedAt			= new Date(0L);
	private Set<String>				sellingModules				= new HashSet<String>();

	public String getBaseName() {
		return baseName;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	public long getSystemId() {
		return systemId;
	}

	public void setSystemId(long systemId) {
		this.systemId = systemId;
	}

	public SHIP_SIZE getMaxLandingPadSize() {
		return maxLandingPadSize;
	}

	public void setMaxLandingPadSize(SHIP_SIZE maxLandingPadSize) {
		this.maxLandingPadSize = maxLandingPadSize;
	}

	public long getDistanceToStar() {
		return distanceToStar;
	}

	public void setDistanceToStar(long distanceToStar) {
		this.distanceToStar = distanceToStar;
	}

	public String getLocalFaction() {
		return localFaction;
	}

	public void setLocalFaction(String localFaction) {
		this.localFaction = localFaction;
	}

	public GOVERMENT_TYPE getGoverment() {
		return goverment;
	}

	public void setGoverment(GOVERMENT_TYPE goverment) {
		this.goverment = goverment;
	}

	public ALLEGIANCE getAllegiance() {
		return allegiance;
	}

	public void setAllegiance(ALLEGIANCE allegiance) {
		this.allegiance = allegiance;
	}

	public STATION_STATE getState() {
		return state;
	}

	public void setState(STATION_STATE state) {
		this.state = state;
	}

	public long getTypeId() {
		return typeId;
	}

	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}

	public SHIP_SIZE getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(SHIP_SIZE maxSize) {
		this.maxSize = maxSize;
	}

	public boolean isPlanetary() {
		return isPlanetary;
	}

	public void setPlanetary(boolean isPlanetary) {
		this.isPlanetary = isPlanetary;
	}

	public boolean isHasBlackMarket() {
		return hasBlackMarket;
	}

	public void setHasBlackMarket(boolean hasBlackMarket) {
		this.hasBlackMarket = hasBlackMarket;
	}

	public boolean isHasRefuel() {
		return hasRefuel;
	}

	public void setHasRefuel(boolean hasRefuel) {
		this.hasRefuel = hasRefuel;
	}

	public boolean isHasRepair() {
		return hasRepair;
	}

	public void setHasRepair(boolean hasRepair) {
		this.hasRepair = hasRepair;
	}

	public boolean isHasReArm() {
		return hasReArm;
	}

	public void setHasReArm(boolean hasReArm) {
		this.hasReArm = hasReArm;
	}

	public boolean isHasOutfitting() {
		return hasOutfitting;
	}

	public void setHasOutfitting(boolean hasOutfitting) {
		this.hasOutfitting = hasOutfitting;
	}

	public boolean isHasShipyard() {
		return hasShipyard;
	}

	public void setHasShipyard(boolean hasShipyard) {
		this.hasShipyard = hasShipyard;
	}

	public boolean isHasCommodities() {
		return hasCommodities;
	}

	public void setHasCommodities(boolean hasCommodities) {
		this.hasCommodities = hasCommodities;
	}

	public Set<Commodity> getImportCommodities() {
		return importCommodities;
	}

	public void setImportCommodities(Set<Commodity> importCommodities) {
		this.importCommodities = importCommodities;
	}

	public Set<Commodity> getExportCommodities() {
		return exportCommodities;
	}

	public void setExportCommodities(Set<Commodity> exportCommodities) {
		this.exportCommodities = exportCommodities;
	}

	public Set<Commodity> getProhibitedCommodities() {
		return prohibitedCommodities;
	}

	public void setProhibitedCommodities(Set<Commodity> prohibitedCommodities) {
		this.prohibitedCommodities = prohibitedCommodities;
	}

	public Set<ECONOMY_TYPE> getEconomies() {
		return economies;
	}

	public void setEconomies(Set<ECONOMY_TYPE> economies) {
		this.economies = economies;
	}

	public Date getStationUpdatedAt() {
		return stationUpdatedAt;
	}

	public void setStationUpdatedAt(Date stationUpdatedAt) {
		this.stationUpdatedAt = stationUpdatedAt;
	}

	public Set<String> getSellingModules() {
		return sellingModules;
	}

	public void setSellingModules(Set<String> sellingModules) {
		this.sellingModules = sellingModules;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void addEconomy(ECONOMY_TYPE type) {
		if (this.economies == null) {
			this.economies = new HashSet<ECONOMY_TYPE>();
		}
		this.economies.add(type);
	}

	public boolean hasEconomy(ECONOMY_TYPE type) {
		return this.economies.contains(type);
	}

	public void addImportCommodity(BaseCommodity commodity) {
		if (this.importCommodities == null) {
			this.importCommodities = new HashSet<Commodity>();
		}
		this.importCommodities.add(commodity);
	}

	public void addExportCommodity(BaseCommodity commodity) {
		if (this.exportCommodities == null) {
			this.exportCommodities = new HashSet<Commodity>();
		}
		this.exportCommodities.add(commodity);
	}
}
