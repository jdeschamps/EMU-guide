package de.embl.rieslab.simpleui;

import de.embl.rieslab.emu.ui.ConfigurablePanel;
import de.embl.rieslab.emu.ui.swingslisteners.SwingUIListeners;
import de.embl.rieslab.emu.ui.uiparameters.StringUIParameter;
import de.embl.rieslab.emu.ui.uiproperties.MultiStateUIProperty;
import de.embl.rieslab.emu.ui.uiproperties.flag.NoFlag;
import de.embl.rieslab.emu.utils.ColorRepository;
import de.embl.rieslab.emu.utils.exceptions.IncorrectUIParameterTypeException;
import de.embl.rieslab.emu.utils.exceptions.UnknownUIParameterException;
import de.embl.rieslab.emu.utils.exceptions.UnknownUIPropertyException;

import javax.swing.border.TitledBorder;
import javax.swing.JToggleButton;
import java.awt.Insets;
import javax.swing.ButtonGroup;

public class FilterWheelPanel extends ConfigurablePanel {

	private static final long serialVersionUID = 1L;

	//////// Properties
	public static String FW_POSITION = " position";
	
	//////// Parameters
	public final static String PARAM_NAMES = " names";
	public final static String PARAM_COLORS = " colors";
	
	//////// Initial parameters
	public final static int NUM_POS = 6;
	
