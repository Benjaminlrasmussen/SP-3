package control;

import entity.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class PersonFacade implements IPersonFacade
{

    private final EntityManagerFactory emf;

    public PersonFacade(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    @Override
    public Person addPerson(Person p)
    {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        em.persist(p);
        em.getTransaction().commit();

        em.close();

        return p;
    }

    @Override
    public Person deletePerson(int id)
    {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Person found = em.find(Person.class, id);
        System.out.println("Id: " + found.getId() + " Firstname: " + found.getFirstName() + " Lastname: " + found.getLastName() + " phone: " + found.getPhone());
        em.remove(found);

        em.getTransaction().commit();
        em.close();

        return found;
    }

    @Override
    public Person getPerson(int id)
    {
        EntityManager em = emf.createEntityManager();
        Person found = em.find(Person.class, id);
        em.close();

        return found;
    }

    @Override
    public List<Person> getPersons()
    {
        EntityManager em = emf.createEntityManager();
        List<Person> persons = em.createQuery("select p from Person p").getResultList();
        em.close();

        return persons;
    }

    @Override
    public Person editPerson(Person p)
    {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Person found = em.find(Person.class, p.getId());

        if (found != null)
        {
            found.setFirstName(p.getFirstName());
            found.setLastName(p.getLastName());
            found.setPhone(p.getPhone());
        }

        em.getTransaction().commit();
        em.close();

        return p;
    }
}
