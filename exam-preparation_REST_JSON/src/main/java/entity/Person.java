package entity;

public class Person 
{
    private String fname;
    private String lname;
    private int id;
    private int age;

    public Person(String fname, String lname, int id, int age)
    {
        this.fname = fname;
        this.lname = lname;
        this.id = id;
        this.age = age;
    }

    public String getFname()
    {
        return fname;
    }

    public String getLname()
    {
        return lname;
    }

    public int getId()
    {
        return id;
    }

    public int getAge()
    {
        return age;
    }
}