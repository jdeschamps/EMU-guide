# Installing EMU

EMU is now distributed with Micro-Manager. If you wish to install EMU from the source code, then follow the next steps:

1. Download and install [Micro-Manager 2-gamma](https://micro-manager.org/wiki/Download Micro-Manager_Latest Release).

2. Install git (for windows users: [git bash](https://gitforwindows.org/))

3. Download the [Java SE Development Kit 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html). Set the environment variable [JAVA_HOME to the JDK 1.8 folder path](https://confluence.atlassian.com/doc/setting-the-java_home-variable-in-windows-8895.html).

4. Download and install [Maven](https://maven.apache.org/install.html).

5. Using the git console, go to the folder you wish to install EMU in and type (omitting the $):

   ```bash
   $ git clone https://github.com/jdeschamps/EMU.git
   ```

6. Navigate to the EMU folder and compile it using the path to Micro-manager:

   ```bash
   $ cd emu
   $ ./compileAndDeploy.sh "C:\Path\to\MicroManager2gamma"
   ```

   > Note: the script requires bash, which is shipped with the git console on Windows.

7. Install an EMU plugin by placing the plugin compiled .jar file in "C:\Path\to\MicroManager2gamma\EMU\".



#### EMU plugins

- [EMU plugin examples]( https://github.com/jdeschamps/EMU-guide/tree/master/examples )
- [Build your own plugin with Eclipse Windows Builder](  https://jdeschamps.github.io/EMU-guide/tutorial/ )
- [htSMLM](https://github.com/jdeschamps/htSMLM), the Ries group EMU plugin.



[Back to the main menu](index.md)

