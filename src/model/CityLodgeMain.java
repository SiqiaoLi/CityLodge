package model;
/* Student name: Siqiao Li
 * Student number: s3774940 
 * this is the city lodge startup class which contains 
 * a main method in which an object of the city lodge 
 * class is created and a single method is called on 
 * that object to run the entire CityLodge application*/


import javafx.application.Application;
import javafx.stage.Stage;
import view.MainWindow;

public class CityLodgeMain extends Application{

	public static void main(String[] args) {
		// create an object of the CityLodge. 
		CityLodge myCityLodge = new CityLodge();
		Application.launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		MainWindow.mainWindow();
	}

}
