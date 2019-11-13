# ![EMU](img/logo64.png)EMU guide

The EMU guide provides information on the motivation at the origin of EMU (Why use EMU?), how to use it (EMU: a user perspective) and a programming guide (Programming guide). Finally, some resources are linked the end (Resources).



## Table of content

1. [Why use EMU?](#why)
2. [EMU: a user perspective](#user)
   1. [Configuration](#user-conf)
   2. [Plugin](#user-plug)
3. [Programming guide](#impl)
   1. [EMU principle](#emuprinc)
   2. [JComponents](#jcompo)
   3. [ConfigurablePanel](#confpane)
      1. [UIProperty](#uiprop)
      2. [SwingUIListeners methods](#swing)
      3. [UIParameter](#uiparam)
      4. [InternalProperty](#intprop)
      5. [Implementing a ConfigurablePanel](#implconf)
      6. [Methods calling order](#orderpane)
      7. [Useful methods](#useflpane)
   4. [ConfigurableMainFrame](#confframe)
      1. [Plugin Settings](#plugsettgs)
      2. [Instantiating the ConfigurablePanels](#instframe)
      3. [Methods calling order](#orderframe)
      4. [Useful methods](#useflframe)
   5. [UIPlugin](#uiplug)
   6. [Drag and drop softwares](#dragndrop)
4. [Resources](#res)
   1. [Tutorial](#tuto)
   2. [Examples](#ex)
   3. [htSMLM](#htsmlm)



## Why use EMU?  <a name="why"></a>  

Micro-Manager controls the devices on your microscope using device properties (e.g. laser on/off). All device properties can be access through the device property browser. However, controlling the microscope through a long list of properties is cumbersome, slow and rather aimed at the microscope engineer than at the user. While interaction with device properties can be facilitated by using configuration preset groups or the quick access plugin, these cannot replace a user interface (UI) tailored to the microscope. 

Tailored interfaces have the advantage of rendering the control of the microscope intuitive. However, tailoring the UI often means hard-coded references to the specific device properties and a need to recompile every time something changes on the microscope.

Easier Micro-manager User interface (EMU) offers means to make your Micro-Manager interface reconfigurable:

- Rapid design compatible with **drag and drop softwares** (e.g. Eclipse WindowBuilder).
- Functional UI with only few lines of code thanks to EMU's back-end.
- Flexible and transferable: easy and intuitive configuration through EMU's interface.



## EMU: a user perspective  <a name="user"></a>  

##### Plugin

While EMU is a Micro-Manager plugin, it also loads its own plugins: user interfaces. These UIs need to be implemented in agreement with EMU classes and plugin system. Once compiled into a .jar file, the plugin must be placed under the EMU folder in Micro-Manager.

##### Settings, properties and parameters

In EMU, UIs consist of a single frame composed of multiple panels. The frame offers some options (called *settings*), while each panel declares a number of *properties* and *parameters*:

- Plugin *settings* allow to define options, such as titles or showing/hiding a panel.
- *Properties* are mapped through EMU's interface to Micro-Manager device properties.
- *Parameters* are used to customize the panels (e.g. titles, colors, enable/disable button).

##### Starting EMU

Once installed, EMU is accessible from Micro-Manager by clicking on "Plugins/Interface/EMU" in the main window. If a single plugin is present in the EMU folder, then it will be automatically loaded. If more than one plugin is present, then the user will be prompted with a choice. Then, the chose UI will be loaded.

The first step in using EMU and one of its plugin is to create a *plugin configuration* containing the values of each *setting*, *property* and *parameter*. Later on, the default *plugin configuration* is loaded automatically at start.

##### Using EMU

Once the configuration done, interacting with the components of the UI will trigger an update of the device properties to the corresponding values. Note that modifying those values outside the UI will not refresh the UI components and the states will then become out of sync. A refresh UI button is available in EMU's menu. 



### Configuration  <a name="user-conf"></a>  

The configuration is a JSON file saved in your Micro-Manager folder under the EMU folder (e.g.: Micro-Manager/EMU/config.uicfg). It can contain the name of the default *plugin configuration* and one or more *plugin configurations* as pairs of key and values. 

Configuration related actions are accessible through the menu bar, by selecting *Configuration*.

![menu-config](img\menu-config.png)



- **Modify configuration**: Starts the *configuration wizard*, allowing the user to modify the values of the *settings*, *properties* and *parameters*. The *configuration wizard* is automatically launched at start-up if no *plugin configuration* exists. 
- **Manage configurations**: Starts the *configuration manager*, allowing the user to delete *plugin configurations*.
- **Switch plugin**: Allows loading a different plugin instead of the current one. It also makes the new *plugin configuration* default.
- **Switch configuration**:  Allows loading a different *plugin configuration*. The default is set to the new configuration.



#### Configuration wizard

Once EMU is loaded in Micro-manager, the user can configure the UI graphically by using the *configuration wizard*, accessible through "**Configuration/Modify configuration**". It is automatically started if no configuration is found.

![config_wizard](C:\Users\Ries\workspaceMM\EMU-guide\img\config_wizard.PNG)

The configuration wizard displays four tabs:

- Properties: mapping of a property to a Micro-Manager device and device property.
- Parameters: parameters to customize the UI.
- Plugin Settings: plugin options. 
- Global Settings: EMU options.



##### Help

Selecting the *help* button opens the *help window*. Select a row of the properties/parameters/settings to refresh the content of the *help window* with a description of the currently selected property/parameter/setting.



##### Plugin settings

*Plugin settings* allow options at the level of the main frame. For instance, they can be used to name a panel or to make it optional (show/hide setting).

###### Example: htSMLM

![config_wizard_plugsettings](C:\Users\Ries\workspaceMM\EMU-guide\img\config_wizard_plugsettings.PNG)



Changing *plugin settings* might cause a change in the list of properties and parameters. For instance, if a *plugin setting* is an option to show an extra panel, the properties and parameters corresponding to this panel will not yet be in the properties and parameters tabs. They will however appear next time you start the configuration wizard.

**Therefore**,  whenever you modify the *plugin settings*, save the configuration and start the *configuration wizard* anew.



##### Global settings

For the moment, there is a single global setting:

- **enable unallocated warnings**: when checked, EMU notifies the user when saving the *plugin configuration* that some UIProperties are not allocated. Modifying this setting does not require restarting the configuration manager.

  

##### Properties

The *properties* tab contains three columns: the UI properties, device names from micro-manager and their corresponding device properties.

For each relevant UI *property*, select the corresponding device from the drop down list.  Use the *help window* to get more details on which type of device to select. Then, select the appropriate device property.

###### Example: htSMLM

![config_wizard](C:\Users\Ries\workspaceMM\EMU-guide\img\config_wizard.PNG)

> In this example, htSMLM has a "Camera exposure" UI property, therefore one must select the camera in the devices (here "Andor") and choose its "Exposure" device property.



Some property require also specific states. After selecting the device property, use the device property browser (Micro-Manager) to determine which are the device property values required. 

There are three types of such properties:

- Single-state: the property requires only one state value to be specified.
- Two-state: the property requires two state values, corresponding to an On and an Off states.
- Multi-state: the property requires a specified number of state values, labeled state 0, state 1,...

> In the previous example, htSMLM has a "Filter wheel position" UI property with 6 states. Therefore, 6 values of the "Position1" device property should be entered. Another UI property, "Focus-lock enable fine" requires an On and Off states, with two values of the device property to set.   

If the values are not set correctly, or only partially set, then a pop-up will appear after saving the configuration.

If two UI properties are set to the same device property, then updating one through the UI will update the second on.



##### Parameters

The *parameters* tab allows the user to customize some aspects of the UI, such as titles, colors or default values.

There are several types of UI parameters:

- Boolean: appears as a checkbox.
- Color: a drop-down list to select a color.
- Combo: a drop-down list to select an item among a list.
- Double: a number with decimals.
- Integer: a positive or negative whole number.
- String: a chain of characters.
- UIProperty: a drop-down list containing UI properties to select from.



###### Example: htSMLM

![param](C:\Users\Ries\workspaceMM\EMU-guide\img\param.png)



##### Configuration name

On top of the *configuration wizard*, one can modify the name of the *plugin configuration*. As prompted when doing so, modifying the name will create a new *plugin configuration* upon saving. Configuration can be deleted in the *configuration manager*.



#### Configuration manager

The *configuration manager* is accessible through the menu "Configuration/Manage configurations". It allows deleting *plugin configurations* (except the current one).



![config_manager](C:\Users\Ries\workspaceMM\EMU-guide\img\config_manager.PNG)





### Plugin  <a name="user-plug"></a>  

![menu-plugin](C:\Users\Ries\workspaceMM\EMU-guide\img\menu-plugin.png)



The Plugin menu gives access to two actions: refresh UI and show description.

#### Refresh

Modifying device properties outside the UI plugin does not impact the state of the UI, whether it is done by interacting with configuration group presets, the device property browser or scrips. Therefore, synchronizing the UI with the current state of the device properties.

#### Description

The description is automatically generated from the panels own description and should tell the user what each panel is intended for.



## Programming guide  <a name="impl"></a>  



### EMU principle  <a name="emuprinc"></a>  

EMU is based on the Swing library. The main building blocks of EMU are *ConfigurablePanels*, which extend a JPanel (Swing). They declare UIProperties (e.g. laser power percentage), InternalProperties (values shared between panels) and UIParameters (e.g. title color). A ConfigurablePanel should be a unit of control corresponding to a device on the microscope. This can for instance be a laser controller (power, on/off) or a filterwheel (position).

ConfigurablePanels are assembled in a single ConfigurableMainFrame. The latter defines Settings, allowing for instance having optional panels or defining how many panels of a certain type there are. 

UIproperties, UIParameters and Settings appear in the *configuration wizard*, the graphical interface enabling the rapid configuration of the plugin by the user. After configuration, the UIProperties are mapped to Micro-Manager device properties. Then, any user interaction with the components of a ConfigurablePanel trigger the UIProperties, which in turn change the state of their linked device properties.

EMU is designed to involve minimum heavy lifting and coding in the plugin in order to obtain a functional UI.



### JComponents  <a name="jcompo"></a>  

JComponents are the elements the user interacts with and are central to building a ConfigurablePanel. Here are examples of the most common components:

![JComponents](img/jcomponents.png)



### ConfigurablePanel  <a name="confpane"></a>  

It is important to plan some aspects of a ConfigurablePanel before starting building it. Here are few points worth considering:

- What will the panel look like? List the components necessary and lay out their organization.
- What function will it fulfill? List the type of device properties it will modify, then decide on the required UIProperties.
- What aspect should be parametrized? Picture which elements should be easily changed by the user (title, colors...etc...) and decide on UIParameters.



A ConfigurablePanel must implements few methods, which are shown empty in the following example:

```java
import de.embl.rieslab.emu.ui.ConfigurablePanel;

public class MyPanel extends ConfigurablePanel {

	//////////////////////// Constructor 
	public MyPanel(String label) {
		super(label);
		
		// Add JComponents here
 	}

	//////////////////////// UIProperties 

	@Override
	protected void initializeProperties() {
        // Add the UIProperties here
	}

	@Override
	protected void propertyhasChanged(String propertyName, String newvalue) {
        // What happens when the UIProperty propertyName change?
	}
	
	@Override
	protected void addComponentListeners() {
        // Here add the JComponent listeners to react to user interactions
	}
	
	//////////////////////// InternalProperties 
	
	@Override
	protected void initializeInternalProperties() {
        // Add the InternalProperties here
	}

	@Override
	public void internalpropertyhasChanged(String propertyName) {
        // What happens when the InternalProperty propertyName change?
	}
	
	//////////////////////// UIParameters 

	@Override
	protected void initializeParameters() {
        // Add the UIParameters here
	}

	@Override
	protected void parameterhasChanged(String parameterName) {
        // What happens when the UIParameter parameterName change?
	}

	//////////////////////// Others 
	
	@Override
	public String getDescription() {
        // Change the description to something informative about the panel
		return "Description";
	}

	@Override
	public void shutDown() {
        // Here stops running threads created within the panel
	}
}
```

All components should be declared and added in the constructor. Then, the method are called in a particular order:

1. super-constructor()
2. initializeProperties() 
3. initializeParameters()
4. initializeInternalProperties()
5. rest of the constructor() (instantiation of the JComponents)

Finally, upon configuration (at the start-up of EMU):

1. propertyhasChanged(...) looped call on all UIProperties
2. parameterhasChanged(...) looped call on all UIParameters
3. addComponentListeners(...), which allows using the UIParameter's values



The following sections describe in more depths how UIProperties, InternalProperties and UIParameters are used, in particular how to implement the previous methods.



#### UIProperty  <a name="uiprop"></a>  

UIProperties are the link between Micro-Manager device properties and the JComponents. Different types of UIProperties are available in EMU:

| UIProperty type       | Note                                | Compatible JComponents                                       |
| --------------------- | ----------------------------------- | ------------------------------------------------------------ |
| UIProperty            | general UI property, no restriction | JTextFields<br />JSpinners<br />JSliders                     |
| SingleStateUIProperty | accepts a single state only         | JButtons                                                     |
| TwoStateUIProperty    | accepts an On and an Off state      | JToggleButtons<br />JCheckBoxes<br />JRadioButtons<br />ButtonGroups (N=2) |
| MultiStateUIProperty  | accepts a fixed number of states    | ButtonGroups<br />JComboBoxes                                |
| RescaledUIProperty    | rescales a property                 | JTextFields<br />JSpinners<br />JSliders                     |



###### Creating a UIProperty

UIProperties should be instantiated and added to the ConfigurablePanel in the initializeUIProperties() method:

```java
private final static String PROP_KEY = "MyProperty";

@Override
protected void initializeProperties() {
	addUIProperty(new UIProperty(this, PROP_KEY, "Description"));    
}
```
We recommend using a static String as UIProperty key and not making the UIProperty global in the class. Note that the UIProperty label (PROP_KEY) **should be unique** otherwise only one will be kept by EMU. The label should be easily understandable by the user, as it will appear as "[Panel name] [UIProperty label]" in the configuration wizard.

example: A ConfigurablePanel named "Laser0" with a UIProperty labeled "power (mW)" will appear in the configuration wizard as "Laser0 power (mW)" 



###### Retrieving a UIProperty

The UIProperty can then easily be retrieved using the following call:

	UIProperty uiprop = getUIProperty(PROP_KEY);


###### Changing the state of a UIProperty

The state of the UIProperty should not be changed directly but through the setUIProperty(...) method:

```java
setUIProperty(PROP_KEY, newValue);
```



###### Implementing propertyhasChanged(...)

This method should modify the JComponents based on a newvalue from the UIProperty propertyName.

```java
@Override
protected void propertyhasChanged(String propertyName, String newvalue) {
    if(PROP_KEY.equals(propertyName)){
        // Here change the corresponding JComponents accordingly to newvalue
    }
}
```
example: update a JTextField with the new value.



###### Extensions of UIProperty

##### SingleStateUIProperty

A SingleStateUIProperty can only set the device property to a single-state. This type of UIProperty is particularly suited for JButtons, as the latter can only be clicked and not selected.

In the configuration wizard, a SingleStateUIProperty automatically generates an additional line to allow the user to set the value of the single-state.

example: zero a stage position



##### TwoStateUIProperty

A TwoStateUIProperty can only set the device property to an On state or to an Off state. These states are set in the configuration. TwoStateUIProperties are particularly suitable for components with two states such as JToggleButton, JCheckBox, JRadioButton or even JComboBox/ButtonGroup with only two elements.

TwoStateUIProperties accept the following values:

- On state: 
  - The actual On state value (as set in the configuration)
  - TwoStateUIProperty.getOnStateName()
  - "1"
  - "true"
- Off state:
  - The actual Off state value (as set in the configuration)
  - TwoStateUIProperty.getOffStateName()
  - "0"
  - "false"

Note that 0/1 or true/false have lower priority and in case of redundancy the actual On/Off states or the static methods will prevail.

TwoStateUIProperties provide also the following convenience methods:

```java
public boolean isOnState(String value);
public boolean isOffState(String value);
```

These two methods can be used in propertyhasChanged(...) in order to determine if the new value is the On or Off state.

In the configuration wizard, a SingleStateUIProperty automatically generates two additional lines to allow the user to set the value of the On and Off states.

example: turn a laser on or off.



##### MultiStateUIProperty

A MultiStateUIProperty has a fixed number of states, declared during instantiation:

```java
int number_states = 4;
addUIProperty(new MultiStateUIProperty(this, PROP_KEY, "Property with 4 states.", number_states));
```
MultiStateUIProperties are useful in translating multi-state components to a set of device property values. They are in particular well suited for ButtonGroups or JComboBoxes.

Because the actual state values are not relevant to the UI (only to the device property), using state indices or names is more useful:

```java
public boolean setStateNames(String[] stateNames); // for instance with StringUIParameter

// get name from index or value
public String getStateName(int pos);
public String getStateNameFromValue(String value);

// get index from value
public int getStateIndex(String val);
```

MultiStateUIProperties accept by order of priority the following values:

1. The actual value of a state
2. The name of a state
3. The index of a state

Querying the index or the state name can be done as follow: 

```java
@Override
protected void propertyhasChanged(String propertyName, String newvalue) {
    if(PROP_KEY.equals(propertyName)){
        MultiStateUIProperties mp = (MultiStateUIProperties) getUIproperty(PROP_KEY);
       
        // Get the index of the new state
        int index = mp.getStateIndex(newvalue);
            
        // Or get the state name
        String name = mp.getStateNameFromValue(newvalue);
    }
}
```

Other convenience methods can be called:

```java
public int getNumberOfStates();
private boolean isEqual(String stateval, String valToCompare);
```



In the configuration wizard, a MultiStateUIProperty automatically generates as many additional lines as states to allow the user to set their values.

example: the different filters of a filterwheel.



##### RescaledUIProperty

A RescaledUIProperty is a UIProperty that is only compatible with Float and Integer device properties with limits (a minimum and a maximum). 

It has two parameters that can be set post-initialization: slope and offset. 

```java
public boolean setScalingFactors(double slope, double offset);
```

Each value submitted to a RescaledUIProperty  will be scaled to *slope\*value+offset* before setting the device property. Similarly, any new value will be forwarded to the RescaledUIProperty  as *(value-offset)/slope*.

RescaledUIProperty have the following convenience methods: 

```java
public double getSlope();
public double getOffset();
public boolean haveSlopeOffsetBeenSet();
```

For now, the offset and the slop can be set through DoubleUIParameters by the user.

example: percentage of a device property.



###### SwingUIListeners

The last method to implement in the ConfigurablePanel that is related directly to the UIProperties is addComponentListeners(). In this method, one should add the action or change listeners on the JComponents that dictate the effect of user interactions (e.g.: user clicking on a button).

For each relevant JComponent, a UIproperty can be affected and propagate change to its corresponding device property. EMU provides many static methods to avoid implementing these actions:



////////////////

##### Flags

Flags are 

changing a property state



##### Examples

- MultiStateUIPropertyButtonGroup.java
- MultiStateUIPropertyComboBox.java
- RescaledUIPropertyPercentage.java
- SingleStateUIPropertyButton.java
- TwoStateUIPropertyToggleButton.java
- UIPropertyText.java



#### UIParameter  <a name="uiparam"></a>  

UIParameters are parameters that are communicated to a ConfgiurablePanel at the start of EMU or after saving a configuration. They are of different types:



| UIParameter types     | Type                            | Example use                                                  |
| --------------------- | ------------------------------- | ------------------------------------------------------------ |
| BoolUIParameter       | Boolean value                   | enable/disable a button<br />hide/show a JPanel<br />default value for a JComponent |
| ColorUIParameter      | Color object                    | title color<br />button color                                |
| ComboUIParameter      | String value from a known array | default value of a JComboBox<br />choice between several options |
| DoubleUIParameter     | Double value                    | set an attribute value                                       |
| IntegerUIParameter    | Integer value                   | set an attribute value                                       |
| StringUIParameter     | String value                    | title or text<br />comma separated values                    |
| UIPropertyUIParameter | String label of a UIProperty    | to be able to query another UIProperty                       |

Naming convention and process

##### BoolUIParameter

##### ColorUIParameter

##### ComboUIParameter

##### DoubleUIParameter

##### IntegerUIParameter

##### StringUIParameter

##### UIPropertyUIParameter



```java
// parameter
public final static String PARAM_LABEL = "Name of the parameter"; 

@Override
protected void initializeParameters() {
	
    String description = "Description of the parameter";
    boolean default_val = true;
    this.addUIParameter(new BoolUIParameter(this, PARAM_LABEL, 
                                            description, default_val));
}

@Override
protected void parameterhasChanged(String parameterName) {
	if(PARAM_LABEL.equals(parameterName)) { 
		try {
			boolean b  = getBoolUIParameterValue(PARAM_LABEL);
            // do something with b
		} catch (IncorrectUIParameterTypeException | UnknownUIParameterException e) { 
			e.printStackTrace();
		}
	}
}
```



##### Examples

- BoolEnableComponent.java
- BoolShowPanel.java
- ColorBorderTitle.java
- ComboDefaultValue.java
- DoubleAttributeValue.java
- IntAttributeValue.java
- StringBorderTitle.java
- StringButtonText.java
- StringMultipleTextsAndColors.java



#### InternalProperty  <a name="intprop"></a>  

InternalProperties are intended to share values between two ConfigurablePanels. They are declared by using the following method in initializeInternalProperties():

```java
addInternalProperty(...);
```

If two InternalProperties bear the same label (in their instantiation) and are of the same type, then those two InternalProperties will be fused and the two ConfigurablePanels will share the same one.

InternalProperties are of the following type:

- **BoolInternalProperty**

- **DoubleInternalProperty**

- **IntegerInternalProperty**

  

###### usage example:

In two ConfigurablePanels:

```java
public final static String INTERNAL_VALUE = "Shared value";

@Override
protected void initializeInternalProperties() {		
	int value = 0;
    addInternalProperty(new IntegerInternalProperty(this, INTERNAL_VALUE, value));
}

@Override
public void internalpropertyhasChanged(String label) {
	if(INTERNAL_MAXPULSE.equals(label)){
		try {
			int value = getIntegerInternalPropertyValue(INTERNAL_MAXPULSE);
            // do something
		} catch (IncorrectInternalPropertyTypeException |
                  UnknownInternalPropertyException e) {
			e.printStackTrace();
		}
	}
}
```

Then, when the InternalProperty is modified in ConfigurablePanel #1:

```java
try {
	setInternalPropertyValue(INTERNAL_VALUE,val);
} catch (IncorrectInternalPropertyTypeException | UnknownInternalPropertyException e) {
	e.printStackTrace();
}
```

Then internalpropertyhasChanged(INTERNAL_VALUE) is called in the second ConfigurablePanel #2.



#### Implementing a ConfigurablePanel  <a name="implconf"></a>  

#### Methods calling order  <a name="orderpane"></a>  

All components should be declared and added in the constructor.

1. super-constructor()
2. initializeProperties() 
3. initializeParameters()
4. initializeInternalProperties()
5. rest of the constructor() (instantiation of the JComponents)

Then call from ConfigurableMainFrame causes:

1. propertyhasChanged() on all UIProperties
2. parameterhasChanged() on all UIParameters
3. addComponentListeners() (parameters apply and the first update of the UIProperties does not trigger a changeUIProperty)

Other methods:

#### Useful methods  <a name="useflpane"></a>  

### ConfigurableMainFrame  <a name="confframe"></a>  

#### Plugin Settings  <a name="plugsettgs"></a>  



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



```java
boolean b = ((BoolSetting) settings.get(SETTING_USE_PANEL)).getValue();
int b = ((IntSetting) settings.get(SETTING_NB_BUTTONS)).getValue();
String b = ((StringSetting) settings.get(SETTING_TITLE_PANE)).getValue();
```



#### Instantiating the ConfigurablePanels  <a name="instframe"></a>  

#### Methods calling order  <a name="orderframe"></a>  

1. super-constructor(), this includes setting the Settings
2. register the settings (plugin settings from the configuration)
3. initComponents() (where all ConfigurablePanels are instanciated)
4. link all InternalProperties
   1. if two InternalProperties have the same label and type, then they become shared between the two ConfigurablePanels.
   2. if they have the same label but different types, then the second discovered InternalProperty is ignored
5. retrieves UIProperties and UIParameters
   1. if two properties have the same label, only the last one is kept (unknown order)
   2. if two parameters have the same label and type they become shared between the two ConfigurablePanels. If not, the second discovered one is ignored.

Upon saving a new configuration or loading one:

1. All of the above in order.
2. update all ConfigurablePanels (propertyHasChanged(), then parameterHasChanged())
3. addComponentListeners() on all ConfigurablePanels 



#### Useful methods  <a name="useflframe"></a>  

### UIPlugin  <a name="uiplug"></a>  

### Drag and drop softwares  <a name="dragndrop"></a>  

EMU is compatible with drag and drop softwares. Refer to the tutorial section for an example with Eclipse.

Popular drag and drop Swing designers include:

- [Eclipse WindowBuilder]( https://www.eclipse.org/windowbuilder/ )
- [IntelliJ GUI Designer]( https://www.jetbrains.com/help/idea/gui-designer-basics.html ) (not tested)
- [NetBeans GUI Builder]( https://netbeans.org/kb/docs/java/quickstart-gui.html ) (not tested)

## Resources  <a name="res"></a>  

### Tutorial  <a name="tuto"></a>  

The EMU tutorial offers a detailed walk through of how to build a UI for EMU using drag and drop softwares (Eclipse WindowBuilder). The plugin built during the tutorial controls four lasers and a filterwheel.

### Examples  <a name="ex"></a>  

The EMU example repository offers example UI for EMU:

- A base plugin to help kick-start a new EMU plugin.
- A plugin controlling a single iBeamSmart (Toptica) laser.
- A simple UI controlling four lasers and a filterwheel (the plugin is based on the tutorial example with few extra steps).

### htSMLM  <a name="htsmlm"></a>  

htSMLM is a complex EMU plugin used by the Ries lab (EMBL) to control their automated localization microscopes. 






