package de.embl.rieslab.emuguide.uiparameters;


import de.embl.rieslab.emu.ui.ConfigurablePanel;
import de.embl.rieslab.emu.ui.uiparameters.IntegerUIParameter;
import de.embl.rieslab.emu.utils.exceptions.IncorrectUIParameterTypeException;
import de.embl.rieslab.emu.utils.exceptions.UnknownUIParameterException;

public class IntAttributeValue extends ConfigurablePanel {

	private static final long serialVersionUID = 1L;
	
	// parameter
	public final String PARAM_LABEL = "Attribute value"; 
	
	// declares the attribute to access it in all methods
	private int attribute;

	public IntAttributeValue(String label) {
		super(label);
	}

	@Override
	protected void initializeProperties() {}

	@Override
	protected void initializeInternalProperties() {}

	@Override
	protected void initializeParameters() {
		attribute = 0;
		String description = "Sets the value of the attribute.";
		
		// adds the IntegerUIParameter	
		this.addUIParameter(new IntegerUIParameter(this, PARAM_LABEL, description, attribute));
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
				attribute = this.getIntegerUIParameterValue(parameterName);
			} catch (IncorrectUIParameterTypeException | UnknownUIParameterException e) { // In case PARAM_LABEL is not known or not a IntegerUIParameter
				e.printStackTrace();
			}
		}
	}

	@Override
	public void shutDown() {}

	@Override
	public String getDescription() {
		return "ConfigurablePanel with an integer parameter for an attribute's value.";
	}

}