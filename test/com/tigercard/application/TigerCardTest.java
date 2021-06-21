package com.tigercard.application;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tigercard.model.Commute;
import com.tigercard.model.Zone;
import com.tigercard.service.FareService;

public class TigerCardTest {

	@BeforeEach
	void clearCache() {
		FareService.getInstance().clearCache();
	}

	@Test
	void testComputeFareNullList() {
		assertEquals(new TigerCard().computeFare(null), 0);
	}

	@Test
	void testComputeFareEmptyObject() {
		assertEquals(new TigerCard().computeFare(new ArrayList<>()), 0);
	}

	@Test
	void testComputeFareInvalidValues() {
		List<Commute> commuteList = new ArrayList<>();

		commuteList.add(null);
		commuteList.add(new Commute(null, null, null, null));
		commuteList.add(new Commute(DayOfWeek.MONDAY, null, null, null));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, null, null));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, null));

		assertEquals(new TigerCard().computeFare(new ArrayList<>()), 0);
	}

	@Test
	void testComputeFareSingleDayDifferetZone() {
		List<Commute> commuteList = new ArrayList<>();

		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_1, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_2, LocalTime.parse("19:00")));

		assertEquals(new TigerCard().computeFare(commuteList), 120);
	}

	@Test
	void testComputeFareSingleDayZone1() {
		List<Commute> commuteList = new ArrayList<>();

		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("19:45")));

		assertEquals(new TigerCard().computeFare(commuteList), 100);
	}

	@Test
	void testComputeFareSingleDayZone2() {
		List<Commute> commuteList = new ArrayList<>();

		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("16:15")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:45")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("19:45")));

		assertEquals(new TigerCard().computeFare(commuteList), 80);
	}

	@Test
	void testComputeFareSingleDayWithInvalidEntries() {
		List<Commute> commuteList = new ArrayList<>();

		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:45")));
		commuteList.add(null);
		commuteList.add(new Commute(null, null, null, null));
		commuteList.add(new Commute(DayOfWeek.MONDAY, null, null, null));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, null, null));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, null));

		assertEquals(new TigerCard().computeFare(commuteList), 45);
	}

	@Test
	void testComputeFareSingleWeekZone1() {
		List<Commute> commuteList = new ArrayList<>();

		// Monday 100
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45")));

		// Tuesday 100
		commuteList.add(new Commute(DayOfWeek.TUESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.TUESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.TUESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15")));
		commuteList.add(new Commute(DayOfWeek.TUESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.TUESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45")));

		// Wed 100
		commuteList.add(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15")));
		commuteList.add(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45")));

		// Thurs 100
		commuteList.add(new Commute(DayOfWeek.THURSDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.THURSDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.THURSDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15")));
		commuteList.add(new Commute(DayOfWeek.THURSDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.THURSDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45")));

		// Fri 100
		commuteList.add(new Commute(DayOfWeek.FRIDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.FRIDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.FRIDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15")));
		commuteList.add(new Commute(DayOfWeek.FRIDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.FRIDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45")));

		// Sat 60
		commuteList.add(new Commute(DayOfWeek.SATURDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.SATURDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.SATURDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15")));

		// Sun
		commuteList.add(new Commute(DayOfWeek.SUNDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.SUNDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45")));

		assertEquals(new TigerCard().computeFare(commuteList), 500);
	}

	@Test
	void testComputeFareTwoWeeksZone1() {
		List<Commute> commuteList = new ArrayList<>();

		// Monday 100
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45")));

		// Tuesday 100
		commuteList.add(new Commute(DayOfWeek.TUESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.TUESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.TUESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15")));
		commuteList.add(new Commute(DayOfWeek.TUESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.TUESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45")));

		// Wed 100
		commuteList.add(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15")));
		commuteList.add(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45")));

		// Thurs 100
		commuteList.add(new Commute(DayOfWeek.THURSDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.THURSDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.THURSDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15")));
		commuteList.add(new Commute(DayOfWeek.THURSDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.THURSDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45")));

		// Fri 100
		commuteList.add(new Commute(DayOfWeek.FRIDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.FRIDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.FRIDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15")));
		commuteList.add(new Commute(DayOfWeek.FRIDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.FRIDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45")));

		// Sat 60
		commuteList.add(new Commute(DayOfWeek.SATURDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.SATURDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.SATURDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15")));

		// Sun
		commuteList.add(new Commute(DayOfWeek.SUNDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.SUNDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45")));

		// Monday Next week 100
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45")));

		assertEquals(new TigerCard().computeFare(commuteList), 600);
	}

	@Test
	void testComputeFareSingleWeekZone2() {
		List<Commute> commuteList = new ArrayList<>();

		// Monday 80
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("16:15")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:45")));

		// Tuesday 80
		commuteList.add(new Commute(DayOfWeek.TUESDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.TUESDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.TUESDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("16:15")));
		commuteList.add(new Commute(DayOfWeek.TUESDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.TUESDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:45")));

		// Wed 80
		commuteList.add(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("16:15")));
		commuteList.add(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:45")));

		// Thurs 80
		commuteList.add(new Commute(DayOfWeek.THURSDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.THURSDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.THURSDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("16:15")));
		commuteList.add(new Commute(DayOfWeek.THURSDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.THURSDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:45")));

		// Fri 80
		commuteList.add(new Commute(DayOfWeek.FRIDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.FRIDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.FRIDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("16:15")));
		commuteList.add(new Commute(DayOfWeek.FRIDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.FRIDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:45")));

		// Sat 0
		commuteList.add(new Commute(DayOfWeek.SATURDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.SATURDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.SATURDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("16:15")));

		// Sun
		commuteList.add(new Commute(DayOfWeek.SUNDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.SUNDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:45")));

		assertEquals(new TigerCard().computeFare(commuteList), 400);
	}

	@Test
	void testComputeFareTwoWeeksZone2() {
		List<Commute> commuteList = new ArrayList<>();

		// Monday 80
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("16:15")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:45")));

		// Tuesday 80
		commuteList.add(new Commute(DayOfWeek.TUESDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.TUESDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.TUESDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("16:15")));
		commuteList.add(new Commute(DayOfWeek.TUESDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.TUESDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:45")));

		// Wed 80
		commuteList.add(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("16:15")));
		commuteList.add(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:45")));

		// Thurs 80
		commuteList.add(new Commute(DayOfWeek.THURSDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.THURSDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.THURSDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("16:15")));
		commuteList.add(new Commute(DayOfWeek.THURSDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.THURSDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:45")));

		// Fri 80
		commuteList.add(new Commute(DayOfWeek.FRIDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.FRIDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.FRIDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("16:15")));
		commuteList.add(new Commute(DayOfWeek.FRIDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.FRIDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:45")));

		// Sat 0
		commuteList.add(new Commute(DayOfWeek.SATURDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.SATURDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.SATURDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("16:15")));

		// Sun
		commuteList.add(new Commute(DayOfWeek.SUNDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.SUNDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("18:45")));

		// Monday Next week 45
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("10:45")));

		assertEquals(new TigerCard().computeFare(commuteList), 445);
	}

	@Test
	void testComputeFareSinlgeWeekDifferentZone() {
		List<Commute> commuteList = new ArrayList<>();

		// Monday 100
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45")));

		// Tuesday 100
		commuteList.add(new Commute(DayOfWeek.TUESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.TUESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.TUESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15")));
		commuteList.add(new Commute(DayOfWeek.TUESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.TUESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45")));

		// Wed 100
		commuteList.add(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15")));
		commuteList.add(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45")));

		// Thurs 100
		commuteList.add(new Commute(DayOfWeek.THURSDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.THURSDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.THURSDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15")));
		commuteList.add(new Commute(DayOfWeek.THURSDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.THURSDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45")));

		// Fri 100
		commuteList.add(new Commute(DayOfWeek.FRIDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.FRIDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.FRIDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15")));
		commuteList.add(new Commute(DayOfWeek.FRIDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.FRIDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45")));

		// Sat 0
		commuteList.add(new Commute(DayOfWeek.SATURDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.SATURDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45")));

		// Sun 120
		commuteList.add(new Commute(DayOfWeek.SUNDAY, Zone.Zone_1, Zone.Zone_2, LocalTime.parse("09:00")));
		commuteList.add(new Commute(DayOfWeek.SUNDAY, Zone.Zone_2, Zone.Zone_1, LocalTime.parse("11:00")));
		commuteList.add(new Commute(DayOfWeek.SUNDAY, Zone.Zone_1, Zone.Zone_2, LocalTime.parse("16:15")));
		commuteList.add(new Commute(DayOfWeek.SUNDAY, Zone.Zone_2, Zone.Zone_1, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.SUNDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45")));
		commuteList.add(new Commute(DayOfWeek.SUNDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("19:45")));

		assertEquals(new TigerCard().computeFare(commuteList), 600);

	}

	@Test
	void testComputeFareTwoWeeksDifferentZone() {
		List<Commute> commuteList = new ArrayList<>();

		// Monday 100
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45")));

		// Tuesday 100
		commuteList.add(new Commute(DayOfWeek.TUESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.TUESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.TUESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15")));
		commuteList.add(new Commute(DayOfWeek.TUESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.TUESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45")));

		// Wed 100
		commuteList.add(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15")));
		commuteList.add(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.WEDNESDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45")));

		// Thurs 100
		commuteList.add(new Commute(DayOfWeek.THURSDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.THURSDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.THURSDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15")));
		commuteList.add(new Commute(DayOfWeek.THURSDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.THURSDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45")));

		// Fri 100
		commuteList.add(new Commute(DayOfWeek.FRIDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.FRIDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45")));
		commuteList.add(new Commute(DayOfWeek.FRIDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("16:15")));
		commuteList.add(new Commute(DayOfWeek.FRIDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.FRIDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45")));

		// Sat 0
		commuteList.add(new Commute(DayOfWeek.SATURDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.SATURDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45")));

		// Sun 100
		commuteList.add(new Commute(DayOfWeek.SUNDAY, Zone.Zone_1, Zone.Zone_2, LocalTime.parse("09:00")));
		commuteList.add(new Commute(DayOfWeek.SUNDAY, Zone.Zone_2, Zone.Zone_1, LocalTime.parse("11:00")));
		commuteList.add(new Commute(DayOfWeek.SUNDAY, Zone.Zone_1, Zone.Zone_2, LocalTime.parse("16:15")));
		commuteList.add(new Commute(DayOfWeek.SUNDAY, Zone.Zone_2, Zone.Zone_1, LocalTime.parse("18:15")));
		commuteList.add(new Commute(DayOfWeek.SUNDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("18:45")));
		commuteList.add(new Commute(DayOfWeek.SUNDAY, Zone.Zone_2, Zone.Zone_2, LocalTime.parse("19:45")));

		// Monday Next week
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:20")));
		commuteList.add(new Commute(DayOfWeek.MONDAY, Zone.Zone_1, Zone.Zone_1, LocalTime.parse("10:45")));

		assertEquals(new TigerCard().computeFare(commuteList), 655);

	}

}
