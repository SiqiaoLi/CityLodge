package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import model.CityLodge;
import model.DateTime;
import model.InvalidInputException;
import model.RentException;
import model.ReturnException;
import model.Room;
import model.RoomDatabase;
import view.AlertBox;
import view.AvailabeStandView;
import view.AvailabeSuiteView;
import view.RentSuccess;
import view.ReturnSuccess;

public class ReturnSuccessHandler implements EventHandler<ActionEvent>{
	private BorderPane pane;
	private DatePicker returnDate;
	private String roomID;
	
	public ReturnSuccessHandler(BorderPane pane, DatePicker returnDate, String roomID) {

		this.pane = pane;
		this.returnDate = returnDate;
		this.roomID = roomID;
	}
	
	@Override
	public void handle(ActionEvent event) {
		try {
			
			// get return date and convert it into DateTime type.
			LocalDate date = returnDate.getValue();
			int month = date.getMonthValue();
			int day = date.getDayOfMonth();
			int year = date.getYear();
			DateTime returnDateDT = new DateTime(day, month, year);
			
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
			room.returnRoom(returnDateDT);
			// update record in database
			RoomDatabase.updateRecord(roomID, room);
			
			// jump to return success page
			pane.setCenter(new ReturnSuccess(pane, roomID, room));

		} catch (ReturnException e) {
			if (e.getReason().compareTo("invalid return date") == 0) {

				AlertBox.display("Error", "Invalid return Date, please confirm your return Date");
			} 
		}

	}
	
	
	
}
