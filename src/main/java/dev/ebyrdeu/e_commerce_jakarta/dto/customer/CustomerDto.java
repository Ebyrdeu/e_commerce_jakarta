package dev.ebyrdeu.e_commerce_jakarta.dto.customer;

import dev.ebyrdeu.e_commerce_jakarta.entity.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record CustomerDto(
        Long id,

        @NotBlank(message = "First name of Customer must not be null")
        @Size(min = 1, max = 15, message = "First name of Customer must be between 2 and 15 chars")
        String first_name,

        @NotBlank(message = "Last name of Customer must not be null")
        @Size(min = 1, max = 15, message = "Last name of Customer must be between 2 and 15 chars")
        String last_name,

        @Email(message = "Email must be valid")
        String email,

        @NotBlank(message = "Username of Customer must not be null")
        @Size(min = 2, max = 15, message = "Username of Customer must be between 2 and 15 chars")
        String username,

        @NotBlank(message = "Password of Customer must not be null")
        @Size(min = 10, max = 20, message = "Username of Customer must be between 2 and 15 chars")
        String password,

        @NotBlank(message = "Phone of Customer must not be null")
        @Size(min = 9, max = 15, message = "Phone of Customer must be between 2 and 15 chars")
        String phone,

        Date created_at,
        Date updated_at
) {
    public static CustomerDto map(Customer entity) {
        return new CustomerDto(
                entity.id(),
                entity.firstName(),
                entity.lastName(),
                entity.email(),
                entity.username(),
                entity.password(),
                entity.phone(),
                entity.createdAt(),
                entity.updatedAt()
        );
    }


    public static Customer map(CustomerDto customerDto) {
        var customer = new Customer();
        customer.setId(customerDto.id);
        customer.setFirstName(customerDto.first_name);
        customer.setLastName(customerDto.last_name);
        customer.setEmail(customerDto.email);
        customer.setUsername(customerDto.username);
        customer.setPassword(customerDto.password);
        customer.setPhone(customerDto.phone);
        customer.setCreatedAt(customerDto.created_at);
        customer.setUpdatedAt(customerDto.updated_at);
        return customer;
    }
}
