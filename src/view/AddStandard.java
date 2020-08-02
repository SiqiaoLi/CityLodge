package view;

import controller.AddStandardCheckHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class AddStandard extends GridPane {
	
	public AddStandard(BorderPane pane) {
		
		// GridPane with 10px padding around edge
		setPadding(new Insets(10, 10, 10, 10));
		setAlignment(Pos.CENTER);
		setVgap(15);
		setHgap(10);

		// Title
		Text title = new Text("Add a standard room");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		GridPane.setConstraints(title, 0, 0, 2, 1);
		
		// Room ID Label - constrains use (child, column, row)
		Label roomIDLabel = new Label("Room ID:");
		GridPane.setConstraints(roomIDLabel, 0, 1);

		// Room ID Input
		TextField roomIDInput = new TextField();
		roomIDInput.setPromptText("R_xxx(three digit number)");
		GridPane.setConstraints(roomIDInput, 1, 1);

		// Number of rooms Label - constrains use (child, column, row)
		Label numOfRoomLabel = new Label("Number of rooms:");
		GridPane.setConstraints(numOfRoomLabel, 0, 2);

		// Number of rooms Input
		HBox numOfRoomInput = new HBox(5);
		numOfRoomInput.setPadding(new Insets(10));
		
		ToggleGroup group = new ToggleGroup();
		
		RadioButton rb1 = new RadioButton("1");
		rb1.setToggleGroup(group);
		rb1.setSelected(true);
		
		RadioButton rb2 = new RadioButton("2");
		rb2.setToggleGroup(group);
		
		RadioButton rb3 = new RadioButton("4");
		rb3.setToggleGroup(group);
		
		numOfRoomInput.getChildren().addAll(rb1, rb2, rb3);
		GridPane.setConstraints(numOfRoomInput, 1, 2);

		// Room feature Label
		Label roomFeatureLabel = new Label("Room feature:");
		GridPane.setConstraints(roomFeatureLabel, 0, 3);

		// Room feature Input
		TextArea roomFeatureInput = new TextArea();
		roomFeatureInput.setPrefRowCount(4);
		roomFeatureInput.setPrefColumnCount(20);
		roomFeatureInput.setPromptText("air conditioning, cable TV, WiFi, fridge");
		GridPane.setConstraints(roomFeatureInput, 1, 3);
		
		// Add room button
		Button addButton = new Button("Add standard room");
		GridPane.setConstraints(addButton, 1, 4);
		
		addButton.setOnAction(new AddStandardCheckHandler(pane, roomIDInput, 
				group, roomFeatureInput));
		
		getChildren().addAll(title, roomIDLabel, roomIDInput, 
        		numOfRoomLabel, numOfRoomInput, roomFeatureLabel, roomFeatureInput, addButton);
	}
}
