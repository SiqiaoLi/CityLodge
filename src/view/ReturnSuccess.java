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
import model.Room;

public class ReturnSuccess extends GridPane {
	
	public ReturnSuccess(BorderPane pane, String roomID, Room room) {

		// GridPane with 10px padding around edge
		setPadding(new Insets(10, 10, 10, 10));
		setAlignment(Pos.CENTER);
		setVgap(15);
		setHgap(10);

		// Title
		Text title = new Text("Return room");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		GridPane.setConstraints(title, 0, 0, 2, 1);
		
		Text returnSuccess = new Text("Room " + roomID + " is now returned successfully");
		GridPane.setConstraints(returnSuccess, 0, 1);
		
		// index of latest record
		int recordIndex = room.getRecords().size() - 1;
		// record details
		Text rentDetails = new Text(room.getRecords().get(recordIndex).getDetails());
		GridPane.setConstraints(rentDetails, 0, 2);
		
		Button backMain = new Button("Back to main page");
		GridPane.setConstraints(backMain, 0, 3);
		
		backMain.setOnAction(new BackMainHandler(pane));
		
		getChildren().addAll(title, returnSuccess, rentDetails, backMain);
	}
}
