## Using Eclipse

### Setting up an EMU project with Eclipse

1. Right-click on the package explorer and create a new java project.
2. Name the project and click on next.
3. In the Source tab, right-click and add successively two new source folders: "src/main/java" and "src/main/resources". Remove the "src" source folder.
4. In the Librairies tab, add an external jar and point to the EMU jar in C:/Path/to/Micro-Manager/mmplugins. Click on finish.
5. Create your packages in the src/main/java folder. Then right click on the relevant package and select new class. Create successively three classes:
   1. A class with a UIPlugin interface.
   2. A class with ConfigurableMainFrame as a superclass
   3. A class with ConfigurablePanel as a superclass
6. Add the provider-configuration file to the src/main/resources/META-INF/services folder. Add the fully-qualified binary name of your implementation of UIPlugin.
7. Once you will have replaced all null returns from your classes method and have no errors, export the project as a jar and place it in the EMU folder. Check that EMU can load the plugin.

Then you are ready to start implementing your plugin.



###  Testing the plugin

After having tested the export to a jar and import in EMU of your base plugin, you can set-up a run configuration in Eclipse to directly start Micro-Manager with your changes included in your plugin without exporting the jar. This would allow you to use the Eclipse debugger and have access to the Java console.

1. Right-click on your project and select Properties. Then go to Java Build path and in the Librairies tab, add the ij.jar from your Micro-Manager folder (add External jar). Then add all jars from the C:/Path/to/Micro-Manager/plugins/Micro-manager folder. Apply and close.
2. Right-click on your project and select Run as then Run configurations.
3. In the configuration window, add a new configuration and name it. Then in Main class, select ImageJ.
4. In the Arguments tab, select other in working directory and point to the Micro-Manager directory. Apply and then close.
5. Click on the arrow next to the run button and make sure your run your newly created configuration.

Micro-Manager should start. You can use Micro-Manager demo device adapter to fake the presence of hardware or directly test on your microscope.



[Back to the programming guide](programmingguide.md)

[Back to the main menu](index.md)