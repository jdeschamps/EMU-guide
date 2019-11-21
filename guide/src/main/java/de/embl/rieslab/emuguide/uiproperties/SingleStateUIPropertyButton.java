package de.embl.rieslab.emuguide.uiproperties;

import javax.swing.JButton;

import de.embl.rieslab.emu.ui.ConfigurablePanel;
import de.embl.rieslab.emu.ui.swinglisteners.SwingUIListeners;
import de.embl.rieslab.emu.ui.uiproperties.SingleStateUIProperty;
import de.embl.rieslab.emu.utils.exceptions.IncorrectUIPropertyTypeException;

public class SingleStateUIPropertyButton extends ConfigurablePanel {

	private static final long serialVersionUID = 1L;

	private static final String PROP = "Property";
	private JButton button;
	
	public SingleStateUIPropertyButton(String label) {
		super(label);

		button = new JButton("Action");
		this.add(button);
	}

	@Override
	protected void initializeProperties() {
		addUIProperty(new SingleStateUIProperty(this, PROP, "A UIProperty with a single state linked to a JButton."));
	}

	@Override
	protected void propertyhasChanged(String propertyName, String newvalue) {
		// Nothing to be done as JButton are not selectable.
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
			/*
			 *  Adds a listener setting the property to its single-state 
			 *  every time the button is pressed.
			 */
			SwingUIListeners.addActionListenerToSingleState(this, PROP, button);
		} catch (IncorrectUIPropertyTypeException e) { // in case PROP is not a SingleStateUIProperty
			e.printStackTrace();
		}
	}

	@Override
	public String getDescription() {
		return "A Panel with a SingleStateUIProperty linked to a JButton.";
	}

	@Override
	public void shutDown() {
		// Do nothing.
	}

}
