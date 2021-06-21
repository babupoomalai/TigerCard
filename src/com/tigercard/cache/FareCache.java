package com.tigercard.cache;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

import com.tigercard.model.Zone;

/**
 * Cache class to maintain the fare paid by user so far
 * 
 * @author bpoomalai
 *
 */
public class FareCache {

	private static FareCache instance;

	private Map<DayOfWeek, CacheObject> fareMap = new HashMap<>();

	private int amountPaidTillPastWeek = 0;

	private DayOfWeek FIRST_DAY = DayOfWeek.MONDAY;

	public static FareCache getInstance() {
		if (instance == null) {
			instance = new FareCache();
		}
		return instance;
	}

	/*
	 * Get the fare paid for requested day
	 */
	public int getFare(DayOfWeek day) {
		// Rehash cache if new week starts
		checkForNewWeekStart(day);

		if (fareMap.containsKey(day)) {
			return fareMap.get(day).getAmount();
		}
		return 0;
	}

	/*
	 * Get total amount paid for the commute
	 */
	public int getTotalAmountPaid() {
		return amountPaidTillPastWeek + getPaidAmountForWeek();
	}

	/**
	 * Get the amount paid in current week
	 * 
	 * @return computed fare
	 */
	public int getPaidAmountForWeek() {
		int paidAmount = 0;

		for (DayOfWeek day : fareMap.keySet()) {
			CacheObject cacheObject = fareMap.get(day);
			paidAmount += cacheObject.getAmount();
		}

		return paidAmount;
	}

	/**
	 * 
	 * Update the cache value with the fare value
	 * 
	 * @param day
	 * @param fromZone
	 * @param toZone
	 * @param fare
	 */
	public void putValue(DayOfWeek day, Zone fromZone, Zone toZone, int fare) {
		// Rehash cache if new week starts
		checkForNewWeekStart(day);

		CacheObject cacheObject;
		if (fareMap.containsKey(day)) {
			cacheObject = updateZone(day, toZone);
		} else {
			cacheObject = new CacheObject(fromZone, toZone, 0);
		}

		cacheObject.setAmount(cacheObject.getAmount() + fare);

		fareMap.put(day, cacheObject);
	}

	/*
	 * Get the start zone for the day based on already traveled trips
	 */
	public Zone getFromZone(Zone fromZone, DayOfWeek day) {
		CacheObject cacheObject = fareMap.get(day);
		if (cacheObject == null) {
			return fromZone;
		}

		return cacheObject.getFromZone();
	}

	/*
	 * Get the end zone for the day based on already traveled trips
	 */
	public Zone getToZone(Zone toZone, DayOfWeek day) {
		CacheObject cacheObject = fareMap.get(day);
		if (cacheObject == null) {
			return toZone;
		}

		if (isZoneDifferent(toZone, cacheObject)) {
			return toZone;
		} else {
			return cacheObject.getToZone();
		}
	}

	/*
	 * Clear cache
	 */
	public void clearCache(boolean isReset) {
		this.fareMap = new HashMap<>();
		if (isReset) {
			amountPaidTillPastWeek = 0;
		}
	}

	/**
	 * Check if requested zone is different from the cache value
	 * 
	 * @param toZone
	 * @param cacheObject
	 * @return
	 */
	private boolean isZoneDifferent(Zone toZone, CacheObject cacheObject) {
		return !cacheObject.getToZone().equals(toZone) && cacheObject.getFromZone().equals(cacheObject.getToZone());
	}

	/**
	 * Update zone if its different from the cache value
	 * 
	 * @param day
	 * @param toZone
	 * @return
	 */
	private CacheObject updateZone(DayOfWeek day, Zone toZone) {
		CacheObject cacheObject = fareMap.get(day);
		// Check if incoming zone is different compared to stored value
		if (isZoneDifferent(toZone, cacheObject)) {
			cacheObject.setToZone(toZone);
		}
		return cacheObject;
	}

	/**
	 * Check if the new week has started and reset cache
	 * 
	 * @param day
	 */
	private void checkForNewWeekStart(DayOfWeek day) {
		if (isWeekStart(day) && fareMap.size() > 1 && fareMap.containsKey(FIRST_DAY)) {
			// Move the values in cache to amountPaidTillPastWeek
			amountPaidTillPastWeek += fareMap.values().stream().mapToInt(obj -> obj.getAmount()).sum();

			clearCache(false);
		}
	}

	/**
	 * Check if the day is week start
	 * 
	 * @param day
	 * @return
	 */
	private boolean isWeekStart(DayOfWeek day) {
		return FIRST_DAY.equals(day);
	}

}
