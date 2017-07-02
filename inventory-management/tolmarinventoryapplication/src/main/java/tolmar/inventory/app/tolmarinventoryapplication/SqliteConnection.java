package tolmar.inventory.app.tolmarinventoryapplication;

import java.sql.*;

import javax.swing.JOptionPane;

public class SqliteConnection {

	public static Connection Connector () {
		
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:E:\\Programming\\SQLite\\UsersDb.sqlite");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
		
	}
	
}
