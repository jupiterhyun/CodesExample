package com.mycompany.jerseyexample.resources;

import com.google.gson.Gson;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Map;
import java.util.HashMap;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

@Path("test")
public class MyResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "dsdzfd";
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

    @GET
    @Path("json")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        Map<String, String> map = new HashMap<>();
        map.put("li", "si");
        map.put("lian", "sisd");
        map.put("liwe", "sifff");
        map.put("liqwei", "sisss");
        Gson gson = new Gson();

        return gson.toJson(map);
    }

    @GET
    @Path("pdf")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getPdf() {
        String filePath = "C:\\Users\\jupiter\\Documents\\NetBeansProjects\\SimpleJerseyexample\\test.pdf";
        File file = new File(filePath);
        if (file.exists()) {
            String path = System.getProperty("user.dir");        
        }
        StreamingOutput stream = null;
        try {
            final InputStream in = new FileInputStream(file);
            stream = new StreamingOutput() {
                @Override
                public void write(OutputStream out) throws IOException, WebApplicationException {
                    try {
                        byte[] bytes = new byte[1024];
                        int read = 0;
                        while ((read = in.read(bytes)) != -1) {
                            out.write(bytes, 0, read);
                        }
                    } catch (Exception e) {
                        throw new WebApplicationException(e);
                    }
                }
            };
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return Response.ok(stream).header("content-disposition", "attachment; filename = " + file.getName()).build();
    }

    @GET
    @Path("csv")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getCSV() {
        String filePath = "C:\\Users\\jupiter\\Documents\\NetBeansProjects\\SimpleJerseyexample\\test.csv";
        File file = new File(filePath);
        if (file.exists()) {
            String path = System.getProperty("user.dir");        
        }
        StreamingOutput stream = null;
        try {
            final InputStream in = new FileInputStream(file);
            stream = new StreamingOutput() {
                @Override
                public void write(OutputStream out) throws IOException, WebApplicationException {
                    try {
                        byte[] bytes = new byte[1024];
                        int read = 0;
                        while ((read = in.read(bytes)) != -1) {
                            out.write(bytes, 0, read);
                        }
                    } catch (Exception e) {
                        throw new WebApplicationException(e);
                    }
                }
            };
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return Response.ok(stream).header("content-disposition", "attachment; filename = " + file.getName()).build();
    }
    
    @GET
    @Path("img")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getImg() {
        String filePath = "C:\\Users\\jupiter\\Documents\\NetBeansProjects\\SimpleJerseyexample\\test.jpg";
        File file = new File(filePath);
        if (file.exists()) {
            String path = System.getProperty("user.dir");        
        }
        StreamingOutput stream = null;
        try {
            final InputStream in = new FileInputStream(file);
            stream = new StreamingOutput() {
                @Override
                public void write(OutputStream out) throws IOException, WebApplicationException {
                    try {
                        byte[] bytes = new byte[1024];
                        int read = 0;
                        while ((read = in.read(bytes)) != -1) {
                            out.write(bytes, 0, read);
                        }
                    } catch (Exception e) {
                        throw new WebApplicationException(e);
                    }
                }
            };
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return Response.ok(stream).header("content-disposition", "attachment; filename = " + file.getName()).build();
    }
    
    
}
