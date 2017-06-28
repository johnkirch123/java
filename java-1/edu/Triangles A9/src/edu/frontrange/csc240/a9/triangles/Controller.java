/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.frontrange.csc240.a9.triangles;

/**
 *
 * @author John
 */
public class Controller 
{
    View view;
    Model model;
    
    public Controller(View view)
    {
        model = new Model();
        this.view = view;
    }
    
    public void initialize()
    {
        model.initialize();
    }
}
