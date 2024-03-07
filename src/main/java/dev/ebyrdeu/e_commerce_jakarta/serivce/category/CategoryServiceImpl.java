package dev.ebyrdeu.e_commerce_jakarta.serivce.category;

import dev.ebyrdeu.e_commerce_jakarta.dto.category.CategoryDto;
import dev.ebyrdeu.e_commerce_jakarta.entity.Category;
import dev.ebyrdeu.e_commerce_jakarta.repository.category.CategoryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;

import static dev.ebyrdeu.e_commerce_jakarta.utils.Utils.isNotNull;

@ApplicationScoped
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository repository;

    public CategoryServiceImpl() {
    }

    @Inject
    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CategoryDto> getAll() {
        return repository.getAll().stream().map(CategoryDto::map).toList();
    }

    @Override
    public Category getOne(long id) {
        return repository.getOne(id)
                .orElseThrow(() -> new EntityNotFoundException("Category with id: " + id + "not found"));
    }

    @Override
    @Transactional
    public Category create(CategoryDto dto) {
        return repository.create(CategoryDto.map(dto));
    }

    @Override
    @Transactional
    public void update(long id, CategoryDto dto) {
        var existingCategory = repository.getOne(id)
                .orElseThrow(() -> new EntityNotFoundException("Category with id: " + id + "not found"));

        isNotNull(existingCategory::setName, dto.category_name());
        isNotNull(existingCategory::setDescription, dto.description());

        repository.update(existingCategory);
    }

    @Override
    @Transactional
    public void remove(long id) {
        var existingCategory = repository.getOne(id)
                .orElseThrow(() -> new EntityNotFoundException("Category with id: " + id + "not found"));

        repository.remove(existingCategory);
    }
}
