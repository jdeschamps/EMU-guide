package de.embl.rieslab.emuguide.uiparameters;

import java.awt.Color;

import javax.swing.JToggleButton;

import de.embl.rieslab.emu.ui.ConfigurablePanel;
import de.embl.rieslab.emu.ui.uiparameters.StringUIParameter;
import de.embl.rieslab.emu.utils.ColorRepository;
import de.embl.rieslab.emu.utils.exceptions.IncorrectUIParameterTypeException;
import de.embl.rieslab.emu.utils.exceptions.UnknownUIParameterException;

public class StringMultipleTextsAndColors extends ConfigurablePanel {

	private static final long serialVersionUID = 1L;
	
	// parameter
	public final static String PARAM_TEXT = "Button text"; 
	public final static String PARAM_COLOR = "Button color"; 
	
	// declares the attributes to access it in all methods
	private JToggleButton[] toggles;
	private final static int NUM = 6;
	private final static String text = "Do nothing";
	private final static Color black = Color.black;

	public StringMultipleTextsAndColors(String label) {
		super(label);

		// instantiates the JToggleButton array
		toggles = new JToggleButton[NUM];
		
		// loop over NUM
		for(int i=0;i<NUM;i++) {
			// instantiate the JToggleButton with the default text
			toggles[i] = new JToggleButton(text);
			
			// adds it to the panel
			this.add(toggles[i]);
		}
	}

	@Override
	protected void initializeProperties() {}

	@Override
	protected void initializeInternalProperties() {}

	@Override
	protected void initializeParameters() {
		// builds a string with NUM x comma separated default names or color
		StringBuilder sb_text = new StringBuilder();
		StringBuilder sb_color = new StringBuilder();
		sb_text.append(text);
		sb_color.append(black);
		for(int i=0;i<NUM-1;i++) {
			sb_text.append(",");
			sb_text.append(text);
			sb_color.append(",");
			sb_color.append(black);
		}
		String default_texts = sb_text.toString();
		String default_colors = sb_color.toString();

		String description_text = "Sets the name of the JToggleButtons. The names should be comma separated.";
		String description_color = "Sets the color of the JToggleButtons. The colors should be comma separated and correspond to colors in EMU ColorRepository.";
		
		// adds the StringUIParameters
		this.addUIParameter(new StringUIParameter(this, PARAM_TEXT, description_text, default_texts));
		this.addUIParameter(new StringUIParameter(this, PARAM_COLOR, description_color, default_colors));
	}

	@Override
	protected void addComponentListeners() {}

	@Override
	public void internalpropertyhasChanged(String propertyName) {}

	@Override
	protected void propertyhasChanged(String propertyName, String newvalue) {}

	@Override
	protected void parameterhasChanged(String parameterName) {
		if(PARAM_TEXT.equals(parameterName)) { // if parameterName is indeed PARAM_TEXT
			try {
				String texts = this.getStringUIParameterValue(PARAM_TEXT);
				setTexts(texts);
			} catch (IncorrectUIParameterTypeException | UnknownUIParameterException e) {// In case PARAM_TEXT is not known or not a StringUIParameter
				e.printStackTrace();
			}
		} else if(PARAM_COLOR.equals(parameterName)) { // if parameterName is indeed PARAM_COLOR
			try {
				String colors = this.getStringUIParameterValue(PARAM_COLOR);
				setColors(colors);
			} catch (IncorrectUIParameterTypeException | UnknownUIParameterException e) {// In case PARAM_COLOR is not known or not a StringUIParameter
				e.printStackTrace();
			}
		}
	}

	/*
	 * Takes a String representing comma separated texts and sets them as the JToggleButton texts
	 */
	private void setTexts(String texts) {
		// Splits the texts using the comma
		String[] textarray = texts.split(",");
		
		// Finds the minimum between NUM and the length of textarray
		int maxind = NUM > textarray.length ? textarray.length : NUM; 
		
		// Changes the texts for the JToggleButton from 0 to maxind
		for(int i=0;i<maxind;i++){
			toggles[i].setText(textarray[i]);
		}
	}
	
	/*
	 * Takes a String representing comma separated color names and sets them as the JToggleButton colors
	 */
	private void setColors(String colors) {
		// Splits the colors using the comma
		String[] colorarray = colors.split(",");

		// Finds the minimum between NUM and the length of textarray
		int maxind = NUM > colorarray.length ? colorarray.length : NUM;

		// Changes the color for the JToggleButton from 0 to maxind
		for(int i=0;i<maxind;i++){
			// uses the EMU ColorRepository to get a Color from a String
			toggles[i].setForeground(ColorRepository.getColor(colorarray[i]));
		}
	}

	@Override
	public void shutDown() {}

	@Override
	public String getDescription() {
		return "ConfigurablePanel with "+NUM+" JToggleButtons and parameters changing their text and color.";
	}

}