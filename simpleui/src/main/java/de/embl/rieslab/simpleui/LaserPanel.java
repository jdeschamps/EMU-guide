package de.embl.rieslab.simpleui;

import de.embl.rieslab.emu.ui.ConfigurablePanel;
import de.embl.rieslab.emu.ui.swingslisteners.SwingUIListeners;
import de.embl.rieslab.emu.ui.uiparameters.ColorUIParameter;
import de.embl.rieslab.emu.ui.uiparameters.IntegerUIParameter;
import de.embl.rieslab.emu.ui.uiparameters.StringUIParameter;
import de.embl.rieslab.emu.ui.uiproperties.TwoStateUIProperty;
import de.embl.rieslab.emu.ui.uiproperties.UIProperty;
import de.embl.rieslab.emu.ui.uiproperties.flag.NoFlag;
import de.embl.rieslab.emu.utils.EmuUtils;
import de.embl.rieslab.emu.utils.exceptions.IncorrectUIParameterTypeException;
import de.embl.rieslab.emu.utils.exceptions.IncorrectUIPropertyTypeException;
import de.embl.rieslab.emu.utils.exceptions.UnknownUIParameterException;
import de.embl.rieslab.emu.utils.exceptions.UnknownUIPropertyException;

import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

public class LaserPanel extends ConfigurablePanel {
	private static final long serialVersionUID = 1L;
	
	private JLabel label;
	private JSlider slider;
	private JToggleButton tglbtnNewToggleButton;
	
	//////// Properties
	public final static String LASER_PERCENTAGE = "power percentage";
	public final static String LASER_OPERATION = "on/off";

	//////// Parameters
	public final static String PARAM_TITLE = "Name";
	public final static String PARAM_COLOR = "Color";	
	public final static String PARAM_SCALING = "Scaling";	
	public int scaling_;

	/**
	 * Create the panel.
	 */
	public LaserPanel(String title) {
		super(title);
		
		setPreferredSize(new Dimension(116, 245));
		setMaximumSize(new Dimension(90, 290));
		setBorder(new TitledBorder(null, "Laser", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);
		
		label = new JLabel("100%");
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 21, 96, 14);
		add(label);
		
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(20);
		slider.setOrientation(SwingConstants.VERTICAL);
		slider.setBounds(10, 36, 96, 166);
		add(slider);
		
		tglbtnNewToggleButton = new JToggleButton("On/Off");
		tglbtnNewToggleButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		tglbtnNewToggleButton.setBounds(20, 204, 73, 30);
		add(tglbtnNewToggleButton);
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
	
		String text1 = "Property changing the percentage of the laser.";
		String text2 = "Property turning the laser on and off.";

		addUIProperty(new UIProperty(this, getUIPropertyLabel(LASER_PERCENTAGE), text1, new NoFlag()));
		addUIProperty(new TwoStateUIProperty(this, getUIPropertyLabel(LASER_OPERATION), text2, new NoFlag()));
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
		scaling_ = 100;
		
		addUIParameter(new StringUIParameter(this, PARAM_TITLE, "Panel title.",getPanelLabel()));
		addUIParameter(new ColorUIParameter(this, PARAM_COLOR, "Laser color.",Color.black));
		addUIParameter(new IntegerUIParameter(this, PARAM_SCALING, "Scaling factor.",scaling_));
	}

	@Override
	protected void addComponentListeners() {
		/*
		 * In this method we can add Swing actionListeners to the 
		 * JComponents or call the methods from SwingUIListeners.
		 */
		SwingUIListeners.addActionListenerOnIntegerValue(this, getUIPropertyLabel(LASER_PERCENTAGE), slider, label, "", "%");
		try {
			SwingUIListeners.addActionListenerToTwoState(this, getUIPropertyLabel(LASER_OPERATION), tglbtnNewToggleButton);
		} catch (IncorrectUIPropertyTypeException e) {
			e.printStackTrace();
		}
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
		
		if(propertyName.equals(getUIPropertyLabel(LASER_PERCENTAGE))) {
			// Let's test if the value is a number
			if(EmuUtils.isNumeric(newvalue)) {
				int val = (int) Double.parseDouble(newvalue); // in case it is a double, we round it up
				
				// scale if necessary using the scaling parameter
				if(scaling_ != 100){
					val = (int) (val*scaling_/100);
				}
				
				if(val >= 0 && val <= 100) {
					//sets the value to the slider
					slider.setValue(val); // this does not trigger the actionListener
					
					// therefore, let's also change the label
					label.setText(String.valueOf(label)+"%");
				}
			}
		} else if(propertyName.equals(getUIPropertyLabel(LASER_OPERATION))) {
			// the try/catch clause is necessary in case we call an unknown UIProperty
			try {
				// if the newvalue is the TwoStateUIProperty's ON state, then select the button 
				if(((TwoStateUIProperty) getUIProperty(getUIPropertyLabel(LASER_OPERATION))).isOnState(newvalue) ){
					tglbtnNewToggleButton.setSelected(true);
				} else {  
					tglbtnNewToggleButton.setSelected(false);
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
		
		if(parameterName.equals(PARAM_TITLE)){
			try {
				// retrieves the title as a String
				String title = getStringUIParameterValue(PARAM_TITLE);	
				
				// gets the TitledBorder and change its title, then updates the panel
				TitledBorder border = (TitledBorder) this.getBorder();
				border.setTitle(title);
				this.repaint();
				
			} catch (IncorrectUIParameterTypeException | UnknownUIParameterException e) {
				e.printStackTrace();
			}
		} else if(parameterName.equals(PARAM_COLOR)){
			try {
				// retrieves the color at a Color type
				Color color = getColorUIParameterValue(PARAM_COLOR);
				
				// gets the TitledBorder and change its title color, then updates the panel
				TitledBorder border = (TitledBorder) this.getBorder();
				border.setTitleColor(color);
				this.repaint();
			} catch (IncorrectUIParameterTypeException | UnknownUIParameterException e) {
				e.printStackTrace();
			}
		}else if(parameterName.equals(PARAM_SCALING)){
			try {
				// retrieves the scaling at an Integer
				scaling_ = getIntegerUIParameterValue(PARAM_SCALING);
			} catch (IncorrectUIParameterTypeException | UnknownUIParameterException e) {
				e.printStackTrace();
			}
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
		return "The "+getPanelLabel()+" panel controls a single laser and allows for rapid on/off and power percentage changes.";
	}

}
