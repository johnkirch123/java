package tolmar.inventory.app.tolmarinventoryapplication;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class History {

	SimpleIntegerProperty list = new SimpleIntegerProperty();
	SimpleStringProperty date = new SimpleStringProperty();
	SimpleStringProperty time = new SimpleStringProperty();
	SimpleStringProperty username = new SimpleStringProperty();
	SimpleStringProperty event = new SimpleStringProperty();
	
	public int getList() {
		
		return list.get();
		
	}
	
	public String getDate() {
		
		return date.get();
		
	}
	
	public String getTime() {
		
		return time.get();
		
	}
	
	public String getUsername() {
		
		return username.get();
		
	}
	
	public String getEvent() {
		
		return event.get();
		
	}
}
