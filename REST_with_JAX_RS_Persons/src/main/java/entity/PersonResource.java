package entity;

import utillity.RuntimeData;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import utillity.JSONConverter;

@Path("person")
public class PersonResource
{

    @Context
    private UriInfo context;

    public PersonResource()
    {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson()
    {
        return JSONConverter.getJSONFromPersons(RuntimeData.getPersonFacade().getPersons());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content)
    {
        RuntimeData.getPersonFacade().addPerson(JSONConverter.getPersonFromJson(content));
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void postJson(String content)
    {
        RuntimeData.getPersonFacade().editPerson(JSONConverter.getPersonFromJson(content));
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteJson(String content)
    {
        RuntimeData.getPersonFacade().deletePerson(JSONConverter.getPersonFromJson(content).getId());
    }
}
