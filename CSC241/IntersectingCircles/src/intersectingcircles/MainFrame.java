package intersectingcircles;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author John Kirch S01581562
 * Lesson 1 Intersecting Circles v1.0
 * 
 * Main frame for the view.
 */
public class MainFrame extends JFrame{
    
    CirclesPanel circlesPanel = new CirclesPanel();
    private final JPanel panel;
    Button reDrawButton = new Button("ReDraw");
    // Contstructor for the layout using BorderLayout. Added 2 panels, south has 
    // the redraw button and center has the canvas.
    public MainFrame(String title) {
        
        super(title);
        
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(circlesPanel, BorderLayout.CENTER);
        panel.add(reDrawButton, BorderLayout.SOUTH);
        initializePanel();
        
        reDrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                circlesPanel.repaint();
            }
        });
    }
    
    private void initializePanel() {
        
        this.add(panel);
    }
    
}
