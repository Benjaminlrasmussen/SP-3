package utillity;

import com.google.gson.Gson;
import entity.Person;
import java.util.List;

public class JSONConverter
{
    private static Gson gson = new Gson();

    private JSONConverter(){}
    
    public static Person getPersonFromJson(String js)
    {
        return gson.fromJson(js, Person.class);
    }

    public static String getJSONFromPerson(Person p)
    {
        return gson.toJson(p, Person.class);
    }

    public static String getJSONFromPersons(List<Person> persons)
    {
        return gson.toJsonTree(persons, List.class).toString();
    }

}
