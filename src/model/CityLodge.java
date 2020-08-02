package model;


/* Student name: Siqiao Li
 * Student number: s3774940
 * this is a class of CityLodge which contains main menu and 
 * all the functions including add room, rent room, return 
 * room, room maintenance, complete maintenance and display 
 * all rooms. */

import java.util.*;


public class CityLodge {

	// final static variables.
	final static int NUMOFROOMSUITE = 6;
	final static int NUMOFROOMERROR = 0;

	// create a array list of rooms.
	private static ArrayList<Room> rooms = new ArrayList<>();

	// constructor of city lodge.
	public CityLodge() {
		ArrayList<String> roomIDDB = new ArrayList<>();
		roomIDDB = RoomDatabase.selectRoomID();
		for(int i = 0; i < roomIDDB.size(); i++) {
			String ID = roomIDDB.get(i);
			System.out.println(ID);
			Room recordDB = RoomDatabase.getRecord(ID);
			System.out.println(recordDB.getRoomID());
			rooms.add(recordDB);
		}
	}

	public static void addRoom(Room room) {
		rooms.add(room);
	}
	
	// method of check if a room is available.
	public static void checkRentRoom(String roomID) throws RentException {
		
		String status = "";
		// get status of the room
		status = RoomDatabase.getStatus(roomID);
	
		// if the status is available, jump to another window for user to enter information.
		if (status.equals("available")) {
			// set view code is in RentComfirmHandler.java
		} else {
			// if the room is not available, throw an exception.
			throw new RentException(roomID, "not available");
		}

	}

	// check if the rent days are valid for a standard room
	public static int numOfRentDayStand(DateTime rentDate, int rentDays) throws RentException {

		int numOfRentDay = 0;

		String nameOfDay = rentDate.getNameOfDay();

		// if the rent date is during weekend.
		if (nameOfDay.equals("Saturday") || nameOfDay.equals("Sunday")) {
			if (rentDays >= 3 && rentDays <= 10) {
				numOfRentDay = rentDays;

			} else {
				throw new RentException("invalid days for weekend");
			}
		} else {
			// if the rent date is during week days.
			if (rentDays >= 2 && rentDays <= 10) {
				numOfRentDay = rentDays;

			} else {
				throw new RentException("invalid days for weekday");
			}
		}
		return numOfRentDay;
	}

	// check if the rent days are valid for a suite
	public static int numOfRentDaySuite(DateTime rentDate, int rentDays, String roomID) throws RentException {

		int numOfRentDay = 0;
		// if it is a suite, we need to consider the maintenance date.
		java.sql.Date maintenanceDatesql = RoomDatabase.getMaintenDate(roomID);
		Calendar cal = Calendar.getInstance();
		cal.setTime(maintenanceDatesql);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int year = cal.get(Calendar.YEAR);
		DateTime maintenDateDT = new DateTime(day, month, year);
		
		DateTime nextMaintenance = new DateTime(maintenDateDT, 10);
		DateTime estimateReturn = new DateTime(rentDate, rentDays);
		int diff = DateTime.diffDays(nextMaintenance, estimateReturn);
		if (diff > 0) {
			numOfRentDay = rentDays;
		} else {
			throw new RentException("invalid days for suite", nextMaintenance);
			//System.out.printf("Due to maintenance, this suite can only be rented to %s.%n", nextMaintenance);
		}

		return numOfRentDay;
	}
	
	// getter for room array list
	public static ArrayList<Room> getRooms() {
		return rooms;
	}	

	// check if the room is rented
	public static void checkReturnRoom(String roomID) throws ReturnException {

		String status = "";
		// get status of the room
		status = RoomDatabase.getStatus(roomID);

		// if the status is rented, jump to another window for user to enter
		// information.
		if (status.equals("rented")) {
			// set view code is in RentComfirmHandler.java
		} else {
			// if the room is not available, throw an exception.
			throw new ReturnException(roomID, "not rented");
		}			
	}


	// check if the room can be put under maintenance
		public static void checkMaintenRoom(String roomID) throws MaintenException {

			String status = "";
			// get status of the room
			status = RoomDatabase.getStatus(roomID);

			// if the status is available, jump to another window for user to enter
			// information.
			if (status.equals("available")) {
				// set view code is in MaintenComfirmHandler.java
			} else if (status.equals("maintenance")){
				// if the room is already under maintenance, throw an exception
				throw new MaintenException(roomID, "maintenance");
			}else {
				// if the room is rented, throw an exception.
				throw new MaintenException(roomID, "rented");
			}			
		}
	

	// check if the room is under maintenance
	public static void checkMainComRoom(String roomID) throws MaintenException {
		String status = "";
		// get status of the room
		status = RoomDatabase.getStatus(roomID);

		// if the status is maintenance, jump to another window for user to enter
		// information.
		if (status.equals("maintenance")) {
			// set view code is in MainComComfirmHandler.java
		} else {
			// if the room is rented, throw an exception.
			throw new MaintenException(roomID, "not under maintenance");
		}
	}

//	public void displayAllRoom() {
//		for (int i = 0; i < rooms.size(); i++) {
//			System.out.println(rooms.get(i).getDetails());
//		}
//	}

}
