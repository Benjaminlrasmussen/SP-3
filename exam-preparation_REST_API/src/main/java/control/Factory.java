package control;

import entities.Pet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Factory 
{
    private EntityManagerFactory emf;
    
    public Factory(EntityManagerFactory emf)
    {
        this.emf = emf;
    }
    
    public List<Pet> getAllPets()
    {
        EntityManager em = emf.createEntityManager();
        
        List<Pet> allPets = em.createQuery("select p from Pet p").getResultList();
        return allPets;
    }
}
