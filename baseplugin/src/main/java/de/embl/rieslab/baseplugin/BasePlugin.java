package de.embl.rieslab.baseplugin;

import java.util.TreeMap;

import de.embl.rieslab.emu.controller.SystemController;
import de.embl.rieslab.emu.plugin.UIPlugin;
import de.embl.rieslab.emu.ui.ConfigurableMainFrame;

public class BasePlugin implements UIPlugin{

	@Override
	public String getName() {
		return "Base plugin";
	}

	@Override
	public ConfigurableMainFrame getMainFrame(SystemController controller, TreeMap<String, String> pluginSettings) {
		return new MainFrame("Title of the Frame", controller, pluginSettings);
	}

}
