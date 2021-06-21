package com.tigercard.model;

public class WeeklyPass extends Pass {

	public WeeklyPass(Zone fromZone, Zone toZone) {
		super(fromZone, toZone);
	}

	@Override
	public int getFare() {
		if (fromZone.equals(toZone)) {
			if (fromZone.equals(Zone.Zone_1)) {
				return 500;
			} else {
				return 400;
			}
		} else {
			return 600;
		}
	}

}
