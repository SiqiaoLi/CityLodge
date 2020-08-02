package model;

public class RentException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String reason;
	private String roomID;
	private DateTime nextMaintendate;
	
	public RentException(String reason) {
		this.reason = reason;
	}
	
	public RentException(String roomID, String reason) {
		this.roomID = roomID;
		this.reason = reason;
	}
	
	public RentException(String reason, DateTime nextMaintendate) {
		this.reason = reason;
		this.nextMaintendate = nextMaintendate;
	} 

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRoomID() {
		return roomID;
	}

	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}

	public DateTime getNextMaintendate() {
		return nextMaintendate;
	}

	public void setNextMaintendate(DateTime nextMaintendate) {
		this.nextMaintendate = nextMaintendate;
	}
	
	
}
