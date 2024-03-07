package dev.ebyrdeu.e_commerce_jakarta.dto.customer;

import dev.ebyrdeu.e_commerce_jakarta.dto.category.CategoryDto;
import dev.ebyrdeu.e_commerce_jakarta.entity.Category;
import dev.ebyrdeu.e_commerce_jakarta.entity.Customer;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CustomerDtoTest {
    private static Validator validator;

    private Customer entity;
    private CustomerDto dto;

    @BeforeAll
    static void setUpValidator() {
        try (var factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();

        }
    }

    @BeforeEach
    void setUp() {
        entity = new Customer()
                .setId((long) 1)
                .setFirstName("Boba")
                .setLastName("Fett")
                .setEmail("boba.fett@gmail.com")
                .setUsername("fetus")
                .setPassword("12345678910")
                .setPhone("123456789")
                .setCreatedAt(new Date())
                .setUpdatedAt(new Date());

        dto = new CustomerDto(
                (long) 1,
                "Boba",
                "Fett",
                "boba.fett@gmail.com",
                "fetus",
                "12345678910",
                "123456789",
                new Date(),
                new Date()
        );
    }


    @Test
    @DisplayName("Dto returns entity with same fields")
    void dtoReturnsEntityWithSameFields() {
        var customerEntity = CustomerDto.map(dto);

        assertAll(
                () -> assertThat(entity.id()).isEqualTo(customerEntity.id()),
                () -> assertThat(entity.firstName()).isEqualTo(customerEntity.firstName()),
                () -> assertThat(entity.lastName()).isEqualTo(customerEntity.lastName()),
                () -> assertThat(entity.email()).isEqualTo(customerEntity.email()),
                () -> assertThat(entity.username()).isEqualTo(customerEntity.username()),
                () -> assertThat(entity.password()).isEqualTo(customerEntity.password()),
                () -> assertThat(entity.phone()).isEqualTo(customerEntity.phone()),
                () -> assertThat(entity.createdAt()).isEqualTo(customerEntity.createdAt()),
                () -> assertThat(entity.updatedAt()).isEqualTo(customerEntity.updatedAt())
        );
    }

    @Test
    @DisplayName("Entity return dto with same fields")
    void entityReturnDtoWithSameFields() {
        var customerDto = CustomerDto.map(entity);

        assertAll(
                () -> assertThat(dto.id()).isEqualTo(customerDto.id()),
                () -> assertThat(dto.first_name()).isEqualTo(customerDto.first_name()),
                () -> assertThat(dto.last_name()).isEqualTo(customerDto.last_name()),
                () -> assertThat(dto.email()).isEqualTo(customerDto.email()),
                () -> assertThat(dto.username()).isEqualTo(customerDto.username()),
                () -> assertThat(dto.password()).isEqualTo(customerDto.password()),
                () -> assertThat(dto.phone()).isEqualTo(customerDto.phone()),
                () -> assertThat(dto.created_at()).isEqualTo(customerDto.created_at()),
                () -> assertThat(dto.updated_at()).isEqualTo(customerDto.updated_at())
        );
    }

    @Test
    @DisplayName("Dto should return entity class")
    void dtoShouldReturnEntityClass() {
        var customerEntity = CustomerDto.map(dto);
        assertThat(customerEntity.getClass()).isEqualTo(Customer.class);
    }

    @Test
    @DisplayName("Entity should return Dto class")
    void entityShouldReturnDtoClass() {
        var customerDto = CustomerDto.map(entity);

        assertThat(customerDto.getClass()).isEqualTo(CustomerDto.class);
    }

@Test
@DisplayName("Dto is invalid")
void dtoIsInvalid() {
        var customerDto = new CustomerDto(
                (long) 1,
                "",
                "",
                "",
                "",
                "",
                "",
                new Date(),
                new Date()
        );

        var violations = validator.validate(customerDto);

        assertThat(violations).hasSize(10);
}

    @Test
    @DisplayName("Dto is valid")
    void dtoIsValid() {
        var violations = validator.validate(dto);

        assertThat(violations).isEmpty();
    }
}