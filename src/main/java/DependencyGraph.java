import com.scitools.understand.*;
import org.jgrapht.*;
import org.jgrapht.graph.*;


/**
 * Created by Ahmed Metwally.
 */
public class DependencyGraph {
    DirectedGraph<String, DefaultEdge> createDepGraph(Database db)
    {
        DirectedGraph<String, DefaultEdge> g =
                new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);

        // Get a list of all classes and interfaces
        Entity[] funcs = db.ents("class ~unknown ~unresolved, interface ~unknown ~unresolved");

        int i=0;
        for(Entity e : funcs) {
            g.addVertex(e.longname(true));
        }

        for(Entity e : funcs){
            // generate the dependency graphs for the given class/interface
            Reference[] paramterRefs = e.refs("Couple","",true);

            for( Reference pRef : paramterRefs){
                Entity pEnt = pRef.ent();
                if(g.containsVertex(pEnt.longname(true)))
                    g.addEdge(e.longname(true), pEnt.longname(true));
            }
        }
        return g;
    }
}
