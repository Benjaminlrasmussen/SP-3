package control;

import com.google.gson.Gson;
import entity.Person;
import java.util.Random;

public class Generator 
{
    private static Gson gson = new Gson();
    private static Random rgen = new Random();
    private static String[] firstnames = {"Carol", "Harold", "Jones", "Joseph", "Karsten", "Anna", "Liliana", "Christian", "Karim"};
    private static String[] lastnames = {"El-Sayed", "Rasmussen", "Hansen", "Smith", "Jensen", "Mauritzen", "Nielsen", "Beck"};
    
    private Generator()
    {
    }
    
    public static String generate(int number, int startID)
    {
        Person[] generated = new Person[number];
        for (int i = 0; i < number; i++)
        {
            generated[i] = new Person(firstnames[rgen.nextInt(firstnames.length - 1)], 
                    lastnames[rgen.nextInt(lastnames.length - 1)], startID + i, rgen.nextInt(53) + 17);
        }
        return gson.toJsonTree(generated, Person[].class).toString();
    }
}