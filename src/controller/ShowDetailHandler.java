package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import model.Room;
import view.RoomDetailView;

public class ShowDetailHandler implements EventHandler<ActionEvent> {
	private BorderPane pane; 
	private Room room;
	
	public ShowDetailHandler(BorderPane pane, Room room) {
		this.pane = pane;
		this.room = room;
	}
	
	@Override
	public void handle(ActionEvent event) {
		pane.setCenter(new RoomDetailView(pane, room));
	}
}
