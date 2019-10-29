package de.embl.rieslab.ibeamsmart;

import java.util.HashMap;
import java.util.TreeMap;

import de.embl.rieslab.emu.controller.SystemController;
import de.embl.rieslab.emu.ui.ConfigurableMainFrame;
import de.embl.rieslab.emu.utils.settings.Setting;

public class IBeamSmartFrame extends ConfigurableMainFrame{

	private static final long serialVersionUID = 1L;

	public IBeamSmartFrame(String title, SystemController controller, TreeMap<String, String> pluginSettings) {
		super(title, controller, pluginSettings);
	}

	@Override
	public HashMap<String, Setting> getDefaultPluginSettings() {
		// We do not have any plugin Setting.
		return new HashMap<String, Setting>();
	}

	@Override
	protected void initComponents() {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info: javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IBeamSmartFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IBeamSmartFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IBeamSmartFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IBeamSmartFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        this.add(new IBeamSmartPanel("iBeamSmart laser control"));
 
        this.pack(); 
        this.setResizable(false);
 	    this.setVisible(true);   
	}

}
