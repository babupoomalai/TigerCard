package com.tigercard.application;

import java.util.List;

import com.tigercard.model.Commute;
import com.tigercard.service.FareService;

/**
 * Entry class for the application
 * 
 * @author bpoomalai
 *
 */
public class TigerCard {

	FareService fareService = FareService.getInstance();

	/**
	 * Compute fare for all trips requested and return the total fare need to be
	 * paid
	 * 
	 * @param commuteList
	 * @return
	 */
	public int computeFare(List<Commute> commuteList) {
		if (commuteList == null || commuteList.size() == 0) {
			return 0;
		}

		for (Commute commute : commuteList) {
			fareService.computeFare(commute);
		}

		return fareService.getTotalAmountPaid();
	}

}
