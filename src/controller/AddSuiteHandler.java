package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import view.AddStandard;
import view.AddSuite;

public class AddSuiteHandler implements EventHandler<ActionEvent> {
	
	private BorderPane pane; 
	
	public AddSuiteHandler(BorderPane pane) {
		this.pane = pane;
	}
	
	@Override
	public void handle(ActionEvent event) {
		pane.setCenter(new AddSuite(pane));
	}
	
}
