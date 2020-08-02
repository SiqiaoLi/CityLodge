package view;

import java.time.LocalDate;

import controller.AddStandardCheckHandler;
import controller.AddSuiteCheckHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class AddSuite extends GridPane {

	public AddSuite(BorderPane pane) {

		// GridPane with 10px padding around edge
		setPadding(new Insets(10, 10, 10, 10));
		setAlignment(Pos.CENTER);
		setVgap(15);
		setHgap(10);

		// Title
		Text title = new Text("Add a suite");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		GridPane.setConstraints(title, 0, 0, 2, 1);

		// Room ID Label - constrains use (child, column, row)
		Label roomIDLabel = new Label("Room ID:");
		GridPane.setConstraints(roomIDLabel, 0, 1);

		// Room ID Input
		TextField roomIDInput = new TextField();
		roomIDInput.setPromptText("S_xxx(three digit number)");
		GridPane.setConstraints(roomIDInput, 1, 1);

		// Number of rooms Label - constrains use (child, column, row)
		Label numOfRoomLabel = new Label("Number of rooms:");
		GridPane.setConstraints(numOfRoomLabel, 0, 2);

		// Number of rooms Input
		Label numOfRoom = new Label("A Suite always has 6 rooms");
		GridPane.setConstraints(numOfRoom, 1, 2);

		// Maintenance date Label - constrains use (child, column, row)
		Label maintenanceLabel = new Label("Maintenance date:");
		GridPane.setConstraints(maintenanceLabel, 0, 3);

		// Maintenance date input
		DatePicker maintenanceDate = new DatePicker();
		maintenanceDate.setValue(LocalDate.now());
		GridPane.setConstraints(maintenanceDate, 1, 3);

		// Room feature Label
		Label roomFeatureLabel = new Label("Room feature:");
		GridPane.setConstraints(roomFeatureLabel, 0, 4);

		// Room feature Input
		TextArea roomFeatureInput = new TextArea();
		roomFeatureInput.setPrefRowCount(4);
		roomFeatureInput.setPrefColumnCount(20);
		roomFeatureInput.setPromptText("air conditioning, cable TV, WiFi, fridge");
		GridPane.setConstraints(roomFeatureInput, 1, 4);

		// Add room button
		Button addButton = new Button("Add suite");
		GridPane.setConstraints(addButton, 1, 5);

		addButton.setOnAction(new AddSuiteCheckHandler(pane, roomIDInput, 
				maintenanceDate, roomFeatureInput));
		
		getChildren().addAll(title, roomIDLabel, roomIDInput, numOfRoomLabel, numOfRoom, maintenanceLabel, maintenanceDate,
				roomFeatureLabel, roomFeatureInput, addButton);
	}

}
