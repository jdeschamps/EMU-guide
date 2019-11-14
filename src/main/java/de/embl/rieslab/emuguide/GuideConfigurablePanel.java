package de.embl.rieslab.emuguide;

import java.awt.Color;
import java.util.LinkedHashMap;

import de.embl.rieslab.emu.ui.ConfigurablePanel;
import de.embl.rieslab.emu.ui.internalproperties.BoolInternalProperty;
import de.embl.rieslab.emu.ui.internalproperties.DoubleInternalProperty;
import de.embl.rieslab.emu.ui.internalproperties.IntegerInternalProperty;
import de.embl.rieslab.emu.ui.uiparameters.BoolUIParameter;
import de.embl.rieslab.emu.ui.uiparameters.ColorUIParameter;
import de.embl.rieslab.emu.ui.uiparameters.ComboUIParameter;
import de.embl.rieslab.emu.ui.uiparameters.DoubleUIParameter;
import de.embl.rieslab.emu.ui.uiparameters.IntegerUIParameter;
import de.embl.rieslab.emu.ui.uiparameters.StringUIParameter;
import de.embl.rieslab.emu.ui.uiparameters.UIPropertyParameter;
import de.embl.rieslab.emu.ui.uiproperties.MultiStateUIProperty;
import de.embl.rieslab.emu.ui.uiproperties.RescaledUIProperty;
import de.embl.rieslab.emu.ui.uiproperties.SingleStateUIProperty;
import de.embl.rieslab.emu.ui.uiproperties.TwoStateUIProperty;
import de.embl.rieslab.emu.ui.uiproperties.UIProperty;
import de.embl.rieslab.emu.ui.uiproperties.flag.NoFlag;
import de.embl.rieslab.emu.ui.uiproperties.flag.PropertyFlag;
import de.embl.rieslab.emu.utils.exceptions.IncorrectInternalPropertyTypeException;
import de.embl.rieslab.emu.utils.exceptions.IncorrectUIParameterTypeException;
import de.embl.rieslab.emu.utils.exceptions.UnknownInternalPropertyException;
import de.embl.rieslab.emu.utils.exceptions.UnknownUIParameterException;
import de.embl.rieslab.emu.utils.exceptions.UnknownUIPropertyException;

public class GuideConfigurablePanel extends ConfigurablePanel{

	private static final long serialVersionUID = 1L;

	private static final String PROP_UIPROP = "UIProperty";
	private static final String PROP_SINGLE = "SingleState";
	private static final String PROP_TWOSTATE = "TwoState";
	private static final String PROP_MULTI = "MultiState";
	private static final String PROP_RESCALED = "Rescaled";
	
	private static final String INTPROP_INT = "Int InternalProperty";
	private static final String INTPROP_BOOL = "Bool InternalProperty";
	private static final String INTPROP_DOUBLE = "Double InternalProperty";

	private static final String PARAM_BOOL = "Bool UIParameter";
	private static final String PARAM_COLOR = "Color UIParameter";
	private static final String PARAM_COMBO = "Combo UIParameter";
	private static final String PARAM_DOUBLE = "Double UIParameter";
	private static final String PARAM_INT = "Int UIParameter";
	private static final String PARAM_STRING = "String UIParameter";
	private static final String PARAM_UIPROP = "UIProp UIParameter";
	
	public GuideConfigurablePanel(String label) {
		super(label);
		
		/*
		 * Here, instantiate and add the components (JTextField, JButtons, JSliders...) to the
		 * Panel.
		 */
	}

