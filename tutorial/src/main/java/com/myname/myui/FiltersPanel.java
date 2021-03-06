package com.myname.myui;

import de.embl.rieslab.emu.ui.ConfigurablePanel;
import de.embl.rieslab.emu.ui.swinglisteners.SwingUIListeners;
import de.embl.rieslab.emu.ui.uiparameters.StringUIParameter;
import de.embl.rieslab.emu.ui.uiproperties.MultiStateUIProperty;
import de.embl.rieslab.emu.utils.ColorRepository;
import de.embl.rieslab.emu.utils.exceptions.UnknownUIParameterException;
import de.embl.rieslab.emu.utils.exceptions.UnknownUIPropertyException;

import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JToggleButton;
import java.awt.Insets;
import javax.swing.ButtonGroup;

public class FiltersPanel extends ConfigurablePanel {

	//////// Generated by Eclipse WindowBuilder
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JToggleButton tglbtnFilter;
	private JToggleButton tglbtnFilter_2;
	private JToggleButton tglbtnFilter_1;
	private JToggleButton tglbtnFilter_5;
	private JToggleButton tglbtnFilter_4;
	private JToggleButton tglbtnFilter_3;

	//////// Properties
	public static String FW_POSITION = "Filterwheel position";
	
	//////// Parameters
	public final String PARAM_NAMES = "Filter names";
	public final String PARAM_COLORS = "Filter colors";
	
	//////// Initial parameter
	public final int NUM_POS = 6;
	
	public FiltersPanel(String label) {
		super(label);
		initComponents();
	}
	
