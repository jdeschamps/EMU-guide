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

While EMU is a Micro-Manager plugin, it also loads its own plugins: user interfaces. These UIs need to be implemented in agreement with EMU classes and plugin system. Once compiled into a .jar file, the plugin must be placed under the EMU folder in Micro-Manager (C:/Path/to/MicroManager/EMU/).

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
- Scaled property: the property requires a slope and offset parameters. These can be used for instance to make a percentage (offset = 0, slope = (max device property value)/100).

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

JComponents are the Swing elements the user interacts with and are central to building a ConfigurablePanel. Here are examples of the most common components:

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

Calling this method ensures two things: that the update does not take place during configuration (when updating is turned off) and that it does not run on the EDT.



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
		try {
			MultiStateUIProperty mp = (MultiStateUIProperty)getUIProperty(PROP_MULTI);
				
			// Get the index of the new state
			int index = mp.getStateIndex(newvalue);

			// Or get the state name
			String name = mp.getStateNameFromValue(newvalue);
				
			// use index or name
			
		} catch (UnknownUIPropertyException e) { // in case PROP_KEY is unknown
			e.printStackTrace();
		}
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



In the configuration wizard, a RescaledUIProperty automatically generates a slope and offset entries with default values.

example: percentage of a device property.



###### SwingUIListeners

The last method to implement in the ConfigurablePanel that is related directly to the UIProperties is addComponentListeners(). In this method, one should add the action or change listeners on the JComponents that dictate the effect of user interactions (e.g.: user clicking on a button).

For each relevant JComponent, a UIproperty can be affected and propagate change to its corresponding device property. EMU provides many static methods in the SwingUIListeners class to avoid implementing these actions:

| Method                                 | JComponent input                                             | JComponents                   |
| -------------------------------------- | ------------------------------------------------------------ | ----------------------------- |
| addActionListenerOnDoubleValue(...)    | double<br />double within [min,max]                          | JTextField                    |
| addActionListenerOnIntegerValue(...)   | integer<br />integer within [min,max]<br />integer with feedback | JTextField<br />JSlider<br /> |
| addActionListenerOnSelectedIndex(...)  | selected index                                               | ButtonGroup<br />JComboBox    |
| addActionListenerToSingleState(...)    | button clicked                                               | JButton                       |
| addActionListenerToTwoState(...)       | button state                                                 | JToggleButton                 |
| addActionListenerOnNumericalValue(...) | double                                                       | JSpinner                      |

The feedback mechanism in addActionListenerOnIntegerValue(...) allows for instancethe value to also be updated on a another JComponent.

Finally, some additional static methods (addActionListenerToAction) use lambda expressions to trigger a method instead of a UIProperty change.

The reason to add the listeners in this method and not in the constructor is two fold: firstly, changing the components in parameterhasChanged(...) and in propertyhasChanged(...) (which are called before) might trigger the listener; secondly, then the value of UIParameters can be used in those methods.



###### Flags

Flags are meant to create categories between UIProperties. The default flag is a NoFlag, created when calling a UIProperty constructor without flag parameter.

Flags can be queried using the getFlag() method:

```java
public Flag getFlag();
```

