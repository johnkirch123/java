
package edu.frontrange.csc240.a8;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import static javax.swing.SwingConstants.CENTER;

/**
 * The ViewSelectionPanel has three components: a label at the top left, a panel
 * containing the selection method for the scale, one for each temperature system,
 * and a text field, for inputting or displaying the numeric value of the
 * temperature.
 *
 * @author	Dr. Bruce K. Haddon, Instructor
 * @version	2.0, 2016-10-15, Assignment 8, Exercise 12.13
 */
@SuppressWarnings("serial")
public class ViewSelectionPanel extends JPanel
{
/**
 * Minimum space either side of the input and output text fields.
 */
private static final int TEXT_FIELD_HORIZONAL_PADDING = 50;  // pixels

/**
 * The width of the field in which temperatures are entered or displayed.
 */
private static final int TEXT_FIELD_WIDTH = 10;				// nominal characters

/**
 * Minimum space between the input section at the top and the one at the bottom.
 */
private static final int TOP_BOTTOM_PANEL_PADDING = 20;		// pixels

/**
 * The text field for displaying temperature as selected (or input)
 */
private JTextField temperatureField;

/**
 * The controller for this view to use for all actions.
 */
private final Controller controller;

/**
 * Indicates whether this is the "From" or "To" panel.
 */
private final boolean from;

/**
 * Constructor.
 *
 * @param controller	the controller to be used by this view
 * @param from			whether panel is the "From" or "To" panel
 */
public ViewSelectionPanel(Controller controller, boolean from)
{
	this.controller = controller;
	this.from = from;
}

/**
 * Initialize the selection panel. The panel has one or more JComponents for the
 * selection of a scale.
 */
public void initialize()
{
	List<JComponent> list = from ?
			controller.getFromSelectors() : controller.getToSelectors();
	/* The panel used for holding the selection component(s). */
	JPanel selectorPanel = new JPanel();
	/* Default layout for a JPanel is FlowLayout. */
	for( JComponent component : list ) selectorPanel.add(component);

	temperatureField = from ?
			controller.getInputField() : controller.getOutputField();
	temperatureField.setColumns(TEXT_FIELD_WIDTH);
	temperatureField.setHorizontalAlignment(CENTER);

	JLabel temperatureLabel = from ?
			controller.getInputFieldLabel() : controller.getOutputFieldLabel();

	/* Place three components into the temperature panel in vertical order. The
	   layout manager with the most control for doing this is a GridBagLayout. */
	GridBagConstraints gb = new GridBagConstraints();
	setLayout(new GridBagLayout());

	gb.gridx = 0;
	gb.gridy = 0;
	Insets insets = new Insets(0, 10, 0, 0);
	gb.insets = insets;
	gb.anchor = GridBagConstraints.LINE_START;
	add(temperatureLabel, gb);
	gb.insets = new Insets(0, 0, 0,  0);

	gb.gridx = 0;
	gb.gridy = 1;
	gb.anchor = GridBagConstraints.CENTER;
	add(selectorPanel, gb);

	gb.gridx = 0;
	gb.gridy = 2;
	gb.anchor = GridBagConstraints.CENTER;
	gb.insets = new Insets(0,
			TEXT_FIELD_HORIZONAL_PADDING,
			TOP_BOTTOM_PANEL_PADDING,
			TEXT_FIELD_HORIZONAL_PADDING);
	add(temperatureField, gb);
}
}
