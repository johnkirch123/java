
package edu.frontrange.csc240.a8;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import static java.util.Collections.unmodifiableList;

/**
 * The Controller is used to interpret actions taken by the user on the View. It
 * contains the logic for inputs that happen on the View and for passing data on to
 * the Model. It performs its own validation (such as the format of the
 * entered numbers) or by informing the user of any problems found by the Model.
 *
 * @author	Dr. Bruce K. Haddon, Instructor
 * @version	2.0, 2016-10-15, Assignment 8, Exercise 12.13
 */
public class Controller
{
/**
 * Format for displaying non-integral values on the View
 */
private static final String FORMAT = "%.2f";

/**
 * The maximum number of buttons to use, otherwise use a combo box. The number of
 * buttons determined by looking at width of produced box.
 */
private static final int MAXIMUM_BUTTONS = 5;

/**
 * (GUI) A list of JComponents to act as selectors of temperature scales "from"
 * which to convert.
 */
private List<JComponent> fromSelectors;

/**
 * (GUI) The text field into which the user enters the requested temperature
 */
private JTextField inputField;

/**
 * (GUI) Label for text field into which the user enters the requested temperature
 */
private JLabel inputFieldLabel;

/**
 * Keep a reference to the current model.
 */
private final Model model;

/**
 * (GUI) The text field used to display the converted temperature
 */
private JTextField outputField;

/**
 * (GUI) Label for text field used to display the converted temperature
 */
private JLabel outputFieldLabel;
/**
 * The title of the application, to go on the window.
 */
private String title;

/**
 * (GUI) A list of JComponents to act as selectors of temperature scales "to" which
 * to convert.
 */
private List<JComponent> toSelectors;

/**
 * The view for which this is the controller.
 */
private final View view;

/**
 * Set the view for which this is the controller.
 *
 * @param view	the view for which this is to be the controller
 */
public Controller(View view)
{
	/* The model is instantiated. This class is responsible for holding and
	   maintaining the relationships between the various representations of the data
	   (there really is only one datum--the current temperature being display (which
	   may be in one or more scales--but it is the one temperature. */
	this.model = new Model();

	/* Remember the view. */
	this.view = view;

	/* Register the View as an observer on the model. */
	model.addObserver(this.view);
}

/**
 * Get a list of JComponents to act as selectors of temperature scales "from"
 * which to convert.
 *
 * @return		the "from" selectors
 */
public List<JComponent> getFromSelectors()
{
	return unmodifiableList(fromSelectors);
}


/**
 * The field into which the user will enter the temperature to be converted.
 *
 * @return the inputField
 */
public JTextField getInputField()
{
	return inputField;
}
/**
 * Label for field into which the user will enter the temperature to be converted.
 *
 * @return		the inputFieldLabel
 */
public JLabel getInputFieldLabel()
{
	return inputFieldLabel;
}

/**
 * Get the model being used by the controller.
 *
 * @return		the model
 */
public Model getModel()
{
	return model;
}

/**
 * The text field in which the converted temperature will be shown to the user.
 *
 * @return the outputField
 */
public JTextField getOutputField()
{
	return outputField;
}

/**
 * Label for text field in which the converted temperature will be shown to the user.
 *
 * @return		the outputFieldLabel
 */
public JLabel getOutputFieldLabel()
{
	return outputFieldLabel;
}

/**
 * Title for the window or elsewhere.
 *
 * @return		the title
 */
public String getTitle()
{
	return title;
}

/**
 * Get a list of JComponents to act as selectors of temperature scales "to"
 * which to convert.
 *
 * @return		the "to" selectors
 */
public List<JComponent> getToSelectors()
{
	return unmodifiableList(toSelectors);
}

/**
 * Create and initialize the "control" components that are going to be requested
 * and organized by the View.
 */
@SuppressWarnings("Convert2Lambda")
public void initialize()
{
	/* True here indicates that the selectors are for the "from" part of the
	   application. */
	fromSelectors = createSelectors(true);

	/* Create an input field for the user to enter the "from" temperature. The same
	   action is taken for the typing of ENTER as for losing the focus by moving
	   the mouse or typing TAB. */
	inputField = new JTextField();
	inputField.setEditable(true);
	inputField.setBackground(Color.WHITE);
	inputField.addActionListener(new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			inputValue();
		}
	});
	inputField.addFocusListener(new FocusListener()
	{
		/* On losing focus, treat as completion action. */
		@Override
		public void focusLost(FocusEvent e)
		{
			inputValue();
		}

		/* No action required. */
		@Override
		public void focusGained(FocusEvent e) {}
	});

	/* Label for the input field and its controls. */
	inputFieldLabel = new JLabel("Convert from:");

	/* Output field to display the converted temperature. This field cannot be
	   changed by the user. And the label for the field and its controls. */
	outputField = new JTextField();
	outputField.setEditable(false);
	outputFieldLabel = new JLabel("Convert to:");

	/* Title for the application. */
	title = "Temperature Conversion";

	/* False here indicates that the selectors are for the "to" part of the
	   application. */
	toSelectors = createSelectors(false);
}

/**
 * Set up the method of selection of the temperature scales. If there is a large
 * number (greater than the allowed MAXIMUM_BUTTONS), use a combo box, otherwise
 * use a row of radio buttons.
 *
 * @param from		indicates whether these selectors are for the "from" scales,
 *					otherwise the "to" scales.
 * @return			a list of the selector components
 */
