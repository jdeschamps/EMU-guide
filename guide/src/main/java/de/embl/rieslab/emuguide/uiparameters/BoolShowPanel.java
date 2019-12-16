package de.embl.rieslab.emuguide.uiparameters;

import java.awt.CardLayout;

import javax.swing.JPanel;
import javax.swing.JToggleButton;

import de.embl.rieslab.emu.ui.ConfigurablePanel;
import de.embl.rieslab.emu.ui.uiparameters.BoolUIParameter;
import de.embl.rieslab.emu.utils.exceptions.IncorrectUIParameterTypeException;
import de.embl.rieslab.emu.utils.exceptions.UnknownUIParameterException;

public class BoolShowPanel extends ConfigurablePanel {

	private static final long serialVersionUID = 1L;
	
	// parameter
	public final String PARAM_LABEL = "Show/hide panel"; 
	public final String SHOW = "show";
	public final String HIDE = "hide";
	
	// declares the component to access it in all methods
	private JToggleButton toggle;
	
	// JPanel used to show or hide the optional panel
	private JPanel cardPanel;

	public BoolShowPanel(String label) {
		super(label);
		
		// creates a Panel we want to make optional
		JPanel optional_panel = new JPanel();
		toggle = new JToggleButton("My button");
		optional_panel.add(toggle);
		
		// creates card panel
		cardPanel = new JPanel(new CardLayout()); // CardLayout shows one of the panel only
		cardPanel.add(optional_panel, SHOW); // If SHOW, then the optional panel
		cardPanel.add(new JPanel(), HIDE); // If HIDE, then an empty panel
		
		/*
		 * In parallel, add any other panels to the ConfigurablePanel
		 */
		
		this.add(cardPanel);
	}

	@Override
	protected void initializeProperties() {}

	@Override
	protected void initializeInternalProperties() {}

	@Override
	protected void initializeParameters() {
		boolean default_val = true;
		String description = "Show/hide the JPanel.";
		
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
				if(getBoolUIParameterValue(PARAM_LABEL)) { // shows the panel 
					((CardLayout) cardPanel.getLayout()).show(cardPanel, SHOW);
				} else { // hides the panel
					((CardLayout) cardPanel.getLayout()).show(cardPanel, HIDE);
				}
			} catch (IncorrectUIParameterTypeException | UnknownUIParameterException e) { // In case PARAM_LABEL is not known or not a BoolUIParameter
				e.printStackTrace();
			}
		}
	}

	@Override
	public void shutDown() {}

	@Override
	public String getDescription() {
		return "ConfigurablePanel with an optional panel.";
	}

}
