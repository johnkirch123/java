package temperature.conversion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * @author John Kirch, S01581562
 * @version 1.0 03-22-2017, CSC-240 Assignment 8
 */
public class TemperatureConversionFrame extends JFrame
{
    
    private final JTextField fromTemperatureJTextField;                         // User input field.
    private final JLabel inputTemperatureJLabel;                                // User information about the JTextField.
    private JLabel outputTemperatureJLabel;                                     // Converted temperature reading.
    private final JLabel conversionJLabel;                                      // User information about the outputTemperatureJLabel.
    private final JPanel TemperatureJPanel;                                     // Main JPanel for the GUI.
    private final JPanel fromRadioButtonJPanel;                                 // Radio Button Panel to group from radio buttons.
    private final JPanel toRadioButtonJPanel;                                   // Radio Button Panel to group to radio buttons.
    private final JPanel outputTemperatureJPanel;                               // JPanel for the Output area at bottom of GUI.
    private final JButton conversionButton;                                     // Conversion Button at bottom of GUI.
    private final ButtonGroup inputRadioGroup;                                  // Radio Button group for from radio buttons.
    private final ButtonGroup outputRadioGroup;                                 // Radio Button group for to radio buttons.
    private final JRadioButton[] radioButtons;                                  // Array for radio buttons.
    private final static String[] UNITS = {"Celsius", "Fahrenheit", "Kelvin"};  // Array for unit types.
    private String conversionTempFrom;                                          // String to pass to Model Constructor for from type.
    private String conversionTempTo;                                            // String to pass to Model Constructor for to type.
    /**
     * TemperatureConversionFrame constructor for building the GUI for the program.
     */
    public TemperatureConversionFrame ()
    {
        super("Temperature Conversion");
        // Creation of Panels and Input Labels from the top of the GUI.
        TemperatureJPanel = new JPanel(new BorderLayout());
        fromRadioButtonJPanel = new JPanel();
        toRadioButtonJPanel = new JPanel();
        inputTemperatureJLabel = new JLabel("Input the temperature you wish to convert: ");
        inputTemperatureJLabel.setHorizontalAlignment(JLabel.CENTER);
        inputTemperatureJLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        // Creation of JTextField for user input.
        fromTemperatureJTextField = new JTextField(20);
        fromTemperatureJTextField.setHorizontalAlignment(JTextField.CENTER);
        // Adds Lables to the radio button groups for user readability
        fromRadioButtonJPanel.add(new JLabel("From: "));
        toRadioButtonJPanel.add(new JLabel("To: "));
        // Creation of radio buttons.
        radioButtons = new JRadioButton[UNITS.length];
        // Creation of radio button groups
        inputRadioGroup = new ButtonGroup();
        outputRadioGroup = new ButtonGroup();
        /* Builds the radio buttons for the convert temperature from set, also
           registers radio buttons with ItemListener */
        for(int i = 0; i < UNITS.length; i++)
        {
            radioButtons[i] = new JRadioButton(UNITS[i]);
            inputRadioGroup.add(radioButtons[i]);
            fromRadioButtonJPanel.add(radioButtons[i]);
            radioButtons[i].addItemListener(new RadioButtonHandlerFrom(UNITS[i]));
        }
        /* Builds the radio buttons for the convert temperature to set, also
           registers radio buttons with ItemListener */
        for (int i = 0; i < UNITS.length; i++)
        {
            radioButtons[i] = new JRadioButton(UNITS[i]);
            outputRadioGroup.add(radioButtons[i]);
            toRadioButtonJPanel.add(radioButtons[i]);
            radioButtons[i].addItemListener(new RadioButtonHandlerTo(UNITS[i]));
        }
        /* Output information for the converted temperature at bottom of GUI 
           2 labels and the conversionButton */
        outputTemperatureJPanel = new JPanel(new BorderLayout());
        conversionJLabel = new JLabel("The converted temperature is: ");
        conversionJLabel.setHorizontalAlignment(JLabel.CENTER);
        conversionJLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        outputTemperatureJLabel = new JLabel("Temperature Output");
        outputTemperatureJLabel.setHorizontalAlignment(JLabel.CENTER);
        outputTemperatureJLabel.setFont(new Font("Serif", Font.PLAIN, 26));
        conversionButton = new JButton("Convert");
        /**
         * ActionListener for the conversionButton. Tests for radio button selection
         * and if something is entered into the JTextField, if not, user is prompted 
         * to enter correct information.
         */
        conversionButton.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent event)
                {
                    try
                    {
                        if (!fromTemperatureJTextField.getText().equals("") && inputRadioGroup.getSelection() != null && outputRadioGroup.getSelection() != null) 
                        {
                            DecimalFormat numberFormat = new DecimalFormat("#.00"); 
                            double temp = Double.parseDouble(fromTemperatureJTextField.getText());
                            TemperatureConversionModel tempConversionModel = new TemperatureConversionModel(temp, conversionTempFrom, conversionTempTo);
                            outputTemperatureJLabel.setText(String.valueOf(numberFormat.format(tempConversionModel.getTemp())));
                        } else {
                            JOptionPane.showMessageDialog(fromTemperatureJTextField, "Please enter a temperature value and/or conversion types to convert.");
                        }
                    } catch (NumberFormatException e)
                    {
                        JOptionPane.showMessageDialog(fromTemperatureJTextField, "Please enter a number.");
                        System.out.print(e);
                    }
                    
                }
            }
        );
        /* Initializes Panels and adds elements, removes overridable methods to a 
        private separate method. */
        initialize();
    }
    /**
     * Radio Button Handler for From radio buttons.
     * @param temp String to temporarily hold from temperature type.
     */
    private class RadioButtonHandlerFrom implements ItemListener
    {
        private final String temp;
        
        public RadioButtonHandlerFrom(String temp)
        {
            this.temp = temp;
        }
        
        @Override
        public void itemStateChanged (ItemEvent event)
        {
            if (temp.equals("Celsius")) conversionTempFrom = UNITS[0];
            else if (temp.equals("Fahrenheit")) conversionTempFrom = UNITS[1];
            else conversionTempFrom =  UNITS[2];
            
            try
            {
                DecimalFormat numberFormat = new DecimalFormat("#.00"); 
                double temp = Double.parseDouble(fromTemperatureJTextField.getText());
                TemperatureConversionModel tempConversionModel = new TemperatureConversionModel(temp, conversionTempFrom, conversionTempTo);
                outputTemperatureJLabel.setText(String.valueOf(numberFormat.format(tempConversionModel.getTemp())));

            } catch (NumberFormatException e){}
        }
    }
    /**
     * Radio Button Handler for To radio buttons.
     * @param temp String to temporarily hold to temperature type.
     */ 
    private class RadioButtonHandlerTo implements ItemListener
    {
        private final String temp;
        
        public RadioButtonHandlerTo (String temp)
        {
            this.temp = temp;
        }
        
        @Override
        public void itemStateChanged (ItemEvent event)
        {
            if (temp.equals("Celsius")) conversionTempTo = UNITS[0];
            else if (temp.equals("Fahrenheit")) conversionTempTo = UNITS[1];
            else conversionTempTo = UNITS[2];
            
            try
            {
                DecimalFormat numberFormat = new DecimalFormat("#.00"); 
                double temp = Double.parseDouble(fromTemperatureJTextField.getText());
                TemperatureConversionModel tempConversionModel = new TemperatureConversionModel(temp, conversionTempFrom, conversionTempTo);
                outputTemperatureJLabel.setText(String.valueOf(numberFormat.format(tempConversionModel.getTemp())));
            } catch (NumberFormatException e){}
        }
    }
    /**
     * Initializes GUI components and removes overridable methods from Constructor
     */
    private void initialize () 
    {
        TemperatureJPanel.add(fromRadioButtonJPanel, BorderLayout.NORTH);
        TemperatureJPanel.add(fromTemperatureJTextField, BorderLayout.CENTER);
        TemperatureJPanel.add(toRadioButtonJPanel, BorderLayout.SOUTH);
        add(inputTemperatureJLabel, BorderLayout.NORTH);
        add(TemperatureJPanel, BorderLayout.CENTER);
        add(outputTemperatureJPanel, BorderLayout.SOUTH);
        outputTemperatureJPanel.add(conversionJLabel, BorderLayout.NORTH);
        outputTemperatureJPanel.add(outputTemperatureJLabel, BorderLayout.CENTER);
        outputTemperatureJPanel.add(conversionButton, BorderLayout.SOUTH);
    }
}
