package com.tigercard.model;

public abstract class Fare {

	Zone fromZone = null;

	Zone toZone = null;

	public Fare(Zone fromZone, Zone toZone) {
		super();
		this.fromZone = fromZone;
		this.toZone = toZone;
	}

	/**
	 * Get the amount for the trip
	 * 
	 * @return
	 */
	public abstract int getAmount();

}
