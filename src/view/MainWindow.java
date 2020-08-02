package view;



import controller.AddStandardHandler;
import controller.AddSuiteHandler;
import controller.MaintenCheckHandler;
import controller.MaintenCompleteHandler;
import controller.RentCheckHandler;
import controller.ReturnCheckHandler;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MainWindow {
	
	public static void mainWindow() {
		
	 	Stage primaryStage = new Stage();
	 	
		// set title for the stage
		primaryStage.setTitle("Main Page");

		// Create a border pane
		BorderPane pane = new BorderPane();
		

		// Create two menus and a sub menu 
		Menu menu = new Menu("Menu");
		Menu submenu1 = new Menu("Add a room");
		Menu file = new Menu("File");

		// Create menu items
		MenuItem m1_1 = new MenuItem("Standard room");
		MenuItem m1_2 = new MenuItem("Suite");
		MenuItem m2 = new MenuItem("Rent a room");
		MenuItem m3 = new MenuItem("Return a room");
		MenuItem m4 = new MenuItem("Room Maintenance");
		MenuItem m5 = new MenuItem("Complete Maintenance");
		MenuItem m0 = new MenuItem("Quit");

		MenuItem f0 = new MenuItem("Back to main page");
		MenuItem f1 = new MenuItem("Import room data");
		MenuItem f2 = new MenuItem("Export room data");

		// add menu items to menu
		submenu1.getItems().add(m1_1);
		submenu1.getItems().add(m1_2);
		
		menu.getItems().add(submenu1);
		menu.getItems().add(m2);
		menu.getItems().add(m3);
		menu.getItems().add(m4);
		menu.getItems().add(m5);
		menu.getItems().add(m0);

		file.getItems().add(f1);
		file.getItems().add(f2);
		file.getItems().add(f0);
		
		// add event
		m1_1.setOnAction(new AddStandardHandler(pane));
		m1_2.setOnAction(new AddSuiteHandler(pane));
		m2.setOnAction(new RentCheckHandler(pane));
		m3.setOnAction(new ReturnCheckHandler(pane));
		m4.setOnAction(new MaintenCheckHandler(pane));
		m5.setOnAction(new MaintenCompleteHandler(pane));
		m0.setOnAction(e -> Platform.exit());
		
		f1.setOnAction(e -> pane.setCenter(new ImportRoomView(pane)));
		f2.setOnAction(e -> pane.setCenter(new ExportRoomView(pane)));
		f0.setOnAction(e -> pane.setCenter(new MainPageContent(pane)));

		// Create menu bar
		MenuBar mb = new MenuBar();

		// add menus to menu bar
		mb.getMenus().addAll(menu, file);


		// Place nodes in the pane
		CustomPane top = new CustomPane("CityLodge");
		
		// Create a VBoox
		VBox vbox = new VBox();
		
		// Add menu bar and top pane to the v box
		vbox.getChildren().addAll(mb, top);
		
		// Put v box to the top of border pane
		pane.setTop(vbox);

		// put scrollPane into center
		MainPageContent mainContent = new MainPageContent(pane);
		
		pane.setCenter(mainContent);
		BorderPane.setAlignment(mainContent, Pos.CENTER);

		// Create a new scene with the border pane
		Scene sc = new Scene(pane, 700, 400);

		// Put scene on the stage
		primaryStage.setScene(sc);
		primaryStage.setHeight(600);
	    primaryStage.setWidth(700);
		primaryStage.show();
	}
	
	
}