	// Generated by Eclipse WindowBuilder
	private void initComponents() {
		setBorder(new TitledBorder(null, "Filters", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(1, 0, 0, 0));
		
		tglbtnFilter = new JToggleButton("Filter 1");
		buttonGroup.add(tglbtnFilter);
		tglbtnFilter.setMargin(new Insets(2, 2, 2, 2));
		add(tglbtnFilter);
		
		tglbtnFilter_1 = new JToggleButton("Filter 2");
		buttonGroup.add(tglbtnFilter_1);
		tglbtnFilter_1.setMargin(new Insets(2, 2, 2, 2));
		add(tglbtnFilter_1);
		
		tglbtnFilter_2 = new JToggleButton("Filter 3");
		buttonGroup.add(tglbtnFilter_2);
		tglbtnFilter_2.setMargin(new Insets(2, 2, 2, 2));
		add(tglbtnFilter_2);
		
		tglbtnFilter_3 = new JToggleButton("Filter 4");
		buttonGroup.add(tglbtnFilter_3);
		tglbtnFilter_3.setMargin(new Insets(2, 2, 2, 2));
		add(tglbtnFilter_3);
		
		tglbtnFilter_4 = new JToggleButton("Filter 5");
		buttonGroup.add(tglbtnFilter_4);
		tglbtnFilter_4.setMargin(new Insets(2, 2, 2, 2));
		add(tglbtnFilter_4);
		
		tglbtnFilter_5 = new JToggleButton("Filter 6");
		buttonGroup.add(tglbtnFilter_5);
		tglbtnFilter_5.setMargin(new Insets(2, 2, 2, 2));
		add(tglbtnFilter_5);
	}
	

	@Override
	protected void addComponentListeners() {
		/*
		 *  MultiStateUIProperty accept indices as input to select the property state. 
		 *  Therefore, here, we add a listener to the button group that updates a 
		 *  UIProperty with the newly selected button index within the ButtonGroup.
		 */
		SwingUIListeners.addActionListenerOnSelectedIndex(this, FW_POSITION, buttonGroup);
	}

	@Override
	public String getDescription() {
		// Returns a description of the panel.
		return "Panel controlling a filter wheel with 6 positions.";
	}

	@Override
	protected void initializeInternalProperties() {
		// Do nothing
	}

	@Override
	protected void initializeParameters() {
		
		// We create comma separated strings of 6 filters a default parameters values.
		String names = "None";
		String colors = "gray";
		for (int i = 0; i < NUM_POS-1; i++) {
			names += "," + "None";
			colors += "," + "gray";
		}

		// Descriptions of the parameters
		String helpNames = "Comma separated filter names, e.g.: \"name1,name2,name3,name4,None,None\".";
		String helpColors = "Comma separated filter colors, e.g: \"blue,dark red, dark green,orange,gray,gray\".";
				
		// Finally, we create two StringUIParametesr
		addUIParameter(new StringUIParameter(this, PARAM_NAMES, helpNames, names));
		addUIParameter(new StringUIParameter(this, PARAM_COLORS, helpColors, colors));
	}

	@Override
	protected void initializeProperties() {
		// Description of the UIProperty
		String description = "Filter wheel position property.";
		
		// We create a MultiStateUIProperty with 6 states.
		addUIProperty(new MultiStateUIProperty(this, FW_POSITION, description, NUM_POS));				
	}

	@Override
	public void internalpropertyhasChanged(String arg0) {
		// Do nothing
	}

	@Override
	protected void parameterhasChanged(String parameter) {
		if(PARAM_NAMES.equals(parameter)){ // if the names parameter has changed
			try {
				// Retrieves the new value of the parameter
				String value = getStringUIParameterValue(PARAM_NAMES); 
				
				// Split it with at the commas
				String[] astr = value.split(",");
				
				// Takes the smallest number between the number of states and the length of astr
				int maxind = NUM_POS > astr.length ? astr.length : NUM_POS;
				
				// Creates an array of buttons to loop on it
				JToggleButton[] buttons = {tglbtnFilter,tglbtnFilter_1,tglbtnFilter_2,tglbtnFilter_3,tglbtnFilter_4,tglbtnFilter_5};
				
				// For each button, sets the new text
				for(int i=0;i<maxind;i++) {
					buttons[i].setText(astr[i]);
				}
				
			} catch (UnknownUIParameterException e) { // necessary in case PARAM_NAMES is not a StringParameter
				e.printStackTrace();
			}
		} else if(PARAM_COLORS.equals(parameter)){
			try {
				// Retrieves the new value of the parameter
				String value = getStringUIParameterValue(PARAM_COLORS);

				// Split it with at the commas
				String[] astr = value.split(",");
				
				// Takes the smallest number between the number of states and the length of astr
				int maxind = NUM_POS > astr.length ? astr.length : NUM_POS;
				
				// Creates an array of buttons to loop on it
				JToggleButton[] buttons = {tglbtnFilter,tglbtnFilter_1,tglbtnFilter_2,tglbtnFilter_3,tglbtnFilter_4,tglbtnFilter_5};

				// For each button, sets the new color using the EMU ColorRepository
				for(int i=0;i<maxind;i++) {
					buttons[i].setForeground(ColorRepository.getColor(astr[i]));
				}
			} catch (UnknownUIParameterException e) {// necessary in case PARAM_COLORS is not known
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void propertyhasChanged(String propertyName, String newvalue) {
		if(FW_POSITION.equals(propertyName)){ // making sure the property is FW_POSITION
			int pos;
			try {
				// Retrieves the current selected position index from the MultiStateUIProperty
				pos = ((MultiStateUIProperty) getUIProperty(FW_POSITION)).getStateIndex(newvalue);
			
				// Selects the corresponding JToggleButton
				switch (pos) {
				case 0:
					tglbtnFilter.setSelected(true);
				case 1:
					tglbtnFilter_1.setSelected(true);
				case 2:
					tglbtnFilter_2.setSelected(true);
				case 3:
					tglbtnFilter_3.setSelected(true);
				case 4:
					tglbtnFilter_4.setSelected(true);
				case 5:
					tglbtnFilter_5.setSelected(true);
				}
			} catch (UnknownUIPropertyException e) { // necessary in case FW_POSITION is not a known UIProperty
				e.printStackTrace();
			}
		}
	}

	@Override
	public void shutDown() {
		// Do nothing
	}

	protected JToggleButton getTglbtnFilter() {
		return tglbtnFilter;
	}
	protected JToggleButton getTglbtnFilter_2() {
		return tglbtnFilter_2;
	}
	protected JToggleButton getTglbtnFilter_1() {
		return tglbtnFilter_1;
	}
	protected JToggleButton getTglbtnFilter_5() {
		return tglbtnFilter_5;
	}
	protected JToggleButton getTglbtnFilter_4() {
		return tglbtnFilter_4;
	}
	protected JToggleButton getTglbtnFilter_3() {
		return tglbtnFilter_3;
	}
}
