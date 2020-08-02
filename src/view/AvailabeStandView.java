package view;

import java.time.LocalDate;

import controller.RentStandSucessHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class AvailabeStandView extends GridPane {

	public AvailabeStandView(BorderPane pane, String roomID) {
		// GridPane with 10px padding around edge
		setPadding(new Insets(10, 10, 10, 10));
		setAlignment(Pos.CENTER);
		setVgap(15);
		setHgap(10);

		// Title
		Text title = new Text("Rent room");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		GridPane.setConstraints(title, 0, 0, 2, 1);

		// Customer ID Label - constrains use (child, column, row)
		Label cusIDLabel = new Label("Customer ID:");
		GridPane.setConstraints(cusIDLabel, 0, 1);

		// Customer ID Input
		TextField cusIDInput = new TextField();
		cusIDInput.setPromptText("CUSxxx(three digit number)");
		GridPane.setConstraints(cusIDInput, 1, 1);

		// Rent date Label - constrains use (child, column, row)
		Label rentDateLabel = new Label("Check-in:");
		GridPane.setConstraints(rentDateLabel, 0, 2);

		// Rent date input
		DatePicker rentDateInput = new DatePicker();
		rentDateInput.setValue(LocalDate.now());
		GridPane.setConstraints(rentDateInput, 1, 2);

		// Rent days Label - constrains use (child, column, row)
		Label numberOfDaysLabel = new Label("How many days ? :");
		GridPane.setConstraints(numberOfDaysLabel, 0, 3);

		// Rent days Input
		TextField numberOfDaysInput = new TextField();
		numberOfDaysInput.setPromptText("");
		GridPane.setConstraints(numberOfDaysInput, 1, 3);
		
		// description
		Text text = new Text();
		text.setText("Check-in:\nMon - Fri: 2-10 days\nSat or Sun: 3-10 days");
		GridPane.setConstraints(text, 0, 4, 2, 1);
		
		// Rent room button
		Button rentButton = new Button("Submit");
		GridPane.setConstraints(rentButton, 1, 5);
		
		rentButton.setOnAction(new RentStandSucessHandler(pane, roomID, cusIDInput, rentDateInput, numberOfDaysInput));
		
		getChildren().addAll(title, cusIDLabel, cusIDInput, rentDateLabel, rentDateInput,
				numberOfDaysLabel, numberOfDaysInput, text, rentButton);
		
	}
}
