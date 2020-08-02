package view;

import controller.BackMainHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class AvailableMaintenView extends GridPane {
	
	public AvailableMaintenView(BorderPane pane, String roomID) {
		// GridPane with 10px padding around edge
		setPadding(new Insets(10, 10, 10, 10));
		setAlignment(Pos.CENTER);
		setVgap(15);
		setHgap(10);
		
		// Title
		Text title = new Text("Room Maintenance");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		GridPane.setConstraints(title, 0, 0, 2, 1);
		
		// Maintenance
		Text maintenance = new Text("Room " + roomID + " is now under maintenance");
		GridPane.setConstraints(maintenance, 0, 1);
		
		// Back to home page button
		Button backMain = new Button("Back to main page");
		GridPane.setConstraints(backMain, 0, 2);
		
		backMain.setOnAction(new BackMainHandler(pane));
		
		getChildren().addAll(title, maintenance, backMain);
	}
}
