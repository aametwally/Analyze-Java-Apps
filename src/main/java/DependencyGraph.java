import com.scitools.understand.*;
import org.jgrapht.*;
import org.jgrapht.graph.*;


/**
 * Created by Ahmed Metwally.
 */
public class DependencyGraph {

    // create a directed dependency graph from the understand udb file.
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

        // generate the dependency graphs for the given class/interface
        for(Entity e : funcs){
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
