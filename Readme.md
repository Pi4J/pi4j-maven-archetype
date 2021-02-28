# A maven archetype to generate PI4J V2 project skeletons
This Maven archetype can generate PI4J-V2 project templates with remote build, run and debug capability.
A template project generated with this archetype can:

- Handle a connection to many RPI targets, configured in the pom.xml file
- Compile your code, packing the compiled code in a jar, uploading it and all the other required jars on the selected remote target
- Run the program on the remote target
- Open a remote debugging session

The archetype is IDE agnostic (I tested it using Netbeans) and depends only on maven and ant. You should be able to use it with your preferred IDE 
program (Netbeans, Eclipse, Idea or whatever else)
# Getting started
Follow these steps to set up the archetype and use it:
## Install Maven
You must have the Maven tool installed on your computer. If not, you can download it from https://maven.apache.org/
## Install the archetype to your local .m2 repository
- Unzip the archetype file in a _folder_
- `cd folder/raspimaven-archetype`
- `mvn install`

## How to generate a new PI4J V2 skeleton project
Now suppose you want to begin a new PI4J-V2 project _my-project_:
- `mkdir my-project`
- `cd my-project`
- `mvn archetype:generate -DarchetypeCatalog=local`
- answer to the questions asked (see below for details)
### Configuring your new project
Before starting the new project generation, the archetype asks you some configuration data:
1. _Choose archetype:_ select the _raspimaven-archetype_ from the list shown
1. _Define value for property 'groupId':_ choose the Maven groupId for your project. (If don't know what is a groupId, don't worry, just type _com.example_)
1. _Define value for property 'artifactId':_ choose a name for the program executable your project will produce
1. _Define value for property 'version':  1.0-SNAPSHOT:_ type Enter to accept the default value shown, or type the initial version, something like _1.0.0_
1. _Define value for property 'package':  com.example:_ type Enter to accept the default value shown

The archetype now presents to you a summary of the configuration parameters you have just typed in, plus the values proposed for the _main-class_ and _package_ parameters.
If the list is ok for you, reply _Y_ to accept, otherwise reply _N_ to change one or more values (you will have to re-type all parameter values ...)

After the list confirmation, the archetype generates a new maven project for you. You should be able to open the new project with your preferred java IDE. The IDE should be able
to recognize the project as a valid Maven project.

### How to generate from a remote repository (optional)
If you deploy the archetype in a remote repository, use this command to generate a new skeleton project:

`mvn archetype:generate -DarchetypeGroupId=it.lb.rasp -DarchetypeArtifactId=raspimaven-archetype -DarchetypeVersion=1.0-SNAPSHOT`

# The generated skeleton project
You can now freely modify the new project, adding your code. The following is a guide on using the new project features.
## How it works ##
This project skeleton allows you to write the source code of your RPI programs using a destop computer (Linux or Windows) and your preferred 
IDE as development station, uploading the executable code to a remote RPI board, running and also debugging your code remotely.
### What do you need
- The development station must be connected to your local network
- A target RPI board must also be connected to the same network
- You must configure the target RPI for a _Headless connection_ with the development computer, i.e. the _ssh_ server must be enabled in your
RPI board and you must be able to connect with a ssh terminal.

#### How to configure
The configuration data about the target RPI remote board is stored in the file _platform/raspberry.properties_. Actually the folder _platform_
can contain as many *target_name.properties* as the RPI boards you may want to connect to. The maven property _target.platform.name_, defined
in the _pom.xml_ file, points to the right file in the _platform_ folder. The default configuration file is _platform/raspberry.properties_ 
and the _target.platform.name = raspberry_ 
#### Configure the _platform/raspberry.properties_ file
Each xxxx.properties file in the _platform_ must be edited to describe the configuration data of a specific RPI board, as follow:
- the target RPI IP address, the ssh connection port, the username and password
- instead of using a password, if your RPI is configured to accept a ssh key, you can specify your ssh key.
- the location of the java JRE / JDK on remote target, the folder where the program executables must be uploaded
- ... other details described in the raspberry.properties 

## How to build, run and debug your project
The project declares in the _pom.xml_ the following maven/ant goals that you can execute with the command shown :
- `mvn clean` : delete all compiled files from local and remote project
- `mvn install` : builds the project, uploads the required jars to the remote target RPI board
- `mvn antrun:run@exec` :  runs the program on the remote target
- `mvn antrun:run@debug` : runs a remote debugging session on the target RPI.

All the modern IDE programs have a mechanism to configure new GUI commands linked to a specific maven goal. See below for an example of such a
GUI configuration of the Netbeans IDE.

### Note on running a debug session
To start a debugging session: 
- run the command `mvn antrun:run@debug`, the program on the remote target starts with JVM in debug mode, waiting for a debugger connection on the port configured for the target
- in your IDE start the debugger setting the host IP = _target IP_ and port = _port configured in raspberry.properties_
You should be able to set remote breakpoints, execute step by step, examine variables on your remote program.

## Some final notes ##
### PI4J version
The _pom.xml_ file adds the pi4j v.2.0 jar dependencies to the project classpath. You may want to update to the last version

### File "_nbactions-template.xml_"
If Netbeans is your preferred IDE you can use the file nbactions-template.xml to add to the Netbeans GUI the two actions "Remote run" and "Remote debug".
Follow these simple steps:
1. Right click the project in the NBs _Projects_ window and select _Properties_
1. Select _Actions_ --> _Build project_
1. Click on _Add_ button and select _Skip Tests_
1. Click _OK_ - (this forces Netbeans to generate the file _nbactions.xml_ in the project folder)
1. Open the file _nbactions.xml_ and _nbactions-template.xml_
1. Select the two _Action_ XML elements with name _CUSTOM-Remote run_ and _CUSTOM-Remote debug_ and copy them to the _nbactions.xml_. - Pay attention to preserve the correct XML syntax of file _nbactions.xml_, and save it.

Now right click the project in the NBs _Projects_ window and select _Run Maven_: you should see the two new goals _Remote run_ and _Remote debug_

When done, you can safely delete _nbactions-template.xml_
