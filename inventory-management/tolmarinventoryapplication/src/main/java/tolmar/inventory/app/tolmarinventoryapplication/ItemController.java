package tolmar.inventory.app.tolmarinventoryapplication;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ItemController implements Initializable{

	@FXML private ImageView imageView = new ImageView();
	@FXML private Label quantityLbl = new Label();
	@FXML private Label idLbl = new Label();
	@FXML private Label manufacturerNameLbl = new Label();
	@FXML private Label modelNumberLbl = new Label();
	@FXML private Label vendorNameLbl = new Label();
	@FXML private Label vendorPartNumberLbl = new Label();
	@FXML private Label tolmarPartNumberLbl = new Label();
	@FXML private Label locationLbl = new Label();
	@FXML private Label descriptionLbl = new Label();
	@FXML private Label equipmentGroupLbl = new Label();
	@FXML private Label equipmentIDLbl = new Label();
	@FXML private Label priceLbl = new Label();
	@FXML private Label minLbl = new Label();
	@FXML private Label maxLbl = new Label();
	@FXML private Label additionalLbl = new Label();
	@FXML private TextField quantityTxt;
	@FXML private Button quantityTakenBtn;
	@FXML private Button quantityAddedBtn;

	public SimpleObjectProperty itemImage = new SimpleObjectProperty();
	private int id, quantity, min, max, newTotal;
	private int listNum = 1;
	private double price;
	private String manufacturer_name, model_number, vendor_name, vendor_part_number, tolmar_part_number, equipment_id;
	private Image image;
	private String part_location, equipment_group, additional_notes, description, dbState, username, date, time;
	
	DBConnection dBC = new DBConnection();
    Connection con = dBC.getDBConnection();

	// Event Listener on Button[#quantityTakenBtn].onAction
	@FXML
	public void quantityTakenClicked(ActionEvent event) {
		
		try {
			
			int value = Integer.parseInt(quantityTxt.getText());
			newTotal = quantity - value;
			
			quantityLbl.setText("" + newTotal);
			String modelNumber = modelNumberLbl.getText();
			String sql = "update " + dbState + "_parts_list" + " set quantity = ?" + " where model_number = ?";
			PreparedStatement changeStmt = con.prepareStatement(sql);
			
			changeStmt.setInt(1, newTotal);
			changeStmt.setString(2, modelNumber);
			changeStmt.executeUpdate();
			
			this.quantity = newTotal;
			
			String max = "SELECT MAX(list) FROM history";
			String itemUpdated = description + " had " + Integer.parseInt(quantityTxt.getText()) + " part removed to " + dbState + "_parts_list database.";
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
			res.close();
			quantityTxt.clear();
			
			int minInt = Integer.parseInt(minLbl.getText());
			if (newTotal <= minInt) {
				messageSend();
			
			}
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, e);
			
		}
	}
	// Event Listener on Button[#quantityAddedBtn].onAction
	@FXML
	public void quantityAddedClicked(ActionEvent event) {
		
		try {
			
			int value = Integer.parseInt(quantityTxt.getText());
			int newTotal = quantity + value;
			
			quantityLbl.setText("" + newTotal);
			String modelNumber = modelNumberLbl.getText();
			String sql = "update " + dbState + "_parts_list" + " set quantity = ?" + " where model_number = ?";
			PreparedStatement changeStmt = con.prepareStatement(sql);
			
			changeStmt.setInt(1, newTotal);
			changeStmt.setString(2, modelNumber);
			changeStmt.executeUpdate();
			
			this.quantity = newTotal;
			String max = "SELECT MAX(list) FROM history";
			String itemUpdated = description + " had " + Integer.parseInt(quantityTxt.getText()) + " part added to " + dbState + "_parts_list database.";
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
			res.close();
			
			quantityTxt.clear();
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, e);
			
		}
	}
	
	public void setUserVariables (String username, String time, String date) {
			
		this.username = username;
		this.date = date;
		this.time = time;
	}
	
	public void setTextItems (int id, String manufacturer_name, String model_number, String vendor_name, String vendor_part_number, String tolmar_part_number,
			        		    	String part_location, double price, int quantity, int min, int max, String img, String equipment_group, String equipment_id,
			        		    		String additional_notes, String description) {
		
		priceLbl.setText("" + price);
		quantityLbl.setText("" + quantity);
		this.quantity = quantity;
		manufacturerNameLbl.setText(manufacturer_name);
		this.manufacturer_name = manufacturer_name;
		modelNumberLbl.setText(model_number);
		this.model_number = model_number;
		vendorNameLbl.setText(vendor_name);
		vendorPartNumberLbl.setText(vendor_part_number);
		this.vendor_name = vendor_name;
		tolmarPartNumberLbl.setText(tolmar_part_number);
		locationLbl.setText(part_location);
		descriptionLbl.setText(description);
		this.description = description;
		equipmentGroupLbl.setText(equipment_group);
		equipmentIDLbl.setText(equipment_id);
		this.equipment_group = equipment_group;
		minLbl.setText("" + min);
		this.min = min;
		maxLbl.setText("" + max);
		this.max = max;
		additionalLbl.setText(additional_notes);
		this.additional_notes = additional_notes;
		
		if(img != null) {
			
			Image image = new Image(img);
            imageView.setImage(image);
            
		} else if (img == null){
			
            Image image = new Image("/images/NoImageFound.png");
	        imageView.setImage(image);
	        
        }	
	}
	
	public void messageSend() {
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		
		Session session = Session.getDefaultInstance(props, 
				new javax.mail.Authenticator() {
			
					protected PasswordAuthentication getPasswordAuthentication() {
						
						return new PasswordAuthentication("tolmar.inventory.app@gmail.com", "Tinventory123");
						
					}
				});
		try{
			
			int orderQuantity = max - newTotal;
			
			Message message = new MimeMessage(session);	
			message.setFrom(new InternetAddress("tolmar.inventory.app@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("tolmar.inventory@gmail.com"));
			message.setSubject( description + ", has reached the minimum and needs to be reordered.");
			message.setText("Model Number: " + model_number + ", Description: " + description +" for the " + equipment_group + " equipment group has reached the minimum of " + min + " and a quantity of " + orderQuantity
					+ " needs to be reordered.\n\nAdditional notes: " + additional_notes + "\n\nThis part is manufactured by " + manufacturer_name + " and sourced by " + vendor_name );
			Transport.send(message);
			
		} catch(Exception e) {
			
			JOptionPane.showMessageDialog(null, e);
			
		}
	}
	
	public Label getDescriptionLbl() {
		
		return descriptionLbl;
		
	}
	
	public void setDescriptionLbl(Label descriptionLbl) {
		
		this.descriptionLbl = descriptionLbl;
		
	}
	
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
	
	public int getListNum() {
		
		return listNum;
		
	}
	
	public void setListNum(int listNum) {
		
		this.listNum = listNum;
		
	}
	
	public double getPrice() {
		
		return price;
		
	}
	
	public void setPrice(double price) {
		
		this.price = price;
		
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
	
	public String getEquipment_id() {
		
		return equipment_id;
		
	}
	
	public void setEquipment_id(String equipment_id) {
		
		this.equipment_id = equipment_id;
		
	}
	
	public Image getImage() {
		
		return image;
		
	}
	
	public void setImage(Image image) {
		
		this.image = image;
		
	}
	
	public String getPart_location() {
		
		return part_location;
		
	}
	
	public void setPart_location(String part_location) {
		
		this.part_location = part_location;
		
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
	
	public void setTextToLabel (String text) {
		
        descriptionLbl.setText(text);
        
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void setDBState (String dbState) {
		
		this.dbState = dbState;
		
	}
}	



























