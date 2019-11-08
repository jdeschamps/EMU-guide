package com.myname.myui;

import java.awt.EventQueue;
import java.util.HashMap;
import java.util.TreeMap;

import javax.swing.JFrame;

import de.embl.rieslab.emu.controller.SystemController;
import de.embl.rieslab.emu.ui.ConfigurableMainFrame;
import de.embl.rieslab.emu.utils.settings.BoolSetting;
import de.embl.rieslab.emu.utils.settings.Setting;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class MyFrame extends ConfigurableMainFrame {

	// Bonus: optional filters panel
	public static final String SETTING_USE_FW = "Use FW";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFrame frame = new MyFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MyFrame() {
		super("",null,null); // calls superconstructor
		initComponents();
	}

	@Override
	protected void initComponents() {
		setBounds(100, 100, 510, 414);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 474, 275);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		LaserPanel laserPanel = new LaserPanel("Laser0");
		panel.add(laserPanel);
		
		LaserPanel laserPanel_1 = new LaserPanel("Laser1");
		panel.add(laserPanel_1);
		
		LaserPanel laserPanel_2 = new LaserPanel("Laser2");
		panel.add(laserPanel_2);
		
		LaserPanel laserPanel_3 = new LaserPanel("Laser3");
		panel.add(laserPanel_3);
		
		// Bonus: optional filters panel
		if(((BoolSetting) this.getCurrentPluginSettings().get(SETTING_USE_FW)).getValue()) {
			FiltersPanel filtersPanel = new FiltersPanel("Filters");
			filtersPanel.setBounds(10, 297, 474, 54);
			getContentPane().add(filtersPanel);
		}
	}

	public MyFrame(String arg0, SystemController arg1, TreeMap<String, String> arg2) {
		super(arg0, arg1, arg2);
	}

	@Override
	public HashMap<String, Setting> getDefaultPluginSettings() {
		HashMap<String, Setting>  settgs = new HashMap<String, Setting>();
		
		// Bonus: optional filters panel
		settgs.put(SETTING_USE_FW, new BoolSetting(SETTING_USE_FW, "Enable/disable filter wheel panel", true));
				
		return settgs;
	}

	@Override
	protected String getPluginInfo() {
		return "Description of the plugin and the author.";
	}

}