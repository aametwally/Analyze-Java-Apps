import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.TreeSet;

import static org.junit.Assert.*;

/**
 * Created by Ahmed Metwally.
 */
public class TransitiveClosureMethodsTest {

    @Test
    public void getTransitiveClosure() throws Exception {

        System.out.println("Test getTransitiveClosure ...");

        SimpleDirectedGraph<String, DefaultEdge> g =
                new SimpleDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
        g.addVertex("A");
        g.addVertex("B");
        g.addVertex("C");
        g.addVertex("D");
        g.addEdge("A", "B");
        g.addEdge("B", "C");
        g.addEdge("C", "D");


        SimpleDirectedGraph<String, DefaultEdge> g_expected =
                new SimpleDirectedGraph<String, DefaultEdge>(DefaultEdge.class);

        g_expected.addVertex("A");
        g_expected.addVertex("B");
        g_expected.addVertex("C");
        g_expected.addVertex("D");
        g_expected.addEdge("A", "B");
        g_expected.addEdge("B", "C");
        g_expected.addEdge("C", "D");
        g_expected.addEdge("A", "C");
        g_expected.addEdge("A", "D");
        g_expected.addEdge("B", "D");

        TransitiveClosureMethods t= new TransitiveClosureMethods();
        t.getTransitiveClosure(g);

        TreeSet myTreeSet1 = new TreeSet();
        TreeSet myTreeSet2 = new TreeSet();

        Iterator x = g.edgeSet().iterator();
        Iterator y = g_expected.edgeSet().iterator();
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

        assertEquals(g_expected.vertexSet(), g.vertexSet());
        assertEquals(myTreeSet1, myTreeSet2);
    }
}