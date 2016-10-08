import com.scitools.understand.Database;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

/**
 * Created by Ahmed Metwally.
 */
public class CallGraphTest {

    @Test
    public void createCallGraph() throws Exception {

        System.out.println("Test createCallGraph ...");
        ConnUnderstandDB u= new ConnUnderstandDB();
        Database db1=u.readUnderstandDB("/home/hady/IdeaProjects/HW2_Understand/datasets/Halstead_sub_1.udb");
        CallGraph c= new CallGraph();
        DirectedGraph<String, DefaultEdge> calculatedG = c.createCallGraph(db1);
        DirectedGraph<String, DefaultEdge> expectedG =
                new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);

        expectedG.addVertex("HalsteadMetrics.getVocabulary");
        expectedG.addVertex("HalsteadMetrics.HalsteadMetrics");
        expectedG.addVertex("Main.main");
        expectedG.addVertex("Main.retrieveFiles");
        expectedG.addEdge("Main.main", "Main.retrieveFiles");
        expectedG.addEdge("Main.main", "HalsteadMetrics.HalsteadMetrics");
        expectedG.addEdge("Main.main", "HalsteadMetrics.getVocabulary");
        expectedG.addEdge("Main.retrieveFiles", "Main.retrieveFiles");


        TreeSet myTreeSet1 = new TreeSet();
        TreeSet myTreeSet2 = new TreeSet();

        Iterator x = expectedG.edgeSet().iterator();
        Iterator y = calculatedG.edgeSet().iterator();
        String node=null;

        while(x.hasNext())
        {
            node=x.next().toString();
            myTreeSet1.add(node);
        }

        while(y.hasNext())
        {
            node=y.next().toString();
            myTreeSet2.add(node);
        }

        assertEquals(expectedG.vertexSet(), calculatedG.vertexSet());
        assertEquals(myTreeSet1, myTreeSet2);
    }
}