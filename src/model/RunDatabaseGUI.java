package model;
// this is a UI of database, can see what data in the database
// remember to hit the terminate after close the interface,
// otherwise, it will cause error when opening next time.

import org.hsqldb.util.DatabaseManagerSwing;

public class RunDatabaseGUI {
	public static void main(String[] args) {
		System.out.println("Launching manager");
		DatabaseManagerSwing.main(new String[] {
				"--url", "jdbc:hsqldb:file:database/testDB", "--noexit"
		});
	}
}
