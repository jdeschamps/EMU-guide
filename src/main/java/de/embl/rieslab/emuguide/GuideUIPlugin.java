package de.embl.rieslab.emuguide;

import java.util.TreeMap;

import de.embl.rieslab.emu.controller.SystemController;
import de.embl.rieslab.emu.plugin.UIPlugin;
import de.embl.rieslab.emu.ui.ConfigurableMainFrame;

public class GuideUIPlugin implements UIPlugin{

	@Override
	public String getName() {
		return "Guide plugin";
	}

	@Override
	public ConfigurableMainFrame getMainFrame(SystemController controller, TreeMap<String, String> pluginSettings) {
		return new GuideConfigurableMainFrame("Guide plugin", controller, pluginSettings);
	}

}
