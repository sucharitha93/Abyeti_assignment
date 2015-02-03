package org.abyeti.productapp;


import org.json.JSONArray;
import org.json.JSONObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Sucharitha Reddy on 1/30/2015.
 */
@Path("/api")
public class Api {
    @POST
    @Path("/addproduct")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addproduct(Product b) {
        System.out.println(b);
        new Functionality().addproduct(b);
        return Response.status(200).build();
    }


    @GET
    @Path("/browse")
    @Produces(MediaType.APPLICATION_JSON)
    public Response browse(String message)
    {
        JSONArray j=new Functionality().returnlist();
        System.out.println(j);
        return Response.status(200).entity(j.toString()).build();
    }

    @GET
    @Path("/delete/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteproduct(@PathParam("param") String msg)
    {
        JSONObject j=new Functionality().deleterecord(msg);
        System.out.println(j);
        return Response.status(200).entity(j.toString()).build();
    }

}
