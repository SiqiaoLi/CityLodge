package view;

import controller.MainComComfirmHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MaintenCompleteCheck extends GridPane {

	public MaintenCompleteCheck(BorderPane pane) {
		// GridPane with 10px padding around edge
		setPadding(new Insets(10, 10, 10, 10));
		setAlignment(Pos.CENTER);
		setVgap(15);
		setHgap(10);

		// Title
		Text title = new Text("Complete maintenance");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		GridPane.setConstraints(title, 0, 0, 2, 1);

		// Room ID Label - constrains use (child, column, row)
		Label roomIDLabel = new Label("Room ID:");
		GridPane.setConstraints(roomIDLabel, 0, 1);

		// Room ID Input
		TextField roomIDInput = new TextField();
		roomIDInput.setPromptText("enter room ID");
		GridPane.setConstraints(roomIDInput, 1, 1);

		// Check room button
		Button checkButton = new Button("Check");
		GridPane.setConstraints(checkButton, 1, 2);

		checkButton.setOnAction(new MainComComfirmHandler(pane, roomIDInput));

		getChildren().addAll(title, roomIDLabel, roomIDInput, checkButton);
	}
}
