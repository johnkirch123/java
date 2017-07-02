package tolmar.inventory.app.tolmarinventoryapplication;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	public Connection getDBConnection () {
		
		try {
			
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:E:\\Programming\\SQLite\\UsersDb.sqlite");
			return conn;
			
		} catch (Exception e) {
			
			return null;
			
		}
		
	}
}
