package de.embl.rieslab.baseplugin;

import java.util.HashMap;
import java.util.TreeMap;

import de.embl.rieslab.emu.controller.SystemController;
import de.embl.rieslab.emu.ui.ConfigurableMainFrame;
import de.embl.rieslab.emu.utils.settings.Setting;

public class BasePluginMainFrame extends ConfigurableMainFrame{

	private static final long serialVersionUID = 1L;

	public BasePluginMainFrame(String title, SystemController controller, TreeMap<String, String> pluginSettings) {
		super(title, controller, pluginSettings);
	}

	@Override
	public HashMap<String, Setting> getDefaultPluginSettings() {
		HashMap<String, Setting> defaultSettings = new HashMap<String, Setting>();
		
		/*
		 * Here create and add entries to the HashMap here to create Settings. These can be used to configure certain
		 * aspects of the UI upon loading it in EMU. For instance, BoolSettings can be used to decide if a 
		 * ConfigurablePanel will be used or not. StringSettings can give you runtime names and labels. IntegerSettings
		 * can give you a number that can be used to decide how many panels or buttons to instantiate.
		 * 
		 * e.g.:
		 *  defaultSettings.put(settingName, new BoolSetting(settingName, "Description of the setting.", defaultValue));
		 */
		
		return defaultSettings;
	}

	@Override
	protected void initComponents() {
		/*
		 * Here create the panels and add them.
		 * 
		 */
		BasePluginPanel myPanel = new BasePluginPanel("My Panel");
		this.add(myPanel);
        
        this.pack(); 
        this.setResizable(false);
 	    this.setVisible(true);  
	}
	
	@Override
	protected String getPluginInfo() {
		return "Description of the plugin and mention of the author.";
	}
}
