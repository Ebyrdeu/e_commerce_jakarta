package dev.ebyrdeu.e_commerce_jakarta.dto.category;

import dev.ebyrdeu.e_commerce_jakarta.entity.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record CategoryDto(
        Long id,

        @NotBlank(message = "Name of Category can't be null")
        @Size(min = 2, max = 15, message = "Name of Category must be between 2 and 15 chars")
        String category_name,

        @Size(min = 2, message = "Description of Category must be at least 2 char long")
        String description,

        Date created_at,
        Date updated_at
) {
    public static CategoryDto map(Category category) {
        return new CategoryDto(
                category.id(),
                category.name(),
                category.description(),
                category.createdAt(),
                category.updatedAt()
        );
    }

    public static Category map(CategoryDto categoryDto) {
        var category = new Category();
        category.setId(categoryDto.id);
        category.setName(categoryDto.category_name);
        category.setDescription(categoryDto.description);
        category.setCreatedAt(categoryDto.created_at);
        category.setUpdatedAt(categoryDto.updated_at);
        return category;
    }
}
