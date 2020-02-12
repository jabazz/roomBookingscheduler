package com.sourabh.bookingScheduler.service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.sourabh.bookingScheduler.model.Booking;

/**
 * Class replicates database with static map.
 * 
 * @author Sourabh Kumawat
 *
 */
public class BookingCalender {
	public static Map<String, List<Booking>> fixedBooking = new HashMap<>();

	/**
	 * prints the content of the map
	 */
	public static void printCalander() {
		for (Entry<String, List<Booking>> entry : fixedBooking.entrySet()) {
			System.out.println(entry.getKey());
			for (Booking book : entry.getValue()) {
				System.out.println(new SimpleDateFormat("HH:mm").format(book.getStartDate()) + " "
						+ new SimpleDateFormat("HH:mm").format(book.getEndDate()) + " " + book.getEmployeeId());
			}
		}

	}

}
