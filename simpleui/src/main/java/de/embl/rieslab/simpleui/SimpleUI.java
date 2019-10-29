package de.embl.rieslab.simpleui;

import java.util.TreeMap;

import de.embl.rieslab.emu.controller.SystemController;
import de.embl.rieslab.emu.plugin.UIPlugin;
import de.embl.rieslab.emu.ui.ConfigurableMainFrame;

public class SimpleUI implements UIPlugin{

	@Override
	public ConfigurableMainFrame getMainFrame(SystemController controller, TreeMap<String, String> pluginSettings) {
		return new SimpleFrame("Simple UI", controller, pluginSettings);
	}

	@Override
	public String getName() {
		return "Simple UI";
	}

}
