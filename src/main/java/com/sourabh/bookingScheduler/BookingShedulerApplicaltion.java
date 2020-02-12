package com.sourabh.bookingScheduler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.sourabh.bookingScheduler.service.BookingService;

/**
 * Bootstrap class of the application.
 * 
 * functions to read all the input from console and feeds to the application for further procesing.
 * 
 * To Start the Java Application, run the following class as "Run as Application"
 * feed the input in console as 
 *  <start time in hhmm>
    0900 1730
	2011-03-17 10:17:06 EMP001
	2011-03-21 09:00 2
	2011-03-16 12:34:56 EMP002
	2011-03-21 09:00 2
	2011-03-16 09:28:23 EMP003
	2011-03-22 14:00 2
	2011-03-17 11:23:45 EMP004
	2011-03-22 16:00 1
	2011-03-15 17:29:12 EMP005
	2011-03-21 16:00 3
	done  
 * 
 * Note:  Last line should be "done" in order to proceed with the execution.
 * @author Sourabh Kumawat
 *
 */
public class BookingShedulerApplicaltion {
	public static void main(String[] args) throws IOException, ParseException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		// Reading data using readLine
		String officeTimings = reader.readLine();
		List<String> input = new ArrayList<>();

		while (true) {
			String str = reader.readLine();
			if ("done".equals(str)) {
				break;
			} else {
				input.add(str);
			}
		}

		BookingService.processRequest(input, officeTimings);

	}
}
