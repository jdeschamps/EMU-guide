package de.embl.rieslab.emuguide.uiproperties;

import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;

import de.embl.rieslab.emu.ui.ConfigurablePanel;
import de.embl.rieslab.emu.ui.swinglisteners.SwingUIListeners;
import de.embl.rieslab.emu.ui.uiproperties.MultiStateUIProperty;
import de.embl.rieslab.emu.utils.exceptions.UnknownUIPropertyException;

public class MultiStateUIPropertyButtonGroup extends ConfigurablePanel{

	private static final long serialVersionUID = 1L;
	
	//////// Property
	public final static String PROP = "Property";
	
	//////// Initial parameter
	public final static int NUM_POS = 6;
	
	//////// JComponents
	private final ButtonGroup buttonGroup;;
	private JToggleButton[] toggles;
	
	public MultiStateUIPropertyButtonGroup(String label) {
		super(label);
		
		buttonGroup = new ButtonGroup();
		toggles = new JToggleButton[NUM_POS];
		
		for(int i=0;i<NUM_POS;i++) {
			// Creates a new JToggleButton
			toggles[i] = new JToggleButton("Button"+i);
		
			// Adds it to the ConfigurablePanel
			this.add(toggles[i]);
			
			// and to the ButtonGroup
			buttonGroup.add(toggles[i]);
		}
	}


	@Override
	protected void initializeProperties() {
		/* 
		 * Adds a MultiStateUIProperty with NUM_POS positions, each corresponding to a single JToggleButton. 
		 */
		addUIProperty(new MultiStateUIProperty(this, PROP, "Property with a fixed number of states.", NUM_POS));	
	}

	@Override
	protected void propertyhasChanged(String propertyName, String newvalue) {	
		if(PROP.equals(propertyName)){ // Makes sure the property is PROP
			try {
				// Retrieves the current selected position index from the MultiStateUIProperty
				int pos = ((MultiStateUIProperty) getUIProperty(PROP)).getStateIndex(newvalue);
			
				// Selects the corresponding JToggleButton
				toggles[pos].setSelected(true);
				
			} catch (UnknownUIPropertyException e) { // necessary in case PROP is not a known UIProperty
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void initializeInternalProperties() {
		// Not used here.
	}

	@Override
	public void internalpropertyhasChanged(String propertyName) {
		// Not used here.
	}

	@Override
	protected void initializeParameters() {
		// Not used here.
	}

	@Override
	protected void parameterhasChanged(String parameterName) {
		// Not used here.
	}

	@Override
	protected void addComponentListeners() {
		/*
		 *  MultiStateUIProperty accept indices as input to select the property state. 
		 *  Therefore, here, we add a listener to the button group that updates a 
		 *  UIProperty with the newly selected button index within the ButtonGroup.
		 */
		SwingUIListeners.addActionListenerOnSelectedIndex(this, PROP, buttonGroup);
	}

	@Override
	public String getDescription() {
		return "Example of button group linked to a MultiStateUIProperty.";
	}

	@Override
	public void shutDown() {
		// Do nothing		
	}
}
