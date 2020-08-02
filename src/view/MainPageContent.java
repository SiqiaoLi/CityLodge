package view;
/* this is the mainpage scroll interface.*/

import java.io.File;
import java.util.ArrayList;

import controller.ShowDetailHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.CityLodge;
import model.Room;
import model.RoomDatabase;

public class MainPageContent extends ScrollPane {
	
	public MainPageContent(BorderPane pane) {
		// get rooms list from CItyLodge
		ArrayList<Room> rooms = new ArrayList<>();
		rooms = CityLodge.getRooms();
		// get how many rooms are there
		int totalRooms = rooms.size();

		// create a V box for room list
		VBox vBoxRoomList = new VBox();
		vBoxRoomList.setAlignment(Pos.CENTER);

		// go through the rooms list
		for (int i = 0; i < totalRooms; i++) {

			Text title = new Text();
			Text feature = new Text();
			Text numOfRoom = new Text();
			Text price = new Text();
			Button roomDetail = new Button("SELECT");
			final Room room = rooms.get(i);

			// click roomDetail button, jump to the room detail page
			roomDetail.setOnAction(new ShowDetailHandler(pane, room));

			// show room image
			String imaName = "src/images/" + RoomDatabase.getImageName(room.getRoomID());
			File imageName = new File(imaName);
			Image image = new Image(imageName.toURI().toString());
			ImageView imageView = new ImageView(image);
			imageView.setFitHeight(170);
			imageView.setFitWidth(220);

			// show room ID
			String roomID = room.getRoomID();
			title.setText(roomID);
			title.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

			// show room feature
			String roomfeature = "Feature:\n" + RoomDatabase.getFeature(room.getRoomID());
			feature.setText(roomfeature);
			feature.setFont(Font.font("Verdana", FontWeight.BOLD, 12));

			// show number of bedrooms
			int roomNum = RoomDatabase.getNumOfRoom(room.getRoomID());
			String roomNumStr = "Bedroom: " + Integer.toString(roomNum);
			numOfRoom.setText(roomNumStr);
			numOfRoom.setFont(Font.font("Verdana", FontWeight.BOLD, 12));

			// show room price
			int roomPrice = getPrice(room.getRoomID(), roomNum);
			String rPriceS = "$ " + Integer.toString(roomPrice);
			price.setText(rPriceS);
			price.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
			price.setFill(Color.CORNFLOWERBLUE);

			// create an anchor pane to hold room details
			AnchorPane anchorPane = new AnchorPane();
			anchorPane.setPrefSize(500, 200);
			anchorPane.getChildren().addAll(imageView, title, feature, numOfRoom, price, roomDetail);
			AnchorPane.setTopAnchor(imageView, 7.0);
			AnchorPane.setLeftAnchor(imageView, 20.0);
			AnchorPane.setTopAnchor(title, 20.0);
			AnchorPane.setLeftAnchor(title, 270.0);
			AnchorPane.setTopAnchor(feature, 70.0);
			AnchorPane.setLeftAnchor(feature, 270.0);
			AnchorPane.setTopAnchor(numOfRoom, 25.0);
			AnchorPane.setRightAnchor(numOfRoom, 220.0);
			AnchorPane.setTopAnchor(price, 20.0);
			AnchorPane.setRightAnchor(price, 70.0);
			AnchorPane.setTopAnchor(roomDetail, 80.0);
			AnchorPane.setRightAnchor(roomDetail, 70.0);

			// add anchor pane to the v box
			vBoxRoomList.getChildren().add(anchorPane);
			
			fitToWidthProperty().set(true);
			setContent(vBoxRoomList);
		}
	}
	
	// return price according to type and bedroom number
		public static int getPrice(String roomID, int numOfRooms) {
			String rType = RoomDatabase.getType(roomID);
			int price = 0;
			if (rType.equals("standard")) {
				if (numOfRooms == 1) {
					price = 59;
				} else if(numOfRooms == 2) {
					price = 99;
				} else if(numOfRooms == 4) {
					price = 199;
				}
			} else if(rType.equals("suite")) {
				price = 999;
			}	
			return price;
		}
}
