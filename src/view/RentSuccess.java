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

public class RentSuccess extends GridPane {

	public RentSuccess(BorderPane pane, String roomID, String cusID) {

		// GridPane with 10px padding around edge
		setPadding(new Insets(10, 10, 10, 10));
		setAlignment(Pos.CENTER);
		setVgap(15);
		setHgap(10);

		// Title
		Text title = new Text("Rent room");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		GridPane.setConstraints(title, 0, 0, 2, 1);
		
		Text rentSuccess = new Text("Room " + roomID + " is now rented by "+ cusID);
		GridPane.setConstraints(rentSuccess, 0, 1);
		
		Button backMain = new Button("Back to main page");
		GridPane.setConstraints(backMain, 0, 2);
		
		backMain.setOnAction(new BackMainHandler(pane));
		
		getChildren().addAll(title, rentSuccess, backMain);
	}

}
