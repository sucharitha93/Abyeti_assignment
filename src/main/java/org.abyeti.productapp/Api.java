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
    @Path("/sregister")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addseller(String b)
    {
        System.out.println(b);
        JSONObject j=new JSONObject(b);
        System.out.println(j);
        new Functions().insertseller(j);
        return Response.status(200).entity("{\"STATUS\" : \"SUCCEEDED\"}").build();
    }

    @POST
    @Path("/bregister")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addcustomer(String b)
    {
        System.out.println(b);
        JSONObject j=new JSONObject(b);
        System.out.println(j);
        new Functions().insertcustomer(j);
        return Response.status(200).entity("{\"STATUS\" : \"SUCCEEDED\"}").build();
    }

    @GET
    @Path("/browse")
    @Produces(MediaType.APPLICATION_JSON)
    public Response browse(String message)
    {
        JSONArray j=new Functions().returnlist();
        System.out.println(j);
        return Response.status(200).entity(j.toString()).build();
    }


    @POST
    @Path("/addproduct")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addproduct(String b)
    {
        System.out.println(b);
        JSONObject j=new JSONObject(b);
        System.out.println(j);
        new Functions().insertproduct(j);
        return Response.status(200).entity("{\"STATUS\" : \"SUCCEEDED\"}").build();
    }

    @POST
    @Path("/addorder")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addorder(String b)
    {
        System.out.println(b);
        JSONObject j=new JSONObject(b);
        System.out.println(j);
        new Functions().insertorder(j);
        return Response.status(200).entity("{\"STATUS\" : \"SUCCEEDED\"}").build();
    }

    @POST
    @Path("/sellersold")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sellersold(String message)
    {
        int i=new JSONObject(message).getInt("message");
        JSONArray j=new Functions().returnsold(i);
        System.out.println(j);
        return Response.status(200).entity(j.toString()).build();
    }
    @POST
    @Path("/sellerunsold")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sellerunsold(String message)
    {
        int i=new JSONObject(message).getInt("message");
        JSONArray j=new Functions().returnunsold(i);
        System.out.println(j);
        return Response.status(200).entity(j.toString()).build();
    }

    @POST
    @Path("/removeproduct")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeproduct(String message)
    {
        JSONObject j=new JSONObject(message);
        int i=j.getInt("pid");
        System.out.println("Delete product"+i);
        new Functions().deleteproduct(i);
        System.out.println("Delete product");
        return Response.status(200).entity("{\"STATUS\" : \"SUCCEEDED\"}").build();
    }

    @POST
    @Path("/loginseller")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginseller(String message)
    {
        JSONObject job=new JSONObject(message);
        System.out.println("Login Seller");
        JSONObject j=new Functions().loginseller(job);
        return Response.status(200).entity(j.toString()).build();
    }

    @POST
    @Path("/logincustomer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response logincustomer(String message)
    {
        JSONObject job=new JSONObject(message);
        System.out.println("Login customer");
        JSONObject j=new Functions().logincustomer(job);
        return Response.status(200).entity(j.toString()).build();
    }

    @POST
    @Path("/vieworder")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response vieworder(String message)
    {

        int i=new JSONObject(message).getInt("message");
        JSONArray j=new Functions().vieworder(i);
        System.out.println(j);
        return Response.status(200).entity(j.toString()).build();
    }
}
