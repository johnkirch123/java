package tolmar.inventory.app.tolmarinventoryapplication;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class ImageDirectoryController implements Initializable{
	
	@FXML private AnchorPane imageDirectory;
	@FXML private Label imageDirLbl, backupLbl;
	@FXML private Button setLocationButton, backupButton;
	@FXML private TextField imageDirPathTxtField;
	@FXML private CheckBox autoSaveCB;

	private Boolean bool;
	Stage window;
	String image_directory;
	DBConnection dBC = new DBConnection();
	Connection con;
	// Initializes autosave checkbox.
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		String boolQuery = "SELECT bool FROM auto_save WHERE rowid = 1";
	    con = dBC.getDBConnection();
	    ResultSet rs = null;
	    
	    try {
	    	
			rs = con.createStatement().executeQuery(boolQuery);
			bool = rs.getBoolean(1);
			 
			if (bool) {
				
				autoSaveCB.setSelected(true);
				
			}
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, e);
			
		} finally {
			
			try {
				
				rs.close();
				
			} catch (SQLException e) {
				
				JOptionPane.showMessageDialog(null, e);
				
			}
		}
	}
	// Sets the address save labels so the user knows where the images are located.
	public void setImageDirectory(String image_address, String backup_address) {

		imageDirLbl.setText(image_address);
		backupLbl.setText(backup_address);
		
	}
	// Sets the location of the primary directory for the parts images.
	@FXML
	public void setLocationButtonClicked () {
		
		String imagesPath = imageDirPathTxtField.getText() +  "tolmarImages\\";
		File files = new File(imagesPath + "asepticImages");
		File generalFiles = new File(imagesPath + "generalImages");
		File facilitiesFiles = new File(imagesPath + "facilitiesImages");
		
		boolean answer = ConfirmBox.display("Set Image", "Are you sure you want to set this location?");
		
		if(answer) {
		
	        if (!files.exists()) {
	            if (files.mkdirs() && generalFiles.mkdirs() && facilitiesFiles.mkdirs()) {
	            	
	            	JOptionPane.showMessageDialog (null, "New Image directories have been created!", "Image directory created", JOptionPane.INFORMATION_MESSAGE);
	            	
	            } else {
	            	
	            	JOptionPane.showMessageDialog (null, "Failed to create multiple directories!", "Image directory not created", JOptionPane.INFORMATION_MESSAGE);
	                
	            }
	        }
	        
		    String updateStmt = "UPDATE image_address SET image_address = ? WHERE rowid = ?";
		    
		    try {
		    	
				PreparedStatement myStmt = con.prepareStatement(updateStmt);
				myStmt.setString(1, imageDirPathTxtField.getText());
				myStmt.setInt(2, 1);
				myStmt.executeUpdate();
				myStmt.close();
				
				imageDirPathTxtField.clear();
				
			} catch (SQLException e) {
				
				JOptionPane.showMessageDialog(null, e);
			}
		}
	}
	// Sets the location of the backup directory for the parts images.
	@FXML
	public void backupButtonClicked () {
		
		String backupStatus = null;
		
		if (backupLbl.getText().equals("")&& !imageDirPathTxtField.getText().equals("")) {
			
			backupStatus = imageDirPathTxtField.getText();
			
		} else if (!imageDirPathTxtField.getText().equals("")) {
			
			backupStatus = imageDirPathTxtField.getText();
			
		} else if (!backupLbl.getText().equals("")){
		
			 backupStatus = backupLbl.getText();
			 
		} else {
				
			JOptionPane.showMessageDialog(null, "You must create a directory.", "No directory created", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		boolean answer = ConfirmBox.display("Set Image", "Are you sure you want to backup the images?");
		
		if(answer) {
	        
		    String updateStmt = "UPDATE image_address SET image_address = ? WHERE rowid = 2";
		    
		    try {
		    	
				PreparedStatement myStmt = con.prepareStatement(updateStmt);
				myStmt.setString(1, backupStatus);
				myStmt.executeUpdate();
				myStmt.close();
				
				String source = imageDirLbl.getText() + "tolmarImages";
		        File srcDir = new File(source);

		        String destination = backupStatus + "tolmarImages";
		        File destDir = new File(destination);

		        try {
		            
		            FileUtils.copyDirectory(srcDir, destDir);
		            JOptionPane.showMessageDialog(null, "Images copied successfully.", "Images copied", JOptionPane.INFORMATION_MESSAGE);
		            
		        } catch (IOException e) {
		        	
		        	JOptionPane.showMessageDialog(null, e);
		            
		        }
				
			} catch (SQLException e) {
				
				JOptionPane.showMessageDialog(null, e);
				
			}
		}    
	}
	// Handles the checkbox for the autosave feature. If there is no backup location specified then you will not have autosave functionality.
	@FXML
	private void autoSave () {
		
		if (autoSaveCB.isSelected() && !backupLbl.getText().equals("")) {
			
			bool = true;
			
		    String updateStmt = "UPDATE auto_save SET bool = ? WHERE rowid = 1";
		    
		    try {
		    	
		    	PreparedStatement myStmt = con.prepareStatement(updateStmt);
				myStmt.setBoolean(1, bool);
				myStmt.executeUpdate();
				myStmt.close();

				JOptionPane.showMessageDialog(null, "Auto save is activated");
				
			} catch (SQLException e) {
				
				JOptionPane.showMessageDialog(null, e);
			} 
		} else {
			
			bool = false;
			
		    String updateStmt = "UPDATE auto_save SET bool = ? WHERE rowid = 1";
		    
		    try {
		    	
		    	PreparedStatement myStmt = con.prepareStatement(updateStmt);
				myStmt.setBoolean(1, bool);
				myStmt.executeUpdate();
				myStmt.close();
				
			} catch (SQLException e) {
				
				JOptionPane.showMessageDialog(null, e);
				
			}
		}
	}
}
