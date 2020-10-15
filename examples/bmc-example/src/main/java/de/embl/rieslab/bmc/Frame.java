package de.embl.rieslab.bmc;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.TreeMap;

import javax.swing.JPanel;

import de.embl.rieslab.emu.controller.SystemController;
import de.embl.rieslab.emu.ui.ConfigurableMainFrame;
import de.embl.rieslab.emu.utils.settings.IntSetting;
import de.embl.rieslab.emu.utils.settings.Setting;

public class Frame extends ConfigurableMainFrame {
	// setting label
	private final String NM_LASERS = "Number of lasers";

	public Frame(String title, SystemController controller, TreeMap<String, String> settings) {
		super(title, controller, settings);
	}

	@Override
	protected void initComponents() {
		// retrieves the setting value
		Setting setting = this.getCurrentPluginSettings().get(NM_LASERS);
		int num_lasers = ((IntSetting) setting).getValue();

		// uses Swing to layout the panels
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, num_lasers));
		for (int i = 0; i < num_lasers; i++) {
			// unique label to each laser panel to avoid property collisions
			LaserPanel laserPanel = new LaserPanel("Laser" + i);
			panel.add(laserPanel);
		}
		this.setContentPane(panel);
		this.pack();
	}

	@Override
	public HashMap<String, Setting> getDefaultPluginSettings() {
		HashMap<String, Setting> settgs = new HashMap<String, Setting>();

		// adds the integer setting with a default value of 4
		settgs.put(NM_LASERS, new IntSetting(NM_LASERS, "Description", 4));

		return settgs;
	}

	@Override
	protected String getPluginInfo() {
		return "Plugin description";
	}
}