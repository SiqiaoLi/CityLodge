package controller;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import model.CityLodge;
import model.DateTime;
import model.InvalidInputException;
import model.RentException;
import model.RoomDatabase;
import view.AlertBox;
import view.RentSuccess;

public class RentSuiteSucessHandler implements EventHandler<ActionEvent> {
	
	private BorderPane pane;
	private TextField cusIDInput;
	private DatePicker rentDate;
	private TextField numberOfDaysInput;
	private String roomID;
	
	// constructor
	public RentSuiteSucessHandler(BorderPane pane, String roomID, TextField cusIDInput, 
			DatePicker rentDate,TextField numberOfDaysInput) {
		this.pane = pane;
		this.roomID = roomID;
		this.cusIDInput = cusIDInput;
		this.rentDate = rentDate;
		this.numberOfDaysInput = numberOfDaysInput;
	}
	
	@Override
	public void handle(ActionEvent event) {
		try {
			// call validate method to check if user input is valid
			validateInput();
			
			// get customer ID the user entered
			String cusID = cusIDInput.getText();
			
			// get rent date and convert it into DateTime type.
			LocalDate Date = rentDate.getValue();
			java.sql.Date rentDateSql = java.sql.Date.valueOf(Date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(rentDateSql);
			int month = cal.get(Calendar.MONTH);
			int day = cal.get(Calendar.DAY_OF_MONTH);
			int year = cal.get(Calendar.YEAR);
			DateTime rentDateDT = new DateTime(day, month, year);
			
			// get number of days the user entered
			String days = numberOfDaysInput.getText();
			int numOfDays = Integer.parseInt(days);

			// check if the number of days is valid
			CityLodge.numOfRentDaySuite(rentDateDT, numOfDays, roomID);
			
			// find the room from rooms list and set it rented and add record
			for(int i = 0; i < CityLodge.getRooms().size(); i++) {
				if(CityLodge.getRooms().get(i).getRoomID().equals(roomID)) {
					CityLodge.getRooms().get(i).rent(cusID, rentDateDT, numOfDays);
					// update record in database
					RoomDatabase.updateRecord(roomID, CityLodge.getRooms().get(i));
				}
			}
		
			// jump to rent success page
			pane.setCenter(new RentSuccess(pane, roomID, cusID));

		} catch (InvalidInputException e) {
			if (e.getReason().compareTo("Empty fields") == 0) {

				AlertBox.display("Error", "Please enter all the information");
				
			} else if (e.getReason().compareTo("Invalid room ID") == 0) {

				AlertBox.display("Error", "Please enter valid room ID");
				
			} else if (e.getReason().compareTo("roomID exists") == 0) {
				
				AlertBox.display("Error", "This room ID already exists");
			}
		}catch(NumberFormatException e) {
			// if the number of days is not a integer, pop up alert window
			AlertBox.display("Error", "Please enter an integer for rent days");
		} catch (RentException e) {
			if (e.getReason().compareTo("invalid days for suite") == 0) {

				AlertBox.display("Error", "Due to maintenance, this suite can only be rented to " + e.getNextMaintendate());
			}
		}

	}

	private void validateInput() throws InvalidInputException {

		// check if user fill out all the information
		boolean empty = cusIDInput.getText().isEmpty() || numberOfDaysInput.getText().isEmpty();

		// check if customer ID match the format
		Pattern p = Pattern.compile("CUS([0-9]{3})");
		Matcher m = p.matcher(cusIDInput.getText());
		boolean invalid = !(m.find() && m.group().equals(cusIDInput.getText()));

		// if user did not enter all the information, throw an exception
		if (empty) {
			throw new InvalidInputException("Empty fields");
		}

		// if the customer id is invalid, throw a exception
		if (invalid) {
			throw new InvalidInputException("Invalid customer ID");
		}
	}

}
