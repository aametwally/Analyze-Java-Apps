import org.jgrapht.graph.*;
import java.util.*;


/**
 * Created by Ahmed Metwally.
 */
public class TransitiveClosureMethods {

    public SimpleDirectedGraph<String, DefaultEdge> getTransitiveClosure(SimpleDirectedGraph<String, DefaultEdge> inhG)
    {
//        System.out.println("Graph before Transitive closure = " + inhG);
        org.jgrapht.alg.TransitiveClosure.INSTANCE.closeSimpleDirectedGraph(inhG);
//        System.out.println("Graph after  Transitive closure = " + inhG);


        return inhG;

//        Set inhG1Vertices= inhG.vertexSet();
//        String vertexElements[] = {"okio.Buffer", "okio.Source", "okio.AsyncTimeout.Watchdog", "okio.AsyncTimeoutTest.RecordingAsyncTimeout", "okio.GzipSourceTest.ExhaustableSource"};
//        Set selectedVertices = new HashSet(Arrays.asList(vertexElements));
//
//
//        Iterator x= selectedVertices.iterator();
//        SimpleDirectedGraph<String, DefaultEdge> userGraph =
//                new SimpleDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
//
//
//        while(x.hasNext())
//        {
//            userGraph.addVertex(x.next().toString());
//        }
//
//        System.out.println("user graph vert "+ userGraph.toString());
//
//
//
//        for (int i=0; i<selectedVertices.size(); i++)
//        {
//            for (int j=0; j<selectedVertices.size(); j++)
//            {
////                if(i==j)
////                    continue;
//
//                if(inhG.containsEdge(selectedVertices.toArray()[i].toString(), selectedVertices.toArray()[j].toString()))
//                {
//                    userGraph.addEdge(selectedVertices.toArray()[i].toString(), selectedVertices.toArray()[j].toString());
//                }
//            }
//        }
//
//        System.out.println("Complete user Graph " + userGraph.toString());
//
//        return inhG;
    }
}
