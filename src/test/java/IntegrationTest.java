import com.scitools.understand.Database;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.junit.Test;

import java.util.Set;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Ahmed Metwally.
 */
public class IntegrationTest {

    @Test
    public void integrationTest() throws Exception {


        System.out.println("Test callGraph and Isomorphism classes ...");
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));


        String DBPath1="datasets/Halstead_sub_2.udb";
        String DBPath2="datasets/Halstead_sub_1.udb";
        System.out.println("1st DB path is: " + DBPath1);
        System.out.println("2nd DB path is: " + DBPath2);



        // connect to the database of the understand two applications
        ConnUnderstandDB u= new ConnUnderstandDB();
        Database db1=u.readUnderstandDB(DBPath1);
        Database db2=u.readUnderstandDB(DBPath2);


        // Create Call Graphs
        CallGraph c= new CallGraph();
        DirectedGraph<String, DefaultEdge> callG1=c.createCallGraph(db1);
        DirectedGraph<String, DefaultEdge> callG2=c.createCallGraph(db2);


        // Get the nodes that exists in graph1 but graph2.
        // This is done by checking if there is a subgraph of g1 that has g2
        // as an isomorphic subgraph
        Isomorphism iso = new Isomorphism();
        Set<String> call_g1Notg2 = iso.getDifference(callG1, callG2, "Call");


        //  check
        assertEquals("HalsteadMetrics.getVolume", call_g1Notg2.toArray()[0].toString());

    }

}
