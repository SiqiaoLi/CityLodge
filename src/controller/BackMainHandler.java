package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import view.MainPageContent;


public class BackMainHandler implements EventHandler<ActionEvent> {
	private BorderPane pane; 
	
	public BackMainHandler(BorderPane pane) {
		this.pane = pane;
	}
	
	@Override
	public void handle(ActionEvent event) {
		pane.setCenter(new MainPageContent(pane));
	}
}
