import org.jgrapht.DirectedGraph;
import org.jgrapht.GraphMapping;
import org.jgrapht.alg.isomorphism.VF2SubgraphIsomorphismInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Ahmed Metwally.
 */
public class IsomorphismTest {

    @Test
    public void createIsomorphism() throws Exception {
        System.out.println("Test createIsomorphism ...");
        DirectedGraph<String, DefaultEdge> g1 =
                new SimpleDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
        g1.addVertex("A");
        g1.addVertex("B");
        g1.addVertex("C");
        g1.addVertex("D");
        g1.addEdge("A", "B");
        g1.addEdge("B", "C");

        DirectedGraph<String, DefaultEdge> g2 =
                new SimpleDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
        g2.addVertex("A");
        g2.addVertex("B");
        g2.addVertex("C");
        g2.addEdge("A", "B");
        g2.addEdge("B", "C");


        Isomorphism iso= new Isomorphism();
        Iterator<GraphMapping<String, DefaultEdge>> graphMapIterator= iso.createIsomorphism(g1, g2);



        String node=null;
        while(graphMapIterator.hasNext())
        {
            node=graphMapIterator.next().toString();
            System.out.println("Map" + node);
        }

        assertEquals("[A=A B=B C=C D=~~]", node);
    }

    @Test
    public void getDifference() throws Exception {

        System.out.println("Test getDifference ...");
        DirectedGraph<String, DefaultEdge> g1 =
                new SimpleDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
        g1.addVertex("A");
        g1.addVertex("B");
        g1.addVertex("C");
        g1.addVertex("D");
        g1.addEdge("A", "B");
        g1.addEdge("B", "C");

        DirectedGraph<String, DefaultEdge> g2 =
                new SimpleDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
        g2.addVertex("A");
        g2.addVertex("B");
        g2.addVertex("C");
        g2.addEdge("A", "B");
        g2.addEdge("B", "C");


        Isomorphism iso= new Isomorphism();
        Set<String> x= iso.getDifference(g1, g2, "test");


        Set<String> trueSet= new HashSet<String>();
        trueSet.add("D");
        Set<String> g1Notg2 = new HashSet<String>();
        assertEquals(trueSet.toString(),x.toString());
    }
}