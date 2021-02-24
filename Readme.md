# A maven archetype to generate PI4J V2 project skeletons
This Maven archetype can generate PI4J-V2 project templates with remote build, run and debug capability.
A template project generated with this archetype can:

- Handle a connection to many RPI targets, configured in the pom.xml file
- Compile your code, packing the compiled code in a jar, uploading it and all the other required jars on the selected remote target
- Run the program on the remote target
- Open a remote debugging session

The archetype is IDE agnostic (I tested it using Netbeans) and depends only on maven and ant. 
## How to install the archetype to your local .m2 repository
- Unzip the archtype file in a _folder_
- `cd folder/raspimaven-archetype`
- `mvn install`

## How to generate a new PI4J V2 skeleton project
- `mkdir my-project`
- `cd my-project`
- `mvn archetype:generate -DarchetypeCatalog=local`
- answer to the questions asked

## How to generate from a remote repository
If you deploy the archetype in a remote repository, use this commend to generate a new skeleton project:

`mvn archetype:generate -DarchetypeGroupId=it.lb.rasp -DarchetypeArtifactId=raspimaven-archetype -DarchetypeVersion=1.0-SNAPSHOT`

