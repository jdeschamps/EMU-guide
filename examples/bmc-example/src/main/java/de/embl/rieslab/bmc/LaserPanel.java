package de.embl.rieslab.bmc;

import javax.swing.BoxLayout;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import de.embl.rieslab.emu.ui.ConfigurablePanel;
import de.embl.rieslab.emu.ui.swinglisteners.SwingUIListeners;
import de.embl.rieslab.emu.ui.uiparameters.StringUIParameter;
import de.embl.rieslab.emu.ui.uiproperties.RescaledUIProperty;
import de.embl.rieslab.emu.ui.uiproperties.TwoStateUIProperty;
import de.embl.rieslab.emu.ui.uiproperties.UIProperty;
import de.embl.rieslab.emu.utils.EmuUtils;
import de.embl.rieslab.emu.utils.exceptions.IncorrectUIPropertyTypeException;
import de.embl.rieslab.emu.utils.exceptions.UnknownUIParameterException;
import de.embl.rieslab.emu.utils.exceptions.UnknownUIPropertyException;

public class LaserPanel extends ConfigurablePanel {
	private JSlider slider;
	private JToggleButton button;

	// the constructor only uses Swing
	public LaserPanel(String title) {
		super(title);
		
		// border
		this.setBorder(new TitledBorder(null, "Laser", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		
		// slider
		slider = new JSlider();
		slider.setMajorTickSpacing(20);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setOrientation(SwingConstants.VERTICAL);
		this.add(slider);

		// toggle button
		button = new JToggleButton("On/Off");
		this.add(button);
	}
	
	// keys used to label the GUI properties
	private final String PERCENTAGE = "power percentage";
	private final String OPERATION = "on/off";
		
	@Override
	protected void initializeProperties() {
	   // Descriptions are shown in the EMU configuration help window
	   String desc1 = "Description percentage";
	   String desc2 = "Description operation";
	   this.addUIProperty(new RescaledUIProperty(this, label(PERCENTAGE), desc1));
	   this.addUIProperty(new TwoStateUIProperty(this, label(OPERATION), desc2));
	}

	// method to avoid property name collisions between panels
	private String label(String property) {
		return this.getPanelLabel() + " " + property;
	}
	
	@Override
	protected void addComponentListeners() {
		// SwingUIListeners is an EMU class
		SwingUIListeners.addActionListenerOnIntegerValue(this, label(PERCENTAGE), slider);
		try {
			SwingUIListeners.addActionListenerToTwoState(this, label(OPERATION), button);
		} catch (IncorrectUIPropertyTypeException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void propertyhasChanged(String propertyName, String newvalue) {
		if (label(PERCENTAGE).equals(propertyName)) {
			if (EmuUtils.isNumeric(newvalue)) {
				// RescaledUIProperty values can represent a float or an int
				int val = (int) Float.parseFloat(newvalue);
				slider.setValue(val); // Swing call
			}
		} else if (label(OPERATION).equals(propertyName)) {
			try {
				UIProperty prop = this.getUIProperty(label(OPERATION));

				// we check if newvalue corresponds to the TwoStateUIProperty ON state
				boolean onValue = ((TwoStateUIProperty) prop).isOnState(newvalue);
				button.setSelected(onValue); // Swing call
			} catch (UnknownUIPropertyException e) {
				e.printStackTrace();
			}
		}
	}
	
	private final String TITLE = "title";

	@Override
	protected void initializeParameters() {
		String desc = "Description"; // shown in the EMU configuration help window
		String def = "Default title"; // default value in the EMU configuration
		this.addUIParameter(new StringUIParameter(this, TITLE, desc, def));
	}

	@Override
	protected void parameterhasChanged(String parameterName) {
		if (TITLE.equals(parameterName)) {
			try {
				String newTitle = this.getStringUIParameterValue(TITLE);
				((TitledBorder) this.getBorder()).setTitle(newTitle); // Swing call
			} catch (UnknownUIParameterException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * The following methods were not shown in the BMC publication and correspond
	 * to additional mechanisms. Refer to the EMU guide:
	 * https://jdeschamps.github.io/EMU-guide/configurablepanel.html
	 */
	
	@Override
	protected void initializeInternalProperties() {}

	@Override
	public void internalpropertyhasChanged(String propertyName) {}

	@Override
	public String getDescription() {
		return "";
	}

	@Override
	public void shutDown() {}
	
}