/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.pkg9.triangles;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.SwingUtilities;

/**
 *
 * @author John
 */
public class View extends JFrame implements Observer
{
    private Controller controller;
    private ViewPanel viewPanel;
    
    public void start()
    {
        controller = new Controller(this);
        controller.initialize();
        
        setTitle(controller.getTitle());
        
        setLayout(new FlowLayout());
        
        setBackground(Color.BLACK);
        viewPanel = new ViewPanel(controller);
        add(viewPanel);
        viewPanel.initialize();
        
        /* Start the GUI using only the dispatch thread. */
	SwingUtilities.invokeLater(new Runnable()
	{
		/**
		 * The run method is executed on the EDT
		 */
		@Override
		public void run()
		{
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setSize(300, 300);
			setVisible(true);		// display frame
		}
	});
    }
    
    /**
     * Launch the view, by creating the view object, and calling start.
     */
    public static void launch()
    {
            View view = new View();
            view.start();
    }
    
    /**
     * The callback from the observed model whenever the Model changes.
     *
     * @param observable the object being observed
     * @param object any report from that object (not used)
     */
   @Override
   public void update(Observable observable, Object object)
   {
           repaint();
   }
}
