package com.sourabh.bookingScheduler.model;

import java.util.Date;

/**
 * Model class for BookingRequest.
 * 
 * @author Sourabh Kumawat
 *
 */
public class BookingRequest implements Comparable<BookingRequest> {

	String employeeId;
	Date requestDate;
	Date bookingDate;
	int timeDuration;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public int getTimeDuration() {
		return timeDuration;
	}

	public void setTimeDuration(int timeDuration) {
		this.timeDuration = timeDuration;
	}

	@Override
	public int compareTo(BookingRequest o) {
		return getRequestDate().compareTo(o.getRequestDate());
	}

	@Override
	public String toString() {
		return "BookingRequest [employeeId=" + employeeId + ", requestDate=" + requestDate + ", bookingDate="
				+ bookingDate + ", timeDuration=" + timeDuration + "]";
	}
}
