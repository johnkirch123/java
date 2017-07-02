import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Main extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	public static void main(String[] args){
		new Main().setVisible(true);
	}
	
	private Main(){
		super("Tutorial - John");
		setSize(820,600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		
		JButton button = new JButton("Click me!");
		JButton button2 = new JButton("Exit");
		
		button.setActionCommand("Click me!");
		button.addActionListener(this);
		button2.setActionCommand("Exit");
		button2.addActionListener(this);
		
		JMenuBar bar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem newMenuItem = new JMenuItem("New");
		JMenuItem save = new JMenuItem("Save");
		JMenuItem close = new JMenuItem("Exit");
		close.addActionListener(this);
		
		JMenuItem extra = new JMenu("Extra");
		JMenuItem hello = new JMenuItem("Hello");
		JMenuItem hello2 = new JMenuItem("Hello 2");
		
		extra.add(hello);
		extra.add(hello2);
		
		file.add(newMenuItem);	
		file.add(save);
		file.add(extra);
		file.addSeparator();
		file.add(close);
		
		bar.add(file);
		
		add(button);
		add(button2);
		setJMenuBar(bar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = e.getActionCommand();
		
		if (name.equals("Click me!")){
			System.out.println("Button has been clicked!");
		}
			else if(name.equals("Exit")){
				System.out.println("Closed");
				System.exit(0);
			}
		
	}
}
