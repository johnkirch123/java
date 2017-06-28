package grids;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * Controller for the Simple grid generating program.
 *
 * @author    John Kirch, S01581562
 * @version   04-07-2017, CSC-240 Assignment 9, Exercise 13.11
 */
public class GridsController 
{
    private String title;                   // title for the first JFrame.
    private String title2;                  // title for the second JFrame.
    private final GridsView view;           // reference to the view.
    private final JFrame frame1;            // reference to the first frame.
    private final JFrame frame2;            // reference to the second frame.
    private final GridsModel model;         // reference to the model.
    private GridsJPanel gridsJPanel;        // JPanel for the first frame.
    private GridsJPanel gridsJPanel2;       // JPanel for the second frame.
    /**
     * initializes the model and passes the frame through to the model.
     * 
     * @param view keeps a reference to the view for the simple grids application.
     * @param frame1 reference to the first frame.
     * @param frame2 reference to the second frame.
     */
    public GridsController(GridsView view, JFrame frame1, JFrame frame2)
    {
        this.view = view;
        this.frame1 = frame1;
        this.frame2 = frame2;
        model = new GridsModel(frame1, frame2);
    }
    /**
     * 
     * @return gridJPanel returns the JPanel for frame1.
     */
    public JComponent getCanvas()
    {
        return gridsJPanel;
    }
    /**
     * 
     * @return gridJPanel2 returns the JPanel for frame2.
     */
    public JComponent getCanvas2()
    {
        return gridsJPanel2;
    }
    /**
     * 
     * @return title returns the title for frame1.
     */
    public String getTitle()
    {
        return title;
    }
    /**
     * 
     * @return title2 returns the title for frame2.
     */
    public String getTitle2()
    {
        return title2;
    }
    /**
     * initializes both of the JPanels for the frames and sets the titles.
     */
    public void initialize()
    {
        gridsJPanel = new GridsJPanel(model);
        gridsJPanel2 = new GridsJPanel(model);
        title = "Grid 1";
        title2 = "Grid 2";
    }
}
