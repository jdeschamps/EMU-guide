package com.myname.myui;

import java.util.TreeMap;

import de.embl.rieslab.emu.controller.SystemController;
import de.embl.rieslab.emu.plugin.UIPlugin;
import de.embl.rieslab.emu.ui.ConfigurableMainFrame;

public class MyPlugin implements UIPlugin {

	@Override
	public ConfigurableMainFrame getMainFrame(SystemController arg0, TreeMap<String, String> arg1) {
		return new MyFrame("MyUI", arg0, arg1);
	}

	@Override
	public String getName() {
		return "MyUI";
	}
}
