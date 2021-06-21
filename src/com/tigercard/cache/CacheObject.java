package com.tigercard.cache;

import com.tigercard.model.Zone;

public class CacheObject {
	private Zone fromZone;
	private Zone toZone;
	private int amount;

	public CacheObject(Zone fromZone, Zone toZone, int amount) {
		super();
		this.fromZone = fromZone;
		this.toZone = toZone;
		this.amount = amount;
	}

	public Zone getFromZone() {
		return fromZone;
	}

	public Zone getToZone() {
		return toZone;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setFromZone(Zone fromZone) {
		this.fromZone = fromZone;
	}

	public void setToZone(Zone toZone) {
		this.toZone = toZone;
	}

	@Override
	public String toString() {
		return "CacheObject [fromZone=" + fromZone + ", toZone=" + toZone + ", amount=" + amount + "]";
	}

}
