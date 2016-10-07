import org.jgrapht.DirectedGraph;
import org.jgrapht.GraphMapping;
import org.jgrapht.alg.isomorphism.IsomorphicGraphMapping;
import org.jgrapht.alg.isomorphism.VF2SubgraphIsomorphismInspector;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by hady on 10/7/16.
 */
public class Isomorphism {


    // Create the Isomorphism inspector between two graphs
    public Iterator<GraphMapping<String, DefaultEdge>> createIsomorphism(DirectedGraph<String, DefaultEdge> g1, DirectedGraph<String, DefaultEdge> g2)
    {
        VF2SubgraphIsomorphismInspector<String, DefaultEdge> isomorphicGraphMap= new VF2SubgraphIsomorphismInspector<String, DefaultEdge>(g1, g2);
        Iterator<GraphMapping<String, DefaultEdge>> graphMapIterator= isomorphicGraphMap.getMappings();
        return graphMapIterator;
    }


    // Get the difference nodes between two graphs
    // Graph type is Call or Dependency, etc ... It is just used in the printing statements
    public Set<String> getDifference(DirectedGraph<String, DefaultEdge> g1, DirectedGraph<String, DefaultEdge> g2,String graphType)
    {
        Iterator<GraphMapping<String, DefaultEdge>> graphMapIterator = createIsomorphism(g1,g2);
        IsomorphicGraphMapping<String, DefaultEdge> isomorphicGraphMap_1 = null;
        Set<String> g1Notg2 = new HashSet<String>();

        // Check if there is any Isomorphism graphs or not.
        if(graphMapIterator.hasNext())
        {
            isomorphicGraphMap_1=(IsomorphicGraphMapping<String, DefaultEdge>)graphMapIterator.next();
            System.out.println("Isomorphism of the " + graphType + " graph =  "+ isomorphicGraphMap_1.toString());

            /// Check the differences between the two graphs
            Iterator iter = g1.vertexSet().iterator();


            while (iter.hasNext())
            {
                String node=iter.next().toString();

                if(!isomorphicGraphMap_1.hasVertexCorrespondence(node))
                {
                    g1Notg2.add(node);
                    System.out.println(node + " is in graphs 1 but not 2");
                }
            }

            if(!g1Notg2.isEmpty())
            {
                System.out.println("Nodes found in V1 but not V2= " + g1Notg2.toString());
            }
            else
            {
                System.out.println("All nodes of the "+ graphType +" graph of the v1 found in v2");
            }
        }
        else
        {
            System.out.println("There is no sub isomorphic graphs between the two " + graphType + " graphs");
        }

        return g1Notg2;
    }
}
