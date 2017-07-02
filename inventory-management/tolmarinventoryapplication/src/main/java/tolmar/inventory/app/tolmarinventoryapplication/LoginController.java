package tolmar.inventory.app.tolmarinventoryapplication;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginController implements Initializable{
	
	public LoginModel loginModel = new LoginModel();
	
	@FXML private Label isConnected;
	@FXML TextField txtUsername;
	@FXML private TextField txtPassword;
	@FXML private Button loginButton;
	@FXML private Button cancelButton;
	Stage primaryStage;
	String userLevel;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		if (loginModel.isDbConnected()) {
			
			isConnected.setText("Connected");
			
		} else {
			
			isConnected.setText("Not Connected");
			
		}
		
		txtPassword.setOnKeyPressed(new EventHandler<KeyEvent> () {
			
	        @Override
	        public void handle (KeyEvent key) {
	        	
	            if (key.getCode().equals (KeyCode.ENTER)) {
	            	
	                loginButton.fire();
	                
	            }
	        }
	    });
	}

	public void Login (ActionEvent event) {
		
		try {
			
			if (loginModel.isLogin(txtUsername.getText(), txtPassword.getText())) {
				
				isConnected.setText("Username and password are correct");
				((Node)event.getSource()).getScene().getWindow().hide();
				
				primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				Pane root = loader.load(getClass().getClassLoader().getResource("fxml/Main.fxml").openStream());
				
				MainController mainController = (MainController)loader.getController();
				mainController.GetUser(txtUsername.getText());
				//mainController.logger();
				mainController.setUserRights(loginModel.userLevel);
				
				Scene scene = new Scene(root);
				primaryStage.setTitle("TOLMARInventory");
				scene.getStylesheets().add(getClass().getClassLoader().getResource("styles/application.css").toExternalForm());
				
				primaryStage.setOnCloseRequest(e -> {
					
					e.consume();
					mainController.closeProgram();
					
				});
				
				primaryStage.setScene(scene);
				primaryStage.show();
				
			} else {
				
				isConnected.setText("Username and password are not correct");
				
			}
		} catch (SQLException e) {
			
			System.out.println("Error is an SQLException");
			isConnected.setText("Username and password are not correct");
			JOptionPane.showMessageDialog(null, e);
			
		} catch (IOException e) {
			
			System.out.println("Error is an IOException");
			JOptionPane.showMessageDialog(null, e);
			
		}
	}
	
	@FXML
	private void getChoice(ChoiceBox<String> userCB) {
		String state = userCB.getValue();
		if (state.equals("Admin")) {
			userLevel = "admin";
	     } else if (state.equals("Editor")) {
	    	 userLevel = "editor";
	     } else if (state.equals("User")) {
	    	 userLevel = "user";
	     }// add user database here for administrator rights.
	}
	
	public void hideWindow(ActionEvent event) {
		System.out.println("making it this far");
		((Node)event.getSource()).getScene().getWindow().hide();
	}
	
	public void cancelLogin() {
		Platform.exit();
	}
}
