package tolmar.inventory.app.tolmarinventoryapplication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginModel {
	
	String username, userLevel;
	
	Connection connection;
	
	public LoginModel () {
		
		connection = SqliteConnection.Connector();
		
		if (connection == null) {
			
			System.exit(1);
		}
	}
	
	public boolean isDbConnected () {
		
		try {
			
			return !connection.isClosed();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
			
		}
	}
	
	public boolean isLogin (String user, String pass) throws SQLException {
		//setUsername(user);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select * from users where username = ? and password = ?";
		
		try {
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user);
			preparedStatement.setString(2, pass);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				
				userLevel = resultSet.getString(7);
				return true;
				
			} else {
				
				return false;
				
			}
		} catch (Exception e) {
			
				return false;
				
		} finally {
			
			preparedStatement.close();
			resultSet.close();
			
		}
	}
}
