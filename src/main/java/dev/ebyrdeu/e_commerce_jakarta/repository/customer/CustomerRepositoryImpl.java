package dev.ebyrdeu.e_commerce_jakarta.repository.customer;

import dev.ebyrdeu.e_commerce_jakarta.entity.Customer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

import static dev.ebyrdeu.e_commerce_jakarta.utils.Utils.isNotNull;


@ApplicationScoped
public class CustomerRepositoryImpl implements CustomerRepository {
    @PersistenceContext(unitName = "default")
    EntityManager em;

    @Override
    public List<Customer> getAll() {
        var query = "select c from  Customer  c";
        return em.createQuery(query, Customer.class).getResultList();
    }

    @Override
    public Customer getOne(Customer entity) {
        return em.find(Customer.class, entity.id());
    }

    @Override
    @Transactional
    public Customer create(Customer entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    @Transactional
    public Customer update(Customer entity) {
        var existingEntity = em.find(Customer.class, entity.id());

        if (existingEntity == null) {
            throw new NotFoundException("Category with id: " + entity.id() + " not found");
        }

        isNotNull(existingEntity::setFirstName, entity.firstName());
        isNotNull(existingEntity::setLastName, entity.lastName());
        isNotNull(existingEntity::setEmail, entity.email());
        isNotNull(existingEntity::setUsername, entity.username());
        isNotNull(existingEntity::setPassword, entity.password());
        isNotNull(existingEntity::setPhone, entity.phone());

        return existingEntity;
    }


    @Override
    @Transactional
    public void remove(Customer entity) {
        var existingEntity = em.find(Customer.class, entity.id());

        if (existingEntity == null) {
            throw new NotFoundException("Category with id: " + entity.id() + " not found");
        }

        em.remove(existingEntity);
    }


}
