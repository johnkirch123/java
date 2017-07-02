package tolmar.inventory.app.tolmarinventoryapplication;

public class SelectedItem {
	private int id, quantity, min, max;
	private double price;
	private String equipment_id, manufacturer_name, model_number, vendor_name, vendor_part_number;
	private String tolmar_part_number, part_location, equipment_group, additional_notes, description;
	private String image, department;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getEquipment_id() {
		return equipment_id;
	}
	public void setEquipment_id(String equipment_id) {
		this.equipment_id = equipment_id;
	}
	public String getManufacturer_name() {
		return manufacturer_name;
	}
	public void setManufacturer_name(String manufacturer_name) {
		this.manufacturer_name = manufacturer_name;
	}
	public String getModel_number() {
		return model_number;
	}
	public void setModel_number(String model_number) {
		this.model_number = model_number;
	}
	public String getVendor_name() {
		return vendor_name;
	}
	public void setVendor_name(String vendor_name) {
		this.vendor_name = vendor_name;
	}
	public String getVendor_part_number() {
		return vendor_part_number;
	}
	public void setVendor_part_number(String vendor_part_number) {
		this.vendor_part_number = vendor_part_number;
	}
	public String getTolmar_part_number() {
		return tolmar_part_number;
	}
	public void setTolmar_part_number(String tolmar_part_number) {
		this.tolmar_part_number = tolmar_part_number;
	}
	public String getPart_location() {
		return part_location;
	}
	public void setPart_location(String location) {
		this.part_location = location;
	}
	public String getEquipment_group() {
		return equipment_group;
	}
	public void setEquipment_group(String equipment_group) {
		this.equipment_group = equipment_group;
	}
	public String getAdditional_notes() {
		return additional_notes;
	}
	public void setAdditional_notes(String additional_notes) {
		this.additional_notes = additional_notes;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	

}
