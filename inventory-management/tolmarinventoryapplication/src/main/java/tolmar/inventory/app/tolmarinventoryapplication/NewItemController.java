package tolmar.inventory.app.tolmarinventoryapplication;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class NewItemController implements Initializable{
	private String date, time, username;
	String aseptic = "aseptic";
	String general = "general";
	String facilities = "facilities";
	String dbState;
	@FXML private Label saveStatusLbl;
	@FXML ImageView imageView;
	@FXML private TextField manufacturerNameTxt;
	@FXML private TextField modelNumberTxt;
	@FXML private TextField vendorNameTxt;
	@FXML private TextField vendorPartNumberTxt;
	@FXML private TextField tolmarPartNumberTxt;
	@FXML private TextField locationTxt;
	@FXML private TextField descriptionTxt;
	@FXML private TextField equipmentGroupTxt;
	@FXML private TextField equipmentIDTxt;
	@FXML private TextField priceTxt;
	@FXML private TextArea additionalNotesTxt;
	@FXML private TextField minTxt;
	@FXML private TextField maxTxt;
	@FXML private TextField quantityTxt;
	@FXML Button saveItemBtn;
	@FXML Button updateItemBtn;
	@FXML private Button cancelBtn;
	@FXML ChoiceBox<String> databaseChoiceBox = new ChoiceBox<>(FXCollections.observableArrayList(aseptic, general, facilities));
	private String img, imageName, image_address, backup_address, model_number;
	private File file;
	private Boolean bool;
	int quantity, min, max, id;
	DBConnection dBC = new DBConnection();
	Connection con = dBC.getDBConnection();
	Stage window;

	// Event Listener on Button[#saveItemBtn].onAction
	@FXML
	public void saveNewItemClicked(ActionEvent event) throws SQLException {
		
		this.dbState = (String) databaseChoiceBox.getValue();
		
		ResultSet res = null;
		try{
			
			String autoSaveQuery = "SELECT bool FROM auto_save WHERE rowid = 1";

			
			res = con.createStatement().executeQuery(autoSaveQuery);
			bool = res.getBoolean(1);
			
			} catch (SQLException e) {
				
				e.printStackTrace();
				
			} finally {
				
				res.close();
				
			}
			
		try {
			ResultSet rs = null;
			String modelNumQuery = "SELECT model_number FROM " + dbState + "_parts_list";
			
			String maxSQL = "SELECT MAX(id) FROM " + dbState + "_parts_list";
			rs = con.createStatement().executeQuery(maxSQL);
			
			int idNumber = rs.getInt(1);
			rs.close();
			idNumber++;
			
			quantity = Integer.parseInt(quantityTxt.getText());
			min = Integer.parseInt(minTxt.getText());
			max = Integer.parseInt(maxTxt.getText());
			
			
			double price = Double.parseDouble(priceTxt.getText());
			SelectedItem selectedItem = new SelectedItem();
			selectedItem.setId(idNumber);
			selectedItem.setQuantity(quantity);
			selectedItem.setMin(min);
			selectedItem.setMax(max);
			selectedItem.setEquipment_id(equipmentIDTxt.getText());
			selectedItem.setEquipment_group(equipmentGroupTxt.getText());
			selectedItem.setPrice(price);
			selectedItem.setManufacturer_name(manufacturerNameTxt.getText());
			selectedItem.setModel_number(modelNumberTxt.getText());
			selectedItem.setVendor_name(vendorNameTxt.getText());
			selectedItem.setVendor_part_number(vendorPartNumberTxt.getText());
			selectedItem.setTolmar_part_number(tolmarPartNumberTxt.getText());
			selectedItem.setDescription(descriptionTxt.getText());
			selectedItem.setAdditional_notes(additionalNotesTxt.getText());
			selectedItem.setPart_location(locationTxt.getText());
			
			if (img != null) {
				
				selectedItem.setImage("File:///" + image_address + "tolmarImages/" + dbState + "Images/" + imageName);	
				
			} else {
				
				selectedItem.setImage("/images/NoImageFound.png");
				
			}
			
			selectedItem.setDepartment(databaseChoiceBox.getValue().toLowerCase());
			ResultSet modelRS = con.createStatement().executeQuery(modelNumQuery);
			
			try {
				
				while (modelRS.next()) {
	
					if (modelRS.getString(1).equals(modelNumberTxt.getText())) {
						
						JOptionPane.showMessageDialog(null, "This model number already exists.");
						return;
						
					}	
				}
			} finally {
				
				modelRS.close();
				
			}
			
			String sql = "INSERT INTO " + dbState + "_parts_list VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement myStmt = con.prepareStatement(sql);
			myStmt.setInt(1, idNumber);
			myStmt.setString(2, selectedItem.getManufacturer_name());
			myStmt.setString(3, selectedItem.getModel_number());
			myStmt.setString(4, selectedItem.getVendor_name());
			myStmt.setString(5, selectedItem.getVendor_part_number());
			myStmt.setString(6, selectedItem.getTolmar_part_number());
			myStmt.setString(7, selectedItem.getPart_location());
			myStmt.setDouble(8, selectedItem.getPrice());
			myStmt.setInt(9, selectedItem.getQuantity());
			myStmt.setInt(10, selectedItem.getMin());
			myStmt.setInt(11, selectedItem.getMax());
			myStmt.setString(12, selectedItem.getImage());
			myStmt.setString(13, selectedItem.getEquipment_group());
			myStmt.setString(14, selectedItem.getEquipment_id());
			myStmt.setString(15, selectedItem.getAdditional_notes());
			myStmt.setString(16, selectedItem.getDescription());
			myStmt.setString(17, selectedItem.getDepartment());
			myStmt.executeUpdate();
			
			saveStatusLbl.setText("New Item Saved");
			
			myStmt.close();
			
			if (bool) {
				
				if (img != null) {
					
					Path source = Paths.get(file.getPath());
					Path target = Paths.get(image_address + "tolmarImages/" + dbState + "Images/", file.getName());
					Path target2 = Paths.get(backup_address + "tolmarImages/" + dbState + "Images/", file.getName());
				
					try {
						//replace existing file using java nio package
						Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
						Files.copy(source, target2, StandardCopyOption.REPLACE_EXISTING);
					
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
			}else {
				
				if (img != null) {
					
					Path source = Paths.get(file.getPath());
					Path target = Paths.get(image_address + "tolmarImages/" + dbState + "Images/", file.getName());
				
					try {
						//replace existing file using java nio package
						Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
					
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			String max = "SELECT MAX(list) FROM history";
			String itemAdded = selectedItem.getQuantity() + " " + selectedItem.getDescription() + " added to " + dbState + "_parts_list database.";
			ResultSet result = con.createStatement().executeQuery(max);
			
			int maxNum = result.getInt(1);
			maxNum++;
			String newItemAdded = "Insert into history values (?, ?, ?, ?, ?)";
			PreparedStatement newItemStmt = con.prepareStatement(newItemAdded);
			newItemStmt.setInt(1, maxNum);
			newItemStmt.setString(2, date);
			newItemStmt.setString(3, time);
			newItemStmt.setString(4, username);
			newItemStmt.setString(5, itemAdded);
			newItemStmt.executeUpdate();
			
			result.close();
			con.close();
			PauseTransition delay = new PauseTransition(Duration.seconds(2));
			delay.setOnFinished( e -> window.close() );
			delay.play();
			
			
			}catch(Exception exc){
				
				exc.printStackTrace();
			}		
	}
	// Event Listener on Button[#updateItemBtn].onAction
		@FXML
	public void updateItemClicked(ActionEvent event) {
			
			try{
				
				this.dbState = (String) databaseChoiceBox.getValue();
			
				quantity = Integer.parseInt(quantityTxt.getText());
				min = Integer.parseInt(minTxt.getText());
				max = Integer.parseInt(maxTxt.getText());
				double price = Double.parseDouble(priceTxt.getText());
				
				SelectedItem selectedItem = new SelectedItem();
				selectedItem.setId(id);
				selectedItem.setQuantity(quantity);
				selectedItem.setMin(min);
				selectedItem.setMax(max);
				selectedItem.setEquipment_id(equipmentIDTxt.getText());
				selectedItem.setEquipment_group(equipmentGroupTxt.getText());
				selectedItem.setPrice(price);
				selectedItem.setManufacturer_name(manufacturerNameTxt.getText());
				selectedItem.setModel_number(modelNumberTxt.getText());
				selectedItem.setVendor_name(vendorNameTxt.getText());
				selectedItem.setVendor_part_number(vendorPartNumberTxt.getText());
				selectedItem.setTolmar_part_number(tolmarPartNumberTxt.getText());
				selectedItem.setDescription(descriptionTxt.getText());
				selectedItem.setAdditional_notes(additionalNotesTxt.getText());
				selectedItem.setPart_location(locationTxt.getText());
				// Sets image to null if NoImageFound.png has been placed into img variable so it does not save that in database.
				if (img.equals("/images/NoImageFound.png")) {
					
    				img = null;
    			}
				// If image is not null and the file is not null (meaning a new image is selected with the selectImageClicked()),
				// get that image path and save it to the database.
				if (img != null && file != null) {
					
					Path source = Paths.get(file.getPath());
					Path target = Paths.get(image_address + "tolmarImages/" + dbState + "Images/", imageName);
					String newImage = "File:///" + image_address + "tolmarImages/" + dbState + "Images/" + imageName;
					selectedItem.setImage(newImage);
				
					try {
						//replace existing file using java nio package
						Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
					
					} catch (IOException e) {
						
						e.printStackTrace();
						
					}
				// If img is not null but the file is null (meaning there has already been an image saved and the selectImageClicked()
				// was not used to attach an image file) then it will just overwrite the database with the same image.
				} else if (img != null && file == null){
					
					selectedItem.setImage(img);
					
				} 
				System.out.println(id);
				selectedItem.setDepartment(databaseChoiceBox.getValue().toLowerCase());
				
				String updateStmt = "UPDATE " + dbState + "_parts_list SET id = ?, manufacturer_name = ?, model_number = ?, vendor_name = ?, vendor_part_number = ?, "
						+ "tolmar_part_number = ?, part_location = ?, price = ?, quantity = ?, min = ?, max = ?, image = ?, equipment_group = ?, equipment_id = ?,"
						+ "additional_notes = ?, description = ?, department = ? WHERE model_number = ?";
						
				PreparedStatement myStmt = con.prepareStatement(updateStmt);
				myStmt.setInt(1, selectedItem.getId());
				myStmt.setString(2, selectedItem.getManufacturer_name());
				myStmt.setString(3, selectedItem.getModel_number());
				myStmt.setString(4, selectedItem.getVendor_name());
				myStmt.setString(5, selectedItem.getVendor_part_number());
				myStmt.setString(6, selectedItem.getTolmar_part_number());
				myStmt.setString(7, selectedItem.getPart_location());
				myStmt.setDouble(8, selectedItem.getPrice());
				myStmt.setInt(9, selectedItem.getQuantity());
				myStmt.setInt(10, selectedItem.getMin());
				myStmt.setInt(11, selectedItem.getMax());
				myStmt.setString(12, selectedItem.getImage());
				myStmt.setString(13, selectedItem.getEquipment_group());
				myStmt.setString(14, selectedItem.getEquipment_id());
				myStmt.setString(15, selectedItem.getAdditional_notes());
				myStmt.setString(16, selectedItem.getDescription());
				myStmt.setString(17, selectedItem.getDepartment());
				myStmt.setString(18, selectedItem.getModel_number());
				myStmt.executeUpdate();
				myStmt.close();
				
				saveStatusLbl.setText("Item Updated");
				
				String max = "SELECT MAX(list) FROM history";
				String itemUpdated = selectedItem.getDescription() + ", updated from " + dbState + "_parts_list database.";
				ResultSet res = con.createStatement().executeQuery(max);
				
				int maxNum = res.getInt(1);
				maxNum++;
				String updateItem = "Insert into history values (?, ?, ?, ?, ?)";
				PreparedStatement updateItemStmt = con.prepareStatement(updateItem);
				updateItemStmt.setInt(1, maxNum);
				updateItemStmt.setString(2, date);
				updateItemStmt.setString(3, time);
				updateItemStmt.setString(4, username);
				updateItemStmt.setString(5, itemUpdated);
				updateItemStmt.executeUpdate();
				
				con.close();
				PauseTransition delay = new PauseTransition(Duration.seconds(2));
				delay.setOnFinished( e -> window.close() );
				delay.play();
				
				
				}catch(Exception exc){
					
					exc.printStackTrace();
				}		
		}
	// Event Listener on Button[#cancelBtn].onAction
	@FXML
	public void cancelClicked(ActionEvent event) {
		
		window.close();
		
	}
	// Initializes the controller
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		databaseChoiceBox.getItems().addAll(aseptic, general, facilities);
		databaseChoiceBox.setValue(aseptic);
		
		file = null;
		
		DBConnection dBC = new DBConnection();
	    con = dBC.getDBConnection();
		
	    try {
	    	
		String saveLocationQuery = "SELECT image_address FROM image_address WHERE rowid = 1";
		PreparedStatement locationStmt = con.prepareStatement(saveLocationQuery);
		ResultSet result = locationStmt.executeQuery();
		image_address = result.getString(1);
		result.close();
		
		String backupLocationQuery = "SELECT image_address FROM image_address WHERE rowid = 2";
		PreparedStatement bLocationStmt = con.prepareStatement(backupLocationQuery);
		ResultSet result2 = bLocationStmt.executeQuery();
		backup_address = result2.getString(1);
		result2.close();
		
	    }catch(Exception exc){
			
			exc.printStackTrace();
		}
		
	}
	
	@FXML
	private void selectImageClicked() {
		
		 FileChooser fileChooser = new FileChooser();
		 fileChooser.setTitle("Select Image");
		 //Set extension filter
	     FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
	     FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
	     fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
		 file = fileChooser.showOpenDialog(window);
		 imageName = file.getName();
		 img = "File:///" + file.getPath();
		
	     Image image = new Image(img);
	     imageView.setImage(image);
             
         
		
	}
	
	public void setTextItems (int id, String manufacturer_name, String model_number, String vendor_name, String vendor_part_number, String tolmar_part_number,
	    	String part_location, double price, int quantity, int min, int max, String img, String equipment_group, String equipment_id,
    		String additional_notes, String description) {
		
		this.id = id;
		this.img = img;
		manufacturerNameTxt.setText(manufacturer_name);
		modelNumberTxt.setText(model_number);
		vendorNameTxt.setText(vendor_name);
		vendorPartNumberTxt.setText(vendor_part_number);
		tolmarPartNumberTxt.setText(tolmar_part_number);
		locationTxt.setText(part_location);
		descriptionTxt.setText(description);
		equipmentGroupTxt.setText(equipment_group);
		equipmentIDTxt.setText(equipment_id);
		priceTxt.setText("" + price);
		additionalNotesTxt.setText("" + additional_notes);
		minTxt.setText("" + min);
		maxTxt.setText("" + max);
		quantityTxt.setText("" + quantity);
		
	}
	
	public void setDBState (String dbState) {
		
		this.dbState = dbState;
		
	}
	
	public void setUserVariables (String username, String time, String date) {
		
		this.username = username;
		this.date = date;
		this.time = time;
	}
	
}

