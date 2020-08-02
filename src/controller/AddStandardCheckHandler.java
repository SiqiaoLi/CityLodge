package controller;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import model.CityLodge;
import model.InvalidInputException;
import model.RoomDatabase;
import model.StandardRoom;
import view.AddSuccess;
import view.AlertBox;

public class AddStandardCheckHandler implements EventHandler<ActionEvent> {

	private BorderPane pane;
	private TextField roomIDInput;
	private ToggleGroup group;
	private TextArea roomFeatureInput;

	public AddStandardCheckHandler(BorderPane pane, TextField roomIDInput, ToggleGroup group,
			TextArea roomFeatureInput) {
		this.pane = pane;
		this.roomIDInput = roomIDInput;
		this.group = group;
		this.roomFeatureInput = roomFeatureInput;
	}

	@Override
	public void handle(ActionEvent event) {

		try {
			validateInput();

			String roomID = roomIDInput.getText();
			String result = ((RadioButton) group.getSelectedToggle()).getText();
			int numOfRoom = Integer.parseInt(result);

			RoomDatabase.insertStandard(roomID, numOfRoom, roomFeatureInput.getText());
			StandardRoom room = new StandardRoom(roomID);
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
		Pattern p = Pattern.compile("R_([0-9]{3})");
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
			}
		}
	}

}
