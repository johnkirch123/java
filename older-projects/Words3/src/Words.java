import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

public class Words extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTextArea text;

	public static void main(String[] args){
		new Words().setVisible(true);
	}
	
	private Words(){
		super("Word Processor");
		setSize(1000,800);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		initialize();
	}
	private void initialize(){
		text = new JTextArea();
		JMenuBar bar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem newItem = new JMenuItem("New");
		JMenuItem open = new JMenuItem("Open");
		JMenuItem save = new JMenuItem("Save");
		JMenuItem saveAs = new JMenuItem("Save As");
		JMenuItem exit = new JMenuItem("Exit");
		
		
		setJMenuBar(bar);
		bar.add(file);
		file.add(newItem);
		file.addSeparator();
		file.add(open);
		file.add(save);
		file.add(saveAs);
		file.addSeparator();
		file.add(exit);
		
		JMenuItem[] items = { newItem, open, save, saveAs, exit };
		for(JMenuItem item : items){
			item.addActionListener(this);
		}
		
		
		add(text);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("New")){
			
		} else if(e.getActionCommand().equals("Open")){
			open();
		}else if(e.getActionCommand().equals("Save")){
			save();
		}else if(e.getActionCommand().equals("Save As")){
			save();
		}else if(e.getActionCommand().equals("Exit")){
			System.exit(0);
		}
	}
	

	private void open(){
		try {
			BufferedReader br = new BufferedReader(new FileReader("file.txt"));
			String line;
			
			while((line = br.readLine()) != null){
				text.append(line + "\n");
			}
			
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void save() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("file.txt"));
			
			bw.write(text.getText());
			
			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
