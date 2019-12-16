package de.embl.rieslab.emuguide.uiproperties;

import javax.swing.JToggleButton;

import de.embl.rieslab.emu.ui.ConfigurablePanel;
import de.embl.rieslab.emu.ui.swinglisteners.SwingUIListeners;
import de.embl.rieslab.emu.ui.uiproperties.TwoStateUIProperty;
import de.embl.rieslab.emu.utils.exceptions.IncorrectUIPropertyTypeException;
import de.embl.rieslab.emu.utils.exceptions.UnknownUIPropertyException;

public class TwoStateUIPropertyToggleButton extends ConfigurablePanel{

	private static final long serialVersionUID = 1L;

	////// Property
	private final String PROP = "Property";
	
	////// JComponent
	private JToggleButton button;
	
	public TwoStateUIPropertyToggleButton(String label) {
		super(label);
		
		button = new JToggleButton();
		this.add(button);
	}

	@Override
	protected void initializeProperties() {
		// Adds a TwoStateUIProperty
		addUIProperty(new TwoStateUIProperty(this, PROP, "UIProperty with two states linked to a JToggleButton."));
	}

	@Override
	protected void propertyhasChanged(String propertyName, String newvalue) {
		if(PROP.equals(propertyName)) { // Makes sure propertyName is PROP
			try {
				// Checks if newvalue corresponds to the On state of the TwoStateUIProperty.
				boolean b = ((TwoStateUIProperty) this.getUIProperty(propertyName)).isOnState(newvalue);	
				
				// Sets the button to the corresponding state.
				button.setSelected(b);
			} catch (UnknownUIPropertyException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void initializeInternalProperties() {
		// Do nothing.
	}

	@Override
	public void internalpropertyhasChanged(String propertyName) {
		// Do nothing.
	}

	@Override
	protected void initializeParameters() {
		// Do nothing.
	}

	@Override
	protected void parameterhasChanged(String parameterName) {
		// Do nothing.
	}

	@Override
	protected void addComponentListeners() {
		try {
			SwingUIListeners.addActionListenerToTwoState(this, PROP, button);
		} catch (IncorrectUIPropertyTypeException e) { // in case PROP is not a TwoStateUIProperty
			e.printStackTrace();
		}
	}

	@Override
	public String getDescription() {
		return "A panel with a TwoStateUIProperty linked to a JToggleButton.";
	}

	@Override
	public void shutDown() {
		// Do nothing.
	}

}
