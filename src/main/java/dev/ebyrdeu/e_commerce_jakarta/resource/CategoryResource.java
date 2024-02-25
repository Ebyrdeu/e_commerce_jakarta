package dev.ebyrdeu.e_commerce_jakarta.resource;

import dev.ebyrdeu.e_commerce_jakarta.dto.category.CategoryDto;
import dev.ebyrdeu.e_commerce_jakarta.serivce.category.CategoryService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/categories")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResource {

    CategoryService service;

    public CategoryResource() {

    }

    @Inject
    public CategoryResource(CategoryService service) {
        this.service = service;
    }

    @GET
    public Response getAll() {
        var categories = service.getAll();
        return Response.ok().entity(categories).build();
    }

    @GET
    @Path("/{id}")
    public Response getOne(@PathParam("id") long id) {
        var category = service.getOne(id);
        return Response.ok().entity(CategoryDto.map(category)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") long id, CategoryDto categoryDto) {
        var category = service.update(id, categoryDto);
        return Response.ok().entity(CategoryDto.map(category)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response remove(@PathParam("id") long id) {
        service.remove(id);
        return Response.noContent().build();
    }

    @POST
    public Response create(CategoryDto categoryDto) {
        var category = service.create(categoryDto);
        return Response.status(201).entity(CategoryDto.map(category)).build();
    }
}
