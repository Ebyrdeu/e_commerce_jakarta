package dev.ebyrdeu.e_commerce_jakarta.resource;

import dev.ebyrdeu.e_commerce_jakarta.dto.product.ProductDto;
import dev.ebyrdeu.e_commerce_jakarta.serivce.product.ProductService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

    ProductService service;

    public ProductResource() {
    }

    @Inject
    public ProductResource(ProductService service) {
        this.service = service;
    }


    @GET
    public Response getAll() {
        var products = service.getAll();
        return Response.ok().entity(products).build();
    }

    @GET
    @Path("/{id}")
    public Response getOne(@PathParam("id") long id) {
        var product = service.getOne(id);
        return Response.ok().entity(product).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") long id, ProductDto productDto) {
        service.update(id, productDto);
        return Response.ok().build();
    }


    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id) {
        service.remove(id);
        return Response.noContent().build();
    }


    @POST
    public Response create(@Valid ProductDto productDto) {
        var product = service.create(productDto);
        return Response.status(201).entity(product).build();
    }
}
