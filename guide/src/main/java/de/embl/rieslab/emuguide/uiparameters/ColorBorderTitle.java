package de.embl.rieslab.emuguide.uiparameters;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

import de.embl.rieslab.emu.ui.ConfigurablePanel;
import de.embl.rieslab.emu.ui.uiparameters.ColorUIParameter;
import de.embl.rieslab.emu.utils.exceptions.IncorrectUIParameterTypeException;
import de.embl.rieslab.emu.utils.exceptions.UnknownUIParameterException;

public class ColorBorderTitle extends ConfigurablePanel {

	private static final long serialVersionUID = 1L;
	
	// parameter
	public final static String PARAM_LABEL = "Border title color"; 

	// to access the border in all methods
	private TitledBorder border;
	
	public ColorBorderTitle(String label) {
		super(label);

		Color default_color = new Color(0,0,0);
		border = BorderFactory.createTitledBorder(null, "Title", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, default_color);
		this.setBorder(border);
	}

	@Override
	protected void initializeProperties() {}

	@Override
	protected void initializeInternalProperties() {}

	@Override
	protected void initializeParameters() {
		Color default_color = new Color(0,0,0);
		String description = "Sets the title color of the JPanel border.";
		
		// adds the ColorUIParameter	
		this.addUIParameter(new ColorUIParameter(this, PARAM_LABEL, description, default_color));
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
				Color newColor = this.getColorUIParameterValue(parameterName);
				border.setTitleColor(newColor);
				
				/*
				 * Alternatively, if border was not declared with a wider scope:
				 * 
				 * TitledBorder border = (TitledBorder) this.getBorder();
				 * border.setTitleColor(newColor);
				 */
			} catch (IncorrectUIParameterTypeException | UnknownUIParameterException e) { // In case PARAM_LABEL is not known or not a ColorUIParameter
				e.printStackTrace();
			}
		}
	}

	@Override
	public void shutDown() {}

	@Override
	public String getDescription() {
		return "ConfigurablePanel with a Color parameter for the border title.";
	}

}