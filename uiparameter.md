# UIParameter   

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

In the configuration wizard, BoolUIParameter appear as a checkbox. The ColorUIParameter are rendered as a drop down list with the available colors and the ComboUIParameter as a drop down list with the choices passed as parameter during their instantiation. Finally, all the other UIParameters appear as a text field.



## Declaring a UIParameter

```java
private final static String PARAM_KEY = "MyParameter";

@Override
protected void initializeParameters() {
    boolean default_val = true;
    addUIParameter(new BoolUIParameter(this, PARAM_BOOL, "Description.", default_val));
}
```



## Naming conventions

If two UIParameter have the same label and the same type (e.g. IntegerUIParameter), then the first one is substituted in the second's ConfigurablePanel. Only a single entry will appear in the configuration wizard table and will affect both ConfigurablePanels.

If the two have different types, then the second encountered UIParameter is discarded.



## Implementing parameterhasChanged()

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



## Examples

All examples are available in the [source folder]( https://github.com/jdeschamps/EMU-guide/tree/master/guide/src/main/java/de/embl/rieslab/emuguide/uiparameters ).

- [Enable/disable a component using a BoolUIParameter](guide/src/main/java/de/embl/rieslab/emuguide/uiparameters/BoolEnableComponent.java)
- [Hide/show a panel using a BoolUIParameter](guide/src/main/java/de/embl/rieslab/emuguide/uiparameters/BoolShowPanel.java)
- [Change the color of border title using a ColorUIParameter](guide/src/main/java/de/embl/rieslab/emuguide/uiparameters/ColorBorderTitle.java)
- [Select a default JComboBox element using a ComboUIParameter](guide/src/main/java/de/embl/rieslab/emuguide/uiparameters/ComboDefaultValue.java)
- [Set a double value using a DoubleUIParameter](guide/src/main/java/de/embl/rieslab/emuguide/uiparameters/DoubleAttributeValue.java)
- [Set an integer value using an IntegerUIParameter](guide/src/main/java/de/embl/rieslab/emuguide/uiparameters/IntAttributeValue.java)
- [Change a border title using a StringUIParameter](guide/src/main/java/de/embl/rieslab/emuguide/uiparameters/StringBorderTitle.java)
- [Change a button text using a StringUIParameter](guide/src/main/java/de/embl/rieslab/emuguide/uiparameters/StringButtonText.java)
- [Use a StringUIParameter to change multiple colors or texts](guide/src/main/java/de/embl/rieslab/emuguide/uiparameters/StringMultipleTextsAndColors.java)



[Back to the ConfigurablePanel](configurablepanel.md)

[Back to the main menu](index.md)