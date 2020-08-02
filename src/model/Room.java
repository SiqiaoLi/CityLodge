package model;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/* this is a abstract class of room, 
 * which has two subclasses 
 * standard room and suite.	*/

public abstract class Room implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// instance variables in room class.
	private String roomID;
	
	final static int MAXOFRECORD = 10;
	final static int OLDESTRECORD = 0;
	protected ArrayList<Record> records = new ArrayList<Record>(MAXOFRECORD);
	private double rentalRate;
	
	// constructor of room
	public Room(String roomID) {
		this.roomID = roomID;
	}
	
	// rent method called by rentRoom in CityLodge.
	public void rent(String customerId, DateTime rentDate, int numOfRentDay) {

		// set status to Rented.
		RoomDatabase.updateStatus(roomID, "rented");

		// get the estimate return date based on rent date and the number of days.
		DateTime estimatedReturnDate = new DateTime(rentDate, numOfRentDay);

		// create new record for the room.
		addRecord(customerId, roomID, rentDate, estimatedReturnDate);
	}

	// method to add a new record.
	public void addRecord(String customerID, String roomID, DateTime rentDate, 
			DateTime estimatedReturnDate) {
		
		Record record = new Record(customerID, roomID, rentDate, estimatedReturnDate);
		
		// if the size of record exceed 10, then remove the oldest record and add a new one
		if (records.size() < MAXOFRECORD) {
			records.add(record);
			
		} else {
			records.remove(OLDESTRECORD);
			records.add(record);
			
		}
	}
	
	public String exportRecord() {
		String str = "";
		for(int i=0; i < records.size(); i++) {
			
			str = str + records.get(records.size() - 1 - i).toString();
		}
		
		return str;
		
	}
	
	

	// return all the record details of the room.
	public String recordDetails() {
		String recordDetails = "RENTAL RECORD\n";
		String line = "\n--------------------------------------\n";
		for (int i = 0; i < records.size(); i++) {
			recordDetails = recordDetails + records.get(records.size() - 1 - i).getDetails() + line;
		}
		return recordDetails;
	}
	
	// abstract classes.
	public abstract void returnRoom(DateTime returnDate) throws ReturnException;
	public abstract void performMaintenance();
	public abstract void completeMaintenance(LocalDate mainComDate) throws MaintenException;
//	public abstract String getDetails();

	// records getter.
	public ArrayList<Record> getRecords() {
		return records;
	}

	// records setter.
	public void setRecords(ArrayList<Record> records) {
		this.records = records;
	}

	// room ID getter.
	public String getRoomID() {
		return roomID;
	}

	// room ID setter.
	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}

	// rental getter.
	public double getRentalRate() {
		return rentalRate;
	}

	// rental setter.
	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}
	
	
}
