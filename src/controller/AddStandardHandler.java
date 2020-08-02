package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.AddStandard;


public class AddStandardHandler implements EventHandler<ActionEvent> {
	
	private BorderPane pane; 
	
	public AddStandardHandler(BorderPane pane) {
		this.pane = pane;
	}
	
	@Override
	public void handle(ActionEvent event) {
		pane.setCenter(new AddStandard(pane));
	}
	
}
