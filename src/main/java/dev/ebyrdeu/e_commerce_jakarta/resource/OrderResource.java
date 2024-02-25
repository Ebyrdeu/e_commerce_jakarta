package dev.ebyrdeu.e_commerce_jakarta.resource;

import dev.ebyrdeu.e_commerce_jakarta.dto.order.OrderDto;
import dev.ebyrdeu.e_commerce_jakarta.serivce.order.OrderService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/orders")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {
    private OrderService service;

    public OrderResource() {

    }

    @Inject
    public OrderResource(OrderService service) {
        this.service = service;
    }

    @GET
    public Response getAll() {
        var orders = service.getAll();
        return Response.ok().entity(orders).build();
    }


    @GET
    @Path("/{id}")
    public Response getOne(@PathParam("id") long id) {
        var order = service.getOne(id);
        return Response.ok().entity(OrderDto.map(order)).build();
    }


    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") long id, OrderDto orderDto) {
        var order = service.update(id, orderDto);
        return Response.ok().entity(OrderDto.map(order)).build();
    }


    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id) {
        service.remove(id);
        return Response.noContent().build();
    }


    @POST
    public Response create(OrderDto orderDto) {
        var order = service.create(orderDto);
        return Response.status(201).entity(order).build();
    }
}
