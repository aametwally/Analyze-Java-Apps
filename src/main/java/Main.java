
/**
 * Created by Ahmed Metwally on 9/27/16.
 */

import com.scitools.understand.*;
import org.jgrapht.*;
import org.jgrapht.graph.*;
import java.util.*;


public class Main {

    public static void main(String[] args) {

        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));


        // get the path of the two udb file of the two application
        String DBPath1=null;
        String DBPath2=null;

        Scanner user_input = new Scanner( System.in );
        System.out.print("Enter DB path of the first version: ");
        DBPath1 = user_input.next( );

        System.out.print("Enter DB path of the second version: ");
        DBPath2 = user_input.next( );


        // TODO: the following line gives an error when run using sbt commandline
        // user_input.close();

        System.out.println("1st DB path is: " + DBPath1);
        System.out.println("2nd DB path is: " + DBPath2);



        // Connect to Understand DB "udb file"
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



        // Get the nodes that exists in graph1 but graph2.
        // This is done by checking if there is a subgraph of g1 that has g2
        // as an isomorphic subgraph
        Isomorphism iso = new Isomorphism();
        Set<String> call_g1Notg2 = iso.getDifference(callG1, callG2, "Call");
        Set<String> dep_g1Notg2 = iso.getDifference(depG1, depG2, "Dependency");



        // Create Inheritance Graph
        InheritanceGraph inh = new InheritanceGraph();
        SimpleDirectedGraph<String, DefaultEdge> inhG1= inh.createInhrGraph(db1);
        SimpleDirectedGraph<String, DefaultEdge> inhG2= inh.createInhrGraph(db2);
        System.out.println("Inheritance Graph 1= " + inhG1);
        System.out.println("Inheritance Graph 1= " + inhG1);



        // Transitive Closure of the inheritance graphs
        TransitiveClosureMethods t = new TransitiveClosureMethods();
        SimpleDirectedGraph<String, DefaultEdge> inhG1_TC= t.getTransitiveClosure(inhG1);
        SimpleDirectedGraph<String, DefaultEdge> inhG2_TC= t.getTransitiveClosure(inhG2);
        System.out.println("Transitive Closure of Inheritance Graph 1= " + inhG1_TC.toString());
        System.out.println("Transitive Closure of Inheritance Graph 2= " + inhG2_TC.toString());
    }
}