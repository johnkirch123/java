package tolmar.inventory.app.tolmarinventoryapplication;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {
	
	static boolean answer;

	public static boolean display(String title, String message) {
		
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		window.setMinHeight(200);
		
		Label label = new Label();
		label.setText(message);
		//Create two buttons
		Button yesButton = new Button("yes");
		Button noButton = new Button("no");
		
		yesButton.setOnAction(e -> {
			
			answer = true;
			window.close();
			
		});
		
		noButton.setOnAction(e -> {
			
			answer = false;
			window.close();
			
		});
		
		VBox layout = new VBox(10);
		HBox buttons = new HBox();
		
		layout.getChildren().addAll(label, buttons);
		buttons.getChildren().addAll(yesButton, noButton);
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(30);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		scene.getStylesheets();
		
		window.setScene(scene);
		window.showAndWait();
		
		return answer;
		
	}

}