	@Override
	protected void initializeProperties() {
		/*
		 * Here, initialize and add properties. UIProperties are mapped automatically after configuration with
		 * Micro-manager device properties. User interactions with UI components (buttons, sliders...etc...) 
		 * should change the UIProperty and thereafter the values of the device properties.
		 */

		addUIProperty(new UIProperty(this, PROP_UIPROP, "A UIProperty"));
		
		addUIProperty(new SingleStateUIProperty(this, PROP_SINGLE, "A UIProperty with a single state."));
		
		addUIProperty(new TwoStateUIProperty(this, PROP_TWOSTATE, "A UIProperty with two states."));
		
		int number_states = 4;
		addUIProperty(new MultiStateUIProperty(this, PROP_MULTI, "A UIProperty with N states.", number_states));
		
		LinkedHashMap<String,String> states = new LinkedHashMap<String,String>();
		
		addUIProperty(new RescaledUIProperty(this, PROP_RESCALED, "A UIProperty with rescaled values."));
	}

	@Override
	protected void initializeInternalProperties() {
		/*
		 * Here, initialize and add internal properties. InternalProperties across the Plugin that have the same label 
		 * will be fused, so that modifying one notifies the other that its value has change. That way you can 
		 * synchronize multiple ConfigurablePanel together.
		 */

		int default_int = 404;
		addInternalProperty(new IntegerInternalProperty(this, INTPROP_INT, default_int));
		
		boolean default_bool = true;
		addInternalProperty(new BoolInternalProperty(this, INTPROP_BOOL, default_bool));
		
		double default_double = 3.1415;
		addInternalProperty(new DoubleInternalProperty(this, INTPROP_DOUBLE, default_double));
	}

	@Override
	protected void initializeParameters() {
		/*
		 * Here, initialize and add parameters. UIParameters allow the user to configure certain aspects of the Panel,
		 * such as titles, colors, enabling buttons...etc...
		 */

		boolean default_bool = true;
		addUIParameter(new BoolUIParameter(this, PARAM_BOOL, "A UIParameter with boolean value.", default_bool));

		Color default_color = Color.red;
		addUIParameter(new ColorUIParameter(this, PARAM_COLOR, "A UIParameter with Color value.", default_color));

		String[] default_vals = {"Val0","Val1","Val2"};
		int default_index = 0;
		addUIParameter(new ComboUIParameter(this, PARAM_COMBO, "A UIParameter with a choice among a list of values.", default_vals,default_index));

		double default_double = 6.626;
		addUIParameter(new DoubleUIParameter(this, PARAM_DOUBLE, "A UIParameter with double value.", default_double));

		int default_int = 1789;
		addUIParameter(new IntegerUIParameter(this, PARAM_INT, "A UIParameter with integer value.", default_int));
		
		String default_string = "Default string";
		addUIParameter(new StringUIParameter(this, PARAM_STRING, "A UIParameter with String value.", default_string));
		
		PropertyFlag flag = new NoFlag();
		addUIParameter(new UIPropertyParameter(this, PARAM_UIPROP, "A UIParameter with a PropertyFlag to select a particular UIProperty.", flag));
	}

	@Override
	protected void addComponentListeners() {
		/*
		 * Here, you can add component listeners. EMU provides static methods in SwingUIListeners to do so. 
		 * Component listeners define what happens when the user interacts with the component. For instance,
		 * clicking on a JToggleButton can call cp.setUIPropertyValue(PROPERTY_NAME, "true") when it is
		 * selected.
		 */
	}

	@Override
	public void internalpropertyhasChanged(String propertyName) {
		/*
		 * Here, defines what happens when an internal property has changed (if there is any).
		 */

		if(INTPROP_INT.equals(propertyName)) {
			try {
				int newvalue = getIntegerInternalPropertyValue(INTPROP_INT);
			} catch (IncorrectInternalPropertyTypeException | UnknownInternalPropertyException e) {
				e.printStackTrace();
			}
		} else if(INTPROP_BOOL.equals(propertyName)) {
			try {
				boolean newvalue = getBoolInternalPropertyValue(INTPROP_INT);
			} catch (IncorrectInternalPropertyTypeException | UnknownInternalPropertyException e) {
				e.printStackTrace();
			}
			
		} else if(INTPROP_DOUBLE.equals(propertyName)) {
			try {
				double newvalue = getDoubleInternalPropertyValue(INTPROP_DOUBLE);
			} catch (IncorrectInternalPropertyTypeException | UnknownInternalPropertyException e) {
				e.printStackTrace();
			}
			
		}
	}

