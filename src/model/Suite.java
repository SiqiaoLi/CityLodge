package model;

import java.sql.Date;
import java.time.LocalDate;

/* this is the suite class which 
 *  is a subclass of suite */

public class Suite extends Room {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2182114066453716361L;

	
	// final static variables. 
	final static int NUMBEROFROOM = 6;
	final static int SUITERATE = 999;
	final static int NOLATEFEES = 0;
	final static double LATEFEESUIT = 1099;
	
	// constructor of suite.
	public Suite(String roomID) {
		super(roomID);
	}
	
	// method to return suite.
	public void returnRoom(DateTime returnDate) throws ReturnException {

		// check how many days the room has been rented.
		int numberOfDays = DateTime.diffDays(returnDate, records.get(records.size() - 1).getRentDate());

		if (numberOfDays >= 0) {

			// set status to Available.
			RoomDatabase.updateStatus(super.getRoomID(), "available");

			// update actual return day in record.
			records.get(records.size() - 1).setActualReturnDate(returnDate);

			// call method to calculate rent fee and update it in record.
			records.get(records.size() - 1).setRentalFee(suiteRentFee(numberOfDays));

			// call method to check if late fee is needed.
			records.get(records.size() - 1).setLateFee(suiteLateFee(returnDate));

		} else {
			// if rent days less than zero, throw return exception
			throw new ReturnException(super.getRoomID(), "invalid return date");
		}
	}
	
	// calculate rent fee of a suite.
	public double suiteRentFee(int numberOfDays) {
		return SUITERATE * numberOfDays;
	}
	
	// calculate late fee of a suite.
	public double suiteLateFee(DateTime returnDate) {
		
		// get estimated return date.
		DateTime estimatedReturnDate = records.get(records.size()-1).getEstimatedReturnDate();
		
		// compare actual return date and estimated return date.
		int lateDay = DateTime.diffDays(returnDate, estimatedReturnDate);
		
		// return late fee.
		if (lateDay > 0) {
			return LATEFEESUIT * lateDay;
		} else {
			return NOLATEFEES;
		}
	}
	
	// method to perform maintenance for a suite.
	public void performMaintenance() {
			
		// set status to maintenance.
		RoomDatabase.updateStatus(super.getRoomID(), "maintenance");
			
		// set today as maintenance date.
		LocalDate today = LocalDate.now();
		java.sql.Date maintenDate = java.sql.Date.valueOf(today);
		RoomDatabase.updateMaintenDate(super.getRoomID(), maintenDate);
	}
	
	// method to complete maintenance.
	public void completeMaintenance(LocalDate mainComDate) throws MaintenException{
		
		// get when maintenance started and check how many days the room has been under maintenance.
		Date mainDate = RoomDatabase.getMaintenDate(super.getRoomID());
		LocalDate mainDateLD = mainDate.toLocalDate();
		int month = mainDateLD.getMonthValue();
		int day = mainDateLD.getDayOfMonth();
		int year = mainDateLD.getYear();
		DateTime mainDateDT = new DateTime(day, month, year);
		
		int monthMCD = mainComDate.getMonthValue();
		int dayMCD = mainComDate.getDayOfMonth();
		int yearMCD = mainComDate.getYear();
		DateTime mainComDateDT = new DateTime(dayMCD, monthMCD, yearMCD);
		
		int numberOfDays = DateTime.diffDays(mainComDateDT, mainDateDT);

		if (numberOfDays >= 0) {

			// if the room is under maintenance, then set status to "Available".
			RoomDatabase.updateStatus(super.getRoomID(), "available");
			java.sql.Date mainComDatesql = java.sql.Date.valueOf(mainComDate);
			RoomDatabase.updateMaintenDate(super.getRoomID(), mainComDatesql);

		} else {
			// if rent days less than zero, throw return exception
			throw new MaintenException(super.getRoomID(), "invalid maintenance completion date");
		}
		
	}
	
	// method return the suite detail in a line.
	public String toString() {
		return String.format("%s:%d:%s:%s:%s:%s", super.getRoomID(), RoomDatabase.getNumOfRoom(super.getRoomID())
				,RoomDatabase.getType(super.getRoomID()), RoomDatabase.getStatus(super.getRoomID()), RoomDatabase.getMaintenDate(super.getRoomID()), RoomDatabase.getFeature(super.getRoomID()));
	}
	
//	// method return the suite details and record details in format.
//	public String getDetails() {
//		
//		// create a room details in format.
//		String roomDetails = String.format("Room ID:              %s\n"
//										 + "Number of bedrooms:   %d\n"
//										 + "Type:                 %s\n"
//										 + "Status:               %s\n"
//										 + "Last maintenance date:%s\n"
//										 + "Feature summary:      %s\n", 
//				super.getRoomID(), super.getNumberOfRoom(), super.getType()
//				, super.getStatus(), maintenanceDate, super.getFeature());
//		
//		// if the record size is zero, then record details should be "empty".
//		if(super.getRecords().size() == 0) {
//			String line = "\n--------------------------------------\n";
//			return roomDetails + String.format("RENTAL RECORD:        %s", "empty") + line;
//		} else {
//			return roomDetails + recordDetails();
//		}
//	}

	
}
