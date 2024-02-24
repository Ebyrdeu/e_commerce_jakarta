package dev.ebyrdeu.e_commerce_jakarta.dto.category;

import dev.ebyrdeu.e_commerce_jakarta.entity.Category;

import java.time.Instant;

public record CategoryDto(String category_name, String description, Instant createdAt, Instant updatedAt) {
    public static CategoryDto map(Category category) {
        return new CategoryDto(category.name(), category.description(), category.createdAt(), category.updatedAt());
    }

    public static Category map(CategoryDto categoryDto) {
        var category = new Category();
        category.setName(categoryDto.category_name);
        category.setDescription(categoryDto.description);
        return category;
    }
}