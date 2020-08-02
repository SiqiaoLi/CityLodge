package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import view.MaintenRoomCheck;

public class MaintenCheckHandler implements EventHandler<ActionEvent> {
	
private BorderPane pane; 
	
	public MaintenCheckHandler(BorderPane pane) {
		this.pane = pane;
	}
	
	@Override
	public void handle(ActionEvent event) {
		pane.setCenter(new MaintenRoomCheck(pane));
	}

}
