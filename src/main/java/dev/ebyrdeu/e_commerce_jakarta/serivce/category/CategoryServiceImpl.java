package dev.ebyrdeu.e_commerce_jakarta.serivce.category;

import dev.ebyrdeu.e_commerce_jakarta.dto.category.Categories;
import dev.ebyrdeu.e_commerce_jakarta.dto.category.CategoryDto;
import dev.ebyrdeu.e_commerce_jakarta.entity.Category;
import dev.ebyrdeu.e_commerce_jakarta.repository.category.CategoryRepositroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepositroy repository;

    public CategoryServiceImpl() {
    }

    @Inject
    public CategoryServiceImpl(CategoryRepositroy repository) {
        this.repository = repository;
    }

    @Override
    public Categories getAll() {
        return new Categories(
                repository.getAll().stream().map(CategoryDto::map).toList()
        );
    }

    @Override
    public Category getOne(long id) {
        var category = repository.getOne(new Category().setId(id));

        if (category == null) {
            throw new NotFoundException("Category with id not found");
        }
        return category;
    }

    @Override
    public Category create(CategoryDto dto) {
//        TODO: change to DTO
        return repository.create(CategoryDto.map(dto));
    }

    @Override
    public Category update(long id, CategoryDto dto) {
        return repository.update(new Category().setId(id).setDescription(dto.description()).setName(dto.category_name()));
    }

    @Override
    public void remove(long id) {
        repository.remove(new Category().setId(id));
    }
}
