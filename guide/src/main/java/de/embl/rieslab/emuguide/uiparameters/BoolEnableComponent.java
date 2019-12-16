package de.embl.rieslab.emuguide.uiparameters;

import javax.swing.JToggleButton;

import de.embl.rieslab.emu.ui.ConfigurablePanel;
import de.embl.rieslab.emu.ui.uiparameters.BoolUIParameter;
import de.embl.rieslab.emu.utils.exceptions.IncorrectUIParameterTypeException;
import de.embl.rieslab.emu.utils.exceptions.UnknownUIParameterException;

public class BoolEnableComponent extends ConfigurablePanel{


	private static final long serialVersionUID = 1L;
	
	// parameter
	public final String PARAM_LABEL = "Enable/disable button"; 
	
	// declares the component to access it in all methods
	private JToggleButton toggle;

	public BoolEnableComponent(String label) {
		super(label);
		
		// creates the component and adds it to the panel
		toggle = new JToggleButton("My button");
		this.add(toggle);
	}

	@Override
	protected void initializeProperties() {}

	@Override
	protected void initializeInternalProperties() {}

	@Override
	protected void initializeParameters() {
		boolean default_val = true;
		String description = "Enable/disable the JToggleButton.";
		
		// adds the BoolUIParameter	
		this.addUIParameter(new BoolUIParameter(this, PARAM_LABEL, description, default_val));
	}

	@Override
	protected void addComponentListeners() {}

	@Override
	public void internalpropertyhasChanged(String propertyName) {}

	@Override
	protected void propertyhasChanged(String propertyName, String newvalue) {}

	@Override
	protected void parameterhasChanged(String parameterName) {
		if(PARAM_LABEL.equals(parameterName)) { // if parameterName is indeed PARAM_LABEL
			try {
				// retrieves the value of the BoolUIParameter
				boolean b = this.getBoolUIParameterValue(PARAM_LABEL);
				
				// enable/disable the component
				toggle.setEnabled(b);
				
			} catch (IncorrectUIParameterTypeException | UnknownUIParameterException e) { // in case PARAM_LABEL is unknown or not a BoolUIProperty
				e.printStackTrace();
			}
		}
	}

	@Override
	public void shutDown() {}

	@Override
	public String getDescription() {
		return "ConfigurablePanel with a single BoolUIParameter enabling/disabling a component.";
	}

}
