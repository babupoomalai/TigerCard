package com.tigercard.helper;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class PeakHoursFinder {

	public static boolean isPeakHours(LocalTime localTime, DayOfWeek day) {
		LocalTime weekdayBatch1StartTime = LocalTime.parse("07:00").minusMinutes(1);
		LocalTime weekdayBatch1EndTime = LocalTime.parse("10:30");
		LocalTime weekdayBatch2StartTime = LocalTime.parse("17:00").minusMinutes(1);
		LocalTime weekdayBatch2EndTime = LocalTime.parse("20:00");

		LocalTime weekendBatch1StartTime = LocalTime.parse("09:00").minusMinutes(1);
		LocalTime weekendBatch1EndTime = LocalTime.parse("11:00");
		LocalTime weekendBatch2StartTime = LocalTime.parse("18:00").minusMinutes(1);
		LocalTime weekendBatch2EndTime = LocalTime.parse("22:00");

		if (isWeekday(day)) {
			if ((localTime.isAfter(weekdayBatch1StartTime) && localTime.isBefore(weekdayBatch1EndTime))
					|| (localTime.isAfter(weekdayBatch2StartTime) && localTime.isBefore(weekdayBatch2EndTime))) {
				return true;
			} else {
				return false;
			}
		} else {
			if ((localTime.isAfter(weekendBatch1StartTime) && localTime.isBefore(weekendBatch1EndTime))
					|| (localTime.isAfter(weekendBatch2StartTime) && localTime.isBefore(weekendBatch2EndTime))) {
				return true;
			} else {
				return false;
			}
		}
	}

	private static boolean isWeekday(DayOfWeek day) {
		if (DayOfWeek.SATURDAY.equals(day) || DayOfWeek.SUNDAY.equals(day)) {
			return false;
		}
		return true;
	}

}
