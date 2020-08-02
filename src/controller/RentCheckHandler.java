package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import view.RentRoomCheck;

public class RentCheckHandler implements EventHandler<ActionEvent> {
	
	private BorderPane pane; 
	
	public RentCheckHandler(BorderPane pane) {
		this.pane = pane;
	}
	
	@Override
	public void handle(ActionEvent event) {
		pane.setCenter(new RentRoomCheck(pane));
	}
	
}
