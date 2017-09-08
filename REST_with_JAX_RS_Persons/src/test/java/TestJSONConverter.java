/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entity.Person;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import utillity.JSONConverter;

/**
 *
 * @author Bade
 */
public class TestJSONConverter
{

    public TestJSONConverter()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
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
    }

    @Test
    public void testGetPersonFromJson()
    {
        Person persona = JSONConverter.getPersonFromJson("{\"id\":1,\"firstName\":\"Heather\",\"lastName\":\"Alexander\",\"phone\":\"66666666\"}");
        String[] expectedData =
        {
            "1", "Heather", "Alexander", "66666666"
        };
        String[] resultData =
        {
            persona.getId().toString(), persona.getFirstName(), persona.getLastName(), persona.getPhone().toString()
        };
        Assert.assertArrayEquals(expectedData, resultData);
    }

    @Test
    public void testGetJsomFromPerson()
    {
        Person persona = new Person(0, "Heather", "Alexander", 66666666);
        String jsonFromPerson = JSONConverter.getJSONFromPerson(persona);
        String jsonExpected = "{\"id\":0,\"firstName\":\"Heather\",\"lastName\":\"Alexander\",\"phone\":66666666}";
        Assert.assertEquals(jsonExpected, jsonFromPerson);
    }

    @Test
    public void testJsonFromPersons()
    {
        List<Person> personList = new ArrayList();
        Person persona1 = new Person(0, "Heather", "Alexander", 66666666);
        Person persona2 = new Person(1, "Jones", "Alexander", 55555555);
        personList.add(persona1);
        personList.add(persona2);
        
        String expected = "[{\"id\":0,\"firstName\":\"Heather\",\"lastName\":\"Alexander\",\"phone\":66666666},{\"id\":1,\"firstName\":\"Jones\",\"lastName\":\"Alexander\",\"phone\":55555555}]";
        
        Assert.assertEquals(expected, JSONConverter.getJSONFromPersons(personList)); 
    }
}
