package de.embl.rieslab.emuguide.uiproperties;

import javax.swing.JTextField;

import de.embl.rieslab.emu.ui.ConfigurablePanel;
import de.embl.rieslab.emu.ui.swinglisteners.SwingUIListeners;
import de.embl.rieslab.emu.ui.uiproperties.UIProperty;
import de.embl.rieslab.emu.utils.EmuUtils;

public class UIPropertyText extends ConfigurablePanel{

	private static final long serialVersionUID = 1L;
	
	private final static String PROP = "Property";
	
	private JTextField txtf;

	public UIPropertyText(String label) {
		super(label);
		
		txtf = new JTextField();
		this.add(txtf);
	}

	@Override
	protected void initializeProperties() {
		addUIProperty(new UIProperty(this, PROP, "A UIProperty linked to a JTextField that accepts integer values only."));
	}

	@Override
	protected void propertyhasChanged(String propertyName, String newvalue) {
		if(PROP.equals(propertyName)) {
			/* 
			 * Checks if it is an integer before proceeding. Alternatively, it could check
			 * if it is a numerical value and round it to an integer.
			 *  
			 */
			if(EmuUtils.isInteger(newvalue)) {
				txtf.setText(newvalue);
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
		SwingUIListeners.addActionListenerOnIntegerValue(this, PROP, txtf);
	}

	@Override
	public String getDescription() {
		return "A panel with a JTextField changing a UIProperty with only integer values.";
	}

	@Override
	public void shutDown() {
		// Do nothing.
	}

}
