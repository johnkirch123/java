/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.pkg9.triangles;


/**
 *
 * @author John
 */
public class Controller 
{
    private final Model model;
    private final View view;
    private String title;
    
    public Controller (View view)
    {
        this.model = new Model();
        this.view = view;
        model.addObserver(this.view);
    }
    
    public void initialize()
    {
        title = "Triangles";
    }
    
    public String getTitle()
    {
        return title;
    }
}
