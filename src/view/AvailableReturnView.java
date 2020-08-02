package view;

import java.time.LocalDate;

import controller.ReturnSuccessHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class AvailableReturnView extends GridPane {

	public AvailableReturnView(BorderPane pane, String roomID) {
		// GridPane with 10px padding around edge
		setPadding(new Insets(10, 10, 10, 10));
		setAlignment(Pos.CENTER);
		setVgap(15);
		setHgap(10);

		// Title
		Text title = new Text("Return room");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		GridPane.setConstraints(title, 0, 0, 2, 1);

		// Return date Label - constrains use (child, column, row)
		Label returnDateLabel = new Label("Check-out:");
		GridPane.setConstraints(returnDateLabel, 0, 1);

		// Return date input
		DatePicker returnDateInput = new DatePicker();
		returnDateInput.setValue(LocalDate.now());
		GridPane.setConstraints(returnDateInput, 1, 1);

		// Return room button
		Button returnButton = new Button("Submit");
		GridPane.setConstraints(returnButton, 1, 2);

		returnButton.setOnAction(new ReturnSuccessHandler(pane, returnDateInput, roomID));
		
		getChildren().addAll(title, returnDateLabel, returnDateInput, returnButton);
	}
}
