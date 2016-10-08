import com.scitools.understand.*;


/**
 * Created by Ahmed Metwally.
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
            System.out.println("Failed opening Database:"+ projPath+ "\t Error: " + e.getMessage());
            System.exit(0);
        }

        return db;
    }
}
