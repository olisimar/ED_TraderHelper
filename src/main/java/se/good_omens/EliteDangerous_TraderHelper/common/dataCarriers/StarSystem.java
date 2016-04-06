package se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers;

import java.util.Date;

import se.good_omens.EliteDangerous_TraderHelper.common.enums.ALLEGIANCE;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.ECONOMY_TYPE;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.POWER;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.POWER_STATE;
import se.good_omens.EliteDangerous_TraderHelper.common.enums.SECURITY_RATING;

public class StarSystem {

	private final long id;
	private final String name;
	private Position position;
	private String faction = null;
	private int population = 0;
	private String government = "none";
	private ALLEGIANCE allegiance;
	private String state = "None";
	private SECURITY_RATING secRating = SECURITY_RATING.UNKNOWN;
	private ECONOMY_TYPE primaryEconomy = ECONOMY_TYPE.NONE;
	private POWER power = POWER.NONE;
	private POWER_STATE powerState = POWER_STATE.NONE;
	private boolean needsPermit = false;
	private Date updatedAt = new Date(0L);
	private String simbadRef = "";
	/*
,"power_state":null
	 */
	public StarSystem(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getFaction() {
		return faction;
	}

	public void setFaction(String faction) {
		this.faction = faction;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public String getGovernment() {
		return government;
	}

	public void setGovernment(String government) {
		this.government = government;
	}

	public ALLEGIANCE getAllegiance() {
		return allegiance;
	}

	public void setAllegiance(ALLEGIANCE allegiance) {
		this.allegiance = allegiance;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public SECURITY_RATING getSecRating() {
		return secRating;
	}

	public void setSecRating(SECURITY_RATING secRating) {
		this.secRating = secRating;
	}

	public ECONOMY_TYPE getPrimaryEconomy() {
		return primaryEconomy;
	}

	public void setPrimaryEconomy(ECONOMY_TYPE primaryEconomy) {
		this.primaryEconomy = primaryEconomy;
	}

	public POWER getPower() {
		return power;
	}

	public void setPower(POWER power) {
		this.power = power;
	}

	public POWER_STATE getPowerState() {
		return powerState;
	}

	public void setPowerState(POWER_STATE powerState) {
		this.powerState = powerState;
	}

	public boolean isNeedsPermit() {
		return needsPermit;
	}

	public void setNeedsPermit(boolean needsPermit) {
		this.needsPermit = needsPermit;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getSimbadRef() {
		return simbadRef;
	}

	public void setSimbadRef(String simbadRef) {
		this.simbadRef = simbadRef;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Position getPosition() {
		return position;
	}


}
