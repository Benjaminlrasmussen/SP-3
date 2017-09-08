/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("data")
public class DataResource {

    @Context
    private UriInfo context;

    public DataResource()
    {
    }

    @GET
    @Path("{number}/{startID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("number") int number, @PathParam("startID") int startID)
    {
        return Generator.generate(number, startID);
    }
}
