# UIProperty  

UIProperties are the link between Micro-Manager device properties and the JComponents. They are declared and added to a ConfigurablePanel in the initializeProperties() method. Interaction between the user and the JComponents should trigger a UIProperty change. Because the UIProperties are paired by the user in the configuration wizard, the UIProperties propagate the value change to the Micro-Manager device properties.

Different types of UIProperties are available in EMU:

| UIProperty type       | Note                                | Compatible JComponents (e.g.)                                |
| --------------------- | ----------------------------------- | ------------------------------------------------------------ |
| UIProperty            | general UI property, no restriction | JTextFields<br />JSpinners<br />JSliders                     |
| SingleStateUIProperty | accepts a single state only         | JButtons                                                     |
| TwoStateUIProperty    | accepts an On and an Off state      | JToggleButtons<br />JCheckBoxes<br />JRadioButtons<br />ButtonGroups (N=2) |
| MultiStateUIProperty  | accepts a fixed number of states    | ButtonGroups<br />JComboBoxes                                |
| RescaledUIProperty    | rescales a property value           | JTextFields<br />JSpinners<br />JSliders                     |



## Table of contents

1. [Creating a UIProperty](#creating)
2. [Retrieving a UIProperty](#retrieving)
3. [Changing the state of a UIProperty](#changing)
4. [Implementing propertyhasChanged](#haschanged)
5. [UIProperty extensions](#extensions)
6. [SwingUIListeners](#swing)
7. [Flags](#flags)
8. [Examples](#examples)

[Back to the ConfigurablePanel](configurablepanel.md)

[Back to the main menu](index.md)



## Creating a UIProperty<a name="creating"></a>  

UIProperties should be instantiated and added to a [ConfigurablePanel](configurablepanel.md) in the initializeUIProperties() method:

```java
private final String PROP_KEY = "MyProperty";

@Override
protected void initializeProperties() {
	addUIProperty(new UIProperty(this, PROP_KEY, "Description"));    
}
```

Note that the UIProperty label (PROP_KEY) **should be unique** otherwise only one UIProperty will be kept by EMU. The label should be easily understandable by the user, as it will appear as "[Panel name] [UIProperty label]" in the configuration wizard (without brackets).

**example**: A ConfigurablePanel named "Laser0" with a UIProperty labeled "power (mW)" will appear in the configuration wizard as "Laser0 power (mW)" 



## Retrieving a UIProperty<a name="retrieving"></a>  

The UIProperty can then easily be retrieved using the following call:

	UIProperty uiprop = getUIProperty(PROP_KEY);



## Changing the state of a UIProperty<a name="changing"></a>  

The state of the UIProperty should not be changed directly but through the setUIProperty(...) method:

```java
setUIProperty(PROP_KEY, newValue);
```

Calling this method ensures two things: that the update does not take place during configuration (when updating is turned off) and that it does not run on the EDT.



**example**: Setting the value of UIProperty based on an integer value entered by the user in a JTextField.

```java
JTextField txtf = new JTextField();
txtf.addActionListener(new java.awt.event.ActionListener() {
	public void actionPerformed(java.awt.event.ActionEvent evt) {
		String s = txtf.getText();
		if (EmuUtils.isInteger(s)) {
			cp.setUIPropertyValue(propertyKey, s);
		}
	}
});
```

Note that EMU provides static methods to add action listeners to Swing components, see [SwingUIListeners](#swing) section.



## Implementing propertyhasChanged(...)<a name="haschanged"></a>  

This method should modify the JComponents based on a newvalue from the UIProperty propertyName.

```java
@Override
protected void propertyhasChanged(String propertyName, String newvalue) {
    if(PROP_KEY.equals(propertyName)){
        // Here change the corresponding JComponents accordingly to the newvalue
    }
}
```



## UIProperty extensions<a name="extensions"></a>  

A UIProperty has no restriction on the input of the new value requested by the user. EMU also provide extensions with some constraints, making them suitable for certain functions.



### SingleStateUIProperty<a name="single"></a>  

A SingleStateUIProperty can only set the device property to a single-state. This type of UIProperty is particularly suited for JButtons, as the latter can only be clicked and not selected.

In the configuration wizard, a SingleStateUIProperty automatically generates an additional line to allow the user to set the value of the single-state.

**example**: zero a stage position



### TwoStateUIProperty<a name="two"></a>  

A TwoStateUIProperty can only set the device property to an **On state** or to an **Off state**. The state values are set in the configuration by the user. TwoStateUIProperties are particularly suitable for components with two states such as JToggleButton, JCheckBox, JRadioButton or even JComboBox/ButtonGroup with only two elements.

TwoStateUIProperties accept the following values (in order of priority):

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

**example**: turn a laser on or off.



### MultiStateUIProperty<a name="multi"></a>  

A MultiStateUIProperty has a fixed number of states, declared during instantiation:

```java
int nStates = 4;
addUIProperty(new MultiStateUIProperty(this, PROP_KEY, "4-state property.", nStates));
```

MultiStateUIProperties are useful in translating multi-state components to a set of device property values. They are in particular well suited for ButtonGroups or JComboBoxes.

Because the actual state values are not relevant to the UI (only to the device property), using state indices or names is more useful:

```java
public boolean setStateNames(String[] stateNames); // for instance with a StringUIParameter in the parameterhasChanged() method of a ConfigurablePanel

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



In the configuration wizard, a MultiStateUIProperty automatically generates as many additional lines as there are states to allow the user to set their values.

**example**: the different filters of a filterwheel.



### RescaledUIProperty<a name="rescaled"></a>  

A RescaledUIProperty is a UIProperty that is only compatible with Float and Integer device properties with limits (a minimum and a maximum). 

In the configuration wizard, a RescaledUIProperty automatically generates a **slope** and **offset** entries with default values.

Each value submitted to a RescaledUIProperty  will be scaled to *slope\*value+offset* before setting the device property. Similarly, any new value will be forwarded to the RescaledUIProperty  as *(value-offset)/slope*.

RescaledUIProperty have the following convenience methods: 

```java
public double getSlope();
public double getOffset();
public boolean haveSlopeOffsetBeenSet();
```

**example**: percentage of a device property.



## SwingUIListeners <a name="swing"></a>  

The last method to implement in the ConfigurablePanel that is related directly to the UIProperties is addComponentListeners(). In this method, one should add the actions or change listeners on the JComponents that dictate the effect of user interactions (e.g.: user clicking on a button).

For each relevant JComponent, a UIProperty can be affected and propagate changes to its corresponding device property. EMU provides many static methods in the SwingUIListeners class to avoid implementing these actions:

| Method                                 | JComponent output / UIProperty inpur                         | JComponents                   |
| -------------------------------------- | ------------------------------------------------------------ | ----------------------------- |
| addActionListenerOnDoubleValue(...)    | double<br />double within [min,max]                          | JTextField                    |
| addActionListenerOnIntegerValue(...)   | integer<br />integer within [min,max]<br />integer with feedback | JTextField<br />JSlider<br /> |
| addActionListenerOnSelectedIndex(...)  | selected index                                               | ButtonGroup<br />JComboBox    |
| addActionListenerToSingleState(...)    | button clicked                                               | JButton                       |
| addActionListenerToTwoState(...)       | button state (selected/deselected)                           | JToggleButton                 |
| addActionListenerOnNumericalValue(...) | double                                                       | JSpinner                      |

Few more methods are available, including feedback to another component. For instance, a JSlider can then modify a UIProperty and change the text of a JLabel or a JTextField to show the use the new value. 

Finally, some additional static methods (addActionListenerToAction) use lambda expressions to trigger a method instead of a UIProperty change.

The reason to add the listeners in this method and not in the constructor is two fold: firstly, changing the components in parameterhasChanged(...) and in propertyhasChanged(...) (which are called before) might trigger the listener; secondly, then the value of UIParameters can be used in those methods.



## Flags<a name="flags"></a>  

Flags are meant to create categories between UIProperties. The default flag is a NoFlag, created when calling a UIProperty constructor without flag parameter.

Flags can be queried using the getFlag() method:

```java
public Flag getFlag();
```

EMU does not provide other flags than the NoFlag, the flags must be created for the purpose of the plugin. For instance, [htSMLM]( https://github.com/jdeschamps/htSMLM ) uses a variety of flags to distinguish laser, focus-related or filterwheel properties. In addition, UIPropertyUIParameters make use of a flag to filter UIProperties available as a value for the UIParameter.



## Examples<a name="examples"></a>  

All examples are available in the [source folder](https://github.com/jdeschamps/EMU-guide/tree/master/guide/src/main/java/de/embl/rieslab/emuguide/uiproperties).

- [A ConfigurablePanel with all UIProperty types declared](guide/src/main/java/de/embl/rieslab/emuguide/GuideConfigurablePanel.java)
- [How to use a MultiStateUIProperty with a ButtonGroup](guide/src/main/java/de/embl/rieslab/emuguide/uiproperties/MultiStateUIPropertyButtonGroup.java)
- [How to use a MultiStateUIProperty with a JComboBox](guide/src/main/java/de/embl/rieslab/emuguide/uiproperties/MultiStateUIPropertyComboBox.java)
- [How to use a RescaledUIProperty with a percentage JSlider](guide/src/main/java/de/embl/rieslab/emuguide/uiproperties/RescaledUIPropertyPercentage.java)
- [How to use a SingleStateUIProperty with a JButton](guide/src/main/java/de/embl/rieslab/emuguide/uiproperties/SingleStateUIPropertyButton.java)
- [How to user a TwoStateUIProperty with a JToggleButton](guide/src/main/java/de/embl/rieslab/emuguide/uiproperties/TwoStateUIPropertyToggleButton.java)
- [How to use a UIProperty with a JTextField](guide/src/main/java/de/embl/rieslab/emuguide/uiproperties/UIPropertyText.java)



[Back to the ConfigurablePanel](configurablepanel.md)

[Back to the main menu](index.md)