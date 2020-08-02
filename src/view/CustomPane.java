package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

class CustomPane extends StackPane {
	public CustomPane(String title) {
		Label label = new Label(title);
		getChildren().add(label);
		label.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		label.setTextFill(Color.BLUE);
		setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
	}
}
