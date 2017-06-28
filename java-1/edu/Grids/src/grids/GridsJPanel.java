package grids;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * JPanel for the Simple grid generating program.
 *
 * @author    John Kirch, S01581562
 * @version   04-07-2017, CSC-240 Assignment 9, Exercise 13.11
 */
public class GridsJPanel extends JPanel 
{
    private final GridsModel model;
    
    public GridsJPanel(GridsModel model)
    {
        this.model = model;
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        model.draw(g);
        
    }
}
