package tolmar.inventory.app.tolmarinventoryapplication;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class HistoryController implements Initializable{
	
	@FXML private TableView<History> historyTable = new TableView<History>();
	@FXML private TableColumn<History, Integer> listCol;
	@FXML private TableColumn<History, String> dateCol;
	@FXML private TableColumn<History, String> timeCol;
	@FXML private TableColumn<History, String> usernameCol;
	@FXML private TableColumn<History, String> eventCol;
	@FXML private Pagination pagination;
	
	private ObservableList<History> historyData = FXCollections.observableArrayList();

	Connection con;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		 assert historyTable != null;
		 listCol.setCellValueFactory(
	 	       new PropertyValueFactory<History, Integer>("list"));
	     dateCol.setCellValueFactory(
	 	        new PropertyValueFactory<History, String>("date"));
	     timeCol.setCellValueFactory(
	 	        new PropertyValueFactory<History, String>("time"));
	     usernameCol.setCellValueFactory(
	 	        new PropertyValueFactory<History, String>("username"));  
	     eventCol.setCellValueFactory(                
	        new PropertyValueFactory<History, String>("event"));
	     
		 historyTable.setFixedCellSize(30.0);
		 
		 // Establish a connection to the database.
	     DBConnection dBC = new DBConnection();
	     con = dBC.getDBConnection();
	     
	     //populates the table with user data from the database.
	     historyData = FXCollections.observableArrayList();
	     
	     try{   
	    	 
	        String historyQuery = "Select * from history";             //Order By id
	        ResultSet result = con.createStatement().executeQuery(historyQuery);
	        		
	        while(result.next()){
	        	
	            History history = new History();
	            
	            history.list.set(result.getInt("list"));                       
	            history.date.set(result.getString("date"));
	            history.time.set(result.getString("time"));
	            history.username.set(result.getString("username"));
	            history.event.set(result.getString("event"));
	            historyData.add(history); 
	            
	        }
	        
	        historyTable.setItems(historyData);
	        FXCollections.reverse(historyData);
	        paginationCreation();
	        
	     } catch(Exception e){
	    	 
	          e.printStackTrace();
	          
	     }
	}
	
	public int rowsPerPage() {
        return 18;
    }
	
	private Node createPage(int pageIndex) {

	    int fromIndex = pageIndex * rowsPerPage();
	    int toIndex = Math.min(fromIndex + rowsPerPage(), historyData.size());
	    historyTable.setItems(FXCollections.observableArrayList(historyData.subList(fromIndex, toIndex)));
	    historyTable.setMinHeight(600);
	    return new VBox(historyTable);
	    
	}
	
	private void paginationCreation() {
		int numOfPages = 1;
	     
	     if (historyData.size() % rowsPerPage() == 0) {
	    	 
	    	 numOfPages = historyData.size() / rowsPerPage();
	    	 
	     } else if (historyData.size() > rowsPerPage()) {
	    	 
	    	 numOfPages = historyData.size() / rowsPerPage() + 1;
	    	 
	     }
	     
	     pagination.setPageCount(numOfPages);
	     pagination.setCurrentPageIndex(0);
	     pagination.setPageFactory(this::createPage);
	     pagination.setMaxHeight(80);
	}

}
