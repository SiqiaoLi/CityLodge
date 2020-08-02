package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.CityLodge;
import model.Room;
import view.AlertBox;

public class ExportRoomHandler implements EventHandler<ActionEvent> {
	
	private BorderPane pane; 
	private Stage stage;
	
	public ExportRoomHandler(BorderPane pane) {
		this.pane = pane;
	}
	
	@Override
	public void handle(ActionEvent event) {
		FileChooser fc = new FileChooser();
        fc.setTitle("Choose a txt file");
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text", "*.txt"));
        fc.setInitialFileName("CityLodge");
        File file = fc.showSaveDialog(stage);
        
        try {
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);

            outputStreamWriter.write(roomRecord());
            outputStreamWriter.close();
            fileOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        AlertBox.display("Export success", "File exported");
	}
	
	public String roomRecord() {
        String s = "";
        ArrayList<Room> rooms = new ArrayList<>();
		rooms = CityLodge.getRooms();
		
        for (int i = 0; i < rooms.size(); i++) {
            s = s + rooms.get(i).toString() + "\n";
            s = s + rooms.get(i).exportRecord();
        }
        
        return s;
    }

}
