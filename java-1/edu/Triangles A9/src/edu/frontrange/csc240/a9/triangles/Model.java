/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.frontrange.csc240.a9.triangles;

import java.awt.Graphics;
import java.security.SecureRandom;
import javax.swing.JPanel;

/**
 *
 * @author John
 */
public class Model extends JPanel
{
    SecureRandom random;
    
    public Model()
    {
        random = new SecureRandom();
    }
    
    public void initialize()
    {
        
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
    }
}
