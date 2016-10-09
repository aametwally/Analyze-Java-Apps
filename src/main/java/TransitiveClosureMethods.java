import org.jgrapht.graph.*;
import java.util.*;


/**
 * Created by Ahmed Metwally.
 */
public class TransitiveClosureMethods {

    // Add the transitive closure edges to directed graph
    public SimpleDirectedGraph<String, DefaultEdge> getTransitiveClosure(SimpleDirectedGraph<String, DefaultEdge> inhG)
    {
        System.out.println("Graph before Transitive closure = " + inhG);
        org.jgrapht.alg.TransitiveClosure.INSTANCE.closeSimpleDirectedGraph(inhG);
        System.out.println("Graph after  Transitive closure = " + inhG);

        return inhG;
    }



    // get subgraph of graph based on the nodes that the user provides.
    public SimpleDirectedGraph<String, DefaultEdge> getSubgraph (SimpleDirectedGraph<String, DefaultEdge> mainGraph, Set<String> vertexElements)
    {
        Iterator x= vertexElements.iterator();
        String temp=null;
        System.out.println("Selected vertices before filtering = "+ vertexElements.toString());
        while(x.hasNext())
        {
            temp=x.next().toString();
            if(!mainGraph.containsVertex(temp))
                x.remove();
        }
        System.out.println("Selected vertices after filtering = "+ vertexElements.toString());


        SimpleDirectedGraph<String, DefaultEdge> userGraph =
                new SimpleDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
        Set selectedVertices = new HashSet(Arrays.asList(vertexElements));


        // create vertices of the subgraph
        while(x.hasNext())
        {
            userGraph.addVertex(x.next().toString());
        }


        // create edges of teh subgraph
        for (int i=0; i<selectedVertices.size(); i++)
        {
            for (int j=0; j<selectedVertices.size(); j++)
            {
                if(mainGraph.containsEdge(selectedVertices.toArray()[i].toString(), selectedVertices.toArray()[j].toString()))
                {
                    userGraph.addEdge(selectedVertices.toArray()[i].toString(), selectedVertices.toArray()[j].toString());
                }
            }
        }

        if(!userGraph.vertexSet().isEmpty())
        {
            System.out.println("The complete graph based on the user choice " + userGraph.toString());
        }
        return userGraph;
    }
}
