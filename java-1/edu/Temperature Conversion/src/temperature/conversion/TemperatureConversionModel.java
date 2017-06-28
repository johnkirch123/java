package temperature.conversion;

import javax.swing.JOptionPane;

/**
 * @author John Kirch, S01581562
 * @version 1.0 03-22-2017, CSC-240 Assignment 8
 */
public class TemperatureConversionModel 
{
    private final static String CELSIUS = "Celsius";
    private final static String FAHRENHEIT = "Fahrenheit";
    private final static String KELVIN = "Kelvin";
    private final static double CELSIUS_ABS_ZERO = -273.15;
    private final static double FAHRENHEIT_ABS_ZERO = -459.67;
    private final static double KELVIN_ABS_ZERO = 0.00;
    TemperatureConversionFrame tempConversion = new TemperatureConversionFrame();
    double temp;
    /**
     * 
     * @param temp input of the temperature value.
     * @param conversionTempFrom temperature type being converted from.
     * @param conversionTempTo temperature type being converted to.
     */
    public TemperatureConversionModel(double temp, String conversionTempFrom, String conversionTempTo)
    {
        
        if (conversionTempFrom.equals(KELVIN))
        {
            switch (conversionTempTo)
            {
                case CELSIUS:
                    this.temp = temp - 273.15;
                    break;
                case FAHRENHEIT:
                    this.temp = 9 * (temp - 273.15) / 5 + 32;
                    break;
                case KELVIN:
                    this.temp = temp;
            }
            if (temp < KELVIN_ABS_ZERO) 
            {
                JOptionPane.showMessageDialog(tempConversion, "Impossible Temperature");
                this.temp = KELVIN_ABS_ZERO;
            }
        }
        
        if (conversionTempFrom.equals(CELSIUS))
        {
            switch (conversionTempTo)
            {
                case CELSIUS:
                    this.temp = temp;
                    break;
                case FAHRENHEIT:
                    this.temp = 9 * temp / 5 + 32;
                    break;
                case KELVIN:
                    this.temp = temp + 273.15;
                    break;
            }
            if (temp < CELSIUS_ABS_ZERO)
            {
                JOptionPane.showMessageDialog(tempConversion, "Impossible Temperature");
                this.temp = CELSIUS_ABS_ZERO;
            }
        }
        
        if (conversionTempFrom.equals(FAHRENHEIT))
        {
            
            switch (conversionTempTo)
            {
                case CELSIUS:
                    this.temp = ( temp - 32 ) * 5 / 9;
                    break;
                case FAHRENHEIT:
                    this.temp = temp;
                    break;
                case KELVIN:
                    this.temp = 5 * ( temp - 32) / 9 + 273.15;
                    break;
            }
        }
        if (temp < FAHRENHEIT_ABS_ZERO)
        {
            JOptionPane.showMessageDialog(tempConversion, "Impossible Temperature");
            this.temp = FAHRENHEIT_ABS_ZERO;
        }
    }
    // Getter for temp.
    public double getTemp()
    {
        return temp;
    }
}
