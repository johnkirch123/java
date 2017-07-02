 package tolmar.inventory.app.tolmarinventoryapplication;
	
import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	
	Stage window;
	Parent root;
	
	@Override
	public void start(Stage primaryStage) {
		
		window = primaryStage;
		
		try {
			
			root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Login.fxml"));
			window.setTitle("INVENTORYlogin");
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("styles/application.css").toExternalForm());
			
			window.setScene(scene);
			window.show();
			
		} catch(Exception e) {

			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public static void main(String[] args) {
		
		launch(args);
		
	}
}
