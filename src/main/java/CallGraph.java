import com.scitools.understand.*;
import org.jgrapht.*;
import org.jgrapht.graph.*;

/**
 * Created by Ahmed Metwally.
 */


public class CallGraph {

    // create a directed call graph from the understand udb file.
    DirectedGraph<String, DefaultEdge> createCallGraph(Database db)
    {
        DirectedGraph<String, DefaultEdge> g =
                new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);

        // Get a list of all functions and methods
        Entity[] funcs = db.ents("function ~unknown ~unresolved, method ~unknown ~unresolved");

        int i=0;
        for(Entity e : funcs) {
            g.addVertex(e.longname(true));
        }


        // Find all calls for the given method/function and build them into a string
        for(Entity e : funcs){
            StringBuilder paramList = new StringBuilder();
            Reference[] paramterRefs = e.refs("Call","",true);

            for( Reference pRef : paramterRefs){
                Entity pEnt = pRef.ent();
                if(g.containsVertex(pEnt.longname(true)))
                    g.addEdge(e.longname(true), pEnt.longname(true));
            }
        }
        return g;
    }
}
