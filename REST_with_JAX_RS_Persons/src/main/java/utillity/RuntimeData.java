package utillity;

import control.PersonFacade;
import java.util.HashMap;
import javax.persistence.Persistence;

public class RuntimeData 
{
    private static PersonFacade facade;
    
    private RuntimeData(){}
    
    public static PersonFacade getPersonFacade()
    {
        if (facade == null)
            facade = new PersonFacade(Persistence.createEntityManagerFactory("jpaPU", new HashMap()));
        
        return facade;
    }
}
