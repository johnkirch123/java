package grids;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * GridsView for the Simple grid generating program.
 *
 * @author    John Kirch, S01581562
 * @version   04-07-2017, CSC-240 Assignment 9, Exercise 13.11
 */
public class GridsView extends JFrame
{   // initializes the JFrames.
    JFrame frame1 = new JFrame();
    JFrame frame2 = new JFrame();
    
    public void start()
    {
        GridsController controller = new GridsController(this, frame1, frame2);
        controller.initialize();
        // initializes the JComponents from the JPanel.
        JComponent canvas = controller.getCanvas();
        JComponent canvas2 = controller.getCanvas2();
        // runs the JFrame on the EDT.
        SwingUtilities.invokeLater(new Runnable()
        {
            /**
             * The run method of the anonymous class.
             */
            @Override
            public void run()
            {
                // build the first frame.
                frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame1.setSize(400, 400);
                frame1.setTitle(controller.getTitle());
                frame1.add(canvas);
                frame1.setVisible(true);
                // build the second frame.
                frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame2.setSize(400, 400);
                frame2.setTitle(controller.getTitle2());
                frame2.add(canvas2);
                frame2.setLocation(400, 0);
                frame2.setVisible(true);
            }
        });
    }
    /*
     * instantiates an instance of GridsView. Runs method start.
     */
    public static void launch()
    {
        GridsView view = new GridsView();
        view.start();
    }
}
