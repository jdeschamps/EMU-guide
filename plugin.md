# UIPlugin  

The UIPlugin interface is the class detected by EMU. The plugin system uses Java ServiceLoader. 

First implement the interface, returning your ConfigurableMainFrame in the getMainFrame() method:

```java
public class GuideUIPlugin implements UIPlugin{

	@Override
	public String getName() {
		return "Guide plugin";
	}

	@Override
	public ConfigurableMainFrame getMainFrame(SystemController controller,
                                              TreeMap<String, String> pluginSettings) {
        return new GuideConfigurableMainFrame("Guide plugin", controller,
                                              pluginSettings);
	}
}
```



## Provider-configuration file 

In order for the ServiceLoader to discover your plugin, you need to make sure the jar files contains the provider-configuration file. This file should be named "de.embl.rieslab.emu.plugin.UIPlugin" and contain the UIPlugin implementation's fully-qualified [binary name](https://docs.oracle.com/javase/7/docs/api/java/lang/ClassLoader.html#name), for instance:

```
de.embl.rieslab.emuguide.GuideUIPlugin
```

In order for the file to appear in the jar, you must respect the following project organization:

```
src/main/
      |
      ---> java/de/embl/rieslab/emuguide/
                                   |
                                   ---> GuideUIPlugin.java
      ---> resources/META-INF/services/
                                   |
                                   ---> de.embl.rieslab.emu.plugin.UIPlugin
              
```

In your project, the "de.embl.rieslab.emuguide.GuideUIPlugin.java" must be replaced by your own package structure and plugin name, in both the projects and the provider-configuration file. 

Make sure that resources is set as a source folder and explore the exported jar to make sure that the META-INF folder contains the /services/de.embl.rieslab.emu.plugin.UIPlugin file.

The most common errors causing the plugin to be ignored by the ServiceLoader are:

- Wrong folder structure
- Mistake in the provider-configuration file name ("de.embl.rieslab.emu.plugin.UIPlugin")
- Mistake in the content of the provider-configuration file (in the example: de.embl.rieslab.emuguide.GuideUIPlugin)



## Export as .jar

Finally, your project must be exported as a .jar. Make sure the configuration file is present in the jar under the correct path. Then place the archive in the EMU folder of your Micro-manager installation folder.



## Example

- [GuideUIPlugin.java: an implementation of a UIPlugin]( https://github.com/jdeschamps/EMU-guide/tree/master/guide/src/main/java/de/embl/rieslab/emuguide )
- [The corresponding provider-configuration file]( https://github.com/jdeschamps/EMU-guide/tree/master/guide/src/main/resources/META-INF/services )



[Back to the programming guide](programmingguide.md)

[Back to the menu](index.md)