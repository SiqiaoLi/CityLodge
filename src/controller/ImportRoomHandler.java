package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.CityLodge;
import model.InvalidInputException;
import model.Record;
import model.RoomDatabase;
import model.StandardRoom;
import model.Suite;
import view.AddStandard;
import view.AlertBox;
import view.ImportRoomView;

public class ImportRoomHandler implements EventHandler<ActionEvent> {
	
	private BorderPane pane; 
	private Stage stage;
	
	public ImportRoomHandler(BorderPane pane) {
		this.pane = pane;
	}
	
	@Override
	public void handle(ActionEvent event) {
		// create a file chooser
		FileChooser importFile = new FileChooser();
		importFile.setTitle("Choose a file");
		importFile.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text", "*.txt"));
		
		// select a file and return to a "file"
		File file = importFile.showOpenDialog(stage);	
		
		try {
			FileReader fileReader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(fileReader);
            String nextLine = null;
//            String roomId = "";
//            ArrayList<Record> records = new ArrayList<Record>();
            
            while ((nextLine = buffer.readLine()) != null) {
                if (nextLine.charAt(5) == ':') {
                    addRoom(nextLine);
                } else {
                    addRecord(nextLine);
                }
               
            }
            
            buffer.close();
		} catch (Exception e) {
            e.printStackTrace();
        }
		
		AlertBox.display("Add success", "Rooms added!");
		
	}
	
	public void addRoom(String roomInfo) {
		// get information from this line, -2 split array to many strings
		String[] arrOfInfo = roomInfo.split(":", -2);
		String roomID = arrOfInfo[0];
		int numOfRooms = Integer.parseInt(arrOfInfo[1]); 
		String type = arrOfInfo[2];
		String status = arrOfInfo[3];
		
		if(type.equals("standard")) {
			String feature = arrOfInfo[4];
			String imageName = arrOfInfo[5];
			
			try {
				validateInput(roomID);
			} catch (InvalidInputException e) {
				if (e.getReason().compareTo("roomID exists") == 0) {
					
					AlertBox.display("Error", "This room ID already exists");
			}
			}
			RoomDatabase.insertStandard(roomID, numOfRooms, feature);
			RoomDatabase.updateStatus(roomID, status);
			StandardRoom room = new StandardRoom(roomID);
			CityLodge.addRoom(room);
			RoomDatabase.insertRecord(roomID, room);
			RoomDatabase.insertImageName(roomID, imageName);
			
		} else if(type.equals("suite")){
			
			String datestring = arrOfInfo[4];
			Date maintenDate = Date.valueOf(datestring);
			String feature = arrOfInfo[5];
			String imageName = arrOfInfo[6];
			
			try {
				validateInput(roomID);
			} catch (InvalidInputException e) {
				if (e.getReason().compareTo("roomID exists") == 0) {
					
					AlertBox.display("Error", "This room ID already exists");
			}
			}
			RoomDatabase.insertSuite(roomID, numOfRooms, feature, maintenDate);
			Suite room = new Suite(roomID);
			CityLodge.addRoom(room);
			RoomDatabase.insertRecord(roomID, room);
			imageName = "noimage.jpg";
			RoomDatabase.insertImageName(roomID, imageName);
		}
	}
	
	public void addRecord(String recordInfo) {
		String[] arr = recordInfo.split("_", -2);
		String roomID = arr[0] + arr[1];
		
		String[] arrInfo = recordInfo.split(":", -2);
		String recordID = arrInfo[0];
		String[] recArray = recordID.split("_", -2);
		String cusID = recArray[2];
		String rentDateStr = recArray[3];
		Date rentDate = Date.valueOf(rentDateStr);
		
		String estiReturnDateStr = arrInfo[1];
		Date estiReturnDate = Date.valueOf(estiReturnDateStr);
		
		String actReturnDateStr = arrInfo[2];
		Date actReturnDate = Date.valueOf(actReturnDateStr);
		String rentalFeeStr = arrInfo[3];
		double rentalFee = Double.parseDouble(rentalFeeStr);
		String lateFeeStr = arrInfo[4];
		double lateFee = Double.parseDouble(lateFeeStr);
		
		//Record newRec = new Record(cusID, roomID, rentDate, estiReturnDate, actReturnDate, rentalFee, lateFee);
		
	}
	
	private void validateInput(String roomID) throws InvalidInputException {

		// check if room ID already exits
		ArrayList<String> roomIDArray = new ArrayList<String>();
		roomIDArray = RoomDatabase.selectRoomID();
		
		for(int i = 0; i < roomIDArray.size(); i++) {
			if (roomIDArray.get(i).equals(roomID)) {
				
				throw new InvalidInputException("roomID exists");
			}
		}
	}
	
	
}
