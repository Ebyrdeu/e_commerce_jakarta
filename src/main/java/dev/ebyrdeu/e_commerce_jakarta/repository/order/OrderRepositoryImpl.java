package dev.ebyrdeu.e_commerce_jakarta.repository.order;

import dev.ebyrdeu.e_commerce_jakarta.entity.Order;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class OrderRepositoryImpl implements OrderRepository {
    @PersistenceContext(unitName = "default")
    EntityManager em;

    @Override
    public List<Order> getAll() {
        var query = "SELECT o FROM Order o";
        return em.createQuery(query, Order.class).getResultList();
    }

    @Override
    public Optional<Order> getOne(Long id) {
        return Optional.ofNullable(em.find(Order.class, id));
    }

    @Override
    public Order create(Order entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public void update(Order entity) {
        em.merge(entity);
    }

    @Override
    public void remove(Order entity) {
        var existingEntity = em.find(Order.class, entity.id());

        if (existingEntity == null) {
            throw new NotFoundException("Product with id: " + entity.id() + " not found");
        }
        em.remove(existingEntity);
    }
}
