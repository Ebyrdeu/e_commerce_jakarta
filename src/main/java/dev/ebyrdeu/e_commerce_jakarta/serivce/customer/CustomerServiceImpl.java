package dev.ebyrdeu.e_commerce_jakarta.serivce.customer;

import dev.ebyrdeu.e_commerce_jakarta.dto.customer.CustomerDto;
import dev.ebyrdeu.e_commerce_jakarta.entity.Customer;
import dev.ebyrdeu.e_commerce_jakarta.repository.customer.CustomerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;

import static dev.ebyrdeu.e_commerce_jakarta.utils.Utils.isNotNull;

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
    public List<CustomerDto> getAll() {
        return repository.getAll().stream().map(CustomerDto::map).toList();
    }

    @Override
    public Customer getOne(long id) {
        return repository.getOne(id).orElseThrow(() -> new EntityNotFoundException("Customer with id: " + id + "not found"));
    }

    @Override
    @Transactional
    public Customer create(CustomerDto dto) {
        return repository.create(CustomerDto.map(dto));
    }

    @Override
    @Transactional
    public void update(long id, CustomerDto dto) {
        var existingEntity = repository.getOne(id).orElseThrow(() -> new EntityNotFoundException("Customer with id: " + id + "not found"));

        isNotNull(existingEntity::setFirstName, dto.first_name());
        isNotNull(existingEntity::setLastName, dto.last_name());
        isNotNull(existingEntity::setEmail, dto.email());
        isNotNull(existingEntity::setUsername, dto.username());
        isNotNull(existingEntity::setPassword, dto.password());
        isNotNull(existingEntity::setPhone, dto.phone());

        repository.update(existingEntity);
    }

    @Override
    @Transactional
    public void remove(long id) {
        repository.remove(new Customer().setId(id));
    }
}
