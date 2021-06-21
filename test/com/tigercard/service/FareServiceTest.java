package com.tigercard.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.DayOfWeek;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tigercard.model.Commute;
import com.tigercard.model.Zone;

public class FareServiceTest {

	@BeforeEach
	void clearCache() {
		FareService.getInstance().clearCache();
	}

	/*
	 * Trip involves with different zone and on Week start
	 */
	@Test
	void testWeekStartDayFareDifferentZone() {
		FareService fareService = FareService.getInstance();

		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_1, LocalTime.parse("10:20"))), 35);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15"))), 30);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_2, LocalTime.parse("18:15"))), 5);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:55"))), 0);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("19:15"))), 0);
	}

	/*
	 * Trip involves with same zone - 1 and on week start
	 */
	@Test
	void testWeekStartDayFareZone1() {
		FareService fareService = FareService.getInstance();

		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20"))), 30);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15"))), 20);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45"))), 0);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_2, LocalTime.parse("19:45"))), 20);
	}

	/*
	 * Trip involves with same zone - 2 and on week start
	 */
	@Test
	void testWeekStartDayFareZone2() {
		FareService fareService = FareService.getInstance();

		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:20"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:45"))), 20);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("16:15"))), 20);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:15"))), 15);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:25"))), 0);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45"))), 30);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("19:45"))), 10);
	}

	@Test
	void testWholeWeekFareDifferentZone() {
		FareService fareService = FareService.getInstance();

		// Monday 120
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_1, LocalTime.parse("10:20"))), 35);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15"))), 30);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_2, LocalTime.parse("18:15"))), 5);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:55"))), 0);

		// Tuesday 100
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.TUESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20"))), 30);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.TUESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.TUESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.TUESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15"))), 20);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.TUESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45"))), 0);

		// Wed 80
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:20"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:45"))), 20);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("16:15"))), 20);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:15"))), 15);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:25"))), 0);

		// Thurs 120
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.THURSDAY, Zone.Zone_2, Zone.Zone_1, LocalTime.parse("10:20"))), 35);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.THURSDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.THURSDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.THURSDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15"))), 30);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.THURSDAY, Zone.Zone_1, Zone.Zone_2, LocalTime.parse("18:15"))), 5);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.THURSDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:55"))), 0);

		// Fri 120
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.FRIDAY, Zone.Zone_2, Zone.Zone_1, LocalTime.parse("10:20"))), 35);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.FRIDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.FRIDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.FRIDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15"))), 30);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.FRIDAY, Zone.Zone_1, Zone.Zone_2, LocalTime.parse("18:15"))), 5);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.FRIDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:55"))), 0);

		// Sat 60
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.SATURDAY, Zone.Zone_2, Zone.Zone_1, LocalTime.parse("10:20"))), 35);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.SATURDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.SATURDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15"))), 0);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.SATURDAY, Zone.Zone_1, Zone.Zone_2, LocalTime.parse("18:15"))), 0);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.SATURDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:55"))), 0);

		// Monday Next week
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_1, LocalTime.parse("10:20"))), 35);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45"))), 25);

	}

	@Test
	void testWholeWeekFareSameZone() {
		FareService fareService = FareService.getInstance();

		// Monday 100
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20"))), 30);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15"))), 20);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45"))), 0);

		// Tuesday 100
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.TUESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20"))), 30);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.TUESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.TUESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.TUESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15"))), 20);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.TUESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45"))), 0);

		// Wed 100
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20"))), 30);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15"))), 20);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45"))), 0);

		// Thurs 100
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.THURSDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20"))), 30);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.THURSDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.THURSDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.THURSDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15"))), 20);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.THURSDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45"))), 0);

		// Fri 100
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.FRIDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20"))), 30);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.FRIDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.FRIDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.FRIDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15"))), 20);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.FRIDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45"))), 0);

		// Sat 60
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.SATURDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20"))), 0);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.SATURDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45"))), 0);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.SATURDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15"))), 0);

		// Sun
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.SUNDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15"))), 0);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.SUNDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45"))), 0);

		// Monday Next week
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20"))), 30);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45"))), 25);
	}

	@Test
	void testWeeklyPassZone1thenTravelDiffZone() {
		FareService fareService = FareService.getInstance();

		// Monday 100
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20"))), 30);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15"))), 20);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45"))), 0);

		// Tuesday 100
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.TUESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20"))), 30);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.TUESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.TUESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.TUESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15"))), 20);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.TUESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45"))), 0);

		// Wed 100
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20"))), 30);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15"))), 20);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45"))), 0);

		// Thurs 100
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.THURSDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20"))), 30);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.THURSDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.THURSDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.THURSDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15"))), 20);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.THURSDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45"))), 0);

		// Fri 100
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.FRIDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20"))), 30);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.FRIDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.FRIDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.FRIDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15"))), 20);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.FRIDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45"))), 0);

		// Sat 0
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.SATURDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20"))), 0);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.SATURDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45"))), 0);

		// Sun 100
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.SUNDAY, Zone.Zone_1, Zone.Zone_2, LocalTime.parse("09:00"))), 35);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.SUNDAY, Zone.Zone_2, Zone.Zone_1, LocalTime.parse("11:00"))), 30);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.SUNDAY, Zone.Zone_1, Zone.Zone_2, LocalTime.parse("16:15"))), 30);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.SUNDAY, Zone.Zone_2, Zone.Zone_1, LocalTime.parse("18:15"))), 5);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.SUNDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45"))), 0);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.SUNDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("19:45"))), 0);

		// Monday Next week
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20"))), 30);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45"))), 25);
	}

	@Test
	void testWeeklyPassZone2ThenTravelDiffZone() {
		FareService fareService = FareService.getInstance();

		// Monday 80
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:20"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:45"))), 20);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("16:15"))), 20);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:15"))), 15);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:45"))), 0);

		// Tuesday 80
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.TUESDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:20"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.TUESDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:45"))), 20);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.TUESDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("16:15"))), 20);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.TUESDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:15"))), 15);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.TUESDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:45"))), 0);

		// Wed 80
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:20"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:45"))), 20);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("16:15"))), 20);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:15"))), 15);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:45"))), 0);

		// Thurs 80
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.THURSDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:20"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.THURSDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:45"))), 20);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.THURSDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("16:15"))), 20);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.THURSDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:15"))), 15);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.THURSDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:45"))), 0);

		// Fri 80
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.FRIDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:20"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.FRIDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:45"))), 20);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.FRIDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("16:15"))), 20);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.FRIDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:15"))), 15);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.FRIDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:45"))), 0);

		// Sat 0
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.SATURDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:20"))), 0);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.SATURDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:45"))), 0);

		// Sun 120
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.SUNDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("09:00"))), 0);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.SUNDAY, Zone.Zone_2, Zone.Zone_1, LocalTime.parse("11:00"))), 30);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.SUNDAY, Zone.Zone_1, Zone.Zone_2, LocalTime.parse("16:15"))), 30);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.SUNDAY, Zone.Zone_2, Zone.Zone_1, LocalTime.parse("18:15"))), 35);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.SUNDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.SUNDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("19:45"))), 0);

		// Monday Next week
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:20"))), 25);
		assertEquals(fareService
				.computeFare(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:45"))), 20);
	}

}
