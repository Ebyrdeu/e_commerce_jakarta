package dev.ebyrdeu.e_commerce_jakarta.resource;

import dev.ebyrdeu.e_commerce_jakarta.dto.category.Categories;
import dev.ebyrdeu.e_commerce_jakarta.dto.category.CategoryDto;
import dev.ebyrdeu.e_commerce_jakarta.entity.Category;
import dev.ebyrdeu.e_commerce_jakarta.repository.category.CategoryRepositoryImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/categories")
public class CategoryResource {
    private CategoryRepositoryImpl repository;

    public CategoryResource() {

    }

    @Inject
    public CategoryResource(CategoryRepositoryImpl repository) {
        this.repository = repository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        var categories = new Categories(
                repository.getAll().stream().map(CategoryDto::map).toList()
        );

        return Response.ok().entity(categories).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOne(@PathParam("id") long id) {
        var category = repository.getOne(new Category().setId(id));

        if (category == null) {
            throw new NotFoundException("Category with id not found");
        }

        return Response.ok().entity(CategoryDto.map(category)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") long id, CategoryDto categoryDto) {
        var category = repository.update(new Category().setId(id).setDescription(categoryDto.description()).setName(categoryDto.category_name()));

        return Response.ok().entity(CategoryDto.map(category)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response remove(@PathParam("id") long id) {
        repository.remove(new Category().setId(id));
        return Response.noContent().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(CategoryDto categoryDto) {
        var category = repository.create(CategoryDto.map(categoryDto));
        return Response.status(201).entity(CategoryDto.map(category)).build();
    }
}
