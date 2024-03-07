package dev.ebyrdeu.e_commerce_jakarta.repository.customer;

import dev.ebyrdeu.e_commerce_jakarta.entity.Customer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.Optional;


@ApplicationScoped
public class CustomerRepositoryImpl implements CustomerRepository {
    @PersistenceContext(unitName = "default")
    EntityManager em;

    @Override
    public List<Customer> getAll() {
        var query = "SELECT c FROM  Customer  c";
        return em.createQuery(query, Customer.class).getResultList();
    }

    @Override
    public Optional<Customer> getOne(Long id) {
        return Optional.ofNullable(em.find(Customer.class, id));
    }

    @Override
    @Transactional
    public Customer create(Customer entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    @Transactional
    public void update(Customer entity) {
        em.merge(entity);
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
