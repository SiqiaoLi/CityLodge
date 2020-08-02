package model;

public class MaintenException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String reason;
	private String roomID;
	
	public MaintenException(String reason) {
		this.reason = reason;
	}
	
	public MaintenException(String roomID, String reason) {
		this.roomID = roomID;
		this.reason = reason;
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

}
