/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import control.IPersonFacade;
import control.PersonFacade;
import entity.Person;
import java.util.HashMap;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Bade
 */
public class TestPersonFacade
{
    
    private static IPersonFacade facade;
    
    public TestPersonFacade()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
        facade = new PersonFacade(Persistence.createEntityManagerFactory("jpaPU"));
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
        facade.deletePerson(3);
    }

    @Test
    public void testAddPerson()
    {
        Person persona = new Person(3, "Jafar", "Wizard", 55555555);
        facade.addPerson(persona);
        Person found = facade.getPerson(3);
        Assert.assertEquals(persona.getFirstName(), found.getFirstName());
    }
    
//    @Test
//    public void testDeletePerson()
//    {
//        
//    }
}
