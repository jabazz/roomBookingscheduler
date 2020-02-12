package com.sourabh.bookingScheduler.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.sourabh.bookingScheduler.model.BookingRequest;

public class BookingServiceTest {
	
	@Test
	public void  testAddHoursToJavaUtilDatePositive() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date requestDate = formatter.parse("2011-03-17 10:17:06");
		Date expected =    formatter.parse("2011-03-17 11:17:06");
		Date actualDate = BookingService.addHoursToJavaUtilDate(requestDate, 1);
		Assert.assertEquals(expected, actualDate);
	}
	
	@Test
	public void  testAddHoursToJavaUtilDateNegative() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date requestDate = formatter.parse("2011-03-17 10:17:06");
		Date expected =    formatter.parse("2011-03-17 14:17:06");
		Date actualDate = BookingService.addHoursToJavaUtilDate(requestDate, 1);
		Assert.assertNotEquals(expected, actualDate);
	}
	
	@Test
	public void testInputToRequestMapper() throws ParseException {
		String request = "2011-03-17 10:17:06 EMP001";
		String bookingRequest = "2011-03-21 09:00 2";
		BookingRequest bookingRequestObj  = BookingService.inputToRequestMapper(request, bookingRequest);
		String expectedBooking = "BookingRequest [employeeId=EMP001, requestDate=Thu Mar 17 10:17:06 IST 2011, bookingDate=Mon Mar 21 09:00:00 IST 2011, timeDuration=2]";
		String actualBooking = bookingRequestObj.toString();
		Assert.assertEquals(expectedBooking, actualBooking);
	}
	@Test
	public void testInputToRequestMapperNegative() throws ParseException {
		String request = "2011-03-17 10:17:06 EMP002";
		String bookingRequest = "2011-03-21 09:00 2";
		BookingRequest bookingRequestObj  = BookingService.inputToRequestMapper(request, bookingRequest);
		String expectedBooking = "BookingRequest [employeeId=EMP001, requestDate=Thu Mar 17 10:17:06 IST 2011, bookingDate=Mon Mar 21 09:00:00 IST 2011, timeDuration=2]";
		String actualBooking = bookingRequestObj.toString();
		Assert.assertNotEquals(expectedBooking, actualBooking);
	}
	
	@Test
	public void testCheckIfInOfficeTimings() throws ParseException {
		BookingRequest request = new BookingRequest();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date requestDate = formatter.parse("2011-03-17 10:17:06");
		Date bookingDate = formatter.parse("2011-03-19 10:17:06");
		request.setBookingDate(bookingDate);
		request.setRequestDate(requestDate);
		request.setEmployeeId("E001");
		request.setTimeDuration(2);
		
		String officeTiming = "0900 1750";
		Assert.assertEquals(true,BookingService.checkIfInOfficeTimings(request, officeTiming));
		
	}

}
