# README

This file contains instructions on running the program via the UI and runnning the tests trough the terminal.


# Running the tests

You can run the tests using ``mvn clean test`` or through the IDE.

# Running the application

### IDE
You can run the application through the IDE in the class Ui at the path src/main/java/dtu/ui/Ui.java. 

### Terminal

You can run the application through the terminal first executing ``mvn clean package`` and then running ``java -jar target/library_full_solution-SNAPSHOT\ 0.0.1.jar``

If you want to use the pom.xml file for you program, remember to change the start class in the pom.xml file to the class in your code which contains the main method.

# Issues
Tests run with Maven and Eclipse. The application needs to be started through the IDE (Eclipse or IntelliJ) from class dtu.library.gui.MainScreen (the Maven pom.xml file does not include the plugin to create a runnable jar file):
* Needs Java 8
* Eclipselink version 2.7.0
* Java Persistence version 2.1.0

Does not work with later Eclipselink and Java Persistence versions, and does also not work with Java 11 or later.

More information: Using JDK 17 will work, but requires the compiler setting to be set to 1.8 (the same as 8). If using a compiler setting of 11 or higher, the database tests will fail.

When updating to later persistence libraries, then `persistence.xml` is not found.

# WYSIWYG editor WindowBuilder for Swing GUI in Eclipse 
In Eclipse, the Swing GUI can be edited using the WindowBuilder plugin from the Eclipse marketplace. Once installed, you can select a Java class with a Swing UI and open using WindowBuilder. This will add a tab to the code window, which allows you to edit the GUI.
