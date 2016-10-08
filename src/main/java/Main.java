/**
 * Created by hady on 9/27/16.
 */

import com.scitools.understand.Database;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.Set;

//import org.jgrapht.alg.isomorphism.VF2SubgraphIsomorphismInspector;
//import org.jgrapht.alg.isomorphism.IsomorphicGraphMapping;

// TODO
// Append Package name to the name of the class.method
// Add README FIle.
// Unit Test
// Integration TEst
// Test SBT
// Handle exceptions (open database, no files, no dependency, graph, no inheritince graph)


// This sentence is to test the sync with gitub

public class Main {

    public static void main(String[] args) {

        // get the db path of the two versions of the application
        String DBPath1=null;
        String DBPath2=null;
//        Scanner user_input = new Scanner( System.in );
//        System.out.print("Enter DB path of the first version: ");
//        DBName1 = user_input.next( );
//
//        System.out.print("Enter DB path of the second version: ");
//        DBName2 = user_input.next( );
//        user_input.close();
//
//        DBName1="/home/hady/Dropbox/UIC/Courses/CS-474-OOP/HW2/TestUnderstand/TestUnderstandOnHW1.udb";
//        DBName2="/home/hady/Dropbox/UIC/Courses/CS-474-OOP/HW2/TestUnderstand/TestUnderstandOnHW1.udb";

        DBPath1="/home/hady/IdeaProjects/HW2_Understand/datasets/okio_1.0.0.udb";
        DBPath2="/home/hady/IdeaProjects/HW2_Understand/datasets/okio_1.2.0.udb";
        System.out.println("First DB path is: " + DBPath1);
        System.out.println("Second DB path is: " + DBPath2);




        // Connect to Understand DB "UDB file"
        ConnUnderstandDB u= new ConnUnderstandDB();
        Database db1=u.readUnderstandDB(DBPath1);
        Database db2=u.readUnderstandDB(DBPath2);



        // Create Call Graphs
        CallGraph c= new CallGraph();
        DirectedGraph<String, DefaultEdge> callG1=c.createCallGraph(db1);
        DirectedGraph<String, DefaultEdge> callG2=c.createCallGraph(db2);
        System.out.println("Call Graph 1= " + callG1.toString());
        System.out.println("Call Graph 2= " + callG2.toString());



        // Create Dependency Graph
        DependencyGraph d = new DependencyGraph();
        DirectedGraph<String, DefaultEdge> depG1=d.createDepGraph(db1);
        DirectedGraph<String, DefaultEdge> depG2=d.createDepGraph(db2);
        System.out.println("Dependency Graph 1= " + depG1.toString());
        System.out.println("Dependency Graph 2= " + depG2.toString());


        // Construct the Isomorphism graph between the two Call graphs and two Dependency Graph.
        // Check for differences.

        System.out.println("Test Iso Graph Function");


        Isomorphism iso = new Isomorphism();
        Set<String> g1Notg2 = iso.getDifference(callG1, callG1, "Call");



        // Transitive Closure
//        SimpleDirectedGraph<String, DefaultEdge> Inh1= u.createInhrGraph(db1);
//        TransitiveClosure.INSTANCE.closeSimpleDirectedGraph(Inh1);
//        System.out.println("Dep Graph= " + Inh1);

    }
}