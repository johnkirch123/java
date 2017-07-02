package tolmar.inventory.app.tolmarinventoryapplication;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class User {

		public int id1;
		public String fn;
		public String ln;
		public String un;
		public String p;
		public String em;
		public String a;

		public SimpleIntegerProperty id = new SimpleIntegerProperty();
		public SimpleStringProperty firstName = new SimpleStringProperty();
		public SimpleStringProperty lastName = new SimpleStringProperty();
		public SimpleStringProperty username = new SimpleStringProperty();
		public SimpleStringProperty password = new SimpleStringProperty();
		public SimpleStringProperty email = new SimpleStringProperty();
		public SimpleStringProperty userLevel = new SimpleStringProperty();
		
		
		public int getId() {
			return id.get();
		}
		
		public String getFirst_name() {
			return firstName.get();
		}
		
		public String getLast_name() {
			return lastName.get();
		}
		
		public String getUsername() {
			return username.get();
		}
		
		public String getPassword() {
			return password.get();
		}
		
		public String getEmail() {
			return email.get();
		}
		
		public String getUser_level() {
			return userLevel.get();
		}
		
		public void setID (Integer id) {
			this.id1 = id;
		}
		
		public void setFirst_name(String firstname) {
			this.fn = firstname;
		}
		
		public void setLast_name(String lastname) {
			this.ln = lastname;
		}
		
		public void setUsername(String username) {
			this.un = username;
		}
		
		public void setPassword(String password) {
			this.p = password;
		}
		
		public void setEmail(String email) {
			this.em = email;
		}
		
		public void setUser_level(String userlevel) {
			this.a = userlevel;
		}
}
