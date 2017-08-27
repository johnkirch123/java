package lesson.pkg1;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author John
 */
public class CirclesView extends JFrame{
    
    CirclesPanel circlesPanel = new CirclesPanel();
    Button reDrawButton = new Button("ReDraw");
    
    public CirclesView(String title) {
        
        super(title);
        
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(circlesPanel, BorderLayout.CENTER);
        panel.add(reDrawButton, BorderLayout.SOUTH);
        this.add(panel);
    }
    
    public void addReDrawListener(ActionListener listenForReDraw) {
        
        reDrawButton.addActionListener(listenForReDraw);
        
    }
}
