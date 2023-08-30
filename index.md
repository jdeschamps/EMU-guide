# EMU guide

## Why use EMU?    <a name="why"></a>  

Micro-Manager controls the devices on your microscope using device properties (e.g. laser on/off). All device properties can be accessed through the device property browser. However, controlling the microscope through a long list of properties is cumbersome, slow and rather aimed at the microscope engineer than at the user. While interactions with device properties can be facilitated by using configuration preset groups or the quick access plugin, these cannot replace a user interface (UI) tailored to the microscope. 

Tailored interfaces have the advantage of rendering the control of the microscope intuitive. However, tailoring the UI often means hard-coded references to the specific device properties of the microscope and a need to recompile it every time something changes on the microscope.

[Easier Micro-manager User interface (EMU)]( https://github.com/jdeschamps/EMU ) offers means to make your Micro-Manager interface reconfigurable:

- Rapid design compatible with **drag and drop softwares** (e.g. Eclipse WindowBuilder).
- Functional Micro-Manager UI with only few lines of code thanks to EMU's back-end.
- Flexible and transferable: easy and intuitive configuration through EMU's interface.



## Guide <a name="guide"></a>  

The guide contains

1. [Installation](installation.md)
2. [Quick introduction](quickintro.md)
3. [EMU: user guide](userguide.md)
4. [Programming guide](programmingguide.md)
5. Resources
   1. [EMU tutorial](tutorial): step-by-step implementation of an EMU plugin using a drag and drop software
   2. [Example plugins](examples)
   3. [htSMLM]( https://github.com/jdeschamps/htSMLM ): a complex EMU plugin used by the Ries lab at EMBL

## Cite us

Deschamps, J., Ries, J. EMU: reconfigurable graphical user interfaces for Micro-Manager. BMC Bioinformatics 21, 456 (2020).
doi: [10.1186/s12859-020-03727-8](https://doi.org/10.1186/s12859-020-03727-8)

## Contact us

By email:  joran.deschamps(at)fht(dot)org

On [image.sc](https://forum.image.sc/): post and tag @jdeschamps.

## Source-code and javadoc<a name="resources"></a>  

The [Javadoc]( https://jdeschamps.github.io/EMU/) is available in the [EMU repository]( https://github.com/jdeschamps/EMU ).
