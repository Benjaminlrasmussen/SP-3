package entity;

public class Quote 
{
    private int id;
    private String quote;
    
    public Quote(int id, String text)
    {
        this.id = id;
        this.quote = text;
    }
    
    public String getQuote()
    {
        return quote;
    }
    
    public int getID()
    {
        return id;
    }
    
    public void setID(int id)
    {
        this.id = id;
    }
}
