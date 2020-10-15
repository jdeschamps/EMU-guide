package de.embl.rieslab.bmc;

import java.util.TreeMap;

import de.embl.rieslab.emu.controller.SystemController;
import de.embl.rieslab.emu.plugin.UIPlugin;
import de.embl.rieslab.emu.ui.ConfigurableMainFrame;

public class BMCExample implements UIPlugin{

	@Override
	public ConfigurableMainFrame getMainFrame(SystemController controller, TreeMap<String, String> pluginSettings) {
		return new Frame("BMC-example", controller, pluginSettings);
	}

	@Override
	public String getName() {
		return "BMC publication plugin example";
	}
}