private List<JComponent> createSelectors(boolean from)
{
	List<JComponent> selectors = new LinkedList<>();

	/* Decide the form of the selectors. */
	if( model.getScales().length > MAXIMUM_BUTTONS )
	{
		SelectionCombo box = new SelectionCombo(model.getScales(), from);
		box.initialize();
		selectors.add(box);
	} else
	{
		/* The button group for grouping the radio buttons. */
		ButtonGroup group = new ButtonGroup();

		/* Get the buttons (in order by the serial position). */
		for( Scale scale : model.getScales() )
		{
			SelectionButton button = new SelectionButton(scale, from);
			button.setSelected(scale ==
						(from ? model.getSourceScale() : model.getTargetScale()));
			group.add(button);
			button.initialize();
			selectors.add(button);
		}
	}
	return Collections.unmodifiableList(selectors);
}

/**
 * If the value is an actual integral value, format as an integer, otherwise format
 * according to the definition of FORMAT.
 *
 * @param value	a vale to represent
 * @return		a String representation
 */
private String formatted(double value)
{
	long version = (long) value;
	return version == value ? Long.toString(version) : String.format(FORMAT, value);
}

/**
 * When the input is changed, if possible, convert the input field into a standard
 * format for display in the converted temperature.
 *
 * @throws NumberFormatException    if the value is not acceptable in format
 * @throws IllegalArgumentException if the input scale is not consistent with the
 *                                  current set temperature and output scale
 */
private void inputValue()
{
	String message = null;
	/* In case no reasonable input is found. */
	model.setInvalid();
	/* If there is nothing in the input field, simply leave the output field blank
	   and return. */
	outputField.setText("");
	String inputText = inputField.getText().trim();
	if( inputText == null || inputText.isEmpty() ) return;

	/* If the input is a legitimate numeric value, make that the new value for the
	   field, replacing the default, otherwise let the NumberFormatExceptio be
	   thrown. If there is input, actually attempt to set the new temperature in the
	   model. If the value is not acceptable, it will throw IllegalStateException.
	   The action of setting a new temperature value makes the source valid
	   again. */
	try
	{
		inputField.setBackground(Color.PINK);
		double value = Double.parseDouble(inputText.replaceAll("\\s|,|_", ""));
		String representation = String.format(FORMAT, value);
		/* Ensure the conversion is of the actual displayed value. */
		value = Double.parseDouble(representation);
		model.setSourceTemperature(value);
		/* Show the value actually held by the model. */
		inputField.setText(formatted(model.getSourceTemperature()));
	} catch( NumberFormatException ex )
	{
		message = "Please correct the format of the number";
	} catch( IllegalStateException ex )
	{
		message = "This temperature is colder than absolute zero";
	}
	/* A message indicates something went wrong, so show the message if needed,
	   and return. */
	if( message != null )
	{
		view.displayErrorMessage(inputField, "Error Message", message);
		return;
	}
	/* It should be OK now to just show the converted temperature. */
	outputField.setText(formatted(model.getTargetTemperature()));
	inputField.setBackground(Color.WHITE);
}

/**
 * Given a scale selected by a selector, inform the model, and cause an update of
 * values.
 *
 * @param scale		the selected scale
 * @param from		true if this is a "from" scale, otherwise a "to" scale
 */
private void setSelectedScale(Scale scale, boolean from)
{
	try
	{
		if( from ) model.setSourceScale(scale);
		else model.setTargetScale(scale);
	} catch( IllegalStateException ex ) { }
	inputValue();
}

/**
 * Specialized representation of a radio button for use in the temperature panel,
 * wrapping Scale as the selected item.
 */
@SuppressWarnings("serial")
private class SelectionButton extends JRadioButton implements ActionListener
{
/**
 * Available so that which button has been selected may be determined.
 */
private final Scale scale;

/**
 * and whether this is a "from" button or a "to" button.
 */
private final boolean from;

/**
 * Each button represents one of the available temperature scales.
 *
 * @param scale		the scale to represent
 * @param from		true if this is a "from" button, false if a "to" button
 */
SelectionButton(Scale scale, boolean from)
{
	/* Show the title for this scale. */
	super(scale.toString());

	/* Remember what scale is represented, and whether this is a from or to
	   button. */
	this.scale = scale;
	this.from = from;
}

/**
 * ActionListener method for selecting buttons.
 *
 * @param event	the source event
 */
@Override
public void actionPerformed(ActionEvent event)
{
	setSelectedScale(scale, from);
}

/**
 * Initialize the operation of the selection button.
 */
public void initialize()
{
	/* Add an listener for a change, i.e., when a new button is selected. */
	addActionListener(this);
}
}

/**
 * Specialized representation of a combo box for use in the temperature panel,
 * wrapping a Scale value as the selected item.
 */
@SuppressWarnings("serial")
private class SelectionCombo extends JComboBox<Scale> implements ItemListener
{
/**
 * Whether this is a "from" box or a "to" box.
 */
private final boolean from;

/**
 * The combo box allows the selection of one of the specified scales
 *
 * @param scales	the scales to represent in the combo box
 * @param from		true if this is a "from" box, false if a "to" box
 */
SelectionCombo(Scale[] scales, boolean from)
{
	/* Set the scales for the combo box. */
	super(scales);
	setEnabled(true);
	/* Remember the direction, and set the initial selection. */
	this.from = from;
	setSelectedItem(this.from ? model.getSourceScale() : model.getTargetScale());
}

/**
 * Initialize the operation of the combo box.
 */
public void initialize()
{
	/* Add an listener for a change, i.e., when a box item is selected. */
	addItemListener(this);
}

/**
 * ItemListener method for selecting items on the combo box.
 *
 * @param e		the source event
 */
@Override
public void itemStateChanged(ItemEvent e)
{
	if( e.getStateChange() == ItemEvent.SELECTED )
	{
		Scale scale = (Scale) e.getItem();
		if( from ) model.setSourceScale(scale);
		else model.setTargetScale(scale);
	}
	/* Update all the values. */
	inputValue();
}
}
}
