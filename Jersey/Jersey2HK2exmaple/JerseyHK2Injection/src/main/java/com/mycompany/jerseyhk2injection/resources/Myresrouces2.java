package com.mycompany.jerseyhk2injection.resources;

import com.mycompany.jerseyhk2injection.classes.Test2;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("my")
public class Myresrouces2 {
    Test2 mu;
    
    @Inject
    public Myresrouces2(Test2 muhaha) {
        this.mu = muhaha;
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return mu.run();
    }
}
