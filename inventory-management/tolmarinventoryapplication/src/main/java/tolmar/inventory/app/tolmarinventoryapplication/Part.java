package tolmar.inventory.app.tolmarinventoryapplication;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.ImageView;

public class Part {
	
	public SimpleIntegerProperty id = new SimpleIntegerProperty();
	public SimpleIntegerProperty quantity = new SimpleIntegerProperty();
	public SimpleIntegerProperty min = new SimpleIntegerProperty();
	public SimpleIntegerProperty max = new SimpleIntegerProperty();
	public SimpleDoubleProperty price = new SimpleDoubleProperty();
	public SimpleStringProperty equipment_id = new SimpleStringProperty();
	public SimpleObjectProperty<ImageView> image = new SimpleObjectProperty<>();
	public SimpleStringProperty manufacturer_name = new SimpleStringProperty();
	public SimpleStringProperty model_number = new SimpleStringProperty();
	public SimpleStringProperty vendor_name = new SimpleStringProperty();
	public SimpleStringProperty vendor_part_number = new SimpleStringProperty();
	public SimpleStringProperty tolmar_part_number = new SimpleStringProperty();
	public SimpleStringProperty part_location = new SimpleStringProperty();
	public SimpleStringProperty equipment_group = new SimpleStringProperty();
	public SimpleStringProperty additional_notes = new SimpleStringProperty();
	public SimpleStringProperty description = new SimpleStringProperty();
	
	public Object getImage() {
		return image.get();
	}
	
	public int getId() {
		return id.get();
	}
	
	public int getQuantity() {
		return quantity.get();
	}
	
	public int getMin() {
		return min.get();
	}
	
	public int getMax() {
		return max.get();
	}
	
	public String getEquipment_id() {
		return equipment_id.get();
	}
	
	public double getPrice() {
		return price.get();
	}
	
	public String getManufacturer_name() {
		return manufacturer_name.get();
	}
	
	public String getModel_number() {
		return model_number.get();
	}
	
	public String getVendor_name() {
		return vendor_name.get();
	}
	
	public String getVendor_part_number() {
		return vendor_part_number.get();
	}
	
	public String getTolmar_part_number() {
		return tolmar_part_number.get();
	}
	
	public String getPart_location() {
		return part_location.get();
	}
	
	public String getEquipment_group() {
		return equipment_group.get();
	}
	
	public String getAdditional_notes() {
		return additional_notes.get();
	}
	
	public String getDescription() {
		return description.get();
	}
}
