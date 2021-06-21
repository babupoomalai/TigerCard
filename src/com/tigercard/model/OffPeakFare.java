package com.tigercard.model;

public class OffPeakFare extends Fare {

	public OffPeakFare(Zone fromZone, Zone toZone) {
		super(fromZone, toZone);
	}

	@Override
	public int getAmount() {
		if (fromZone.equals(toZone)) {
			if (Zone.Zone_1.equals(fromZone)) {
				return 25;
			} else {
				return 20;
			}
		} else {
			return 30;
		}
	}

}
