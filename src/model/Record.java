package model;
/* this is the record class which store the 
 * information of a room */

import java.io.Serializable;

public class Record implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// instance variables.
	private String recordID;
	private String customerID;
	private DateTime rentDate;
	private DateTime estimatedReturnDate;
	private DateTime actualReturnDate;
	private double rentalFee;
	private double lateFee;
	private String roomID;
	
	// constructor of the record.
	public Record(String customerID, String roomID, DateTime rentDate, DateTime estimatedReturnDate, 
			DateTime actualReturnDate, double rentalFee, double lateFee) {
		this.roomID = roomID;
		this.recordID = roomID + "_" + customerID + "_" + rentDate;
		this.rentDate = rentDate;
		this.estimatedReturnDate = estimatedReturnDate;
		this.actualReturnDate = actualReturnDate;
		this.rentalFee = rentalFee;
		this.lateFee = lateFee;
	}
	
	// second constructor 
	public Record(String customerID, String roomID, DateTime rentDate, DateTime estimatedReturnDate) {
		this(customerID, roomID, rentDate, estimatedReturnDate, null, 0, 0);
	}
	
	
	// method to return a string of the record details.
	public String toString() {
		if (actualReturnDate == null) {
			return String.format("%s:%s:%s:none:none:none\n",
					 recordID, rentDate, estimatedReturnDate);
		} else {
			return String.format("%s:%s:%s:%s:%.2f:%.2f\n",
					 recordID, rentDate, estimatedReturnDate, actualReturnDate, rentalFee, lateFee);
		}
	}
	
	// method to return a formated string of the record details.
	public String getDetails() {
		if (actualReturnDate == null) {
			return String.format("Record ID:             %s\n"
							   + "Rent Date:             %s\n"
							   + "Estimated Return Date: %s\n",
							   recordID, rentDate, estimatedReturnDate);
		} else {
			return String.format("Record ID:             %s\n"
					           + "Rent Date:             %s\n"
					           + "Estimated Return Date: %s\n"
					           + "Actural Return Date:   %s\n"
					           + "Rental Fee:            %.2f\n"
					           + "Late Fee:              %.2f\n",
					 recordID, rentDate, estimatedReturnDate, actualReturnDate, rentalFee, lateFee);
		}
	}

	// rent date getter.
	public DateTime getRentDate() {
		return rentDate;
	}

	// rent date setter.
	public void setActualReturnDate(DateTime actualReturnDate) {
		this.actualReturnDate = actualReturnDate;
	}

	// rental fee setter.
	public void setRentalFee(double rentalFee) {
		this.rentalFee = rentalFee;
	}

	// late fee setter.
	public void setLateFee(double lateFee) {
		this.lateFee = lateFee;
	}

	// customer id getter.
	public String getCustomerID() {
		return customerID;
	}

	// customer id setter.
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	// estimate return date getter.
	public DateTime getEstimatedReturnDate() {
		return estimatedReturnDate;
	}

	// estimate return date setter.
	public void setEstimatedReturnDate(DateTime estimatedReturnDate) {
		this.estimatedReturnDate = estimatedReturnDate;
	}

	// rent date setter.
	public void setRentDate(DateTime rentDate) {
		this.rentDate = rentDate;
	}

	public String getRoomID() {
		return roomID;
	}

	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}

	
}
