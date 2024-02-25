package dev.ebyrdeu.e_commerce_jakarta.dto.customer;

import dev.ebyrdeu.e_commerce_jakarta.entity.Customer;

public record CustomerDto(String first_name, String last_name, String email, String username, String password,
                          String phone) {
    public static CustomerDto map(Customer entity) {
        return new CustomerDto(entity.firstName(), entity.lastName(), entity.email(), entity.username(), entity.password(), entity.phone());
    }


    public static Customer map(CustomerDto customerDto) {
        var customer = new Customer();
        customer.setFirstName(customerDto.first_name());
        customer.setLastName(customerDto.last_name());
        customer.setEmail(customerDto.email());
        customer.setUsername(customerDto.username());
        customer.setPassword(customerDto.password());
        customer.setPhone(customerDto.phone());

        return customer;
    }
}
