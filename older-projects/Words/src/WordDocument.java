import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class WordDocument extends JScrollPane{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private JTextArea text;
	
	public WordDocument() {
		this("untitled.txt", new JTextArea());
	}
	
	public WordDocument(String name, JTextArea text) {
		super(text);
		this.name = name;
		this.text = text;
		
	}
	
	public void save(){
		saveAs(name);
	}
	
	public void saveAs(String path){
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));
		
			bw.write(text.getText());
			
			bw.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public JTextArea getText(){
		return text;
	}
	
	@Override
	public String getName(){
		return name;
	}
}
