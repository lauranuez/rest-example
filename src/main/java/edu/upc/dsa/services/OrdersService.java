package edu.upc.dsa.services;


import edu.upc.dsa.Classes.Product;
import edu.upc.dsa.Classes.User;
import edu.upc.dsa.Classes.Order;
import edu.upc.dsa.ProductManager;
import edu.upc.dsa.ProductManagerimpl;
import edu.upc.dsa.TracksManager;
import edu.upc.dsa.models.Track;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/orders", description = "Endpoint to Track Service")
@Path("/orders")
public class OrdersService {

    private TracksManager tm;

    private ProductManager pm;

    public OrdersService() {

        this.pm = ProductManagerimpl.getInstance();
        if (!this.pm.isDirty() ) {

            Product product1 = new Product("coca", 2);
            Product product2 = new Product("pan", 1);
            Product product3 = new Product("bocadillo de lomo", 4);
            Product product4 = new Product("patatas", 3);


            pm.addProduct(product1);
            pm.addProduct(product2);
            pm.addProduct(product3);
            pm.addProduct(product4);

            User user = new User("Juan");
            User user2 = new User("Maria");

            pm.addUser(user);
            pm.addUser(user2);
            pm.dirty();
        }


    }

    @GET
    @ApiOperation(value = "get products by prize", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class, responseContainer="List"),
    })
    @Path("/prize")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsPrize() {

        List<Product> productList = this.pm.getProductByPrize();

        GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(productList) {};
        return Response.status(201).entity(entity).build();

    }

    @GET
    @ApiOperation(value = "get products by sales", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class, responseContainer="List"),
    })
    @Path("/sales")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsSales() {

        List<Product> productList = this.pm.getProductBySales();

        GenericEntity<List<Product>> entity2 = new GenericEntity<List<Product>>(productList) {};
        return Response.status(201).entity(entity2).build();

    }

    @DELETE
    @ApiOperation(value = "process order", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "No orders to process!!")
    })
    @Path("/")
    public Response ProcessOrder() {
        Order o = pm.processOrder();
        if (o == null) return Response.status(404).build();
        else return Response.status(201).build();
    }

    @GET
    @ApiOperation(value = "get orders by user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Order.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/user/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderByUser(@PathParam("name") String id) {
        List<Order> listOrder = pm.getOrdersByUser(id);

        if (listOrder == null) return Response.status(404).build();
        else {
            GenericEntity<List<Order>> entity = new GenericEntity<List<Order>>(listOrder) {};
            return Response.status(201).entity(entity).build();
        }
    }


    @POST
    @ApiOperation(value = "create a new order", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Order.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newOrder(Order o) {

        if (o.getUserName()==null)  return Response.status(500).entity(o).build();
        this.pm.newOrder(o);
        return Response.status(201).build();
    }



    @PUT
    @ApiOperation(value = "update a Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/")
    public Response updateTrack(Track track) {

        Track t = this.tm.updateTrack(track);

        if (t == null) return Response.status(404).build();

        return Response.status(201).build();
    }

}