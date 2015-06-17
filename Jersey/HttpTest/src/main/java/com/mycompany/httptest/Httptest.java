package com.mycompany.httptest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;
/**
 *
 * @author jupiter
 */
public class Httptest {

    public static void main(String[] args) {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8085/app/test");

        String responseEntity = target.request(MediaType.TEXT_PLAIN).get(String.class);
        System.out.println(responseEntity);

        String description = "2005/12/31";       
        responseEntity = target.path(description).request(MediaType.TEXT_PLAIN).get(String.class);
        System.out.println(responseEntity);

        String jsonString = target.path("json").request(MediaType.APPLICATION_JSON_TYPE).get(String.class);
        System.out.println(jsonString);
        
        
        InputStream file = target.path("pdf").request(MediaType.APPLICATION_OCTET_STREAM).get(InputStream.class);
        
        
        
    }

}
