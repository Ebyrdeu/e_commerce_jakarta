package dev.ebyrdeu.e_commerce_jakarta.resource;

import dev.ebyrdeu.e_commerce_jakarta.dto.customer.CustomerDto;
import dev.ebyrdeu.e_commerce_jakarta.serivce.customer.CustomerService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/customers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {
    private CustomerService service;

    public CustomerResource() {
    }

    @Inject
    public CustomerResource(CustomerService service) {
        this.service = service;
    }

    @GET
    public Response getAll() {
        var customers = service.getAll();
        return Response.ok().entity(customers).build();
    }

    @GET
    @Path("/{id}")
    public Response getOne(@PathParam("id") long id) {
        var customer = service.getOne(id);
        return Response.ok().entity(CustomerDto.map(customer)).build();

    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") long id, CustomerDto customerDto) {
        service.update(id, customerDto);
        return Response.ok().build();
    }


    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id) {
        service.remove(id);
        return Response.noContent().build();
    }


    @POST
    public Response create(@Valid CustomerDto customerDto) {
        var customer = service.create(customerDto);
        return Response.status(201).entity(CustomerDto.map(customer)).build();
    }

}

