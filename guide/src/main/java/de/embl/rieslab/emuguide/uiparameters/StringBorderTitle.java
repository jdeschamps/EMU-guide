package de.embl.rieslab.emuguide.uiparameters;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

import de.embl.rieslab.emu.ui.ConfigurablePanel;
import de.embl.rieslab.emu.ui.uiparameters.StringUIParameter;
import de.embl.rieslab.emu.utils.exceptions.UnknownUIParameterException;

public class StringBorderTitle extends ConfigurablePanel {

	private static final long serialVersionUID = 1L;
	
	// parameter
	public final static String PARAM_LABEL = "Border title"; 

	// to access the border in all methods
	private TitledBorder border;
	
	public StringBorderTitle(String label) {
		super(label);

		border = BorderFactory.createTitledBorder(null, "Default title", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, new Color(0,0,0));
		this.setBorder(border);
	}

	@Override
	protected void initializeProperties() {}

	@Override
	protected void initializeInternalProperties() {}

	@Override
	protected void initializeParameters() {
		String default_val = "Default title";
		String description = "Sets the title of the JPanel border.";
		
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
				String newTitle = this.getStringUIParameterValue(parameterName);
				border.setTitle(newTitle);
				
				/*
				 * Alternatively, if border was not declared with a wider scope:
				 * 
				 * TitledBorder border = (TitledBorder) this.getBorder();
				 * border.setTitle(newTitle);
				 */
			} catch (UnknownUIParameterException e) { // In case PARAM_LABEL is not known
				e.printStackTrace();
			}
		}
	}

	@Override
	public void shutDown() {}

	@Override
	public String getDescription() {
		return "ConfigurablePanel with a title parameter for the border.";
	}

}