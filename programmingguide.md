# Programming guide 

## EMU principle  

EMU is based on the Swing library. The main building blocks of EMU are **ConfigurablePanels**. A ConfigurablePanel extends JPanel (Swing) and declares **UIProperties** (e.g. laser power percentage), **InternalProperty** (values shared between panels) and **UIParameter** (e.g. title color). A ConfigurablePanel should be a unit of control corresponding to a device on the microscope. This can for instance be a laser controller (power, on/off) or a filterwheel (position).

ConfigurablePanels are assembled in a single **ConfigurableMainFrame**. The latter defines **Settings**, allowing for instance having optional panels. The ConfigurableMainFrame is instantiated by a **UIPlugin** implementation, which serves as a plugin for EMU. Plugins are discovered by EMU using the Java ServiceLoader mechanism run in the EMU subfolder of Micro-Manager.

UIproperties, UIParameters and Settings appear in the configuration wizard, the graphical interface enabling the rapid configuration of the plugin by the user. After configuration, the UIProperties are mapped to Micro-Manager device properties. Then, any user interaction with a ConfigurablePanel component triggers a UIProperty that, in turn, changes the state of the device property to which it has been allocated.

EMU is designed to involve minimum heavy lifting and coding in the plugin in order to obtain a functional UI.



## Table of content

1. [JComponents](jcomponents.md)
2. [ConfigurablePanel](configurablepanel.md)
   3. [UIProperty](uiproperty.md)
   2. [SwingUIListeners](uiproperty.md#swing)
   3. [UIParameter](uiparameter.md)
   4. [InternalProperty](internalproperty.md)
3. [ConfigurableMainFrame](configurablemainframe.md)
   8. [Methods to implement](configurablemainframe.md)
   2. [Plugin Settings](configurablemainframe.md#settings)
9. [UIPlugin](plugin)
10. [Compiling and debugging](usingeclipse.md)



#### Code example

Note that the [guide](guide) folder contains examples implementations of the different classes. The [tutorial](tutorial) folder is a detailed walk-through of a plugin implementation using a drag and drop software, while the [examples](examples) folder consist of three example plugins.

The [Javadoc](https://jdeschamps.github.io/EMU/) is available in the [EMU repository]( https://github.com/jdeschamps/EMU ).



#### Bug reporting

Please report bugs as Github repository [issues](https://github.com/jdeschamps/EMU/issues).



[Back to the main menu](index.md)

