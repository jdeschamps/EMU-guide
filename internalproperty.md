# InternalProperty  <a name="intprop"></a>  

## Declaring an InternalProperty

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



## Retrieving an InternalProperty value

InternalProperty values are queried using the following methods:

```java
public int getIntegerInternalPropertyValue(String INTPROP_KEY);
public boolean getBoolInternalPropertyValue(String INTPROP_KEY);
public double getDoubleInternalPropertyValue(String INTPROP_KEY);
```



## Example

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



[Back to the ConfigurablePanel](configurablepanel.md)

[Back to the main menu](index.md)