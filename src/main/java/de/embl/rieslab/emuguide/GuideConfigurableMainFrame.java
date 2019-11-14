package de.embl.rieslab.emuguide;

import java.util.HashMap;
import java.util.TreeMap;

import de.embl.rieslab.emu.controller.SystemController;
import de.embl.rieslab.emu.ui.ConfigurableMainFrame;
import de.embl.rieslab.emu.utils.settings.BoolSetting;
import de.embl.rieslab.emu.utils.settings.DoubleSetting;
import de.embl.rieslab.emu.utils.settings.IntSetting;
import de.embl.rieslab.emu.utils.settings.Setting;
import de.embl.rieslab.emu.utils.settings.StringSetting;

public class GuideConfigurableMainFrame extends ConfigurableMainFrame{

	private static final long serialVersionUID = 1L;

	private static final String SETTING_BOOL = "Bool setting";
	private static final String SETTING_STRING = "String setting";
	private static final String SETTING_INT = "Int setting";
	private static final String SETTING_DOUBLE = "Double setting";
	

	public GuideConfigurableMainFrame(String title, SystemController controller, TreeMap<String, String> pluginSettings) {
		super(title, controller, pluginSettings);
	}

	@Override
	public HashMap<String, Setting> getDefaultPluginSettings() {
		HashMap<String, Setting> defaultSettings = new HashMap<String, Setting>();

		/*
		 * Here create and add entries to the HashMap here to create Settings. These can
		 * be used to configure certain aspects of the UI upon loading it in EMU. For
		 * instance, BoolSettings can be used to decide if a ConfigurablePanel will be
		 * used or not. StringSettings can give you runtime names and labels.
		 * IntegerSettings can give you a number that can be used to decide how many
		 * panels or buttons to instantiate.
		 */

		boolean default_bool = true;
		defaultSettings.put(SETTING_BOOL, new BoolSetting(SETTING_BOOL, "Boolean setting.", default_bool));

		String default_string = "default String";
		defaultSettings.put(SETTING_STRING, new StringSetting(SETTING_STRING, "String setting.", default_string));

		int default_int = 42;
		defaultSettings.put(SETTING_INT, new IntSetting(SETTING_INT, "Integer setting.", default_int));

		double default_double = 42.195;
		defaultSettings.put(SETTING_DOUBLE, new DoubleSetting(SETTING_DOUBLE, "Double setting.", default_double));

		return defaultSettings;
	}

	@Override
	protected void initComponents() {
		/*
		 * Here create the panels and add them.
		 * 
		 */
		GuideConfigurablePanel myPanel = new GuideConfigurablePanel("My Panel");
		this.add(myPanel);
        
        this.pack(); 
        this.setResizable(false);
 	    this.setVisible(true);  
	}

	@Override
	protected String getPluginInfo() {
		return "Guide plugin with every types of plugin settings, properties, internal properties and parameters.";
	}
}
