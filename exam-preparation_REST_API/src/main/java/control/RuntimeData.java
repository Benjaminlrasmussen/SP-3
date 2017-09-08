package control;

import java.util.HashMap;
import javax.persistence.Persistence;

public class RuntimeData 
{
    private static Factory currentFactory;
    
    public static Factory getFactory()
    {
        if (currentFactory == null)
            currentFactory = new Factory(Persistence.createEntityManagerFactory("jpaPU", new HashMap()));
        
        return currentFactory;
    }
}
