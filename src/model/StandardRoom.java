package model;

import java.time.LocalDate;

/* this is standard room class which is the subclass of room*/

public class StandardRoom extends Room {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -777366704709596310L;
	// final static variables.
	final static int RATEOFONEROOM = 59;
	final static int RATEOFTWOROOM = 99;
	final static int RATEOFFOURROOM = 199;
	final static double LATERATE = 1.35;
	final static int NOLATEFEE = 0;
	
	// constructor of standard room.
	public StandardRoom(String roomID) {
		super(roomID);
	}
	
	// method to return standard room.
	public void returnRoom(DateTime returnDate) throws ReturnException {
			
			// check how many days the room has been rented.
			int numberOfDays = DateTime.diffDays(returnDate, 
					records.get(records.size()-1).getRentDate());
			
			if (numberOfDays >= 0) {
				
				// set status to Available.
				RoomDatabase.updateStatus(super.getRoomID(), "available");
				
				// update actual return day in record.
				records.get(records.size()-1).setActualReturnDate(returnDate);
				
				// call method to calculate rent fee and update it in record.
				records.get(records.size()-1).setRentalFee(standardRentFee(numberOfDays));
				
				// call method to check if late fee is needed.
				records.get(records.size()-1).setLateFee(shtandardLateFee(returnDate));
			
			} else {
				// if rent days less than zero, throw return exception
				throw new ReturnException(super.getRoomID(), "invalid return date");	
			}
	}
	
	
	// method to calculate rent fee of a standard room.
	public double standardRentFee(int numberOfDays) {
		int NumberOfRoom = RoomDatabase.getNumOfRoom(super.getRoomID());
		// return rent fee of one bedroom standard room.
		if(NumberOfRoom == 1) {
			return RATEOFONEROOM * numberOfDays;
		
		// return rent fee of two bedrooms standard room.
		} else if(NumberOfRoom == 2) {
			return RATEOFTWOROOM * numberOfDays;
			
		// return rent fee of four bedroom standard room.
		} else {
			return RATEOFFOURROOM * numberOfDays;
		}
	}
	
	
	// method to calculate late fee of a standard room.
	public double shtandardLateFee(DateTime returnDate) {
		int NumberOfRoom = RoomDatabase.getNumOfRoom(super.getRoomID());
		// get how many days after the estimated return date.
		int lateDay = DateTime.diffDays(returnDate, records.get(records.size()-1).getEstimatedReturnDate());
		if (lateDay > 0) {
			// return late fee of one bedroom standard room.
			if(NumberOfRoom == 1) {
				return RATEOFONEROOM * LATERATE * lateDay;
			
			// return late fee of two bedrooms standard room.
			} else if(NumberOfRoom == 2) {
				return RATEOFTWOROOM * LATERATE * lateDay;
				
			// return late fee of four bedroom standard room.
			} else {
				return RATEOFFOURROOM * LATERATE * lateDay;
			}
		} else {
			
			// no late fee.
			return NOLATEFEE;
		}
	}
	
	public void performMaintenance() {
		RoomDatabase.updateStatus(super.getRoomID(), "maintenance");
	}
	
	
	// method to complete maintenance.
	public void completeMaintenance(LocalDate mainComDate) {
		// if the room is under maintenance, then set status to "Available".
		RoomDatabase.updateStatus(super.getRoomID(), "available");
	}
	
	
	// method return the room detail in a line.
	public String toString() {
		return String.format("%s:%d:%s:%s:%s", super.getRoomID(), RoomDatabase.getNumOfRoom(super.getRoomID())
				,RoomDatabase.getType(super.getRoomID()), RoomDatabase.getStatus(super.getRoomID()), RoomDatabase.getFeature(super.getRoomID()));
	}
	
	
//	// method return the room details and record details in format.
//	public String getDetails() {
//		
//		// create a room details in format.
//		String roomDetails = String.format("Room ID:              %s\n"
//										 + "Number of bedrooms:   %d\n"
//										 + "Type:                 %s\n"
//										 + "Status:               %s\n"
//										 + "Feature summary:      %s\n", 
//				super.getRoomID(), super.getNumberOfRoom(), super.getType()
//				, super.getStatus(), super.getFeature());
//		
//		// if the record size is zero, then record details should be "empty".
//		if(super.getRecords().size() == 0) {
//			String line = "\n--------------------------------------\n";
//			return roomDetails + String.format("RENTAL RECORD:        %s", "empty") + line;
//			
//		} else {
//			return roomDetails + recordDetails();
//		}
//	}
//	

}
