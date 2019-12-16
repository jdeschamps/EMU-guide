package de.embl.rieslab.emuguide.uiparameters;

import javax.swing.JComboBox;

import de.embl.rieslab.emu.ui.ConfigurablePanel;
import de.embl.rieslab.emu.ui.uiparameters.ComboUIParameter;
import de.embl.rieslab.emu.utils.exceptions.UnknownUIParameterException;

public class ComboDefaultValue extends ConfigurablePanel {

	private static final long serialVersionUID = 1L;
	
	// parameter
	public final String PARAM_LABEL = "Combo default value"; 
	
	// declares the attribute to access it in all methods
	private final static String[] values = {"authorized value 1", "authorized value 2", "authorized value 3"};
	private JComboBox<String> cb;

	public ComboDefaultValue(String label) {
		super(label);

		cb = new JComboBox<String>(values);
		this.add(cb);
	}

	@Override
	protected void initializeProperties() {}

	@Override
	protected void initializeInternalProperties() {}

	@Override
	protected void initializeParameters() {
		int default_index = 0;
		String description = "Sets the default value of the JComboBox.";
		
		// adds the IntegerUIParameter	
		this.addUIParameter(new ComboUIParameter(this, PARAM_LABEL, description, values, default_index));
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
				String value = this.getStringUIParameterValue(PARAM_LABEL);
				cb.setSelectedItem(value);
			} catch (UnknownUIParameterException e) { // In case PARAM_LABEL is not known
				e.printStackTrace();
			}
		}
	}

	@Override
	public void shutDown() {}

	@Override
	public String getDescription() {
		return "ConfigurablePanel with a ComboUIParameter to select the default value of a JComboBox.";
	}

}