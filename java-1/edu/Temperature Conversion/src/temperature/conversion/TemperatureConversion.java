package temperature.conversion;

import javax.swing.JFrame;

/**
 * @author John Kirch, S01581562
 * @version 1.0 03-22-2017, CSC-240 Assignment 8
 */
public class TemperatureConversion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        TemperatureConversionFrame temperatureConversionFrame = new TemperatureConversionFrame();
        temperatureConversionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        temperatureConversionFrame.setSize(400, 250);
        temperatureConversionFrame.setVisible(true);
    }
    
}
