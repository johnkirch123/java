import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class Words extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private JTabbedPane tabPane;
	
	public static void main(String[] args){
		new Words().setVisible(true);
	}
	
	private Words(){
		super("John's Text Box");
		setSize(800,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		initialize();
	}
	
	private void initialize(){
		tabPane = new JTabbedPane();
		
		WordDocument doc = new WordDocument();
		tabPane.addTab(doc.getName(), doc);
		
		JMenuBar bar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem newDoc = new JMenuItem("New");
		JMenuItem open = new JMenuItem("Open");
		JMenuItem save = new JMenuItem("Save");
		JMenuItem saveas = new JMenuItem("Save As");
		JMenuItem exit = new JMenuItem("Exit");
		
		JMenuItem[] items = {newDoc, open, save, saveas, exit};
		for(JMenuItem item : items){
			item.addActionListener(this);
		}
		
		file.add(newDoc);
		file.add(open);
		file.add(save);
		file.add(saveas);
		file.addSeparator();
		file.add(exit);
		
		bar.add(file);
		
 		add(tabPane);
		setJMenuBar(bar);
	}
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("New")){
			WordDocument doc = new WordDocument();
			tabPane.addTab(doc.getName(), doc);
		}else if(e.getActionCommand().equals("Open")){
				open();
		}else if(e.getActionCommand().equals("Save")){
			save();
		}else if(e.getActionCommand().equals("Save As")){
			saveAs();
		}else if(e.getActionCommand().equals("Exit")){
			System.exit(0);
		}
	}
	private void open(){
		JFileChooser chooser = new JFileChooser("./");
		
		int returned = chooser.showOpenDialog(this);
		
		if(returned == JFileChooser.APPROVE_OPTION){
			File file = chooser.getSelectedFile();
			
			WordDocument doc = new WordDocument(file.getName(), new JTextArea());
			
			tabPane.addTab(file.getName(), doc);
			
			try{
				BufferedReader br = new BufferedReader(new FileReader(file));
				
				String line;
				while((line = br.readLine()) != null){
					doc.getText().append(line + "\n");
				}
				
				br.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void save(){
		WordDocument doc = (WordDocument) tabPane.getSelectedComponent();
		doc.save();
	}
	
	private void saveAs(){
		JFileChooser chooser = new JFileChooser("./");
		
		int returned = chooser.showSaveDialog(this);
		
		if(returned == JFileChooser.APPROVE_OPTION){
			File file = chooser.getSelectedFile();
			
			WordDocument doc = (WordDocument) tabPane.getSelectedComponent();
			doc.saveAs(file.getAbsolutePath());
			
		}
	}
}