	@Override
	protected void propertyhasChanged(String propertyName, String newvalue) {
		/*
		 * Here, defines what happens when a property has changed.
		 */

		if(PROP_UIPROP.equals(propertyName)) {
			// use newvalue
		} else if(PROP_SINGLE.equals(propertyName)) {
			// use newvalue
		} else if(PROP_TWOSTATE.equals(propertyName)) {
			try {
				boolean b = ((TwoStateUIProperty) getUIProperty(PROP_TWOSTATE)).isOnState(newvalue);
				
				// use b
				
			} catch (UnknownUIPropertyException e) { // in case PROP_TWOSTATE is not known
				e.printStackTrace();
			}
		} else if(PROP_MULTI.equals(propertyName)) {
			try {
				MultiStateUIProperty mp = (MultiStateUIProperty) getUIProperty(PROP_MULTI);
				
				// Get the index of the new state
				int index = mp.getStateIndex(newvalue);

				// Or get the state name
				String name = mp.getStateNameFromValue(newvalue);
				
				// use index or name
				
			} catch (UnknownUIPropertyException e) {
				e.printStackTrace();
			}
		} else if(PROP_RESCALED.equals(propertyName)) {
			// use newvalue
		}
	}

	@Override
	protected void parameterhasChanged(String parameterName) {
		/*
		 * Here, defines what happens when a parameter has changed (only happens when starting the ui).
		 */
		
		if(PARAM_BOOL.equals(parameterName)) {
			try {
				boolean newvalue = getBoolUIParameterValue(PARAM_BOOL);
			} catch (IncorrectUIParameterTypeException | UnknownUIParameterException e) {
				e.printStackTrace();
			}
		} else if(PARAM_COLOR.equals(parameterName)) {
			try {
				Color newvalue = getColorUIParameterValue(PARAM_COLOR);
			} catch (IncorrectUIParameterTypeException | UnknownUIParameterException e) {
				e.printStackTrace();
			}
			
		} else if(PARAM_COMBO.equals(parameterName)) {
			try {
				String newvalue = getComboUIParameterValue(PARAM_COMBO);
			} catch (IncorrectUIParameterTypeException | UnknownUIParameterException e) {
				e.printStackTrace();
			}
			
		} else if(PARAM_DOUBLE.equals(parameterName)) {
			try {
				double newvalue = getDoubleUIParameterValue(PARAM_DOUBLE);
			} catch (IncorrectUIParameterTypeException | UnknownUIParameterException e) {
				e.printStackTrace();
			}
			
		} else if(PARAM_INT.equals(parameterName)) {
			try {
				int newvalue = getIntegerUIParameterValue(PARAM_INT);
			} catch (IncorrectUIParameterTypeException | UnknownUIParameterException e) {
				e.printStackTrace();
			}
			
		} else if(PARAM_STRING.equals(parameterName)) {
			try {
				String newvalue = getStringUIParameterValue(PARAM_STRING);
			} catch (IncorrectUIParameterTypeException | UnknownUIParameterException e) {
				e.printStackTrace();
			}
			
		} else if(PARAM_UIPROP.equals(parameterName)) {
			try {
				String newvalue = getStringUIParameterValue(PARAM_UIPROP);
			} catch (IncorrectUIParameterTypeException | UnknownUIParameterException e) {
				e.printStackTrace();
			}
			
		}
	}

	@Override
	public void shutDown() {
		/*
		 * Here stop any thread running. 
		 */
	}

	@Override
	public String getDescription() {
		return "Description of the panel: what is it intended for?";
	}

}
