# ConfigurablePanel  

It is important to plan some aspects of a ConfigurablePanel before starting building it. Here are few points worth considering:

- What function will it fulfill? List the type of device properties it will modify, then decide on the corresponding UIProperties (e.g. device on/off pairs with a TwoStateUIProperty).

- What will the panel look like? List the components and lay out their organization, as well as which component interacts with which UIProperty.

- What aspects of it should be parameterized? Picture which elements should be easily changed by the user (title, colors...etc...) and decide on UIParameters.

  

## ConfigurablePanel methods

A ConfigurablePanel must implements few methods, which are shown empty in the following example:

```java
import de.embl.rieslab.emu.ui.ConfigurablePanel;

public class MyPanel extends ConfigurablePanel {

	//////////////////////// Constructor 
	public MyPanel(String label) {
		super(label);
		
		// Add JComponents here 
        // (see Swing tutorials online or use drag and drop software) 
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

All components (JComponents) should be declared and added in the constructor. Then, the methods are called in a particular order:

1. super-constructor()
2. initializeProperties() 
3. initializeParameters()
4. initializeInternalProperties()
5. rest of the constructor() (instantiation of the JComponents)

Finally, upon configuration (at the start-up of EMU):

1. propertyhasChanged(...) looped call on all UIProperties
2. parameterhasChanged(...) looped call on all UIParameters
3. addComponentListeners(...), which allows using the UIParameter's values



The following sections dive into the different objects declared by a ConfigurablePanel:

1. [UIProperty](uiproperty.md)
2. [SwingUIListeners](uiproperty.md#swing)
3. [UIParameter](uiparameter.md)
4. [InternalProperty](internalproperty.md)

In particular, you will find examples on how to implement the different methods of a ConfigurablePanel.



## Roadmap  <a name="roadmap"></a>  

- Create the layout of the JComponents

- Declare the UIParameters

- Implement parameterhasChanged()

- Declare the UIProperties

- Implement propertyhasChanged()

- Screen the SwingUIListeners methods for useful methods

- Implement in addSwingListeners() the missing functionalities (not found in SwingUIListeners)




## Example

- [A ConfigurablePanel declaring all types of UIProperty, UIParameter and InternalProperty](guide\src\main\java\de\embl\rieslab\emuguide\GuideConfigurablePanel.java)



[Back to the programming guide](programmingguide.md)

[Back to the main menu](index.md)
