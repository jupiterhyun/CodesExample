package com.mycompany.jerseyhk2injection.resources;

import com.mycompany.jerseyhk2injection.classes.Test;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("you")
public class Myresources{
    Test muhaha;
    
    @Inject
    public Myresources(Test muhaha) {
        this.muhaha = muhaha;
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return muhaha.run();
    }
}
