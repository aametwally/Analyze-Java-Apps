import org.jgrapht.*;
import org.jgrapht.graph.*;
import java.util.*;
import org.jgrapht.alg.isomorphism.*;



/**
 * Created by Ahmed Metwally.
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

        // Check if there is a subgraph in g1 that is isomorphism to g2
        if(graphMapIterator.hasNext())
        {
            isomorphicGraphMap_1=(IsomorphicGraphMapping<String, DefaultEdge>)graphMapIterator.next();
            System.out.println("Isomorphism of the " + graphType + " graph =  "+ isomorphicGraphMap_1.toString());

            /// Get the vertices that presents in g1 but not in g2.
            Iterator iter = g1.vertexSet().iterator();

            while (iter.hasNext())
            {
                String node=iter.next().toString();

                if(!isomorphicGraphMap_1.hasVertexCorrespondence(node))
                {
                    g1Notg2.add(node);
                }
            }

            if(!g1Notg2.isEmpty())
            {
                System.out.println("Nodes in g1 but not g2= " + g1Notg2.toString());
            }
            else
            {
                System.out.println("All nodes of the "+ graphType +" graph 1 are in the " + graphType + " graph 2");
            }
        }
        else
        {
            System.out.println("There is no sub isomorphic graphs between the two " + graphType + " graphs");
        }

        return g1Notg2;
    }
}
