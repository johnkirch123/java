/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grids;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Model for the Simple grid generating program.
 *
 * @author    John Kirch, S01581562
 * @version   04-07-2017, CSC-240 Assignment 9, Exercise 13.11
 */
public class GridsModel
{
    GridsController controller;                             // keeps a reference to the controller.
    private final JFrame frame1;                            // keeps a reference to frame1.
    private final JFrame frame2;                            // keeps a reference to frame2.
    private final static int ROWS_COLUMNS = 30;             // rows and columns count.
    private int distance = (ROWS_COLUMNS * ROWS_COLUMNS) - 36;   // initial distance of lines for frame1.
    private int distance2 = distance;                       // intial distance of lines for frame2. 
    private int height1;                                    // height of frame1.
    private int height2;                                    // height of frame2.
    private int width1;                                     // width of frame1.
    private int width2;                                     // width of frame2.
    /**
     * 
     * @param frame1 keeps a frame1 reference for dimensions.
     * @param frame2 keeps a frame2 reference for dimensions.
     */
    public GridsModel (JFrame frame1, JFrame frame2)
    {
        this.frame1 = frame1;
        this.frame2 = frame2;
    }
    // draws the grids for the JPanel.
    public void draw(Graphics g)
    {
        if(ROWS_COLUMNS < 2)
        {
            JOptionPane.showMessageDialog(frame1, "Number of rows and columns are too small");
        }
        int spacing2 = distance2/ROWS_COLUMNS;      // dynamic spacing value for grid 2.
        int spacing1 = distance/ROWS_COLUMNS;       // dynamic spacing value for grid 1.
        height1 = frame1.getHeight();               // height of frame1.
        height2 = frame2.getHeight();               // height of frame2.
        width1 = frame1.getWidth();                 // width of frame1.
        width2 = frame2.getWidth();                 // width of frame2.
        int x = 5;                                  // x value integer for frame1.
        int y = 5;                                  // y value integer for frame1.
        int j = 5;                                  // x value integer for frame2.
        int k = 5;                                  // y value integer for frame2.
        
        if(frame1.hasFocus())
        {
            // draws the rows and columns for frame1.
            for(int i = 0; i < ROWS_COLUMNS; i++)
            {
                g.setColor(Color.BLACK);
                g.drawLine(5, y, distance, y);
                g.drawLine(x, 5, x, distance);
                
                if (height1 >= width1)
                {
                    x = x + width1/ROWS_COLUMNS;
                    y = y + width1/ROWS_COLUMNS;
                } else
                {
                    x = x + height1/ROWS_COLUMNS;
                    y = y + height1/ROWS_COLUMNS;
                }
            }
        } else
        {
            // draws the rows and columns for frame2.
            for(int i = 0; i < ROWS_COLUMNS; i++)
            {
                g.setColor(Color.BLACK);
                g.drawLine(5, k, distance2, k);
                g.drawLine(j, 5, j, distance2); 
                
                if (height2 >= width2)
                {
                    j = j + width2/ROWS_COLUMNS;
                    k = k + width2/ROWS_COLUMNS;
                } else 
                {
                    j = j + height2/ROWS_COLUMNS;
                    k = k + height2/ROWS_COLUMNS;
                }
            }
        }
        // changes the line length when the frame is re-drawn.
        distance = x - spacing1;
        distance2 = j - spacing2;
        
    }
}
