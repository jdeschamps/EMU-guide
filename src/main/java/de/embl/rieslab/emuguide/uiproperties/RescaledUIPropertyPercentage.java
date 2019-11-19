package de.embl.rieslab.emuguide.uiproperties;

import javax.swing.JSlider;
import javax.swing.SwingConstants;

import de.embl.rieslab.emu.ui.ConfigurablePanel;
import de.embl.rieslab.emu.ui.swinglisteners.SwingUIListeners;
import de.embl.rieslab.emu.ui.uiparameters.IntegerUIParameter;
import de.embl.rieslab.emu.ui.uiproperties.RescaledUIProperty;
import de.embl.rieslab.emu.utils.EmuUtils;
import de.embl.rieslab.emu.utils.exceptions.IncorrectUIParameterTypeException;
import de.embl.rieslab.emu.utils.exceptions.UnknownUIParameterException;
import de.embl.rieslab.emu.utils.exceptions.UnknownUIPropertyException;

public class RescaledUIPropertyPercentage extends ConfigurablePanel{

	private static final long serialVersionUID = 1L;

	//////// JComponent
	private JSlider slider;
	
	//////// Property and parameter
	public final static String PROP_PERCENTAGE = "Percentage";
	
	public RescaledUIPropertyPercentage(String label) {
		super(label);
		
		// JSlider with values ranging from 0 to 100 (default)
		slider = new JSlider();
		slider.setMajorTickSpacing(20);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setOrientation(SwingConstants.VERTICAL);
		add(slider);
	}

	@Override
	protected void initializeProperties() {
		/*
		 * RescaledUIProperty:
		 *
		 * If the device property linked to this UIProperty is a percentage, then offset
		 * should be set to 0 and slope to 1. If the device property is not a percentage,
		 * then the offset should be set to 0 and the slope to (max device property)/100.
		 */
		String description = "Percentage property: set the offset to 0 and the slope to (max device property)/100 or 100 if the device property is already a percentage.";
		addUIProperty(new RescaledUIProperty(this, PROP_PERCENTAGE,description));
	}

	@Override
	protected void propertyhasChanged(String propertyName, String newvalue) {
		/*
		 * This method is called when the property has changed outside of this
		 * ConfigurablePanel. 
		 */
		
		if(PROP_PERCENTAGE.equals(propertyName)) { // if the change concerns the percentage
			// Let's test if the value is a number
			if(EmuUtils.isNumeric(newvalue)) {
				// JSlider accept only an integer, in case it is a double, we round it up
				int val = (int) Double.parseDouble(newvalue);
				
				// We make sure it is a value between 0 and 100
				if (val >= 0 && val <= 100) {
					// sets the value of the JSLider
					slider.setValue(val);
				}
			}
		}
	}

	@Override
	protected void initializeInternalProperties() {
		// Not used here
	}

	@Override
	public void internalpropertyhasChanged(String propertyName) {
		// Not used here
	}

	@Override
	protected void initializeParameters() {
		// Not used here
	}


	@Override
	protected void parameterhasChanged(String parameterName) {
		// Not used here
	}


	@Override
	protected void addComponentListeners() {
		// Updates the UIProperty when the user interacts with the JSlider
		SwingUIListeners.addActionListenerOnIntegerValue(this, PROP_PERCENTAGE, slider);
	}

	@Override
	public String getDescription() {
		return "Example of a RescaledUIProperty used for a percentage.";
	}

	@Override
	public void shutDown() {
		// Do nothing
	}

}
