package dev.ebyrdeu.e_commerce_jakarta.dto.category;

import dev.ebyrdeu.e_commerce_jakarta.entity.Category;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CategoryDtoTest {
    private static Validator validator;
    private Category entity;
    private CategoryDto dto;

    @BeforeAll
    static void setUpValidator() {
        try (var factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @BeforeEach
    void setUp() {
        entity = new Category()
                .setId((long) 1)
                .setName("Name")
                .setDescription("Some description")
                .setCreatedAt(new Date())
                .setUpdatedAt(new Date());

        dto = new CategoryDto(
                (long) 1,
                "name",
                "Some description",
                new Date(),
                new Date()
        );

    }


    @Test
    @DisplayName("Dto returns entity with same fields")
    void dtoReturnsEntityWithSameFields() {
        var categoryDto = CategoryDto.map(entity);
        assertAll(
                () -> assertThat(entity.id()).isEqualTo(categoryDto.id()),
                () -> assertThat(entity.name()).isEqualTo(categoryDto.category_name()),
                () -> assertThat(entity.description()).isEqualTo(categoryDto.description()),
                () -> assertThat(entity.createdAt()).isEqualTo(categoryDto.created_at()),
                () -> assertThat(entity.updatedAt()).isEqualTo(categoryDto.updated_at())
        );
    }

    @Test
    @DisplayName("Entity returns dto with same fields")
    void entityReturnsDtoWithSameFields() {
        var categoryEntity = CategoryDto.map(dto);

        assertAll(
                () -> assertThat(dto.id()).isEqualTo(categoryEntity.id()),
                () -> assertThat(dto.category_name()).isEqualTo(categoryEntity.name()),
                () -> assertThat(dto.description()).isEqualTo(categoryEntity.description()),
                () -> assertThat(dto.created_at()).isEqualTo(categoryEntity.createdAt()),
                () -> assertThat(dto.updated_at()).isEqualTo(categoryEntity.updatedAt())
        );
    }


    @Test
    @DisplayName("Dto should return entity class")
    void dtoShouldReturnEntityClass() {
        var categoryEntity = CategoryDto.map(dto);
        assertThat(categoryEntity.getClass()).isEqualTo(Category.class);
    }

    @Test
    @DisplayName("Entity should return Dto class")
    void entityShouldReturnDtoClass() {
        var categoryDto = CategoryDto.map(entity);

        assertThat(categoryDto.getClass()).isEqualTo(CategoryDto.class);
    }

    @Test
    @DisplayName("Dto is invalid")
    void dtoIsInvalid() {
        var categoryDto = new CategoryDto(
                (long) 1,
                "more than 15 characters long name",
                "n",
                Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)),
                Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC))
        );

        var violations = validator.validate(categoryDto);

        boolean nameSizeViolationFound = violations.stream().anyMatch(v -> "Name of Category must be between 2 and 15 chars".equals(v.getMessage()));
        boolean descriptionSizeViolationFound = violations.stream().anyMatch(v -> "Description of Category must be at least 2 char long".equals(v.getMessage()));

        assertThat(violations).hasSize(2);
        assertThat(nameSizeViolationFound).isTrue();
        assertThat(descriptionSizeViolationFound).isTrue();
    }

    @Test
    @DisplayName("Dto is valid")
    void dtoIsValid() {
        var violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("Dto is invalid while name is blank")
    void dtoIsInvalidWhileNameIsBlank() {
        var categoryDto = new CategoryDto(
                null,
                "",
                "adasd",
                Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)),
                Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC))
        );

        var violations = validator.validate(categoryDto);

        boolean nameNotBlankViolationFound = violations.stream().anyMatch(v -> "Name of Category can't be null".equals(v.getMessage()));
        boolean nameSizeViolationFound = violations.stream().anyMatch(v -> "Name of Category must be between 2 and 15 chars".equals(v.getMessage()));

        assertThat(violations).hasSize(2);
        assertThat(nameNotBlankViolationFound).isTrue();
        assertThat(nameSizeViolationFound).isTrue();
    }

    @Test
    @DisplayName("Dto is invalid Description is lower than 2 chars")
    void dtoIsInvalidDescriptionIsLowerThan2Chars() {
        var categoryDto = new CategoryDto(
                null,
                "Food",
                "d",
                Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)),
                Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC))
        );

        var violations = validator.validate(categoryDto);

        boolean descriptionSizeViolationFound = violations.stream().anyMatch(v -> "Description of Category must be at least 2 char long".equals(v.getMessage()));

        assertThat(violations).hasSize(1);
        assertThat(descriptionSizeViolationFound).isTrue();
    }
}