	private JToggleButton toggleButton_3;
	private JToggleButton toggleButton_4;
	private JToggleButton toggleButton_2;
	private JToggleButton toggleButton_0;
	private JToggleButton toggleButton_5;
	private JToggleButton toggleButton_1;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the panel.
	 */
	public FilterWheelPanel(String title) {
		super(title);
		setBorder(new TitledBorder(null, "Filterwheel", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		setLayout(null);
		
		toggleButton_0 = new JToggleButton("Filter0");
		buttonGroup.add(toggleButton_0);
		toggleButton_0.setMargin(new Insets(2, 2, 2, 2));
		toggleButton_0.setBounds(10, 18, 63, 23);
		add(toggleButton_0);
		
		toggleButton_1 = new JToggleButton("Filter0");
		buttonGroup.add(toggleButton_1);
		toggleButton_1.setMargin(new Insets(2, 2, 2, 2));
		toggleButton_1.setBounds(71, 18, 63, 23);
		add(toggleButton_1);
		
		toggleButton_2 = new JToggleButton("Filter0");
		buttonGroup.add(toggleButton_2);
		toggleButton_2.setMargin(new Insets(2, 2, 2, 2));
		toggleButton_2.setBounds(132, 18, 63, 23);
		add(toggleButton_2);
		
		toggleButton_3 = new JToggleButton("Filter0");
		buttonGroup.add(toggleButton_3);
		toggleButton_3.setMargin(new Insets(2, 2, 2, 2));
		toggleButton_3.setBounds(193, 18, 63, 23);
		add(toggleButton_3);
		
		toggleButton_4 = new JToggleButton("Filter0");
		buttonGroup.add(toggleButton_4);
		toggleButton_4.setMargin(new Insets(2, 2, 2, 2));
		toggleButton_4.setBounds(254, 18, 63, 23);
		add(toggleButton_4);
		
		toggleButton_5 = new JToggleButton("Filter0");
		buttonGroup.add(toggleButton_5);
		toggleButton_5.setMargin(new Insets(2, 2, 2, 2));
		toggleButton_5.setBounds(315, 18, 63, 23);
		add(toggleButton_5);
	}

	private String getUIPropertyLabel(String property) {
		return getPanelLabel() + " " + property;
	}
	
	@Override
	protected void initializeProperties() {
		/* 
		 * In this method, we need to declare the UIProperties and 
		 * add them to the ConfigurableFrame using the method
		 * ConfigurableFrame.addUIProperty(UIProperty).
		 */
		
		addUIProperty(new MultiStateUIProperty(this, getUIPropertyLabel(FW_POSITION),"Filter wheel position property.", new NoFlag(), NUM_POS));
	}

	@Override
	protected void initializeInternalProperties() {
		/* 
		 * In this method, we can declare the InternalProperties
		 * and add them to the ConfigurableFrame using the method
		 * ConfigurableFrame.addInternalProperty(InternalProperty).
		 */
		
		// In this example, we have none.
	}

	@Override
	protected void initializeParameters() {
		/* 
		 * In this method, we need to declare the UIParameters
		 * and add them to the ConfigurableFrame using the method
		 * ConfigurableFrame.addUIParameter(UIParameter).
		 */
		
		// Build initial values
		String names = "None";
		String colors = "grey";
		for (int i = 0; i < NUM_POS - 1; i++) {
			names += "," + "None";
			colors += "," + "grey";
		}

		String helpNames = "Filter names displayed by the UI. The entry should be written as \"name1,name2,name3,None,None,None\". The names should be separated by a comma. "
						+ "The maximum number of filters name is " + NUM_POS
						+ ", beyond that the names will be ignored. If the comma are not present, then the entry will be set as the name of the first filter.";
		
		String helpColors = "Filter colors displayed by the UI. The entry should be written as \"color1,color2,color3,grey,grey,grey\". The names should be separated by a comma. "
						+ "The maximum number of filters color is " + NUM_POS
						+ ", beyond that the colors will be ignored. If the comma are not present, then no color will be allocated. The available colors are:\n"
						+ ColorRepository.getColorsInOneColumn();
				
		
		addUIParameter(new StringUIParameter(this, PARAM_NAMES, helpNames, names));
		addUIParameter(new StringUIParameter(this, PARAM_COLORS, helpColors, colors));
	}

	@Override
	protected void addComponentListeners() {
		/*
		 * In this method we can add Swing actionListeners to the 
		 * JComponents or call the methods from SwingUIListeners.
		 */
		
		SwingUIListeners.addActionListenerOnSelectedIndex(this, FW_POSITION, buttonGroup);
	}

	@Override
	public void internalpropertyhasChanged(String propertyName) {
		/*
		 * This method is called when an InternalProperty has changed.
		 * Here we can modify the UI to reflect this change.
		 */
		
		// Here, we do not have to do anything.
	}

	@Override
	protected void propertyhasChanged(String propertyName, String newvalue) {
		/*
		 * This method is called when an UIProperty has changed.
		 * Here we can modify the UI to reflect this change.
		 */
		if(propertyName.equals(getUIPropertyLabel(FW_POSITION))){
			int pos;
			try {
				pos = ((MultiStateUIProperty) getUIProperty(getUIPropertyLabel(FW_POSITION))).getStatePositionNumber(newvalue);
				if(pos == 0){
					toggleButton_0.setSelected(true);
				} else if(pos == 1){
					toggleButton_1.setSelected(true);
				} else if(pos == 2){
					toggleButton_2.setSelected(true);
				} else if(pos == 3){
					toggleButton_3.setSelected(true);
				} else if(pos == 4){
					toggleButton_4.setSelected(true);
				} else if(pos == 5){
					toggleButton_5.setSelected(true);
				}
			} catch (UnknownUIPropertyException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void parameterhasChanged(String parameterName) {
		/*
		 * This method is called when a UIProperty has changed.
		 * Here we can modify the UI to reflect this change. It
		 * is only called when the plugin is loaded or the configuration
		 * changed.
		 */
		
		if(parameterName.equals(PARAM_NAMES)){
			try {
				setNames(getStringUIParameterValue(PARAM_NAMES));
			} catch (IncorrectUIParameterTypeException | UnknownUIParameterException e) {
				e.printStackTrace();
			}
		} else if(parameterName.equals(PARAM_COLORS)){
			try {
				setColors(getStringUIParameterValue(PARAM_COLORS));
			} catch (IncorrectUIParameterTypeException | UnknownUIParameterException e) {
				e.printStackTrace();
			}
		}
	}
	

	private void setNames(String names){
		// split names using the comma
		String[] astr = names.split(",");
		
		// select the maximum between NUM_POS and the length of astr
		// if astr is shorter than NUM_POS, then we take the length of astr and ignore the other indices
		// if astr is longer than NUM_POS then the user entered too any values, we only care for the NUM_POS first ones
		int maxind = NUM_POS > astr.length ? astr.length : NUM_POS-1;
		
		// For each button whose index is contained in astr, set the text according to the element in astr
		if(maxind >= 0) {
			toggleButton_0.setText(astr[0]);
		}
		
		if(maxind >= 1) {
			toggleButton_1.setText(astr[1]);
		}
		
		if(maxind >= 2) {
			toggleButton_2.setText(astr[2]);
		}
		
		if(maxind >= 3) {
			toggleButton_3.setText(astr[3]);
		}
		
		if(maxind >= 4) {
			toggleButton_4.setText(astr[4]);
		}
		
		if(maxind >= 5) {
			toggleButton_5.setText(astr[5]);
		}
	}
	
	private void setColors(String colors){
		String[] astr = colors.split(",");
		int maxind = NUM_POS > astr.length ? astr.length : NUM_POS-1;
		
		
		if(maxind >= 0) {
			toggleButton_0.setForeground(ColorRepository.getColor(astr[0]));
		}
		
		if(maxind >= 1) {
			toggleButton_1.setForeground(ColorRepository.getColor(astr[1]));
		}
		
		if(maxind >= 2) {
			toggleButton_2.setForeground(ColorRepository.getColor(astr[2]));
		}
		
		if(maxind >= 3) {
			toggleButton_3.setForeground(ColorRepository.getColor(astr[3]));
		}
		
		if(maxind >= 4) {
			toggleButton_4.setForeground(ColorRepository.getColor(astr[4]));
		}
		
		if(maxind >= 5) {
			toggleButton_5.setForeground(ColorRepository.getColor(astr[5]));
		}
		
	}

	@Override
	public void shutDown() {
		/*
		 * This method is called upon closing the plugin and can be 
		 * used to reset some properties or stop running threads.
		 */
		
		// Here do nothing.
	}

	@Override
	public String getDescription() {
		/*
		 * Here, we return the description of the ConfigurablePanel,
		 * this description is used to help the user understand how the 
		 * panel works.
		 */		
		return "The "+getPanelLabel()+" panel is used to control a filterwheel with a maximum of "+NUM_POS+" positions. The names and colors of the filters can be set with parameters.";
	}

	protected JToggleButton getToggleButton_2() {
		return toggleButton_3;
	}
	protected JToggleButton getToggleButton_3() {
		return toggleButton_4;
	}
	protected JToggleButton getToggleButton_1() {
		return toggleButton_2;
	}
	protected JToggleButton getTglbtnState() {
		return toggleButton_0;
	}
	protected JToggleButton getToggleButton_4() {
		return toggleButton_5;
	}
	protected JToggleButton getToggleButton() {
		return toggleButton_1;
	}
}
