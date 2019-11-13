package de.embl.rieslab.baseplugin;

import de.embl.rieslab.emu.ui.ConfigurablePanel;

public class BasePluginPanel extends ConfigurablePanel{

	private static final long serialVersionUID = 1L;

	// examples of how to remember the names of properties and parameters
	public final static String PROPERTY_NAME = "My property";
	public final static String PARAMETER_NAME = "My parameter";
	public final static String INTPROPERTY_NAME = "My internal property";
	
	public BasePluginPanel(String label) {
		super(label);
		
		/*
		 * Here, instantiate and add the components (JTextField, JButtons, JSliders...) to the
		 * Panel. You can also here add components listeners (defining what happens when the user
		 * interacts with them), or do this in the addComponentListeners() method.
		 */
	}

	@Override
	protected void initializeProperties() {
		/*
		 * Here, initialize and add properties. UIProperties are mapped automatically after configuration with
		 * Micro-manager device properties. User interactions with UI components (buttons, sliders...etc...) 
		 * should change the UIProperty and thereafter the values of the device properties.
		 * 
		 * Several types of UIProperties are available:
		 *  - UIProperty: the general class.
		 *  - TwoStateUIProperty: a UIProperty with only two states (on/off).
		 *  - MultiStateUIProperty: a UIProperty with multiple states (number defined during instantiation).
		 *  - SingleStateUIProperty: a UIProperty with a single state.
		 *  - RescaledUIProperty: a UIProperty that do a linear rescaling of the device property value (e.g.: percentages).
		 * 
		 * e.g.: 
		 * addUIProperty(new UIProperty(this, PROPERTY_NAME, "Description of the Property", new NoFlag()));
		 */
	}

	@Override
	protected void initializeInternalProperties() {
		/*
		 * Here, initialize and add internal properties. InternalProperties across the Plugin that have the same label 
		 * will be fused, so that modifying one notifies the other that its value has change. That way you can 
		 * synchronize multiple ConfigurablePanel together.
		 * 
		 * e.g.: 
		 * addInternalProperty(new IntegerInternalProperty(this, INTPROPERTY_NAME, defaultValue));
		 */
	}

	@Override
	protected void initializeParameters() {
		/*
		 * Here, initialize and add parameters. UIParameters allow the user to configure certain aspects of the Panel,
		 * such as titles, colors, enabling buttons...etc...
		 * 
		 * Several types of UIParameter are available:
		 * - BoolUIParameter: on/off UIParameter.
		 * - ColorUIParameter: color UIParameter.
		 * - ComboUIParameter: UIParameter with multiple states.
		 * - DoubleUIParameter: UIParameter with a Double value.
		 * - IntegerUIParameter: UIParameter with an Integer value.
		 * - StringUIParameter: UIParameter with a String value.
		 * - UIPropertyUIParameter: UIParameter corresponding to UIProperty.
		 * 
		 * e.g.: 
		 * 	addUIParameter(new BoolUIParameter(this, PARAMETER_NAME, "Description of the parameter.", true));
		 */
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
		 * 
		 * e.g.:
		 * if(INTPROP_NAME.equals(propertyName)){
		 * 		int val = getIntegerInternalPropertyValue(label);
		 * 		// do something with the value (for instance: set the maximum of a JSlider)
		 * } else if (...) {
		 * 	(...)
		 * }
		 */
	}

	@Override
	protected void propertyhasChanged(String propertyName, String newvalue) {
		/*
		 * Here, defines what happens when a property has changed.
		 * 
		 * e.g.:
		 * if(PROPERTY_NAME.equals(propertyName)){
		 * 		// do something with newvalue (for instance: set the value of a JSlider)
		 * } else if (...){
		 * 	(...)
		 * }
		 */
	}

	@Override
	protected void parameterhasChanged(String parameterName) {
		/*
		 * Here, defines what happens when a parameter has changed (only happens when starting the ui).
		 * 
		 * e.g.:
		 * if(PARAMETER_NAME.equals(parameterName)){ 
		 *	 try {
		 *	 	if(getBoolUIParameterValue(PARAMETER_NAME)) { // that is, if PARAMETER_NAME is a BoolUIParameter
		 *			// for instance: enable a JToggleButton
		 *	 	} else {
         *			// for instance: disable a JToggleButton
		 *	 	}
		 *	 } catch (IncorrectUIParameterTypeException | UnknownUIParameterException e) { 
		 *		// these catches are necessary in case PARAMETER_NAME is not a BoolUIParameter
		 *		e.printStackTrace();
		 *	 }
		 * } else if (...){
		 * 	(...)
		 * }
		 */
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
