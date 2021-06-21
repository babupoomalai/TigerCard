package com.tigercard.model;

public abstract class Pass {

	Zone fromZone = null;

	Zone toZone = null;

	public Pass(Zone fromZone, Zone toZone) {
		super();
		this.fromZone = fromZone;
		this.toZone = toZone;
	}

	/**
	 * Get the amount for the pass type based on zones
	 * 
	 * @return
	 */
	public abstract int getFare();

}
