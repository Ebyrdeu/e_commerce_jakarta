package dev.ebyrdeu.e_commerce_jakarta.repository.poduct;

import dev.ebyrdeu.e_commerce_jakarta.entity.Product;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.Optional;


@ApplicationScoped
public class ProductRepositoryImpl implements ProductRepository {
    @PersistenceContext(unitName = "default")
    EntityManager em;

    @Override
    public List<Product> getAll() {
        var query = "SELECT p FROM Product  p";
        return em.createQuery(query, Product.class).getResultList();
    }

    @Override
    public Optional<Product> getOne(Long id) {
        return Optional.ofNullable(em.find(Product.class, id));
    }

    @Override
    public Product create(Product entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public void update(Product entity) {
        em.merge(entity);
    }

    @Override
    public void remove(Product entity) {
        var existingEntity = em.find(Product.class, entity.id());

        if (existingEntity == null) {
            throw new NotFoundException("Product with id: " + entity.id() + " not found");
        }

        em.remove(existingEntity);
    }
}
