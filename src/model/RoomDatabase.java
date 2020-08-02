package model;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RoomDatabase {

	// add standard room into database
	public static void insertStandard(String roomID, int numOfRoom, String feature) {

		final String DB_NAME = "testDB";
		final String TABLE_NAME = "ROOM";
		String type = "standard";
		String status = "available";

		// use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {
			String query = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?, ?, ?, ?)";

			PreparedStatement pres = con.prepareStatement(query);
			pres.setString(1, roomID);
			pres.setInt(2, numOfRoom);
			pres.setString(3, type);
			pres.setString(4, feature);
			pres.setString(5, status);
			pres.setDate(6, null);

			int result = pres.executeUpdate();

			con.commit();

			System.out.println("Insert into table " + TABLE_NAME + " executed successfully");
			System.out.println(result + " row(s) affected");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// add suite into database
	public static void insertSuite(String roomID, int numOfRoom, String feature, Date Date) {

		final String DB_NAME = "testDB";
		final String TABLE_NAME = "ROOM";
		String type = "suite";
		String status = "available";

		// use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {
			String query = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?, ?, ?, ?)";

			PreparedStatement pres = con.prepareStatement(query);
			pres.setString(1, roomID);
			pres.setInt(2, numOfRoom);
			pres.setString(3, type);
			pres.setString(4, feature);
			pres.setString(5, status);
			pres.setDate(6, Date);

			int result = pres.executeUpdate();

			con.commit();

			System.out.println("Insert into table " + TABLE_NAME + " executed successfully");
			System.out.println(result + " row(s) affected");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// insert record into database
	public static void insertRecord(String roomID, Room record) {

		final String DB_NAME = "testDB";
		final String TABLE_NAME = "RECORD";

		// use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {
			String query = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?)";

			PreparedStatement pres = con.prepareStatement(query);
			pres.setString(1, roomID);
			
			
			pres.setObject(2, roomToBlob(record));
			pres.addBatch();
	        pres.executeBatch();
			
			//int result = pres.executeUpdate();

			con.commit();

			System.out.println("Insert into table " + TABLE_NAME + " executed successfully");
			//System.out.println(result + " row(s) affected");
			if (pres != null) {
                pres.close();
            }
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// insert image name
	public static void insertImageName(String roomID, String imageName) {

		final String DB_NAME = "testDB";
		final String TABLE_NAME = "IMAGE";

		// use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {
			String query = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?)";

			PreparedStatement pres = con.prepareStatement(query);
			pres.setString(1, roomID);
			pres.setString(2, imageName);
			
			int result = pres.executeUpdate();

			con.commit();

			System.out.println("Insert into table " + TABLE_NAME + " executed successfully");
			System.out.println(result + " row(s) affected");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// get all room ID and return in a arraylist
	public static ArrayList<String> selectRoomID() {
		
		final String DB_NAME = "testDB";
		final String TABLE_NAME = "ROOM";
		ArrayList<String> roomIDArray = new ArrayList<String>();

		// use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {
			String query = "SELECT roomID FROM " + TABLE_NAME;

			try (ResultSet resultSet = stmt.executeQuery(query)) {
				while (resultSet.next()) {
					roomIDArray.add(resultSet.getString("roomID"));
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return roomIDArray;
	}
	
	// get the status of a room
	public static String getStatus(String roomID) {
		
		final String DB_NAME = "testDB";
		final String TABLE_NAME = "ROOM";
		String status = "";

		// use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {
			String query = "SELECT status FROM " + TABLE_NAME
							+ " WHERE roomID = ?" ;

			PreparedStatement pres = con.prepareStatement(query);
			pres.setString(1, roomID);
			
			try (ResultSet resultSet = pres.executeQuery()) {
				while (resultSet.next()) {
					status = resultSet.getString("status");
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return status;
	}
	
	// get type of a room
	public static String getType(String roomID) {
		
		final String DB_NAME = "testDB";
		final String TABLE_NAME = "ROOM";
		String type = "";

		// use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {
			String query = "SELECT type FROM " + TABLE_NAME
							+ " WHERE roomID = ?" ;

			PreparedStatement pres = con.prepareStatement(query);
			pres.setString(1, roomID);
			
			try (ResultSet resultSet = pres.executeQuery()) {
				while (resultSet.next()) {
					type = resultSet.getString("type");
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return type;
	}
	
	// get feature of room
	public static String getFeature(String roomID) {
		
		final String DB_NAME = "testDB";
		final String TABLE_NAME = "ROOM";
		String feature = "";

		// use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {
			String query = "SELECT feature FROM " + TABLE_NAME
							+ " WHERE roomID = ?" ;

			PreparedStatement pres = con.prepareStatement(query);
			pres.setString(1, roomID);
			
			try (ResultSet resultSet = pres.executeQuery()) {
				while (resultSet.next()) {
					feature = resultSet.getString("feature");
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return feature;
	}
	
	
	// get number of rooms of a room
	public static int getNumOfRoom(String roomID) {

		final String DB_NAME = "testDB";
		final String TABLE_NAME = "ROOM";
		int numOfRoom = 0;

		// use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {
			String query = "SELECT numOfRoom FROM " + TABLE_NAME + " WHERE roomID = ?";

			PreparedStatement pres = con.prepareStatement(query);
			pres.setString(1, roomID);

			try (ResultSet resultSet = pres.executeQuery()) {
				while (resultSet.next()) {
					numOfRoom = resultSet.getInt("numOfRoom");
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return numOfRoom;
	}
	
	// update status after renting
	public static void updateStatus(String roomID, String status) {
		final String DB_NAME = "testDB";
		final String TABLE_NAME = "ROOM";
		
		//use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			String query = "UPDATE " + TABLE_NAME +
					" SET status = ?" +
					" WHERE roomID = ?";
			
			PreparedStatement pres = con.prepareStatement(query);
			pres.setString(1, status);
			pres.setString(2, roomID);
			
			int result = pres.executeUpdate();
			
			System.out.println("Update table " + TABLE_NAME + " executed successfully");
			System.out.println(result + " row(s) affected");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// get maintenance date of a room
	public static Date getMaintenDate(String roomID) {

		final String DB_NAME = "testDB";
		final String TABLE_NAME = "ROOM";
		Date maintenDate = null;

		// use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {
			String query = "SELECT maintenDate FROM " + TABLE_NAME + " WHERE roomID = ?";

			PreparedStatement pres = con.prepareStatement(query);
			pres.setString(1, roomID);

			try (ResultSet resultSet = pres.executeQuery()) {
				while (resultSet.next()) {
					maintenDate = resultSet.getDate("maintenDate");
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return maintenDate;
	}
	
	// update status after renting
	public static void updateMaintenDate (String roomID, Date maintenanceDate) {
		final String DB_NAME = "testDB";
		final String TABLE_NAME = "ROOM";

		// use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {
			String query = "UPDATE " + TABLE_NAME + " SET maintenDate = ?" + " WHERE roomID = ?";

			PreparedStatement pres = con.prepareStatement(query);
			pres.setDate(1, maintenanceDate);
			pres.setString(2, roomID);

			int result = pres.executeUpdate();

			System.out.println("Update table " + TABLE_NAME + " executed successfully");
			System.out.println(result + " row(s) affected");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// get record of a room from database
	public static Room getRecord(String roomID) {
		final String DB_NAME = "testDB";
		final String TABLE_NAME = "RECORD";
		Room record = null;

		// use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {
			String query = "SELECT record FROM " + TABLE_NAME
							+ " WHERE roomID = ?" ;

			PreparedStatement pres = con.prepareStatement(query);
			pres.setString(1, roomID);
			
			try (ResultSet resultSet = pres.executeQuery()) {
				while (resultSet.next()) {
					
					Blob inBlob = resultSet.getBlob(1);

	                InputStream is = inBlob.getBinaryStream();
	                BufferedInputStream bis = new BufferedInputStream(is);

	                byte[] bytes = new byte[(int) inBlob.length()];
	                while (-1 != (bis.read(bytes, 0, bytes.length))) {
	                    ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bytes));
	                    	record = (Room) in.readObject();

	                }
	                bis.close();
	                is.close();
	                
				    System.out.println(record.getRoomID());
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return record;
	}
	
	
	// update record in database
	public static void updateRecord(String roomID, Room record) {
		final String DB_NAME = "testDB";
		final String TABLE_NAME = "RECORD";
		
		//use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			String query = "UPDATE " + TABLE_NAME +
					" SET record = ? " +
					" WHERE roomID = ?";
			
			PreparedStatement pres = con.prepareStatement(query);
			
			pres.setObject(1, roomToBlob(record));
			pres.setString(2, roomID);
			pres.addBatch();
	        pres.executeBatch();
			
			System.out.println("Update table " + TABLE_NAME + " executed successfully");
			//System.out.println(result + " row(s) affected");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// get image name of a room
	public static String getImageName(String roomID) {

		final String DB_NAME = "testDB";
		final String TABLE_NAME = "IMAGE";
		String imageName = null;

		// use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {
			String query = "SELECT imageName FROM " + TABLE_NAME + " WHERE roomID = ?";

			PreparedStatement pres = con.prepareStatement(query);
			pres.setString(1, roomID);

			try (ResultSet resultSet = pres.executeQuery()) {
				while (resultSet.next()) {
					imageName = resultSet.getString("imageName");
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		if (imageName == null) {
	          imageName = "noimage.jpg";
	    }
	    return imageName;
	}

	// create a room table
	public static void createRoomTable() throws SQLException {

		final String DB_NAME = "testDB";
		final String TABLE_NAME = "ROOM";

		try (Connection con = ConnectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {
			int result = stmt.executeUpdate("CREATE TABLE room (" 
					+ "roomID VARCHAR(30) NOT NULL,"
					+ "numOfRoom INT NOT NULL," 
					+ "type VARCHAR(30) NOT NULL," 
					+ "feature VARCHAR(100) NOT NULL,"
					+ "status VARCHAR(20) NOT NULL," 
					+ "maintenDate DATE," 
					+ "PRIMARY KEY (roomID))");
			
			if (result == 0) {
				System.out.println("Table " + TABLE_NAME + " has been created successfully");
			} else {
				System.out.println("Table " + TABLE_NAME + " is not created");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// create a record table
	public static void createRecordTable() throws SQLException {

		final String DB_NAME = "testDB";
		final String TABLE_NAME = "RECORD";

		try (Connection con = ConnectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {
			int result = stmt.executeUpdate("CREATE TABLE record (" 
					+ "roomID VARCHAR(30) NOT NULL,"
					+ "record Blob,"  
					+ "PRIMARY KEY (roomID))");
			if (result == 0) {
				System.out.println("Table " + TABLE_NAME + " has been created successfully");
			} else {
				System.out.println("Table " + TABLE_NAME + " is not created");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// create a record table
	public static void createImageTable() throws SQLException {

		final String DB_NAME = "testDB";
		final String TABLE_NAME = "IMAGE";

		try (Connection con = ConnectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {
			int result = stmt.executeUpdate("CREATE TABLE image (" 
											+ "roomID VARCHAR(30) NOT NULL," 
											+ "imageName VARCHAR(50),"
											+ "PRIMARY KEY (roomID))");
			if (result == 0) {
				System.out.println("Table " + TABLE_NAME + " has been created successfully");
			} else {
				System.out.println("Table " + TABLE_NAME + " is not created");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// convert room type into blob
	public static byte[] roomToBlob(Object object) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream outputStream = new ObjectOutputStream(out);
            outputStream.writeObject(object);
            byte[] bytes = out.toByteArray();
            outputStream.close();
            out.close();
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	
}
