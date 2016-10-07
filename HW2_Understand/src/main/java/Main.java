/**
 * Created by hady on 9/27/16.
 */
import com.scitools.understand.*;
import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.alg.isomorphism.VF2SubgraphIsomorphismInspector;
import org.jgrapht.alg.TransitiveClosure;
import java.util.Iterator;
import  java.util.HashSet;
import org.jgrapht.alg.isomorphism.*;
import org.jgrapht.alg.isomorphism.IsomorphicGraphMapping;
import org.jgrapht.graph.DefaultDirectedGraph;
import java.util.Scanner;
import java.util.Set;

// TODO
// Append Package name to the name of the class.method
// Give access to the Prof/ TA



public class Main {
    //public static String projPath = "/home/hady/Dropbox/UIC/Courses/CS-474-OOP/HW2/TestUnderstand/TestUnderstandOnHW1.udb";
    public static void main(String[] args) {

        // get the db path of the two versions of the application
        String DBName1=null;
        String DBName2=null;
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


        DBName1="/home/hady/IdeaProjects/HW2_Understand/datasets/okio_1.0.0.udb";
        DBName2="/home/hady/IdeaProjects/HW2_Understand/datasets/okio_1.2.0.udb";

        System.out.println("First DB path is: " + DBName1);
        System.out.println("Second DB path is: " + DBName2);

        UnderstandAPI u= new UnderstandAPI();
        Database db1=u.readUnderstandDB(DBName1);
        DirectedGraph<String, DefaultEdge> g1=u.createCallGraph(db1);
        DirectedGraph<String, DefaultEdge> depG1=u.createDepGraph(db1);



        Database db2=u.readUnderstandDB(DBName2);
        DirectedGraph<String, DefaultEdge> g2=u.createCallGraph(db2);
        DirectedGraph<String, DefaultEdge> depG2=u.createDepGraph(db2);


        System.out.println("Call Graph 1= " + g1.toString());
        System.out.println("Call Graph 2= " + g2.toString());
        System.out.println("Depn. Graph 1= " + depG1.toString());
        System.out.println("Depn. Graph 2= " + depG2.toString());


//        System.out.println("Call Graph 1=" + g2.vertexSet());
//        System.out.println("Depn. Graph 1=" + depG1.vertexSet());


        // Call Graph mapping
        VF2SubgraphIsomorphismInspector<String, DefaultEdge> callGraphMap= new VF2SubgraphIsomorphismInspector<String, DefaultEdge>(g1,g2);
        Iterator<GraphMapping<String, DefaultEdge>> callGraphMapIterator= callGraphMap.getMappings();
        IsomorphicGraphMapping<String, DefaultEdge> mapCallGraph = null;

        if(callGraphMapIterator.hasNext())
        {
            mapCallGraph=(IsomorphicGraphMapping<String, DefaultEdge>)callGraphMapIterator.next();
            System.out.println("Alignment of Call=  "+ mapCallGraph.toString());

            /// Check the differences
            Iterator iter = g1.vertexSet().iterator();

            Set<String> g1Notg2 = new HashSet<String>();

            while (iter.hasNext())
            {
                String node=iter.next().toString();

                System.out.println(node);
                if(mapCallGraph.hasVertexCorrespondence(node))
                {
 //                System.out.println(node);

                    System.out.println("yessss in map");
                }
                else
                {
                    g1Notg2.add(node);
//                System.out.println(node);
                    System.out.println("NOT in map");
                }
            }
            System.out.println("Methods found in V1 but not V2= " + g1Notg2.toString());
        }
        else
        {
            System.out.println("No mapping between Call Graphs");
        }



        System.out.println("Verteces of call Graph 1=" + g1.vertexSet());
        System.out.println("Verteces of call Graph 2s=" + g2.vertexSet());


        // Dependency Graph mapping
//        VF2SubgraphIsomorphismInspector<String, DefaultEdge> depnGraphMap= new VF2SubgraphIsomorphismInspector<String, DefaultEdge>(depG1, depG2);
//        Iterator<GraphMapping<String, DefaultEdge>> depnGraphMapIterator= depnGraphMap.getMappings();
//
//        IsomorphicGraphMapping<String, DefaultEdge> mapDepnGraph=null;
//        if(depnGraphMapIterator.hasNext())
//        {
//            mapDepnGraph=(IsomorphicGraphMapping<String, DefaultEdge>)depnGraphMapIterator.next();
//            System.out.println("Alignment of Depn=  "+ mapDepnGraph.toString());
//        }
//        else
//        {
//            System.out.println("No mapping between Dependency Graphs");
//        }







        SimpleDirectedGraph<String, DefaultEdge> inh1= u.createInhrGraph(db1);
        TransitiveClosure.INSTANCE.closeSimpleDirectedGraph(inh1);
        System.out.println("Dep1" + depG1);
        System.out.println("Inr2" + inh1);





    }
}