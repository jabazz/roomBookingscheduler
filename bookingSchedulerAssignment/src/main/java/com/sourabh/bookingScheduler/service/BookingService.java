package com.sourabh.bookingScheduler.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.sourabh.bookingScheduler.model.Booking;
import com.sourabh.bookingScheduler.model.BookingRequest;

public class BookingService {

	/**
	 * prime method of service class takes all the input from driver class and 
	 * process the request.
	 * @param input  - booking requests
	 * @param Officetimings
	 * @throws ParseException
	 */
	public static void processRequest(List<String> input, String Officetimings) throws ParseException {

		List<BookingRequest> bookingReqList = new ArrayList<>();
		for (int i = 0; i < input.size(); i = i + 2) {
			bookingReqList.add(inputToRequestMapper(input.get(i), input.get(i + 1)));
		}
		
		//sorting the requests according to chronology. 
		Collections.sort(bookingReqList);
		
		// for confirming the booking
		confirmBookingRequests(bookingReqList, Officetimings);
		
		//for printingg the calender
		BookingCalender.printCalander();
		
	}
	/**
	 * method compares the requested booking timing with existing booking timings for a specific day
	 * and ignores the booking request if booking already exists for the interval  
	 * if booking is not present in the system confirms the slot for the requester.
	 * 
	 * @param bookingReqList - all booking request. 
	 * @param timings  - office timing.
	 * @throws ParseException
	 */
	public static void confirmBookingRequests(List<BookingRequest> bookingReqList, String timings)
			throws ParseException {

		for (BookingRequest request : bookingReqList) {
			String empoyeeId = request.getEmployeeId();
			Date bookDate = request.getBookingDate();
			int duration = request.getTimeDuration();
			boolean isDateBlocked = false;
			if (checkIfInOfficeTimings(request, timings)) {
				Booking book;
				book = new Booking();
				book.setEmployeeId(empoyeeId);
				book.setStartDate(bookDate);
				book.setEndDate(addHoursToJavaUtilDate(bookDate, duration));

				String BookingDateKey = new SimpleDateFormat("MM-dd-yyyy").format(bookDate);
				List<Booking> employeebookinglist = new ArrayList<>();
				/*
				 * condition checks if the booking request dates availabe in system 
				 * if yes then puts the confirms the booking slot.
				 * else ignores the request.
				 * 
				 */
				if (!BookingCalender.fixedBooking.containsKey(BookingDateKey)) {
					employeebookinglist.add(book);
					BookingCalender.fixedBooking.put(BookingDateKey, employeebookinglist);
				} else {
					employeebookinglist = BookingCalender.fixedBooking.get(BookingDateKey);
					for (Booking already : employeebookinglist) { 
						if ((book.getStartDate().before(already.getStartDate())) && (book.getEndDate().before(already.getStartDate()))){	
						}
						else if((book.getStartDate().compareTo(already.getEndDate())>=0)) {
						}
						else {
							isDateBlocked = true;
							break;
						}
					}
					if (!isDateBlocked) {
						employeebookinglist.add(book);
					}
				}
			}
		}

	}

	/**
	 * method checks if booking request falls in office timing or not.
	 * 
	 * @param request - booking request
	 * @param timings - office timings
	 * @return true - if booking request is valid 
	 * 			false - if booking request is not valid.
	 * 
	 * @throws ParseException
	 */
	public static boolean checkIfInOfficeTimings(BookingRequest request, String timings) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("HHmm");
		Date officeStartTime = formatter.parse(timings.split(" ")[0]);
		Date officeEndTime = formatter.parse(timings.split(" ")[1]);
		String startDateString = request.getBookingDate().toString().substring(11, 16).replace(":", "");
		String endDateString = addHoursToJavaUtilDate(request.getBookingDate(), request.getTimeDuration())
				.toString().substring(11, 16).replace(":", "");
		Date startDate = formatter.parse(startDateString);
		Date endDate = formatter.parse(endDateString);
		if ((startDate.compareTo(officeStartTime) >= 0)&& endDate.before(officeEndTime)) {
			return true;
		}
		return false;
	}
	
	/**
	 *  method maps the input to {@link BookingRequest} object.
	 *  
	 * @param requestString, having request date for booking along with the requester Employee Id.
	 * 			eg: 2011-03-17 10:17:06 EMP001
	 * @param bookString, date and time for booking request and duration for booking.	
	 *			eg: 2011-03-21 09:00 2
	 *
	 * @return {@link BookingRequest} object mapped with requested parameters.
	 * @throws ParseException
	 */
	public static BookingRequest inputToRequestMapper(String requestString, String bookString) throws ParseException {
		String[] request = requestString.split(" ");
		String[] booking = bookString.split(" ");
		BookingRequest bookingReq = new BookingRequest();

		// 2011-03-17 10:17:06 EMP001
		// 2011-03-21 09:00 2
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date requestDate = formatter.parse(request[0] + " " + request[1]);
		Date bookingDate = formatter.parse(booking[0] + " " + booking[1] + ":00");
		bookingReq.setRequestDate(requestDate);

		bookingReq.setEmployeeId(request[2]);

		bookingReq.setBookingDate(bookingDate);

		bookingReq.setTimeDuration(Integer.parseInt(booking[2]));

		return bookingReq;

	}
	
	/**
	 * increases number of hours in given input date.
	 * 
	 * @param date
	 * @param hours to be added to given date.
	 * @return date with increase hours.
	 */
	public static Date addHoursToJavaUtilDate(Date date, int hours) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, hours);
		return calendar.getTime();
	}
}
