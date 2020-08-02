package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import model.CityLodge;
import model.InvalidInputException;
import model.RoomDatabase;
import model.Suite;
import view.AddSuccess;
import view.AlertBox;

public class AddSuiteCheckHandler implements EventHandler<ActionEvent> {

	private BorderPane pane;
	private TextField roomIDInput;
	private TextArea roomFeatureInput;
	private DatePicker maintenanceDate;
	private static final int ROOMNUM = 6;

	public AddSuiteCheckHandler(BorderPane pane, TextField roomIDInput, 
			DatePicker maintenanceDate, TextArea roomFeatureInput) {
		
		this.pane = pane;
		this.roomIDInput = roomIDInput;
		this.maintenanceDate = maintenanceDate;
		this.roomFeatureInput = roomFeatureInput;
	}

	@Override
	public void handle(ActionEvent event) {

		try {
			validateInput();

			String roomID = roomIDInput.getText();
			LocalDate Date = maintenanceDate.getValue();
			java.sql.Date maintenDate = java.sql.Date.valueOf(Date);
			
			RoomDatabase.insertSuite(roomID, ROOMNUM, roomFeatureInput.getText(), maintenDate);
			Suite room = new Suite(roomID);
			CityLodge.addRoom(room);
			RoomDatabase.insertRecord(roomID, room);
			String imageName = "noimage.jpg";
			RoomDatabase.insertImageName(roomID, imageName);
			
			pane.setCenter(new AddSuccess(pane, roomID));

		} catch (InvalidInputException e) {
			if (e.getReason().compareTo("Empty fields") == 0) {

				AlertBox.display("Error", "Please enter all the information");
				
			} else if (e.getReason().compareTo("Invalid room ID") == 0) {

				AlertBox.display("Error", "Please enter valid room ID");
				
			} else if (e.getReason().compareTo("roomID exists") == 0) {
				
				AlertBox.display("Error", "This room ID already exists");
			}
		}

	}

	private void validateInput() throws InvalidInputException {

		// check if user fill out all the information
		boolean empty = roomIDInput.getText().isEmpty() || roomFeatureInput.getText().isEmpty();

		// check if room ID match the format
		Pattern p = Pattern.compile("S_([0-9]{3})");
		Matcher m = p.matcher(roomIDInput.getText());

		boolean invalid = !(m.find() && m.group().equals(roomIDInput.getText()));

		if (empty) {
			throw new InvalidInputException("Empty fields");
		}

		if (invalid) {
			throw new InvalidInputException("Invalid room ID");
		}

		// check if room ID already exits
		ArrayList<String> roomIDArray = new ArrayList<String>();
		roomIDArray = RoomDatabase.selectRoomID();
		
		for(int i = 0; i < roomIDArray.size(); i++) {
			if (roomIDArray.get(i).equals(roomIDInput.getText())) {
				throw new InvalidInputException("roomID exists");
				//System.out.println(roomIDArray.get(i));
			}
		}
	}

}
