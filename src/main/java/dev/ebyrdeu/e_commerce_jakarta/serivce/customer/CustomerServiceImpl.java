package dev.ebyrdeu.e_commerce_jakarta.serivce.customer;

import dev.ebyrdeu.e_commerce_jakarta.dto.customer.CustomerDto;
import dev.ebyrdeu.e_commerce_jakarta.dto.customer.Customers;
import dev.ebyrdeu.e_commerce_jakarta.entity.Customer;
import dev.ebyrdeu.e_commerce_jakarta.repository.customer.CustomerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository repository;

    public CustomerServiceImpl() {
    }

    @Inject
    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customers getAll() {
        return new Customers(
                repository.getAll().stream().map(CustomerDto::map).toList()
        );
    }

    @Override
    public Customer getOne(long id) {
        var customer = repository.getOne(new Customer().setId(id));

        if (customer == null) {
            throw new NotFoundException("Customer with id not found");
        }

        return customer;
    }

    @Override
    public Customer create(CustomerDto dto) {
        return repository.create(CustomerDto.map(dto));
    }

    @Override
    public Customer update(long id, CustomerDto dto) {
        return repository.update(
                new Customer()
                        .setId(id)
                        .setFirstName(dto.first_name())
                        .setLastName(dto.last_name())
                        .setEmail(dto.email())
                        .setUsername(dto.username())
                        .setPhone(dto.phone())
                        .setPassword(dto.password())
        );
    }

    @Override
    public void remove(long id) {
        repository.remove(new Customer().setId(id));
    }
}
