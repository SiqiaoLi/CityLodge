package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import model.CityLodge;
import model.MaintenException;
import model.Room;
import view.AlertBox;
import view.MainComSuccess;

public class MainComSuccessHandler implements EventHandler<ActionEvent> {
	private BorderPane pane;
	private DatePicker mainComDateInput;
	private String roomID;
	
	public MainComSuccessHandler(BorderPane pane, DatePicker mainComDateInput, String roomID) {

		this.pane = pane;
		this.mainComDateInput = mainComDateInput;
		this.roomID = roomID;
	}
	
	@Override
	public void handle(ActionEvent event) {
		try {
			
			// get maintenance completion date and convert it into DateTime type.
			LocalDate date = mainComDateInput.getValue();
			
			// get this room from rooms list
			ArrayList<Room> rooms = new ArrayList<>();
			rooms = CityLodge.getRooms();
			Room room = null;
			for(int i = 0; i < rooms.size(); i++) {
				if (rooms.get(i).getRoomID().equals(roomID)) {
					room = rooms.get(i);
				}
			}	
			
			// return room 
			room.completeMaintenance(date);
			
			// jump to return success page
			pane.setCenter(new MainComSuccess(pane, roomID));

		} catch (MaintenException e) {
			if (e.getReason().compareTo("invalid maintenance completion date") == 0) {

				AlertBox.display("Error", "Invalid maintenance completion Date, please confirm your maintenance completion Date");
			} 
		}

	}
}
