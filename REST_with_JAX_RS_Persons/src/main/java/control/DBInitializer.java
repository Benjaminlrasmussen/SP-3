package control;

import java.util.HashMap;
import javax.persistence.Persistence;

public class DBInitializer 
{
    public static void main(String[] args)
    {
        HashMap<String, String> properties = new HashMap();
        properties.put("javax.persistence.sql-load-script-source", "sql_scripts/clear_db.sql");
        Persistence.generateSchema("jpaPU", properties);
        
        properties.remove("javax.persistence.sql-load-script-source", "sql_scripts/clear_db.sql");
        properties.put("javax.persistence.sql-load-script-source", "sql_scripts/populate.sql");
        Persistence.generateSchema("jpaPU", properties);
    }
}
