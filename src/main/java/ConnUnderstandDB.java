import com.scitools.understand.*;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

/**
 * Created by hady on 10/6/16.
 */
public class ConnUnderstandDB {


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
}
