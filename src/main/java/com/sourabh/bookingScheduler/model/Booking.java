package com.sourabh.bookingScheduler.model;

import java.util.Date;
/**
 * model class for booking.
 * 
 * @author Sourabh Kumawat
 *
 */
public class Booking {
	String employeeId;
	Date startDate;
	Date endDate;
	
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "Booking [EmployeeId=" + employeeId + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
	

}
