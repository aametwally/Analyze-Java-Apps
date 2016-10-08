import com.scitools.understand.*;
import org.jgrapht.*;
import org.jgrapht.graph.*;


/**
 * Created by Ahmed Metwally.
 */
public class InheritanceGraph {


    SimpleDirectedGraph<String, DefaultEdge> createInhrGraph(Database db)
    {
        SimpleDirectedGraph<String, DefaultEdge> g =
                new SimpleDirectedGraph<String, DefaultEdge>(DefaultEdge.class);

        // Get a list of all classes/interfaces
        Entity[] funcs = db.ents("class ~unknown ~unresolved, interface ~unknown ~unresolved");

        int i=0;
        for(Entity e : funcs) {
            g.addVertex(e.longname(true));
        }

        for(Entity e : funcs){
            // get all extended/implemented classes/interfaces
            Reference[] paramterRefs = e.refs("implement,extend","",true);

            for( Reference pRef : paramterRefs){
                Entity pEnt = pRef.ent();
                if(g.containsVertex(pEnt.longname(true)))
                    g.addEdge(e.longname(true), pEnt.longname(true));
            }
        }
        return g;
    }
}
