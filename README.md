# ![EMU](img/logo64.png)EMU guide



## Why use EMU?    <a name="why"></a>  

Micro-Manager controls the devices on your microscope using device properties (e.g. laser on/off). All device properties can be accessed through the device property browser. However, controlling the microscope through a long list of properties is cumbersome, slow and rather aimed at the microscope engineer than at the user. While interactions with device properties can be facilitated by using configuration preset groups or the quick access plugin, these cannot replace a user interface (UI) tailored to the microscope. 

Tailored interfaces have the advantage of rendering the control of the microscope intuitive. However, tailoring the UI often means hard-coded references to the specific device properties of the microscope and a need to recompile it every time something changes on the microscope.

[Easier Micro-manager User interface (EMU)]( https://github.com/jdeschamps/EMU ) offers means to make your Micro-Manager interface reconfigurable:

- Rapid design compatible with **drag and drop softwares** (e.g. Eclipse WindowBuilder).
- Functional Micro-Manager UI with only few lines of code thanks to EMU's back-end.
- Flexible and transferable: easy and intuitive configuration through EMU's interface.



## Guide <a name="guide"></a>  

1. [Installation](installation.md)
2. [EMU: a user perspective](userguide.md)
   1. [Principle](userguide.md#principle)
   2. [Configuration](userguide.md#configuration)
      1. [Configuration wizard](userguide.md#confwiz)
      2. [Configuration manager](userguide.md#confwiz)
   3. [Plugin menu](userguide.md#plugmenu)
3. [Programming guide](programmingguide.md)
   1. [EMU principle](programmingguide.md)
   2. [JComponents](jcomponents.md)
   3. [ConfigurablePanel](configurablepanel.md)
      1. [UIProperty](uiproperty.md)
      2. [SwingUIListeners](uiproperty.md#swing)
      3. [UIParameter](uiparameter.md)
      4. [InternalProperty](internalproperty.md)
   4. [ConfigurableMainFrame](configurablemainframe.md)
      1. [Methods to implement](configurablemainframe.md)
      2. [Plugin Settings](configurablemainframe.md#settings)
   5. [UIPlugin](plugin.md)
   6. [Using Eclipse](usingeclipse.md)
4. [Resources](#resources)
   1. [Tutorial](#tuto)
   2. [Examples](#expl)
   3. [htSMLM](#htsmlm)
   4. [Javadoc](#javadoc)

## Cite us
Joran Deschamps and Jonas Ries, ["EMU: reconfigurable graphical user interfaces for Micro-Manager", bioRxiv 2020.03.18.997494.](https://www.biorxiv.org/content/10.1101/2020.03.18.997494v1)


## Resources  <a name="resources"></a>  

### Tutorial  <a name="tuto"></a>  

The [EMU tutorial](tutorial) offers a detailed walk through of how to build a UI for EMU using a drag and drop software (Eclipse WindowBuilder). 

### Examples   <a name="expl"></a>  

This repository offers [example plugins for EMU](examples):

- A base plugin to help kick-start a new EMU plugin.
- A plugin controlling a single iBeamSmart laser (Toptica).
- A simple UI controlling four lasers and a filterwheel.

Note that the latter two plugins are distributed with EMU in Micro-Manager 2-gamma.

### htSMLM  <a name="htsmlm"></a>    

[htSMLM]( https://github.com/jdeschamps/htSMLM ) is a complex EMU plugin used by the Ries lab (EMBL) to control their automated localization microscopes. 

### Javadoc<a name="javadoc"></a>    

The [Javadoc]( https://jdeschamps.github.io/EMU/ ) is available is the [EMU repository]( https://github.com/jdeschamps/EMU ).
