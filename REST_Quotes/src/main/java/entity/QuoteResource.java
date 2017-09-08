/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Bade
 */
@Path("quote")
public class QuoteResource
{

    @Context
    private UriInfo context;

    private static Map<Integer, Quote> quotes = new HashMap()
    {
        {
            put(0, new Quote(0, "Friends are kisses blown to us by angels"));
            put(1, new Quote(1, "Do not take life too seriously. You will never get out of it alive"));
            put(2, new Quote(2, "Behind every great man, is a woman rolling her eyes"));
        }
    };

    private static Gson gson = new Gson();

    public QuoteResource()
    {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllQuotes()
    {
        Quote[] converted = new Quote[quotes.size()];
        int i = 0;
        for (Integer key : quotes.keySet())
        {
            converted[i] = quotes.get(key);
            i++;
        }
        return gson.toJsonTree(converted, Quote[].class).toString();
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getQuoteFromID(@PathParam("id") int id)
    {
        Quote found = quotes.get(id);

        if (found == null)
            return "{\"error\": \"Could not find quote\"}";

        return gson.toJson(found);
    }

    @GET
    @Path("random")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandomQuote()
    {
        Random rgen = new Random();
        Quote found = quotes.get(rgen.nextInt(quotes.size()));
        return gson.toJson(found);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content)
    {
        Quote quote = gson.fromJson(content, Quote.class);

        Quote found = quotes.get(quote.getID());

        if (found == null)
            return;

        quotes.put(quote.getID(), quote);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertQuote(String content)
    {
        Quote converted = gson.fromJson(content, Quote.class);

        if (converted != null)
        {
            converted.setID(quotes.size());
            quotes.put(quotes.size(), converted);
        }
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteQuote(String content)
    {
        Quote converted = gson.fromJson(content, Quote.class);
        Quote found = quotes.get(converted.getID());

        if (found != null)
            quotes.remove(converted.getID());
    }
}
