import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class tuna extends JPanel{
	
	private JButton b;
	private Color color = (Color.WHITE);
	private JPanel panel;
	
	public tuna(){
		
		super();
		panel = new JPanel();
		panel.setBackground(color);
		
		b = new JButton("Choose a color");
		b.addActionListener( 
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						color = JColorChooser.showDialog(null, "Pick your color", color);
					}
				}
				);
	}

}
