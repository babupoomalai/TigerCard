package com.tigercard.model;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Commute {

	private DayOfWeek day;

	private Zone fromZone;

	private Zone toZone;

	private LocalTime time;

	public Commute(DayOfWeek day, Zone fromZone, Zone toZone, LocalTime time) {
		super();
		this.day = day;
		this.fromZone = fromZone;
		this.toZone = toZone;
		this.time = time;
	}

	public DayOfWeek getDay() {
		return day;
	}

	public Zone getFromZone() {
		return fromZone;
	}

	public Zone getToZone() {
		return toZone;
	}

	public LocalTime getTime() {
		return time;
	}

	@Override
	public String toString() {
		return "Commute [day=" + day + ", fromZone=" + fromZone + ", toZone=" + toZone + ", time=" + time + "]";
	}

}
