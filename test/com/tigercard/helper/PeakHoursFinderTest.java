package com.tigercard.helper;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.DayOfWeek;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class PeakHoursFinderTest {

	/*
	 * Test Peak and off peak hours for weekdays
	 */
	@Test
	void testWeekdays() {
		assertTrue(PeakHoursFinder.isPeakHours(LocalTime.parse("20:00"), DayOfWeek.MONDAY));
		assertTrue(PeakHoursFinder.isPeakHours(LocalTime.parse("17:00"), DayOfWeek.TUESDAY));
		assertTrue(PeakHoursFinder.isPeakHours(LocalTime.parse("10:30"), DayOfWeek.WEDNESDAY));
		assertTrue(PeakHoursFinder.isPeakHours(LocalTime.parse("07:00"), DayOfWeek.THURSDAY));
		assertTrue(PeakHoursFinder.isPeakHours(LocalTime.parse("20:00"), DayOfWeek.FRIDAY));

		assertFalse(PeakHoursFinder.isPeakHours(LocalTime.parse("20:01"), DayOfWeek.MONDAY));
		assertFalse(PeakHoursFinder.isPeakHours(LocalTime.parse("16:59"), DayOfWeek.TUESDAY));
		assertFalse(PeakHoursFinder.isPeakHours(LocalTime.parse("10:31"), DayOfWeek.WEDNESDAY));
		assertFalse(PeakHoursFinder.isPeakHours(LocalTime.parse("06:59"), DayOfWeek.THURSDAY));
		assertFalse(PeakHoursFinder.isPeakHours(LocalTime.parse("20:01"), DayOfWeek.FRIDAY));
	}

	/*
	 * Test peak and off peak hours for weekends
	 */
	@Test
	void testWeekEnds() {
		assertTrue(PeakHoursFinder.isPeakHours(LocalTime.parse("22:00"), DayOfWeek.SATURDAY));
		assertTrue(PeakHoursFinder.isPeakHours(LocalTime.parse("18:00"), DayOfWeek.SUNDAY));
		assertTrue(PeakHoursFinder.isPeakHours(LocalTime.parse("11:00"), DayOfWeek.SUNDAY));
		assertTrue(PeakHoursFinder.isPeakHours(LocalTime.parse("09:00"), DayOfWeek.SUNDAY));
		assertTrue(PeakHoursFinder.isPeakHours(LocalTime.parse("22:00"), DayOfWeek.SUNDAY));

		assertFalse(PeakHoursFinder.isPeakHours(LocalTime.parse("22:01"), DayOfWeek.SATURDAY));
		assertFalse(PeakHoursFinder.isPeakHours(LocalTime.parse("17:59"), DayOfWeek.SUNDAY));
		assertFalse(PeakHoursFinder.isPeakHours(LocalTime.parse("11:01"), DayOfWeek.SATURDAY));
		assertFalse(PeakHoursFinder.isPeakHours(LocalTime.parse("08:59"), DayOfWeek.SUNDAY));
		assertFalse(PeakHoursFinder.isPeakHours(LocalTime.parse("22:01"), DayOfWeek.SUNDAY));
	}

}
