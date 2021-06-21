package com.tigercard.model;

public class PeakFare extends Fare {

	public PeakFare(Zone fromZone, Zone toZone) {
		super(fromZone, toZone);
	}

	@Override
	public int getAmount() {
		if (fromZone.equals(toZone)) {
			if (fromZone.equals(Zone.Zone_1)) {
				return 30;
			} else {
				return 25;
			}
		} else {
			return 35;
		}
	}

}
