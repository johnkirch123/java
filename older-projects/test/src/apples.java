import javax.swing.*;

public class apples {

	public static void main(String args[]){
		JFrame f = new JFrame("Title");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tuna t = new tuna();
		f.add(t);
		f.setSize(400, 250);
		f.setVisible(true);
	}

}
