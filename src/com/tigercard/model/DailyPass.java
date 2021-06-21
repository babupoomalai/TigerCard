package com.tigercard.model;

public class DailyPass extends Pass {

	public DailyPass(Zone fromZone, Zone toZone) {
		super(fromZone, toZone);
	}

	@Override
	public int getFare() {
		if (fromZone.equals(toZone)) {
			if (fromZone.equals(Zone.Zone_1)) {
				return 100;
			} else {
				return 80;
			}
		} else {
			return 120;
		}
	}

}
