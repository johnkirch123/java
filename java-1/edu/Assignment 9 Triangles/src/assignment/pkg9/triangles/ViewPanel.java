/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.pkg9.triangles;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author John
 */
public class ViewPanel extends JPanel
{
    private final Controller controller;
    
    public ViewPanel(Controller controller)
    {
        this.controller = controller;
    }
    
    public void initialize()
    {
        repaint();
    }
    
    public void PaintComponent(Graphics g)
    {
        super.paintComponent(g);
        setBackground(Color.BLACK);
    }
}
