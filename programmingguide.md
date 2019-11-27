# Programming guide 

## EMU principle  

EMU is based on the Swing library. The main building blocks of EMU are **ConfigurablePanels**, which extend a JPanel (Swing). They declare **UIProperties** (e.g. laser power percentage), **InternalProperties** (values shared between panels) and **UIParameters** (e.g. title color). A ConfigurablePanel should be a unit of control corresponding to a device on the microscope. This can for instance be a laser controller (power, on/off) or a filterwheel (position).

ConfigurablePanels are assembled in a single **ConfigurableMainFrame**. The latter defines **Settings**, allowing for instance having optional panels or defining how many panels of a certain type there are. The ConfigurableMainFrame is instantiated by a **UIPlugin** implementation, which serves as a plugin for EMU. Plugins are discovered by EMU using the Java ServiceLoader mechanism and is run in the EMU subfolder of Micro-Manager.

UIproperties, UIParameters and Settings appear in the configuration wizard, the graphical interface enabling the rapid configuration of the plugin by the user. After configuration, the UIProperties are mapped to Micro-Manager device properties. Then, any user interaction with the a ConfigurablePanel component triggers a UIProperty, which in turn changes the state of the device property it has been allocated to.

EMU is designed to involve minimum heavy lifting and coding in the plugin in order to obtain a functional UI.



## Table of content

1. [JComponents](jcomponents.md)
2. [ConfigurablePanel](configurablepanel.md)
   1. [UIProperty](uiproperty.md)
   2. [SwingUIListeners](uiproperty.md#swing)
   3. [UIParameter](uiparameter.md)
   4. [InternalProperty](internalproperty.md)
3. [ConfigurableMainFrame](configurablemainframe.md)
   1. [Methods to implement](configurablemainframe.md)
   2. [Plugin Settings](configurablemainframe.md#settings)
4. [UIPlugin](plugin)
5. [Using Eclipse](usingeclipse.md)



Note that the [guide](guide) folder contains examples implementations of the different classes. The [tutorial](tutorial) folder is a detailed walk-through of a plugin implementation using a drag and drop software, while the [examples](examples) folder consist of three example plugins.

The [Javadoc](https://jdeschamps.github.io/EMU/) is available in the [EMU repository]( https://github.com/jdeschamps/EMU ).

[Back to the menu](README.md#guide)

