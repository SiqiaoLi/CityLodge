package controller;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import model.CityLodge;
import model.InvalidInputException;
import model.MaintenException;
import model.Room;
import model.RoomDatabase;
import view.AlertBox;
import view.AvailableMainComView;
import view.AvailableMaintenView;

public class MainComComfirmHandler implements EventHandler<ActionEvent> {

	private BorderPane pane;
	private TextField roomIDInput;

	public MainComComfirmHandler(BorderPane pane, TextField roomIDInput) {
		this.pane = pane;
		this.roomIDInput = roomIDInput;
	}

	@Override
	public void handle(ActionEvent event) {
		try {
			// check if the input is valid
			validateInput();

			String roomID = roomIDInput.getText();

			// check if the room is under maintenance
			CityLodge.checkMainComRoom(roomID);

			pane.setCenter(new AvailableMainComView(pane, roomID));

		} catch (InvalidInputException e) {
			// if not all the information is filled out, an alert window will pop up
			if (e.getReason().compareTo("Empty fields") == 0) {
				AlertBox.display("Error", "Please enter all the information");

				// if the room id is invalid, an alert window will pop up
			} else if (e.getReason().compareTo("Invalid room ID") == 0) {
				AlertBox.display("Error", "Please enter valid room ID");

				// if there is no such room, an alert window will pop up
			} else if (e.getReason().compareTo("no such room") == 0) {
				AlertBox.display("Error", "There is no such room!");
			}
		} catch (MaintenException e) {
			// if the room is not under maintenance, pop up an alert window.
			if (e.getReason().compareTo("not under maintenance") == 0) {
				// if the room is under maintenance, a alert window would pop up
				AlertBox.display("Error", "This room is not under maintenance");
			} 
		}
	}

	// method to check if the user input is valid or not
	private void validateInput() throws InvalidInputException {

		// check if user fill out all the information
		boolean empty = roomIDInput.getText().isEmpty();

		// check if room ID match the format
		Pattern p = Pattern.compile("[RS]{1}_([0-9]{3})");
		Matcher m = p.matcher(roomIDInput.getText());
		boolean invalid = !(m.find() && m.group().equals(roomIDInput.getText()));

		// if not all the information is filled out, throw an exception
		if (empty) {
			throw new InvalidInputException("Empty fields");
		}

		// if the room ID is invalid, throw an exception
		if (invalid) {
			throw new InvalidInputException("Invalid room ID");
		}

		// check if there is such room
		ArrayList<String> roomIDArray = new ArrayList<String>();
		roomIDArray = RoomDatabase.selectRoomID();
		int match = 0;

		for (int i = 0; i < roomIDArray.size(); i++) {
			if (roomIDArray.get(i).equals(roomIDInput.getText())) {
				match++;
			}
		}

		// if there is no such room, throw an exception
		if (match == 0) {
			throw new InvalidInputException("no such room");
		}
	}
}
