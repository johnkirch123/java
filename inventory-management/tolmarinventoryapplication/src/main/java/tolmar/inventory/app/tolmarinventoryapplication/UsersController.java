package tolmar.inventory.app.tolmarinventoryapplication;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.control.cell.PropertyValueFactory;

public class UsersController implements Initializable{
	@FXML private TableView<User> userTable = new TableView<User>();
	@FXML private TableColumn<User, Integer> iCol;
	@FXML private TableColumn<User, String> fnCol;
	@FXML private TableColumn<User, String> lnCol;
	@FXML private TableColumn<User, String> unCol;
	@FXML private TableColumn<User, String> pCol;
	@FXML private TableColumn<User, String> emCol;
	@FXML private TableColumn<User, String> aCol;
	@FXML private TextField fn;
	@FXML private TextField ln;
	@FXML private TextField un;
	@FXML private TextField p;
	@FXML private TextField em;
	@FXML private ChoiceBox<String> a = new ChoiceBox<>();;
	@FXML private Button addButton;
	@FXML private Button removeButton;
	@FXML private CheckBox passwordCB;
	
	int listNum;
	Connection con;
	private ObservableList<User> userData = FXCollections.observableArrayList();


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		 
		 assert userTable != null;
		 iCol.setCellValueFactory(
	 	       new PropertyValueFactory<User, Integer>("id"));
	     fnCol.setCellValueFactory(
	 	        new PropertyValueFactory<User, String>("first_name"));
	     lnCol.setCellValueFactory(
	 	        new PropertyValueFactory<User, String>("last_name"));
	     unCol.setCellValueFactory(
	 	        new PropertyValueFactory<User, String>("username"));  
	     pCol.setCellValueFactory(                
	        new PropertyValueFactory<User, String>("password"));
	     emCol.setCellValueFactory(
	 	        new PropertyValueFactory<User, String>("email"));
	     aCol.setCellValueFactory(
		 	        new PropertyValueFactory<User, String>("user_level"));
	     
	     a.getItems().addAll( "admin", "editor", "user");
		 a.setValue("user");
		 userTable.setFixedCellSize(30.0);
		
		// Establish a connection to the database.
	     DBConnection dBC = new DBConnection();
	     con = dBC.getDBConnection();
	     //int listNum = 1;
	     
	     //populates the table with user data from the database.
	     userData = FXCollections.observableArrayList();
	     try{   
	        String userQuery = "Select * from users";             //Order By id
	        ResultSet result = con.createStatement().executeQuery(userQuery);  
	        while(result.next()){
	        	
	            User u = new User();
	            
	            u.id.set(result.getInt("id"));                       
	            u.firstName.set(result.getString("firstname"));
	            u.lastName.set(result.getString("lastname"));
	            u.username.set(result.getString("username"));
	            u.password.set(result.getString("password"));
	            u.email.set(result.getString("email"));
	            u.userLevel.set(result.getString("userlevel"));
	            userData.add(u); 
	        }
	        
	        userTable.setItems(userData);
	     }
	     catch(Exception e){
	          e.printStackTrace();
	          System.out.println("Error on Building Data");            
	     }
	}
	
	// Adds a new user from the provided fields and then clears the fields, updates the tableView.
	@FXML
	private void addButtonClicked() {
		try{
			String maxSQL = "SELECT MAX(id) FROM USERS";
			ResultSet rs = con.createStatement().executeQuery(maxSQL);
			
			int idNumber = rs.getInt(1);
			idNumber++;
			
			User user = new User();
			user.setFirst_name(fn.getText());
			user.setLast_name(ln.getText());
			user.setUsername(un.getText());
			user.setPassword(p.getText());
			user.setEmail(em.getText());
			user.setUser_level(a.getValue());
			//userTable.getItems().clear();
			//userTable.getItems().addAll(user);
			
			String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?, ?, ?, ?)";
					
			PreparedStatement myStmt = con.prepareStatement(sql);
			myStmt.setInt(1, idNumber);
			myStmt.setString(2, user.fn);
			myStmt.setString(3, user.ln);
			myStmt.setString(4, user.un);
			myStmt.setString(5, user.p);
			myStmt.setString(6, user.em);
			myStmt.setString(7, user.a);
			
			myStmt.executeUpdate();
			con.close();
			//initialize(null, null);
			fn.clear();
			ln.clear();
			un.clear();
			p.clear();
			em.clear();
			userData.removeAll(userData);
			try{   
				// Establish a connection to the database.
			    DBConnection dBC = new DBConnection();
			    con = dBC.getDBConnection();
			    //Query DB to populate tableview with all users 
		        String userQuery = "Select * from users";             //Order By id
		        ResultSet result = con.createStatement().executeQuery(userQuery);  
		        while(result.next()){
		        	
		            User u = new User();
		            
		            u.id.set(result.getInt("id"));                       
		            u.firstName.set(result.getString("firstname"));
		            u.lastName.set(result.getString("lastname"));
		            u.username.set(result.getString("username"));
		            u.password.set(result.getString("password"));
		            u.email.set(result.getString("email"));
		            u.userLevel.set(result.getString("userlevel"));
		            userData.add(u); 
		        }
		        
		        userTable.setItems(userData);
		     }
		     catch(Exception e){
		          e.printStackTrace();
		          System.out.println("Error on Building Data");            
		     }
	
			}catch(Exception exc){
				System.out.println("exception error");
				exc.printStackTrace();
			}		
		}
	
	// Removes a user from the database, updates the tableView
	@FXML
	private void removeButtonClicked() {
		
		try {
			ObservableList<User> userSelected, allUsers;
			allUsers = userTable.getItems();
			userSelected = userTable.getSelectionModel().getSelectedItems();
			String usernameSelected = userTable.getSelectionModel().getSelectedItem().getUsername();
			
			String sql = "DELETE FROM USERS WHERE username = ?";
			PreparedStatement removeStmt = con.prepareStatement(sql);
			removeStmt.setString(1, usernameSelected);
			removeStmt.executeUpdate();
			userSelected.forEach(allUsers::remove);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setUsers(List<User> users) {
		userTable.getItems().setAll(users);
    }
	
	@FXML
	private void hideUserInfo () {
		
		if (passwordCB.isSelected()) {
			iCol.setVisible(false);
			fnCol.setVisible(false);
			lnCol.setVisible(false);
			unCol.setVisible(false);
			pCol.setVisible(false);
			emCol.setVisible(false);
			aCol.setVisible(false);
		} else {
			iCol.setVisible(true);
			fnCol.setVisible(true);
			lnCol.setVisible(true);
			unCol.setVisible(true);
			pCol.setVisible(true);
			emCol.setVisible(true);
			aCol.setVisible(true);
		}
	}
}


