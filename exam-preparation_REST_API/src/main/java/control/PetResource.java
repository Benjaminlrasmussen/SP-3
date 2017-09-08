/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import entities.Event;
import entities.Pet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Bade
 */
@Path("pet")
public class PetResource
{

    private Gson gson = new Gson();

    @Context
    private UriInfo context;

    public PetResource()
    {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPets()
    {
        List<Pet> allPets = RuntimeData.getFactory().getAllPets();
        JsonObject[] jsons = petArrayBuilder(allPets);
        return gson.toJsonTree(jsons).toString();
    }

    @GET
    @Path("living")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllLivingPets()
    {
        List<Pet> allPets = RuntimeData.getFactory().getAllPets();
        List<Pet> livingPets = new ArrayList();

        for (Pet pet : allPets)
        {
            if (pet.getDeath() == null)
                livingPets.add(pet);
        }

        JsonObject[] jsons = petArrayBuilder(livingPets);
        return gson.toJsonTree(jsons).toString();
    }

    // Does not work //
    @GET
    @Path("date/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPetsWithEventOnDay(@PathParam("date") String date)
    {
        String[] splitArr = date.split("-");
        Date converted = new Date(Integer.parseInt(splitArr[0]) - 1900, Integer.parseInt(splitArr[1]) + 1, Integer.parseInt(splitArr[2]));
        List<Pet> allPets = RuntimeData.getFactory().getAllPets();
        List<Pet> petsOnDay = new ArrayList();

        for (Pet pet : allPets)
        {
            for (Event evt : pet.getEventCollection())
            {
                System.out.println(evt.getDate().toString() + " === " + converted.toString());
                if (evt.getDate().equals(converted))
                    petsOnDay.add(pet);
            }
        }

        JsonObject[] jsons = petArrayBuilder(petsOnDay);
        return gson.toJsonTree(jsons).toString();
    }

    @GET
    @Path("count")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPetCount()
    {
        return gson.toJson(RuntimeData.getFactory().getAllPets().size(), Integer.class);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content)
    {
    }

    private JsonObject[] petArrayBuilder(List<Pet> allPets)
    {
        JsonObject[] jsons = new JsonObject[allPets.size()];
        for (int i = 0; i < allPets.size(); i++)
        {
            JsonObject pet = new JsonObject();
            pet.addProperty("id", allPets.get(i).getId());
            pet.addProperty("name", allPets.get(i).getName());
            pet.addProperty("birth", allPets.get(i).getBirth().toString());
            pet.addProperty("species", allPets.get(i).getSpecies());
            pet.addProperty("ownerFirstname", allPets.get(i).getOwnerId().getFirstName());
            pet.addProperty("ownerLastname", allPets.get(i).getOwnerId().getLastName());
            jsons[i] = pet;
        }
        return jsons;
    }
}