EMU does not provide other flags than the NoFlag, the flags must be created for the purpose of the plugin. For instance, [htSMLM]( https://github.com/jdeschamps/htSMLM ) uses a variety of flags to distinguish laser, focus-related or filterwheel properties. In addition, UIPropertyUIParameters make use of a flag to filter UIProperties available as a value for the UIParameter.



##### Examples

- [A ConfigurablePanel with all UIProperty types declared](src/main/java/de/embl/rieslab/emuguide/GuideConfigurablePanel.java)
- [How to use a MultiStateUIProperty with a ButtonGroup](src/main/java/de/embl/rieslab/emuguide/uiproperties/MultiStateUIPropertyButtonGroup.java)
- [How to use a MultiStateUIProperty with a JComboBox](src/main/java/de/embl/rieslab/emuguide/uiproperties/MultiStateUIPropertyComboBox.java)
- [How to use a RescaledUIProperty with a percentage JSlider](src/main/java/de/embl/rieslab/emuguide/uiproperties/RescaledUIPropertyPercentage.java)
- [How to use a SingleStateUIProperty with a JButton](src/main/java/de/embl/rieslab/emuguide/uiproperties/SingleStateUIPropertyButton.java)
- [How to user a TwoStateUIProperty with a JToggleButton](src/main/java/de/embl/rieslab/emuguide/uiproperties/TwoStateUIPropertyToggleButton.java)
- [How to use a UIProperty with a JTextField](src/main/java/de/embl/rieslab/emuguide/uiproperties/UIPropertyText.java)



#### UIParameter  <a name="uiparam"></a>  

UIParameters are parameters that are communicated to a ConfigurablePanel at the start of EMU or after saving a configuration. They are of different types:

| UIParameter types     | Type                            | Example use                                                  |
| --------------------- | ------------------------------- | ------------------------------------------------------------ |
| BoolUIParameter       | Boolean value                   | enable/disable a button<br />hide/show a JPanel<br />default value for a JComponent |
| ColorUIParameter      | Color object                    | title color<br />button color                                |
| ComboUIParameter      | String value from a known array | default value of a JComboBox<br />choice between several options |
| DoubleUIParameter     | Double value                    | set an attribute value                                       |
| IntegerUIParameter    | Integer value                   | set an attribute value                                       |
| StringUIParameter     | String value                    | title or text<br />comma separated values                    |
| UIPropertyUIParameter | String label of a UIProperty    | to be able to query another UIProperty                       |



###### Declaring a UIParameter

```java
private final static String PARAM_KEY = "MyParameter";

@Override
protected void initializeParameters() {
    boolean default_val = true;
    addUIParameter(new BoolUIParameter(this, PARAM_BOOL, "Description.", default_val));
}
```



###### Naming conventions

If two UIParameter have the same label and the same type (e.g. IntegerUIParameter), then the first one is substituted in the second's ConfigurablePanel. Only a single entry will appear in the configuration wizard table and will affect both ConfigurablePanels.

If the two have different types, then the second encountered UIParameter is discarded.



###### UIParameter has changed

The parameterhasChanged(...) method is called upon configuration of the plugin. There, you can retrieve the new value of the parameter and use it to modify accordingly the ConfigurablePanel.

```java
@Override
protected void parameterhasChanged(String parameterName) {
    if(PARAM_KEY.equals(parameterName))  {
        // retrieve the value of the UIParameter
        try {
			boolean newvalue = getBoolUIParameterValue(PARAM_BOOL);
            
            // Do something with the value
		} catch (IncorrectUIParameterTypeException | UnknownUIParameterException e) {
			e.printStackTrace();
		}
    }
}
```

The ConfigurablePanel class has the following methods to retrieve parameter values:

```java
public boolean getBoolUIParameterValue(String PARAMETER_KEY);
public Color getColorUIParameterValue(String PARAMETER_KEY); 
public double getDoubleUIParameterValue(String PARAMETER_KEY); 
public int getIntegerUIParameterValue(String PARAMETER_KEY); 

// For StringUIParameter, ComboUIParameter and UIPropertyUIParameter
// (note that it would work for any UIParameter)
public String getStringUIParameterValue(String PARAMETER_KEY);
```

Note that these methods can throw an UnknownUIParameterException and/or an IncorrectUIParameterTypeException in case the PARAMETER_KEY is unknown or of the wrong type (therefore the method cannot return anything).

##### Examples

- [Enable/disable a component using a BoolUIParameter](src/main/java/de/embl/rieslab/emuguide/uiparameters/BoolEnableComponent.java)
- [Hide/show a panel using a BoolUIParameter](src/main/java/de/embl/rieslab/emuguide/uiparameters/BoolShowPanel.java)
- [Change the color of border title using a ColorUIParameter](src/main/java/de/embl/rieslab/emuguide/uiparameters/ColorBorderTitle.java)
- [Select a default JComboBox element using a ComboUIParameter](src/main/java/de/embl/rieslab/emuguide/uiparameters/ComboDefaultValue.java)
- [Set a double value using a DoubleUIParameter](src/main/java/de/embl/rieslab/emuguide/uiparameters/DoubleAttributeValue.java)
- [Set an integer value using an IntegerUIParameter](src/main/java/de/embl/rieslab/emuguide/uiparameters/IntAttributeValue.java)
- [Change a border title using a StringUIParameter](src/main/java/de/embl/rieslab/emuguide/uiparameters/StringBorderTitle.java)
- [Change a button text using a StringUIParameter](src/main/java/de/embl/rieslab/emuguide/uiparameters/StringButtonText.java)
- [Use a StringUIParameter to change multiple colors or texts](src/main/java/de/embl/rieslab/emuguide/uiparameters/StringMultipleTextsAndColors.java)



#### InternalProperty  <a name="intprop"></a>  

InternalProperties are intended to share values between two ConfigurablePanels. They are declared by using the following method in initializeInternalProperties():

```java
public final static String INTERNAL_PROP = "Internal prop";

@Override
protected void initializeInternalProperties() {
    int defval = 1;
	addInternalProperty(new IntegerInternalProperty(this, INTERNAL_PROP, defval));
}
```

If two InternalProperties bear the same label (in their instantiation) and are of the same type, then those two InternalProperties will be fused and the two ConfigurablePanels will share the same one. The default value will be the one set in the first encountered InternalProperty.

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

InternalProperty values are queried using the following methods:

```java
public int getIntegerInternalPropertyValue(String INTPROP_KEY);
public boolean getBoolInternalPropertyValue(String INTPROP_KEY);
public double getDoubleInternalPropertyValue(String INTPROP_KEY);
```



#### Implementing a ConfigurablePanel  <a name="implconf"></a>  

- Create the layout of the JComponents

- Declare the UIParameters

- Implement parameterhasChanged()

- Declare the UIProperties

- Implement propertyhasChanged()

- Screen the SwingUIListeners methods for useful methods

- Implement in addSwingListeners() the missing functionalities (not found in SwingUIListeners)

  

### ConfigurableMainFrame  <a name="confframe"></a>  

There is only a single ConfigurableMainFrame per plugin, instantiated by the plugin class itself. A ConfigurableMainFrame must assemble all ConfigurablePanels. The latter are automatically detected to extract all properties and parameters.

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



#### Plugin Settings  <a name="plugsettgs"></a>  

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



#### Instantiating the ConfigurablePanels  <a name="instframe"></a>  

ConfigurablePanels behave like JPanels and can therefore be added to a ConfigurableMainFrame using the add() method.



#### Useful methods  <a name="useflframe"></a>  

ConfigurableMainFrames  expose the EMU SystemController and Micro-Manager core. Through the SystemController, a ConfigurableMainFrame and the necessary ConfigurablePanels can get a reference to the Micro-Manager Studio and therefore the whole Micro-Manager Java API.



### UIPlugin  <a name="uiplug"></a>  

The UIPlugin interface is the class detected by EMU. The plugin system uses Java ServiceLoader. 

First implement the interface, returning your ConfigurableMainFrame in the getMainFrame() method:

```java
public class GuideUIPlugin implements UIPlugin{

	@Override
	public String getName() {
		return "Guide plugin";
	}

	@Override
	public ConfigurableMainFrame getMainFrame(SystemController controller,
                                              TreeMap<String, String> pluginSettings) {
        return new GuideConfigurableMainFrame("Guide plugin", controller,
                                              pluginSettings);
	}
}
```

####  

#### Provider-configuration file 

In order for the ServiceLoader to discover your plugin, you need to make sure the jar files contains the provider-configuration file. This file should be named "de.embl.rieslab.emu.plugin.UIPlugin" and contain the UIPlugin implementation's fully-qualified [binary name](https://docs.oracle.com/javase/7/docs/api/java/lang/ClassLoader.html#name), for instance:

```
de.embl.rieslab.emuguide.GuideUIPlugin
```

In order for the file to appear in the jar, you must respect the following project organization:

```
src/main/
      |
      ---> java/de/embl/rieslab/emuguide/
                                   |
                                   ---> GuideUIPlugin.java
      ---> resources/META-INF/services/
                                   |
                                   ---> de.embl.rieslab.emu.plugin.UIPlugin
              
```

In your project, the "de.embl.rieslab.emuguide.GuideUIPlugin.java" must be replaced by your own package structure and plugin name, in both the projects and the provider-configuration file. 

Make sure that resources is set as a source folder and explore the exported jar to make sure that the META-INF folder contains the /services/de.embl.rieslab.emu.plugin.UIPlugin file.

The most common errors causing the plugin to be ignored by the ServiceLoader are:

- Wrong folder structure
- Mistake in the file name ("de.embl.rieslab.emu.plugin.UIPlugin")
- Mistake in the path to the plugin (in the example: de.embl.rieslab.emuguide.GuideUIPlugin)



#### Export as jar

Finally, your project must be exported as a .jar. Make sure the configuration file is present in the jar under the correct path. Then place the archive in the EMU folder of your Micro-manager installation folder.



### Setting up an EMU project with Eclipse

1. Right-click on the package explorer and create a new java project.
2. Name the project and click on next.
3. In the Source tab, right-click and add successively two new source folders: "src/main/java" and "src/main/resources". Remove the "src" source folder.
4. In the Librairies tab, add an external jar and point to the EMU jar in C:/Path/to/Micro-Manager/mmplugins. Click on finish.
5. Create your packages in the src/main/java folder. Then right click on the relevant package and select new class. Create successively three classes:
   1. A class with a UIPlugin interface.
   2. A class with ConfigurableMainFrame as a superclass
   3. A class with ConfigurablePanel as a superclass
6. Add the provider-configuration file to the src/main/resources/META-INF/services folder. Add the fully-qualified binary name of your implementation of UIPlugin.
7. Once you will have replaced all null returns from your classes method and have no errors, export the project as a jar and place it in the EMU folder. Check that EMU can load the plugin.

Then you are ready to start implementing your plugin.



####  Testing the plugin

After having tested the export to a jar and import in EMU of your base plugin, you can set-up a run configuration in Eclipse to directly start Micro-Manager with your changes included in your plugin without exporting the jar. This would allow you to use the Eclipse debugger and have access to the Java console.

1. Right-click on your project and select Properties. Then go to Java Build path and in the Librairies tab, add the ij.jar from your Micro-Manager folder (add External jar). Then add all jars from the C:/Path/to/Micro-Manager/plugins/Micro-manager folder. Apply and close.
2. Right-click on your project and select Run as then Run configurations.
3. In the configuration window, add a new configuration and name it. Then in Main class, select ImageJ.
4. In the Arguments tab, select other in working directory and point to the Micro-Manager directory. Apply and then close.
5. Click on the arrow next to the run button and make sure your run your newly created configuration.

Micro-Manager should start. You can use Micro-Manager demo device adapter to fake the presence of hardware or directly test on your microscope.



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






