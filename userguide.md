# EMU: a user perspective

In EMU, user interfaces declare **properties** and **parameters**. EMU  a menu to map the UI properties to **Micro-Manager device properties** and to set the values of the parameters.

Then, the parameters are applied to the UI, changing its look. Interacting with the components of the UI (buttons, sliders, text area...etc...) triggers the UI properties, which in turn change the state of the device properties to which they are allocated.

EMU therefore makes the UI easily configurable and transferable, without hard-coding and need to recompile each time a device changes.



## Table of contents

1. [Principle](#principle)
2. [Configuration](#configuration)
3. [Configuration wizard](#confwiz)
4. [Help](#help)
5. [Plugin settings](#pluginsett)
6. [Global settings](#glob)
7. [Properties](#prop)
8. [Parameters](#param)
9. [Configuration name](#name)
10. [Configuration manager](#confwiz)
11. [Plugin menu](#plugmenu)

[Back to the menu](README.md#guide)



## Principle <a name="principle"></a>

#### Plugin

While EMU is a Micro-Manager plugin, it also loads its own plugins: user interfaces. Once compiled into a .jar file, the plugins must be placed under the EMU folder in Micro-Manager (C:/Path/to/MicroManager/EMU/).

#### Settings, properties and parameters

In EMU, UIs consist of a **single frame** composed of multiple **panels**. The frame offers some options (called *settings*), while each panel declares a number of *properties* and *parameters*:

- Plugin *settings* allow to define options, such as titles or showing/hiding a panel.
- *Properties* are mapped through EMU's interface to Micro-Manager device properties.
- *Parameters* are used to customize the panels (e.g. titles, colors, enable/disable button).



## Configuration<a name="configuration"></a>

Once installed, EMU is accessible from Micro-Manager by clicking on "**Plugins/Interface/EMU**" in the main window. If a single plugin is present in the EMU folder, then it will be automatically loaded. If more than one plugin is present, then the user will be prompted with a choice. Then, the chosen UI will be loaded.

The first step in using EMU and one of its plugin is to create a *plugin configuration* containing the values of each *setting*, *property* and *parameter*. Later on, the default *plugin configuration* is loaded automatically at start.

The configuration is a JSON file saved in your Micro-Manager folder under the EMU folder (e.g.: Micro-Manager/EMU/config.uicfg). It can contain the name of the default *plugin configuration* and one or more *plugin configurations* as pairs of key and values. 

Configuration related actions are accessible through the menu bar, by selecting *Configuration*.

![menu-config](C:/Users/Ries/EMU/emu-guide/img/menu-config.png)



- **Modify configuration**: Starts the *configuration wizard*, allowing the user to modify the values of the *settings*, *properties* and *parameters*. The *configuration wizard* is automatically launched at start-up if no *plugin configuration* exists. 
- **Manage configurations**: Starts the *configuration manager*, allowing the user to delete *plugin configurations*.
- **Switch plugin**: Allows loading a different plugin instead of the current one. It also makes the new *plugin configuration* default.
- **Switch configuration**:  Allows loading a different *plugin configuration*. The default is set to the new configuration.



### Configuration wizard<a name="confwiz"></a>

Once EMU is loaded in Micro-manager, the user can configure the UI graphically by using the *configuration wizard*, accessible through "**Configuration/Modify configuration**". It is automatically started if no configuration is found.

![config_wizard](C:/Users/Ries/EMU/emu-guide/img/config_wizard.PNG)

The configuration wizard displays four tabs:

- Properties: mapping of a property to a Micro-Manager device and device property.
- Parameters: parameters to customize the UI.
- Plugin Settings: plugin options. 
- Global Settings: EMU options.



#### Help<a name="help"></a>

Selecting the *help* button opens the *help window*. Select a row of the properties/parameters/settings to refresh the content of the *help window* with a description of the currently selected property/parameter/setting.



#### Plugin settings<a name="pluginsett"></a>

*Plugin settings* allow options at the level of the main frame. For instance, they can be used to name a panel or to make it optional (show/hide setting).

###### Example: htSMLM

![config_wizard_plugsettings](C:/Users/Ries/EMU/emu-guide/img/config_wizard_plugsettings.PNG)



Changing *plugin settings* might cause a change in the list of properties and parameters. For instance, if a *plugin setting* is an option to show an extra panel, the properties and parameters corresponding to this panel will not yet be in the properties and parameters tabs. They will however appear next time you start the configuration wizard.

**Therefore**,  whenever you modify the *plugin settings*, save the configuration and start the *configuration wizard* anew.



#### Global **settings**<a name="glob"></a>

For the moment, there is a single global setting:

- **enable unallocated warnings**: when checked, EMU notifies the user when saving the *plugin configuration* that some UIProperties are not allocated. Modifying this setting does not require restarting the configuration manager.

  

#### Properties<a name="prop"></a>

The *properties* tab contains three columns: the UI properties, device names from micro-manager and their corresponding device properties.

For each relevant UI *property*, select the corresponding device from the drop down list.  Use the *help window* to get more details on which type of device to select. Then, select the appropriate device property.

###### Example: htSMLM

![config_wizard](C:/Users/Ries/EMU/emu-guide/img/config_wizard.PNG)

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



#### Parameters<a name="param"></a>

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

![param](C:/Users/Ries/EMU/emu-guide/img/param.png)



#### Configuration name<a name="name"></a>

On top of the *configuration wizard*, one can modify the name of the *plugin configuration*. As prompted when doing so, modifying the name will create a new *plugin configuration* upon saving. Configuration can be deleted in the *configuration manager*.



### Configuration manager<a name="confman"></a>

The *configuration manager* is accessible through the menu "Configuration/Manage configurations". It allows deleting *plugin configurations* (except the current one).



![config_manager](C:/Users/Ries/EMU/emu-guide/img/config_manager.PNG)





## Plugin menu  <a name="plugmenu"></a>

![menu-plugin](C:/Users/Ries/EMU/emu-guide/img/menu-plugin.png)



The Plugin menu gives access to two actions: refresh UI and show description.

#### Refresh

Modifying device properties outside the UI plugin does not impact the state of the UI, whether it is done by interacting with configuration group presets, the device property browser or scrips. Thereby synchronizing the UI with the current state of the device properties.

#### Description

The description is automatically generated from the panels own description and should tell the user what each panel is intended for.



[Back to the menu](README.md#guide)