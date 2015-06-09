package com.mycompany.jerseyexample.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Root resource (exposed at "myresource" path)
 */
//@Path("country/{id}")
@Path("my")
public class MyResource {
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "dsdzfd";
    }
    
    
    @GET
    @Path("you")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIts() {
        return "called by app/my/you";
        //return String.valueOf(id);
    }
    
    @GET
    @Path("{year}/{month}/{day}")
    public Response getUserHistory(
	@PathParam("year") int year,
	@PathParam("month") int month, 
	@PathParam("day") int day) {
 
        String date = year + "/" + month + "/" + day;
 
        return Response.status(200)
            .entity("getUserHistory is called, year/month/day : " + date)
            .build();

    }
    
}
