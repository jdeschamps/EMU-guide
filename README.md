# EMU examples

[Easier Micro-manager User interface (EMU)]( https://github.com/jdeschamps/EMU ) is a [Micro-Manager](https://micro-manager.org/wiki/Micro-Manager) plugin that loads easily reconfigurable user interfaces to control your microscope. 

This repository contains examples of EMU plugins:

- [Base plugin](baseplugin): An empty plugin with comments within each method, explaining what needs to be implemented.

- [iBeamSmart](ibeamsmart): A simple UI controlling a single iBeamSmart laser (Toptica), including on/off, power percentage, fine percentages and external trigger.

- [Simple UI](simpleui): A UI similar to the example developed in the [EMU tutorial]( https://github.com/jdeschamps/EMU-tutorial ), albeit with optional scaling of laser power (mW) to laser percentage (%).

  

# How to build the examples

1. Follow the installation steps for [EMU](https://github.com/jdeschamps/EMU)

2. Using the console, go to the folder you wish to install **EMU-examples** in and type:

   ```bash
   $ git clone https://github.com/jdeschamps/EMU-examples.git
   ```

3. Finally, compile all the examples using Maven

   ```bash
   $ git cd EMU-examples
   $ mvn clean install
   ```

4. Then for each example, copy the .jar file generated in the **/target/** folder to **/Micro-Manager/EMU/**. 

5. Start Micro-Manager and select **Plugins->Interface->EMU**.