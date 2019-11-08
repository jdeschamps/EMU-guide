package de.embl.rieslab.emuguide.uiparameters;

import javax.swing.JToggleButton;

import de.embl.rieslab.emu.ui.ConfigurablePanel;
import de.embl.rieslab.emu.ui.uiparameters.StringUIParameter;
import de.embl.rieslab.emu.utils.exceptions.IncorrectUIParameterTypeException;
import de.embl.rieslab.emu.utils.exceptions.UnknownUIParameterException;

public class StringButtonText extends ConfigurablePanel {

	private static final long serialVersionUID = 1L;
	
	// parameter
	public final static String PARAM_LABEL = "Button text"; 
	
	// declares the component to access it in all methods
	private JToggleButton toggle;

	public StringButtonText(String label) {
		super(label);

		toggle = new JToggleButton("Default name");
		
		this.add(toggle);
	}

	@Override
	protected void initializeProperties() {}

	@Override
	protected void initializeInternalProperties() {}

	@Override
	protected void initializeParameters() {
		String default_val = "Button";
		String description = "Sets the text of the JToggleButton.";
		
		// adds the StringUIParameter	
		this.addUIParameter(new StringUIParameter(this, PARAM_LABEL, description, default_val));
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
				String newText = this.getStringUIParameterValue(parameterName);
				toggle.setText(newText);
			} catch (IncorrectUIParameterTypeException | UnknownUIParameterException e) { // In case PARAM_LABEL is not known or not a StringUIParameter
				e.printStackTrace();
			}
		}
	}

	@Override
	public void shutDown() {}

	@Override
	public String getDescription() {
		return "ConfigurablePanel with a text parameter for a component.";
	}

}