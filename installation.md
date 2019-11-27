# Installing EMU

1. Download and install [Micro-Manager 2-gamma](https://micro-manager.org/wiki/Download Micro-Manager_Latest Release).

2. Install git (for windows users: [git bash](https://gitforwindows.org/))

3. Download the [Java SE Development Kit 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).

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

   

[Back to the menu](README.md#guide)

