package view;

import java.io.File;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.CityLodge;
import model.MaintenException;
import model.RentException;
import model.ReturnException;
import model.Room;
import model.RoomDatabase;

public class RoomDetailView extends GridPane {
	
	public RoomDetailView(BorderPane pane, Room room) {
		
		// GridPane with 10px padding around edge
		setPadding(new Insets(10, 10, 10, 10));
		setAlignment(Pos.CENTER);
		setVgap(15);
		setHgap(10);
		
		// get image
		String imaName = "src/images/" + RoomDatabase.getImageName(room.getRoomID());
		
		File imageName = new File(imaName);
		Image image = new Image(imageName.toURI().toString());
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(250);
		imageView.setFitWidth(200);
		
		//Records
        Text record = new Text(room.recordDetails());
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(300, 200);
        scrollPane.setContent(record);
        
        // rent the room
        Button rentButton = new Button("Rent");
        rentButton.setMaxWidth(138);
        String roomID = room.getRoomID();
        
        rentButton.setOnAction(e -> {
        	// check if the room is available
        	try {
				CityLodge.checkRentRoom(roomID);
				String rType = RoomDatabase.getType(roomID);
	        	if (rType.equals("standard")) {
	        		// if the standard room is available jump to the rent form (Standard)
					pane.setCenter(new AvailabeStandView(pane, roomID));
	        	} else if (rType.equals("suite")) {
					// if the suite is available jump to the rent form (Suite)
					pane.setCenter(new AvailabeSuiteView(pane, roomID));
				}
			} catch (RentException ex) {
				// if the room is not available, pop up an alert window.
				if (ex.getReason().compareTo("not available") == 0) {
					AlertBox.display("Error", "This room is not available at the moment");
				}
			}
        });
        
        // return the room
        Button returnButton = new Button("Return");
        returnButton.setMaxWidth(138);
        returnButton.setOnAction(e -> {
        	// check if the room is rented
        	try {
        		CityLodge.checkReturnRoom(roomID);
        		pane.setCenter(new AvailableReturnView(pane, roomID));
        		
			} catch (ReturnException ex) {
				// if the room is not rented, pop up an alert window.
				if (ex.getReason().compareTo("not available") == 0) {
					AlertBox.display("Error", "This room is not rented at the moment");
				}
			}	
        });
        
        // perform maintenance
        Button maintenanceButton = new Button("Maintenance");
        maintenanceButton.setMaxWidth(138);
        maintenanceButton.setOnAction(e -> {
        	// check if the room is rented
        	try {
        		CityLodge.checkMaintenRoom(roomID);
        		room.performMaintenance();
    			pane.setCenter(new AvailableMaintenView(pane, roomID));
    			
			} catch (MaintenException ex) {
				// if the room is not available, pop up an alert window.
				if (ex.getReason().compareTo("maintenance") == 0) {
					// if the room is under maintenance, a alert window would pop up
					AlertBox.display("Error", "This room is already under maintenance");
				} else if (ex.getReason().compareTo("rented") == 0) {
					// if the room is rented, a alert window would pop up
					AlertBox.display("Error", "This room is rented");
				}
			}
        });
        
        // complete maintenance
        Button MainComButton = new Button("Complete Maintenance");
        MainComButton.setMaxWidth(138);
        MainComButton.setOnAction(e -> {
        	
        	try {
        		// check if the room is under maintenance
    			CityLodge.checkMainComRoom(roomID);
    			pane.setCenter(new AvailableMainComView(pane, roomID));
			} catch (MaintenException ex) {
				// if the room is not under maintenance, pop up an alert window.
				if (ex.getReason().compareTo("not under maintenance") == 0) {
					// if the room is under maintenance, a alert window would pop up
					AlertBox.display("Error", "This room is not under maintenance");
				} 
			}
        });
        
        // back to main page
        Button backButton = new Button("Back to main page");
        backButton.setMaxWidth(138);
        backButton.setOnAction(e -> pane.setCenter(new MainPageContent(pane)));
        
        // create a vbox to hold all the button
        VBox vBox = new VBox();
        vBox.setPrefSize(150, 200);
        vBox.setSpacing(20.0);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(rentButton, returnButton, maintenanceButton, 
        		MainComButton, backButton);
        
        // hold picture, button, and room details
        
        setAlignment(Pos.CENTER);
        add(imageView, 0, 0);
        add(scrollPane, 1, 0);
        add(vBox, 2, 0);
		
	}
}
