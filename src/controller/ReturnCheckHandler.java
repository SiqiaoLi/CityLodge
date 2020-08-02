package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import view.ReturnRoomCheck;

public class ReturnCheckHandler implements EventHandler<ActionEvent> {
	
private BorderPane pane; 
	
	public ReturnCheckHandler(BorderPane pane) {
		this.pane = pane;
	}
	
	@Override
	public void handle(ActionEvent event) {
		pane.setCenter(new ReturnRoomCheck(pane));
	}

}
