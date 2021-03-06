# EMU examples

**Note**: The SimpleUI and iBeamSmart plugins are now distributed with EMU in Micro-Manager 2-gamma. Therefore they are no longer maintained here. For the latest source code, refer to the [EMU repository](https://github.com/jdeschamps/EMU). 



This repository contains examples of EMU plugins:

- [Base plugin]( https://github.com/jdeschamps/EMU-guide/tree/master/examples/baseplugin): An empty plugin with comments within each method, explaining what needs to be implemented. Can be used as basis for a [new project](#newproject).

- [BMC-example](https://github.com/jdeschamps/EMU-guide/tree/master/examples/bmc-example): The plugin built from the code snippets from the BMC publication.

- [iBeamSmart]( https://github.com/jdeschamps/EMU-guide/tree/master/examples/ibeamsmart): A simple UI controlling a single iBeamSmart laser (Toptica), including on/off, power percentage, fine percentages and external trigger.

  ![iBeamSmart](img/iBeamSmart.PNG)



- [Simple UI]( https://github.com/jdeschamps/EMU-guide/tree/master/examples/simpleui): A UI similar to the example developed in the [EMU tutorial]( https://github.com/jdeschamps/EMU-guide/tree/master/tutorial ), albeit with optional scaling of laser power (mW) to laser percentage (%).

  ![SimpleUI](img/simpleUI.PNG)
  
  > Note that all colors and names of the lasers and filters are configurable through the EMU interface and can therefore be changed easily.
  
  

# Installation

1. Follow the installation steps for [EMU](https://github.com/jdeschamps/EMU)

2. Using the console, go to the folder you wish to install **EMU-examples** in and type (omitting the $):

   ```bash
   $ git clone https://github.com/jdeschamps/EMU-guide.git
   ```

3. Finally, compile all the examples using Maven

   ```bash
   $ cd emu-guide/examples
   $ mvn clean install
   ```

4. Then for each example, copy the .jar file generated in the **example-name\target\\** folder to **C:\Path\To\Micro-Manager2gamma\EMU\\**. 
   e.g.: "emu-examples/ibeamsmart/target/ibeamsmart-1.0.jar".

5. Start Micro-Manager and select **Plugins->Interface->EMU**.



# Base plugin as a starting project <a name="newproject"></a>

1. Copy the "baseplugin/src" folder in your own project folder.
2. In Eclipse, create a new Java project and uncheck "use default location". Choose your project folder. Make sure Java 1.8 or 8 is selected. Click next.
3. Verify that the source folder is "src/main/java". In the Libraries tab, add an external JAR and navigate to the emu jar present in your Micro-Manager folder ("Micro-Manager/mmplugins/" if EMU was correctly installed). Then, click on finish.
4. You can then rename the package to match your institution, name and project name, as well as renaming the different classes.

> Note: When you change the name of the packages and of BasePlugin.java, you will need to change the path in the "src/main/resources/META-INF/services/de.embl.rieslab.emu.plugin.UIPlugin" file accordingly (the file name itself should NOT change) to point to your own implementation of UIPlugin.

