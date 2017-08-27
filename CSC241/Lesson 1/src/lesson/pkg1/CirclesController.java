/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lesson.pkg1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author John
 */
public class CirclesController {
    
    CirclesPanel circlesPanel;
    CirclesView view;
    Circles circles;
    
    public CirclesController(CirclesView view, CirclesPanel circlesPanel) {
        
        this.view = view;
        this.circlesPanel = circlesPanel;
        
        this.view.addReDrawListener(new ReDrawListener());
        
    }
    
    class ReDrawListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            circlesPanel.repaint();
            
        }
    
        
    
    }
}
