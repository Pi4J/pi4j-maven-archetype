# A maven archtype to generate PI4J V2 project skeletons
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

