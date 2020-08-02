package view;

import controller.ExportRoomHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ExportRoomView extends GridPane {
	
	public ExportRoomView(BorderPane pane) {
		
		// GridPane with 10px padding around edge
		setPadding(new Insets(10, 10, 10, 10));
		setAlignment(Pos.CENTER);
		setVgap(15);
		setHgap(10);

		// Title
		Text title = new Text("Export room");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		GridPane.setConstraints(title, 0, 0, 2, 1);
		
		// Add room button
		Button importButton = new Button("Export");
		GridPane.setConstraints(importButton, 0, 1);
		
		// Add room button
		Button backButton = new Button("Back to main page");
		GridPane.setConstraints(backButton, 1, 1);
		
		importButton.setOnAction(new ExportRoomHandler(pane));
		backButton.setOnAction(e -> pane.setCenter(new MainPageContent(pane)));
		
		getChildren().addAll(title, importButton, backButton);
		
	}

}
