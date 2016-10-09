# Analyze two versions of a Java application using Understand® APIs.


This project is intended to get the difference between the call graph and dependency graph of the two versions.
Calculate the transitive closure of the inheritance graph of any of these versions for a selected  types. The project uses Understand® (from Scitools) APIs to build a database of the two versions of the applications. Then use JgraphT library to get the subgraph isomorphic. Analyzing the mapping between graphs can get the difference between them. More information can be found on:

* Call Graph: https://en.wikipedia.org/wiki/Call_graph
* Dependency Graph: https://en.wikipedia.org/wiki/Dependency_graph
* Understand® APIs: https://scitools.com/
* Transitive Closure: https://en.wikipedia.org/wiki/Transitive_closure
* JgraphT: http://jgrapht.org/




## Getting Started

### Prerequisites
* JDK 8 and JRE 8 to be installed on the machine.
* SBT to be installed on the machine.
* Understand® IDE installed on the machine.




### Installing, Testing, and Running

Clone the project to your local repository:
```
git clone https://ametwally@bitbucket.org/ametwally/ahmed_metwally_hw2.git
```


Copy the Understand jar file (in my case it is com.scitools.understand.plugin_1.1.3.jar) to the lib directory.  



Configure environmental variables for Understand. Example, for Linux users: open the ~/.bashrc and add the following. You need to replace [Path] by the absolute path of the directory where scitools package exists. 


```
export PATH=$PATH:/home/hady/Dropbox/UIC/Courses/CS-474-OOP/HW2/scitools/bin/linux64
export CLASSPATH=$CLASSPATH:[Path]/scitools/bin/linux64/Java/com.scitools.understand.plugin_1.1.3.jar

export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:[Path]/scitools/bin/linux64/
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:"/usr/java/jre1.8.0_91/lib/amd64/"
```



To make sure that you build the code from scratch, navigate to the project's main directory, remove any pre-built files:
```
sbt clean
```


Then, build the project using: 
```
sbt compile
```



There are a couple of test cases implemented in this program. These test cases ensure that every method works as expected. You can test them using:
```
sbt "test-only CallGraphTest IsomorphismTest TransitiveClosureMethodsTest"
```


Also, there is one integration test that test for callGraph and Isomorphism classes.
```
sbt "test-only IntegrationTest"
```



To execute the program from command line, use:
```
sbt run
```


Then the program should ask you to enter the path to a two udb database files of the two applications that you want the analyze the difference between them. I provide the following pre-built udb files in the "datasets" directory
```
* datasets/okio_1.0.0.udb
* datasets/okio_1.2.0.udb
* datasets/Halstead_sub_1.udb
* datasets/Halstead_sub_2.udb
```



### How to generate the database udb file using Understand®:
1. Open Understand® IDE and import the java project.
1. Selected the location where you should save the projects' udb file.
1. when the importing finished successfully, you should find the generated udb file in the selected location. 




### Implementation Notes:
1. Call Graph: The nodes of this graph represent the names of all methods that the application has. There is an edge "directed edge" between two nodes when the one method calls the other.
1. Dependency Graph: The nodes of this graph represents the names of the classes that the application has. Two nodes should have edges if there is any coupling between them.
1. Isomorphism: The current implementation check for the subgraph isomorphism, which is given two graphs A and B, the methods check if there is any subgraph of A that is isomorphic to B. If found, the method return the first alignment between the two graphs. Because it is really difficult to find a practical two applications that the whole new version is a subgraph of the old version, this method when tested with real applications it won't return any alignment. In the test cases, I show how it works using a small number of nodes.
1. Transitive closure: The implemented method takes one graph, and it returns the same graph but added to it the edges of the transitive closure. 
1. There is an implemented option that allows the user to subgraph any graph based on a set of nodes that the user provide. Of course, these provided nodes should be part of the main graph. If they are part of the graph, the program will return the subgraph along with the all transitive closures.