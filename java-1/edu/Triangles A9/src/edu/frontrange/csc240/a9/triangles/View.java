/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.frontrange.csc240.a9.triangles;

import java.awt.Color;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.SwingUtilities;

/**
 *
 * @author John
 */
public class View extends JFrame
{
    Controller controller;
    
    public void start()
    {
        controller = new Controller(this);
        
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
                    pack();
                    setSize(400, 400);
                    setVisible(true);		// display frame
		}
	});
    }
    
    public static void launch()
    {
        View view = new View();
        view.start();
    }
}
