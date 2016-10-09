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
}
