package de.embl.rieslab.emuguide.uiproperties;

import javax.swing.JComboBox;

import de.embl.rieslab.emu.ui.ConfigurablePanel;
import de.embl.rieslab.emu.ui.swinglisteners.SwingUIListeners;
import de.embl.rieslab.emu.ui.uiproperties.MultiStateUIProperty;
import de.embl.rieslab.emu.utils.exceptions.UnknownUIPropertyException;

public class MultiStateUIPropertyComboBox extends ConfigurablePanel{

	private static final long serialVersionUID = 1L;
	
	private static final String PROP = "Property";
	private static final int NUM_POS = 6;
	private JComboBox<String> cbx;

	public MultiStateUIPropertyComboBox(String label) {
		super(label);
	
		String[] states = new String[NUM_POS];
		String state = "State ";
		for(int i= 0;i<NUM_POS;i++) {
			states[i] = state+i;
		}
		
		cbx = new JComboBox<String>(states);
		this.add(cbx);
	}

	@Override
	protected void initializeProperties() {
		addUIProperty(new MultiStateUIProperty(this, PROP, "UIProperty with a fixed number of states linked to a JComboBox.", NUM_POS));
	}

	@Override
	protected void propertyhasChanged(String propertyName, String newvalue) {
		if(PROP.equals(propertyName)) {
			try {
				// Retrieves the index corresponding to the new value
				int index = ((MultiStateUIProperty) this.getUIProperty(PROP)).getStateIndex(newvalue);
				
				// Sets the JComboBox to correct selected index
				cbx.setSelectedIndex(index);
				
			} catch (UnknownUIPropertyException e) { // in case PROP is not a MultiStateUIProperty
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
		// Adds a component listener to cbx that change the UIProperty according to the selected index.
		SwingUIListeners.addActionListenerOnSelectedIndex(this, PROP, cbx);
	}

	@Override
	public String getDescription() {
		return "A panel with a MultiStateUIProperty linked to a JComboBox.";
	}

	@Override
	public void shutDown() {
		// Do nothing.
	}

}
