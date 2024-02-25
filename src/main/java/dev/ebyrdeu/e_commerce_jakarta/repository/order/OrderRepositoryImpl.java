package dev.ebyrdeu.e_commerce_jakarta.repository.order;

import dev.ebyrdeu.e_commerce_jakarta.entity.Order;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

import static dev.ebyrdeu.e_commerce_jakarta.utils.Utils.isNotNull;

@ApplicationScoped
public class OrderRepositoryImpl implements OrderRepository {
    @PersistenceContext(unitName = "default")
    EntityManager em;

    @Override
    public List<Order> getAll() {
        var query = "select o from Order o";
        return em.createQuery(query, Order.class).getResultList();
    }

    @Override
    public Order getOne(Order entity) {
        return em.find(Order.class, entity.id());
    }

    @Override
    @Transactional
    public Order create(Order entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    @Transactional
    public Order update(Order entity) {
        var existingEntity = em.find(Order.class, entity.id());
        if (existingEntity == null) {
            throw new NotFoundException("Product with id: " + entity.id() + " not found");
        }

        isNotNull(existingEntity::setStatus, entity.status());
        isNotNull(existingEntity::setOrderDate, entity.orderDate());

        return entity;
    }

    @Override
    @Transactional
    public void remove(Order entity) {

        var existingEntity = em.find(Order.class, entity.id());
        if (existingEntity == null) {
            throw new NotFoundException("Product with id: " + entity.id() + " not found");
        }
        em.remove(existingEntity);
    }
}
