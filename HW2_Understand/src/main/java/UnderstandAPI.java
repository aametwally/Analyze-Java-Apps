import com.scitools.understand.*;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

/**
 * Created by hady on 10/6/16.
 */
public class UnderstandAPI {


    public Database readUnderstandDB(String projPath)
    {
//        DirectedGraph<String, DefaultEdge> g =
//                new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);

        Database db=null;
        try{
            //Open the Understand Database
            db = Understand.open(projPath);

        }catch (UnderstandException e){
            System.out.println("Failed opening Database:" + e.getMessage());
            System.exit(0);
        }

        return db;

    }


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
        for(Entity e : funcs){
            //Find all the parameters for the given method/function and build them into a string
            StringBuilder paramList = new StringBuilder();
            //Reference [] paramterRefs = e.refs("Define","Parameter",true);
            Reference[] paramterRefs = e.refs("Call","",true);

            for( Reference pRef : paramterRefs){
                Entity pEnt = pRef.ent();
                if(g.containsVertex(pEnt.longname(true)))
                    g.addEdge(e.longname(true), pEnt.longname(true));
            }
        }
//        System.out.println(g.toString());
        return g;
    }

    DirectedGraph<String, DefaultEdge> createDepGraph(Database db)
    {
        DirectedGraph<String, DefaultEdge> g =
                new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);

        // Get a list of all functions and methods
        Entity[] funcs = db.ents("class ~unknown ~unresolved, interface ~unknown ~unresolved");

        int i=0;
        for(Entity e : funcs) {
            g.addVertex(e.longname(true));
        }
        for(Entity e : funcs){
            //Find all the parameters for the given method/function and build them into a string
            StringBuilder paramList = new StringBuilder();
            //Reference [] paramterRefs = e.refs("Define","Parameter",true);
            Reference[] paramterRefs = e.refs("Couple","",true);

            for( Reference pRef : paramterRefs){
                Entity pEnt = pRef.ent();
                if(g.containsVertex(pEnt.longname(true)))
                    g.addEdge(e.longname(true), pEnt.longname(true));
            }
        }
//        System.out.println(g.toString());
        return g;
    }


    /// Check the extends and implements not the Couple
    SimpleDirectedGraph<String, DefaultEdge> createInhrGraph(Database db)
    {
        SimpleDirectedGraph<String, DefaultEdge> g =
                new SimpleDirectedGraph<String, DefaultEdge>(DefaultEdge.class);

        // Get a list of all functions and methods
        Entity[] funcs = db.ents("class ~unknown ~unresolved, interface ~unknown ~unresolved");

        int i=0;
        for(Entity e : funcs) {
            g.addVertex(e.longname(true));
        }
        for(Entity e : funcs){
            //Find all the parameters for the given method/function and build them into a string
            StringBuilder paramList = new StringBuilder();
            //Reference [] paramterRefs = e.refs("Define","Parameter",true);
//            Reference[] paramterRefs = e.refs("Couple","",true);
            Reference[] paramterRefs = e.refs("Implements,Extends","",true);


            for( Reference pRef : paramterRefs){
                Entity pEnt = pRef.ent();
                if(g.containsVertex(pEnt.longname(true)))
                    g.addEdge(e.longname(true), pEnt.longname(true));
            }
        }
//        System.out.println(g.toString());
        return g;
    }


}
