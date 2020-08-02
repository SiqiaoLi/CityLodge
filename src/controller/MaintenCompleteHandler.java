package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import view.MaintenCompleteCheck;

public class MaintenCompleteHandler implements EventHandler<ActionEvent> {
	
private BorderPane pane; 
	
	public MaintenCompleteHandler(BorderPane pane) {
		this.pane = pane;
	}
	
	@Override
	public void handle(ActionEvent event) {
		pane.setCenter(new MaintenCompleteCheck(pane));
	}
}
