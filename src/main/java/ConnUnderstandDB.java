import com.scitools.understand.*;


/**
 * Created by Ahmed Metwally.
 */
public class ConnUnderstandDB {

    //Open the Understand Database
    public Database readUnderstandDB(String projPath)
    {

        Database db=null;
        try{
            db = Understand.open(projPath);

        }catch (UnderstandException e){
            System.out.println("Failed opening Database:"+ projPath+ "\t Error: " + e.getMessage());
            System.exit(0);
        }

        return db;
    }
}
