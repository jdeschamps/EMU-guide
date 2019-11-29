# ConfigurableMainFrame 

There is only a single ConfigurableMainFrame per plugin, instantiated by the plugin class itself. A ConfigurableMainFrame must assemble all ConfigurablePanels. The latter are automatically detected to extract all properties and parameters.



## Methods to implement

ConfigurableMainFrame must implement three methods:

```java
public class MyFrame extends ConfigurableMainFrame {
	public MyFrame(String title, SystemController controller, 
                   TreeMap<String, String> 	pluginSettings) {
		super(title, controller, pluginSettings);
	}

	@Override
	public HashMap<String, Setting> getDefaultPluginSettings() {
		HashMap<String, Setting> settgs = new HashMap<String, Setting>();
		// Here add Settings to sttgs if applicable.
		return settgs;
	}

	@Override
	protected void initComponents() {
		// Here add all ConfigurablePanels
	}

	@Override
	protected String getPluginInfo() {
		// Return an informative description of the plugin's aim.
		return "";
	}
}
```

It is important that **all ConfigurablePanels are instantiated and added to the ConfigurableMainFrame  in the initComponents() method**. As ConfigurablePanels extend the Swing class JPanel, they are added to the ConfigurableMainFrame using the add() method.



## Plugin Settings  <a name="settings"></a>  

A ConfigurableMainFrame may declared Settings that will appear in the configuration wizard under the "Plugin Settings" tab. Settings are a mechanism for saving parameters related to ConfigurableMainFrame  in the configuration. These Settings are to be used as options, such as using or not a ConfigurablePanel. Plugin Settings are read from the configuration and used in the ConfigurableMainFrame  constructor.

Plugin Settings are originally declared as a default HasMap in the getDefaultPluginSettings() method. If there are no plugin settings, then just return an empty HashMap. Here is an example of three different types of plugin Settings: 

```java
private final static String SETTING_USE_PANEL = "Use panel";
private final static String SETTING_NB_BUTTONS = "Number of buttons";
private final static String SETTING_TITLE_PANE = "Panel title";

@Override
public HashMap<String, Setting> getDefaultPluginSettings() {
	HashMap<String, Setting> defaultSettings = new HashMap<String, Setting>();

    String desc_use_panel = "Check to use the JPanel.";
    BoolSetting use_panel = new BoolSetting(SETTING_USE_TRIGGER, desc_use_panel, true);
    defaultSettings.put(SETTING_USE_PANEL, use_panel);

    String desc_nb_buttons = "Number of Buttons.";
    IntSetting number_buttons = new IntSetting(SETTING_NB_BUTTONS,desc_nb_buttons,4);
    defaultSettings.put(SETTING_NB_BUTTONS, number_buttons);

    String desc_title_pane = "Title of Panel.";
    StringSetting title_pane = new StringSetting(SETTING_TITLE_PANE,desc_title_pane
                                                 ,"Title");
    defaultSettings.put(SETTING_TITLE_PANE, title_pane);
    
	return defaultSettings;
}
```

Since the plugin Settings are passed as parameters to the constructor, they can be used during instantiation of the ConfigurablePanels. Their values are queried as follows:

```java
// retrieves the plugin settings
HashMap<String, Setting> settings = this.getCurrentPluginSettings();

// get the setting value
boolean b = ((BoolSetting) settings.get(SETTING_USE_PANEL)).getValue();
int i = ((IntSetting) settings.get(SETTING_NB_BUTTONS)).getValue();
String s = ((StringSetting) settings.get(SETTING_TITLE_PANE)).getValue();
```



## Useful methods 

ConfigurableMainFrames  expose the EMU SystemController and Micro-Manager core. Through the SystemController, a ConfigurableMainFrame and the necessary ConfigurablePanels can get a reference to the Micro-Manager Studio and therefore the whole Micro-Manager Java API.



## Example

- [GuideConfigurableMainFrame.java: a ConfigurableMainFrame declaring all types of plugin Settings](https://github.com/jdeschamps/EMU-guide/tree/master/guide/src/main/java/de/embl/rieslab/emuguide)



[Back to the programming guide](programmingguide.md)

[Back to the main menu](index.md)