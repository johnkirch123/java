package tolmar.inventory.app.tolmarinventoryapplication;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController implements Initializable{
	
	/*
	 * Author: John Kirch
	 * Version 1.0
	 * March 26, 2016
	 */
	
	@FXML private VBox element;
	@FXML private MenuBar menuBar;
	@FXML private Menu fileMenu, partsMenu, usersMenu, reportsMenu, helpMenu, imageMenu;
	@FXML private MenuItem addMenuItem, editMenuItem, deleteMenuItem, closeMenuItem, userMenuItem;
	@FXML private MenuItem userHistoryMenuItem, newSearchMenuItem, imageLocationMenuItem;
	
	@FXML private TabPane tabs;
	@FXML private Tab userTab, resultsTab;
	
	@FXML Label userLbl;
	@FXML Label dateLbl;
	@FXML Label timeLbl;
	
	@FXML private TextField equipIDFilterField;
	@FXML private TextField filterField;
	@FXML private TextField fn;
	@FXML private TextField ln;
	@FXML private TextField un;
	@FXML private TextField p;
	@FXML private TextField em;
	@FXML private TextField a;
	
	@FXML ComboBox<String> departmentCB = new ComboBox<String>();
	@FXML ComboBox<String> searchCB = new ComboBox<>();
	
	@FXML FilteredList<Part> filteredData;
	private ObservableList<Part> data = FXCollections.observableArrayList();

	@FXML private TableView<User> userTable;
	@FXML private TableView<History> historyTable;
	@FXML private TableView<Part> tableView;
	@FXML private TableView<Part> equipID = new TableView<>();

	@FXML private TableColumn<Part, Integer> idCol;
	@FXML private TableColumn<Object, ImageView> imgCol;
	@FXML private TableColumn<Part, Integer> quantityCol;
	@FXML private TableColumn<Part, String> manCol;
	@FXML private TableColumn<Part, String> venCol;
	@FXML private TableColumn<Part, String> mnCol;
	@FXML private TableColumn<Part, String> pnCol;
	@FXML private TableColumn<Part, String> descCol;
	@FXML private TableColumn<Part, String> equipmentIDCol;
	
	@FXML int id, quantity, min, max;
	@FXML int listNum = 1;
	@FXML double price;
	@FXML String manufacturer_name, model_number, vendor_name, vendor_part_number, tolmar_part_number, equipment_id;
	@FXML String part_location, equipment_group, additional_notes, description, image_address;
	
	Tab tab = new Tab();
	
	LoginModel lm = new LoginModel();
	LoginController lc = new LoginController();
	Part partName = new Part();
	
	String aseptic = "Aseptic";
	String general = "General";
	String facilities = "Facilities";
	String users = "Users";
	String admin = "Admin";
	String state = "Aseptic";
	String selectedPart, date, time, user, img, filterState, fileName;
	
	Image image;
	
	SimpleStringProperty dynamicTimeProperty = new SimpleStringProperty();
	SimpleStringProperty dynamicDateProperty = new SimpleStringProperty();
	
	Connection con;
	// Initialization of the MainController.
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// System.out.println(System.getProperty("user.home"));
		// Sets variable user from the label located on the main page
		user = userLbl.getText();	
		// Create and set the columns displayed in the main search area.
		 assert tableView != null;
	     idCol.setCellValueFactory(
	 	        new PropertyValueFactory<Part, Integer>("id"));
	     imgCol.setCellValueFactory(
	 	        new PropertyValueFactory<Object,ImageView>("image"));
	     pnCol.setCellValueFactory(
	 	        new PropertyValueFactory<Part,String>("vendor_part_number"));
	     descCol.setCellValueFactory(
	 	        new PropertyValueFactory<Part,String>("description"));  
	     quantityCol.setCellValueFactory(                
	        new PropertyValueFactory<Part,Integer>("quantity"));
	     venCol.setCellValueFactory(
	 	        new PropertyValueFactory<Part,String>("vendor_name"));
	     mnCol.setCellValueFactory(
		 	        new PropertyValueFactory<Part,String>("model_number"));
	     equipmentIDCol.setCellValueFactory(                
	 	        new PropertyValueFactory<Part,String>("equipment_id"));
	     // Establish a connection to the database.
	     DBConnection dBC = new DBConnection();
	     con = dBC.getDBConnection();
	     // Set ComboBox items
	     departmentCB.getItems().addAll(aseptic, general, facilities);
	     departmentCB.valueProperty().addListener(new ChangeListener<String>() {
	         @Override public void changed(ObservableValue ov, String t, String t1) {
		           getCombo();
		           SingleSelectionModel<Tab> selectionModel = tabs.getSelectionModel();
		           selectionModel.select(0);
	         }    
	     });
	     // Set ChoiceBox items
	     searchCB.getItems().addAll("Description", "Model Number", "Vendor");
	     searchCB.setValue("Description");
	     searchCB.valueProperty().addListener(new ChangeListener<String>() {
	         @Override public void changed(ObservableValue ov, String t, String t1) {
		           filterData();
	         }    
	     });
	     
	     // Set time label
	     timeLbl.textProperty().bind(dynamicTimeProperty);
	     Thread t = new Thread(new Runnable() {
	    	 
	     	@Override
	     	public void run() {
	     		
	     		while (true) {
	     			
	     			final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	     			Platform.runLater(new Runnable() {
	     				
	     				@Override
	     				public void run() {
	     					
	     					dynamicTimeProperty.set(sdf.format(new Date()));
	     					
	     				}
	     			});
	     			
	     			try {
	     				
	     				Thread.sleep(100);
	     				
	     			} catch (InterruptedException ex) {
	     				
	     				break;
	     				
	     			}
	     		}
	     	}
	     });
	     
	     t.setName("Runnable Time Updater");
	     t.setDaemon(true);
	     t.start();
	     // Set date label
	     dateLbl.textProperty().bind(dynamicDateProperty);
	     Thread t2 = new Thread(new Runnable() {
	    	 
	     	@Override
	     	public void run() {
	     		
	     		while (true) {
	     			
	     			final SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/YYYY");
	     			Platform.runLater(new Runnable() {
	     				
	     				@Override
	     				public void run() {
	     					
	     					dynamicDateProperty.set(sdf2.format(new Date()));
	     					
	     				}
	     			});
	     			
	     			try {
	     				
	     				Thread.sleep(100);
	     				
	     			} catch (InterruptedException ex) {
	     				
	     				break;
	     				
	     			}
	     		}
	     	}
	     });
	     
	     t2.setName("Runnable Date Updater");
	     t2.setDaemon(true);
	     t2.start();
	     // *********************************************************************** //
	     // Event handler for double clicking line items to open the item details tab.
	     tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			    @Override
			    public void handle(MouseEvent click) {

			        if (click.getClickCount() == 2) {
			   	     
			        	ResultSet res = null;
					    PreparedStatement newItemStmt = null;
			        	
			    	    String maxQuery = "SELECT MAX(list) FROM history";
			    		String itemViewed = tableView.getSelectionModel().getSelectedItem().getDescription() + " has been viewed from " + state + "_parts_list database.";
			    		
			    		try {
			    			res = con.createStatement().executeQuery(maxQuery);
			    			int maxNum = res.getInt(1);
			    			maxNum++;
			    			String newItemViewed = "Insert into history values (?, ?, ?, ?, ?)";
			    			newItemStmt = con.prepareStatement(newItemViewed);
			    			newItemStmt.setInt(1, maxNum);
			    			newItemStmt.setString(2, dateLbl.getText());
			    			newItemStmt.setString(3, timeLbl.getText());
			    			newItemStmt.setString(4, user);
			    			newItemStmt.setString(5, itemViewed);
			    			newItemStmt.executeUpdate();
			    			
			    			
			    		} catch (SQLException e) {
			    			// TODO Auto-generated catch block
			    			e.printStackTrace();
			    		}finally {
					    	
					    	try {
					    		
					    		res.close();
					    		newItemStmt.close();
					    		
					    	} catch (SQLException e) {
					    	  
					    		e.printStackTrace();
					    		
					    	}
					    } 
			        	
						if (state.equals(aseptic) || state.equals("Department")) {
							// initializes database variables to null for the aseptic database
							ResultSet rs = null;
						    PreparedStatement selectedItemStmt = null;
						    Connection con = null;
						    
						    try {
						    	// establish a connection
						    	con = dBC.getDBConnection();
						    	// preparing and setting the 'statement' for querying the database, and creating a ResultSet of the query by identifying the model number from the table
						    	String selectedPart = tableView.getSelectionModel().getSelectedItem().getModel_number();
						    	String sql = "select * from aseptic_parts_list where model_number = ?";
						    	selectedItemStmt = con.prepareStatement(sql); // create a statement
						    	selectedItemStmt.setString(1, selectedPart); // set input parameter
						    	rs = selectedItemStmt.executeQuery();
						    	// extract data from the ResultSet and set the class variables to pass through to the item controller
						    	while (rs.next()) {
						    		
						    		id = rs.getInt(1);
						    		manufacturer_name = rs.getString(2);
						    		model_number = rs.getString(3);
						    		vendor_name = rs.getString(4);
						    		vendor_part_number = rs.getString(5);
						    		tolmar_part_number = rs.getString(6);
						    		part_location = rs.getString(7);
						    		price = rs.getDouble(8);
						    		quantity = rs.getInt(9);
						    		min = rs.getInt(10);
						    		max = rs.getInt(11);
						    		img = rs.getString(12);
						    		equipment_group = rs.getString(13);
						    		equipment_id = rs.getString(14);
						    		additional_notes = rs.getString(15);
						    		description = rs.getString(16);
						    		// open a new tab and set the tab name to the description of the item
						    		Tab tab = new Tab();
						            tabs.getTabs().add(tab);
						            tab.setText(tableView.getSelectionModel().getSelectedItem().getDescription());
						            // loads the previously instantiated version of the Item.fxml resource
						            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/Item.fxml"));
						            tab.setContent((Node) loader.load());
						            
						            date = dateLbl.getText();
						    		time = timeLbl.getText();
						    		user = userLbl.getText();
						            
						            SingleSelectionModel<Tab> selectionModel = tabs.getSelectionModel();
						            selectionModel.select(tab);
						            // creates an ItemController object and passes through the results of the database query and stores them as variables in the ItemController, also sets the database to aseptic
						            ItemController controller = loader.<ItemController>getController();
						            controller.setTextItems(id, manufacturer_name, model_number, vendor_name, vendor_part_number, tolmar_part_number, part_location, price, quantity, 
										    				min, max, img, equipment_group, equipment_id, additional_notes, description);
						            controller.setUserVariables(user, time, date);
						            controller.setDBState(aseptic);
						            	  
						    	}
						    } catch (Exception e) {
						    	
						    	e.printStackTrace();
						    	JOptionPane.showMessageDialog(null, e);
						      
						    }finally {
						    	
						    	try {
						    		
						    		rs.close();
						    		selectedItemStmt.close();
						    		con.close();
						    		
						    	} catch (SQLException e) {
						    	  
						    		e.printStackTrace();
						    		JOptionPane.showMessageDialog(null, e);
						        
						    	}
						    } 
						} else if (state.equals(general)) {
							// initializes database variables to null for the general database
							ResultSet rs = null;
						    PreparedStatement selectedItemStmt = null;
						    Connection con = null;
						    
						    try {
						    	// establish a connection
						    	con = dBC.getDBConnection();
						    	// preparing and setting the 'statement' for querying the database, and creating a ResultSet of the query by identifying the model number from the table
						    	String selectedPart = tableView.getSelectionModel().getSelectedItem().getModel_number();
						    	String sql = "select * from general_parts_list where model_number = ?";
						    	selectedItemStmt = con.prepareStatement(sql); // create a statement
						    	selectedItemStmt.setString(1, selectedPart); // set input parameter
						    	rs = selectedItemStmt.executeQuery();
						    	// extract data from the ResultSet and set the class variables to pass through to the item controller
						    	while (rs.next()) {
						    		
						    		id = rs.getInt(1);
						    		manufacturer_name = rs.getString(2);
						    		model_number = rs.getString(3);
						    		vendor_name = rs.getString(4);
						    		vendor_part_number = rs.getString(5);
						    		tolmar_part_number = rs.getString(6);
						    		part_location = rs.getString(7);
						    		price = rs.getDouble(8);
						    		quantity = rs.getInt(9);
						    		min = rs.getInt(10);
						    		max = rs.getInt(11);
						    		img = rs.getString(12);
						    		equipment_group = rs.getString(13);
						    		equipment_id = rs.getString(14);
						    		additional_notes = rs.getString(15);
						    		description = rs.getString(16);
						    		// open a new tab and set the tab name to the description of the item
						    		Tab tab = new Tab();
						            tabs.getTabs().add(tab);
						            tab.setText(tableView.getSelectionModel().getSelectedItem().getDescription());
						            // loads the previously instantiated version of the Item.fxml resource
						            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/Item.fxml"));
						            tab.setContent((Node) loader.load());
						            
						            SingleSelectionModel<Tab> selectionModel = tabs.getSelectionModel();
						            selectionModel.select(tab);
						            // creates an ItemController object and passes through the results of the database query and stores them as variables in the ItemController, also sets the database to general
						            ItemController controller = loader.<ItemController>getController();
						            controller.setTextItems(id, manufacturer_name, model_number, vendor_name, vendor_part_number, tolmar_part_number, part_location, price, quantity, 
										    				min, max, img, equipment_group, equipment_id, additional_notes, description);  
						            controller.setDBState(general);
						    	}
						    } catch (Exception e) {

						      e.printStackTrace();
						      
						    } finally {
						    	
						    	try {
						    	  
						    		rs.close();
						    		selectedItemStmt.close();
						    		con.close();
						    	  
						    	} catch (SQLException e) {
						    	  
						    		e.printStackTrace();
						        
						    	}
						    }
						    
						} else if (state.equals(facilities)) {
							// initializes database variables to null for the general database
							ResultSet rs = null;
						    PreparedStatement selectedItemStmt = null;
						    Connection con = null;
						    
						    try {
						    	// establish a connection
						    	con = dBC.getDBConnection();
						    	// preparing and setting the 'statement' for querying the database, and creating a ResultSet of the query by identifying the model number from the table
						    	String selectedPart = tableView.getSelectionModel().getSelectedItem().getModel_number();
						    	String sql = "select * from facilities_parts_list where model_number = ?";
						    	selectedItemStmt = con.prepareStatement(sql); // create a statement
						    	selectedItemStmt.setString(1, selectedPart); // set input parameter
						    	rs = selectedItemStmt.executeQuery();
						    	// extract data from the ResultSet and set the class variables to pass through to the item controller
						    	while (rs.next()) {
						    		
						    		id = rs.getInt(1);
						    		manufacturer_name = rs.getString(2);
						    		model_number = rs.getString(3);
						    		vendor_name = rs.getString(4);
						    		vendor_part_number = rs.getString(5);
						    		tolmar_part_number = rs.getString(6);
						    		part_location = rs.getString(7);
						    		price = rs.getDouble(8);
						    		quantity = rs.getInt(9);
						    		min = rs.getInt(10);
						    		max = rs.getInt(11);
						    		img = rs.getString(12);
						    		equipment_group = rs.getString(13);
						    		equipment_id = rs.getString(14);
						    		additional_notes = rs.getString(15);
						    		description = rs.getString(16);
						    		// open a new tab and set the tab name to the description of the item
						    		Tab tab = new Tab();
						            tabs.getTabs().add(tab);
						            tab.setText(tableView.getSelectionModel().getSelectedItem().getDescription());
						            // loads the previously instantiated version of the Item.fxml resource
						            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/Item.fxml"));
						            tab.setContent((Node) loader.load());
						            
						            SingleSelectionModel<Tab> selectionModel = tabs.getSelectionModel();
						            selectionModel.select(tab);
						            // creates an ItemController object and passes through the results of the database query and stores them as variables in the ItemController, also sets the database to facilities
						            ItemController controller = loader.<ItemController>getController();
						            controller.setTextItems(id, manufacturer_name, model_number, vendor_name, vendor_part_number, tolmar_part_number, part_location, price, quantity, 
										    				min, max, img, equipment_group, equipment_id, additional_notes, description);
						            controller.setDBState(facilities);
						            	  
						    	}
						    } catch (Exception e) {
						    	
						    	e.printStackTrace();
						      
						    } finally {
						    	
						    	try {
						    		
						    		rs.close();
						    		selectedItemStmt.close();
						    		con.close();
						    		
						    	} catch (SQLException e) {
						    	  
						    		e.printStackTrace();
						        
						    	}
						    }
						}else {
							
							System.out.println("Select a database");
							
						}
			        }
			    }
	     	});
	     
	    buildDataAseptic();

	}
	// Sets the database from the selected choice box.
	@FXML
	private void getCombo() {
		
		state = departmentCB.getValue();
		
		if (state.equals(aseptic)) {
			
	    	 buildDataAseptic();
	    	 
	     } else if (state.equals(general)) {
	    	 
	    	 buildDataGeneral();
	    	 
	     } else if (state.equals(facilities)) {
	    	 
	    	 buildDataFacilities();
	    	 
	     }
	}
	// Builds the Aseptic Database for viewing.
	@FXML
	public void buildDataAseptic(){
		
    	listNum = 1;        
	    data = FXCollections.observableArrayList();
	    
	    try{   
	    	
	        String SQL = "Select * from aseptic_parts_list";             //Order By id
	        ResultSet rs = con.createStatement().executeQuery(SQL);
 
	        while(rs.next()){
	        	
	            Part asepticPart = new Part();
	            asepticPart.id.set(listNum++);
	            
	            if(rs.getString("image") != null) {
	            	
		            Image img = new Image(rs.getString("image"));
		            ImageView imageView = new ImageView();
		            imageView.setImage(img);
		            asepticPart.image.set(imageView);
		            imageView.setFitWidth(120);
		            imageView.setFitHeight(80);
	            
	            } else {
	            	
	            	Image img = new Image("/images/NoImageFoundSmall.png");
		            ImageView imageView = new ImageView();
		            imageView.setImage(img);
		            asepticPart.image.set(imageView);
		            imageView.setFitWidth(120);
		            imageView.setFitHeight(80);
		            
	            }
	            
	            asepticPart.vendor_part_number.set(rs.getString("vendor_part_number"));
	            asepticPart.description.set(rs.getString("description"));
	            asepticPart.quantity.set(rs.getInt("quantity"));
	            asepticPart.vendor_name.set(rs.getString("vendor_name"));
	            asepticPart.model_number.set(rs.getString("model_number"));
	            asepticPart.equipment_id.set(rs.getString("equipment_id"));
	            data.add(asepticPart);     
	            
	        }
	        
		    filterData();
	       
	    } catch (Exception e) {
	    	
	        e.printStackTrace();
	          
	    }
	    
	}
	// Builds the General Database for viewing.
	@FXML
	public void buildDataGeneral(){
		
    	listNum = 1;        
	    data = FXCollections.observableArrayList();
	    
	    try{      
	    	
	        String SQL = "Select * from general_parts_list";             //Order By id
	        ResultSet rs = con.createStatement().executeQuery(SQL); 
	        
	        while(rs.next()){
	        	
	            Part generalPart = new Part();
	            generalPart.id.set(listNum++);        
	            
	            if(rs.getString("image") != null) {
	            	
		            Image img = new Image(rs.getString("image"));
		            ImageView imageView = new ImageView();
		            imageView.setImage(img);
		            generalPart.image.set(imageView);
		            imageView.setFitWidth(120);
		            imageView.setFitHeight(80);
		            
	            } else {
		            	
		            Image img = new Image("/images/NoImageFound.png");
			        ImageView imageView = new ImageView();
			        imageView.setImage(img);
			        generalPart.image.set(imageView);
			        imageView.setFitWidth(120);
			        imageView.setFitHeight(80);
			            
	            }
	            
	            generalPart.vendor_part_number.set(rs.getString("vendor_part_number"));
	            generalPart.description.set(rs.getString("description"));
	            generalPart.quantity.set(rs.getInt("quantity"));
	            generalPart.equipment_id.set(rs.getString("equipment_id"));
	            generalPart.vendor_name.set(rs.getString("vendor_name"));
	            generalPart.model_number.set(rs.getString("model_number"));
	            generalPart.equipment_id.set(rs.getString("equipment_id"));
	            data.add(generalPart);                  
	        }
	        
	        tableView.setItems(data);
	        
	    } catch(Exception e){
	    	
	        e.printStackTrace();
	          
	    }
	    
	    filterData();
	    
	}
	// Builds the Facilities Database for viewing.
	@FXML
	public void buildDataFacilities(){
		
    	listNum = 1;        
	    data = FXCollections.observableArrayList();
	    
	    try{
	    	
	        String SQL = "Select * from facilities_parts_list";             //Order By id
	        ResultSet rs = con.createStatement().executeQuery(SQL);
	        
	        while(rs.next()){
	        	
	            Part facilitiesPart = new Part();
	            facilitiesPart.id.set(listNum++);  
	            
	            if(rs.getString("image") != null) {
	            	
		            Image img = new Image(rs.getString("image"));
		            ImageView imageView = new ImageView();
		            imageView.setImage(img);
		            facilitiesPart.image.set(imageView);
		            imageView.setFitWidth(120);
		            imageView.setFitHeight(80);
		            
	            } else {
	            	
		            Image img = new Image("/images/NoImageFound.png");
			        ImageView imageView = new ImageView();
			        imageView.setImage(img);
			        facilitiesPart.image.set(imageView);
			        imageView.setFitWidth(120);
			        imageView.setFitHeight(80);
			        
	            }
	            
	            facilitiesPart.vendor_part_number.set(rs.getString("vendor_part_number"));
	            facilitiesPart.description.set(rs.getString("description"));
	            facilitiesPart.quantity.set(rs.getInt("quantity"));
	            facilitiesPart.vendor_name.set(rs.getString("vendor_name"));
	            facilitiesPart.model_number.set(rs.getString("model_number"));
	            facilitiesPart.equipment_id.set(rs.getString("equipment_id"));
	            data.add(facilitiesPart); 
	            
	        }
	        
	        tableView.setItems(data);
	        
	    } catch(Exception e){
	    	
	        e.printStackTrace();
	          
	    }
	    
	    filterData();
	    
	}
	// Opens the Users database as a new tab.
	@FXML
	private void userWindow(ActionEvent event){
		
		try {
			
			Tab tab = new Tab();
            tabs.getTabs().add(tab);
            tab.setText("Users");
            tab.setContent((Node) FXMLLoader.load(this.getClass().getClassLoader().getResource("fxml/Users.fxml")));
            SingleSelectionModel<Tab> selectionModel = tabs.getSelectionModel();
            selectionModel.select(tab);
            
		} catch (IOException e1) {
			
			e1.printStackTrace();
			
		}
	}
	// Filters the data from the TextField search box.
	private void filterData() {
		filterState = searchCB.getValue();
		filteredData = new FilteredList<>(data, p -> true);
		
		if (filterState.equals("Description")) {
			
			filterField.setPromptText("Filter: Description");
			filteredData.predicateProperty().bind(Bindings.createObjectBinding(() ->
		    part -> part.getEquipment_id().contains(equipIDFilterField.getText())
		           && part.getDescription().toLowerCase().contains(filterField.getText().toLowerCase()),

		    equipIDFilterField.textProperty(),
		    filterField.textProperty()
		    
			));
		} else if (filterState.equals("Model Number")) {
			
			filterField.setPromptText("Filter: Model Number");
			filteredData.predicateProperty().bind(Bindings.createObjectBinding(() ->
		    part -> part.getEquipment_id().contains(equipIDFilterField.getText())
		           && part.getModel_number().toLowerCase().contains(filterField.getText().toLowerCase()),

		    equipIDFilterField.textProperty(),
		    filterField.textProperty()
		    
			));
		} else if (filterState.equals("Vendor")) {
			
			filterField.setPromptText("Filter: Vendor Name");
			filteredData.predicateProperty().bind(Bindings.createObjectBinding(() ->
			part -> part.getEquipment_id().contains(equipIDFilterField.getText())
				&& part.getVendor_name().toLowerCase().contains(filterField.getText().toLowerCase()),

			equipIDFilterField.textProperty(),
			filterField.textProperty()
	    
			));
		}
	     
	     SortedList<Part> sortedData = new SortedList<>(filteredData);
	     sortedData.comparatorProperty().bind(tableView.comparatorProperty());
	     tableView.setItems(sortedData);
		
	}
	// Creates a new Search result for the selected database.
	@FXML
	public void searchButtonClicked(ActionEvent event) {
			
		tab = new Tab();
			
		tab.setText("Search Results");
	    tabs.getTabs().add(tab);
	    tabs.getSelectionModel().select(tab); 
	    tab.setContent(element);
	        
	}
	// Opens user history information, items added, deleted, or removed, user login and log out.
	@FXML
	private void userHistoryMenuItemClicked(ActionEvent event) {

		date = dateLbl.getText();
		time = timeLbl.getText();
		
		try {
			
			Tab tab = new Tab();
            tabs.getTabs().add(tab);
            tab.setText("User History");
            tab.setContent((Node) FXMLLoader.load(this.getClass().getClassLoader().getResource("fxml/History.fxml")));
            SingleSelectionModel<Tab> selectionModel = tabs.getSelectionModel();
            selectionModel.select(tab);
            
		} catch (IOException e1) {
			
			e1.printStackTrace();
			
		}
	}
	// Adds a new item to the database. New NewItem Controller is called.
	@FXML
	public void addItemButtonClicked(ActionEvent event) {
		
		date = dateLbl.getText();
		time = timeLbl.getText();
		user = userLbl.getText();
			
		Stage window = new Stage();
		FXMLLoader loader;
		Parent root;
		
		try {
			
			loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/NewItem.fxml"));
			window.setTitle("Add new item");
			root = (Parent)loader.load();
			
			Scene scene = new Scene(root);				
			scene.getStylesheets().add(getClass().getClassLoader().getResource("styles/application.css").toExternalForm());
			
			window.initModality(Modality.APPLICATION_MODAL);
			window.setScene(scene);
			window.show();
			
			NewItemController newItemController = (NewItemController)loader.getController();
			newItemController.window = window;
			newItemController.updateItemBtn.setVisible(false);
			newItemController.setUserVariables(user, time, date);
				
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}				
	}
	// Edits an item from the database. New NewItem Controller is called.
	@FXML
	public void editItemButtonClicked(ActionEvent event) {
		
		date = dateLbl.getText();
		time = timeLbl.getText();
		user = userLbl.getText();
		
		try{
			
			selectedPart = tableView.getSelectionModel().getSelectedItem().getModel_number();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
    	if (selectedPart != null) {
    		
    		DBConnection dBC = new DBConnection();
    		con = dBC.getDBConnection();
    		Stage window = new Stage();
    		FXMLLoader loader;
    		Parent root;
    		
			try {
				
				loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/NewItem.fxml"));
				window.setTitle("Edit item");
				root = (Parent)loader.load();
				
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getClassLoader().getResource("styles/application.css").toExternalForm());
				
				window.initModality(Modality.APPLICATION_MODAL);
				window.setScene(scene);
				window.show();
				
				if (departmentCB.getValue() == null) {
					departmentCB.setValue(aseptic);
				}
				NewItemController editItemController = (NewItemController)loader.getController();
				editItemController.window = window;
				editItemController.saveItemBtn.setVisible(false);
				editItemController.setUserVariables(user, time, date);
				editItemController.databaseChoiceBox.setValue(departmentCB.getValue().toLowerCase());
				
				ResultSet rs = null;
			    PreparedStatement selectedItemStmt = null;
			    Connection con = null;
			    
			    try {
			    	
			    	con = dBC.getDBConnection();
			    	String sql = "select * from " + state + "_parts_list where model_number = ?";
	
			    	selectedItemStmt = con.prepareStatement(sql); // create a statement
			    	selectedItemStmt.setString(1, selectedPart); // set input parameter
			    	rs = selectedItemStmt.executeQuery();
			    	// extract data from the ResultSet
			    	while (rs.next()) {
			    		
			    		id = rs.getInt(1);
			    		manufacturer_name = rs.getString(2);
			    		model_number = rs.getString(3);
			    		vendor_name = rs.getString(4);
			    		vendor_part_number = rs.getString(5);
			    		tolmar_part_number = rs.getString(6);
			    		part_location = rs.getString(7);
			    		price = rs.getDouble(8);
			    		quantity = rs.getInt(9);
			    		min = rs.getInt(10);
			    		max = rs.getInt(11);
			    		img = rs.getString(12);
			    		equipment_group = rs.getString(13);
		    			equipment_id = rs.getString(14);
		    			additional_notes = rs.getString(15);
		    			description = rs.getString(16);
		    			
		    			
		    			if (img == null) {
		    				img = "/images/NoImageFound.png";
		    			}
		    			
		            	editItemController.setTextItems(id, manufacturer_name, model_number, vendor_name, vendor_part_number, tolmar_part_number, part_location, price, quantity, 
						    				min, max, img, equipment_group, equipment_id, additional_notes, description);
		            	editItemController.setDBState(this.state);
		            	Image image = new Image(img);
			        	editItemController.imageView.setImage(image);
		            
		            	  
		    		}
		    	} catch (Exception e) {
		    	
		    		e.printStackTrace();
		      
		    	} finally {
		    	
		    		try {
		    		
		    			rs.close();
		    			selectedItemStmt.close();
		    			con.close();
		    		
		    		} catch (SQLException e) {
		    		
		    			e.printStackTrace();
		    		
		    		}
		    	}
			} catch(Exception e) {
			
				e.printStackTrace();
			
			}
    	}else {
    	
    		JOptionPane.showMessageDialog(null, "No Item selected");
    		
    	}	
	}
	// Deletes an item from the database. Uses the Model Number associated with the observable list, queries the database, and removes all items with same Model Number.
	@FXML
	public void deleteItemButtonClicked(ActionEvent event) {
		
		try {
			
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
		    //get current date time with Date()
		    Date date = new Date();
		    //get current date time with Calendar()
		    DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		    Calendar cal = Calendar.getInstance();
			
			String selectedPart = tableView.getSelectionModel().getSelectedItem().getModel_number();
			String selectedDescription = tableView.getSelectionModel().getSelectedItem().getDescription();
			int selectedQuantity = tableView.getSelectionModel().getSelectedItem().getQuantity();
			// look into what I was trying to do here, not sure what equaling null is for.
			if (selectedPart == null) {
				
			}
			
			String sql = "DELETE FROM " + state.toLowerCase() + "_parts_list WHERE model_number = ?";
			PreparedStatement removeStmt = con.prepareStatement(sql);
			
			removeStmt.setString(1, selectedPart);
			SingleSelectionModel<Tab> selectionModel = tabs.getSelectionModel();
            selectionModel.select(resultsTab);
			boolean answer = ConfirmBox.display("Delete Item", "Are you sure you want to delete this item?");
			
			String max = "SELECT MAX(list) FROM history";
			String itemAdded = selectedQuantity + ", Description: " + selectedDescription + ", Model Number: " + selectedPart +" removed from " + state.toLowerCase() + "_parts_list database.";
			ResultSet res = con.createStatement().executeQuery(max);
			
			int maxNum = res.getInt(1);
			maxNum++;
			
			String newItemAdded = "Insert into history values (?, ?, ?, ?, ?)";
			PreparedStatement newItemStmt = con.prepareStatement(newItemAdded);
			
			newItemStmt.setInt(1, maxNum);
			newItemStmt.setString(2, dateFormat.format(date));
			newItemStmt.setString(3, timeFormat.format(cal.getTime()));
			newItemStmt.setString(4, user);
			newItemStmt.setString(5, itemAdded);
			newItemStmt.executeUpdate();
			
			res.close();
			
			if(answer) {
				
				removeStmt.executeUpdate();
				
				if (state.equals(aseptic)) {
					
			    	 buildDataAseptic();
			    	 
			     } else if (state.equals(general)) {
			    	 
			    	 buildDataGeneral();
			    	 
			     } else if (state.equals(facilities)) {
			    	 
			    	 buildDataFacilities();
			    	 
			     }
			}	
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}	
	}
	// Closes the program, calls ConfirmBox.
	public void closeProgram(){
		
		boolean answer = ConfirmBox.display("Tolmar ", "Are you sure you want to exit?");
		
		if(answer) {
			
			loggerOut();
			System.exit(0);
			
		}
	}
	// Gets user String to display as label at bottom of program.
	public void GetUser (String user) {
		
		userLbl.setText(user);
		this.user = user;
		
	}
	// Hides Menu fields depending on admin rights from the database.
	public void setUserRights (String userLevel) {
		
		if (userLevel.equals("editor")) {
			
			usersMenu.setVisible(false);
			imageMenu.setVisible(false);
		
		}
	 	else if (userLevel.equals("user")) {
		
	 		usersMenu.setVisible(false);
			partsMenu.setVisible(false);
			imageMenu.setVisible(false);
		
	 	} else if (userLevel.equals("admin")) {
	 		
	 	}
	}
	// Sets the image folder location on preferred drive.
	public void imageLocationMenuItemClicked () {
{
    		
    		DBConnection dBC = new DBConnection();
    		con = dBC.getDBConnection();
    		Stage window = new Stage();
    		FXMLLoader loader;
    		Parent root;
    		
			try {
				
				loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/ImageDirectory.fxml"));
				window.setTitle("Set Image Folder Location");
				root = (Parent)loader.load();
				
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getClassLoader().getResource("styles/application.css").toExternalForm());
				
				window.initModality(Modality.APPLICATION_MODAL);
				window.setScene(scene);
				window.show();
				
				
				ResultSet rs = null;
			    PreparedStatement query = null;
			    ResultSet rs2 = null;
			    PreparedStatement query2 = null;
			    Connection con = null;
			    
			    try {
			    	
			    	con = dBC.getDBConnection();
			    	String sql = "SELECT image_address from image_address WHERE rowid = 1";
			    	String sql2 = "SELECT image_address from image_address WHERE rowid = 2";
	
			    	query = con.prepareStatement(sql); // create a statement
			    	rs = query.executeQuery();
			    	// extract data from the ResultSet
			    	
			    	image_address = rs.getString(1);
			    	
			    	query2 = con.prepareStatement(sql2); // create a statement
			    	rs2 = query2.executeQuery();
			    	
			    	String backup_address = rs2.getString(1);
			    	
					ImageDirectoryController imageDirectoryController = (ImageDirectoryController)loader.getController();
					imageDirectoryController.setImageDirectory(image_address, backup_address);
			    		
		    	} catch (Exception e) {
		    	
		    		e.printStackTrace();
		      
		    	} finally {
		    	
		    		try {
		    		
		    			rs.close();
		    			query.close();
		    			con.close();
		    		
		    		} catch (SQLException e) {
		    		
		    			e.printStackTrace();
		    		
		    		}
		    	}
			} catch(Exception e) {
			
				e.printStackTrace();
			
			}
    	}
	}
	// records date and time of user log in.
	/*public void logger() {

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
	    //get current date time with Date()
	    Date date = new Date();
	    //get current date time with Calendar()
	    DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	    Calendar cal = Calendar.getInstance();
		// Record user login info
		try {
	    String maxID = "SELECT MAX(list) FROM history";
		String log = userLbl.getText() + " logged in";
		ResultSet res = con.createStatement().executeQuery(maxID);
			
		int maxNum = res.getInt(1);
		maxNum++;
		String logger = "Insert into history values (?, ?, ?, ?, ?)";
		PreparedStatement logStmt = con.prepareStatement(logger);
		logStmt.setInt(1, maxNum);
		logStmt.setString(2, dateFormat.format(date));
		logStmt.setString(3, timeFormat.format(cal.getTime()));
		logStmt.setString(4, userLbl.getText());
		logStmt.setString(5, log);
		logStmt.executeUpdate();
		
		res.close();
		
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
	}*/
	// records date and time of user log out.
	public void loggerOut() {

		// Establish a connection to the database.
		DBConnection dBC = new DBConnection();
	    Connection con = dBC.getDBConnection();
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
	    //get current date time with Date()
	    Date date = new Date();
	    //get current date time with Calendar()
	    DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	    Calendar cal = Calendar.getInstance();
		// Record user login info
		try {
			
		    String maxID = "SELECT MAX(list) FROM history";
			String log = user + " logged off";
			ResultSet res = con.createStatement().executeQuery(maxID);
				
			int maxNum = res.getInt(1);
			maxNum++;
			
			String logger = "Insert into history values (?, ?, ?, ?, ?)";
			PreparedStatement logStmt = con.prepareStatement(logger);
			
			logStmt.setInt(1, maxNum);
			logStmt.setString(2, dateFormat.format(date));
			logStmt.setString(3, timeFormat.format(cal.getTime()));
			logStmt.setString(4, user);
			logStmt.setString(5, log);
			logStmt.executeUpdate();
			
			res.close();
			con.close();
			
		} catch (Exception e) {
				
			JOptionPane.showMessageDialog(null, e);
				
		}
	}
}



