package com.tigercard.service;

import java.time.DayOfWeek;
import java.time.LocalTime;

import com.tigercard.cache.FareCache;
import com.tigercard.helper.PeakHoursFinder;
import com.tigercard.model.Commute;
import com.tigercard.model.DailyPass;
import com.tigercard.model.Fare;
import com.tigercard.model.OffPeakFare;
import com.tigercard.model.PeakFare;
import com.tigercard.model.WeeklyPass;
import com.tigercard.model.Zone;

/**
 * Service class to compute fare
 * 
 * @author bpoomalai
 *
 */
public class FareService {

	private static FareService instance;

	private static FareCache fareCache = FareCache.getInstance();

	public static FareService getInstance() {
		if (instance == null) {
			instance = new FareService();
		}
		return instance;
	}

	/*
	 * Compute the fare for the requested commute trip
	 */
	public int computeFare(Commute commute) {
		if (commute == null) {
			return -1;
		}

		DayOfWeek day = commute.getDay();
		Zone fromZone = commute.getFromZone();
		Zone toZone = commute.getToZone();
		LocalTime time = commute.getTime();

		if (day == null || fromZone == null || toZone == null || time == null) {
			return -1;
		}

		Zone cacheFromZone = fareCache.getFromZone(fromZone, day);
		Zone cacheToZone = fareCache.getToZone(toZone, day);

		boolean isPeakHours = PeakHoursFinder.isPeakHours(time, day);

		// Find the fare of a individual trip
		Fare fareObj;
		if (isPeakHours) {
			fareObj = new PeakFare(fromZone, toZone);
		} else {
			fareObj = new OffPeakFare(fromZone, toZone);
		}
		int commuteFare = fareObj.getAmount();

		// Get the fare paid for today and current week
		int calculatedFare = 0;
		int todayPaidFare = fareCache.getFare(day);
		int weeklyPaidFare = fareCache.getPaidAmountForWeek();

		// Daily pass and Weekly pass for the route user traveled
		DailyPass dailyPass = new DailyPass(cacheFromZone, cacheToZone);
		int dailyPassFare = dailyPass.getFare();
		WeeklyPass weeklyPass = new WeeklyPass(cacheFromZone, cacheToZone);
		int weeklyPassFare = weeklyPass.getFare();

		// Calculate the fare based on Weekly and daily cap
		if (weeklyPaidFare >= weeklyPassFare || todayPaidFare >= dailyPassFare) {
			calculatedFare = 0;
		} else {
			int diffToWeeklyCap = weeklyPassFare - weeklyPaidFare;
			int diffToDailyCap = dailyPassFare - todayPaidFare;

			if (commuteFare <= diffToDailyCap && commuteFare <= diffToWeeklyCap) {
				calculatedFare = commuteFare;
			} else {
				int amntToConsider = diffToDailyCap < diffToWeeklyCap ? diffToDailyCap : diffToWeeklyCap;
				calculatedFare = amntToConsider;
			}
		}
		// Update the cache
		fareCache.putValue(day, fromZone, cacheToZone, calculatedFare);

		return calculatedFare;
	}

	public void clearCache() {
		fareCache.clearCache(true);
	}

	public int getTotalAmountPaid() {
		return fareCache.getTotalAmountPaid();
	}

}
