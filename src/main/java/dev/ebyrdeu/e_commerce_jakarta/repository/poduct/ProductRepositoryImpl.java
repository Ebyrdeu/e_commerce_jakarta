package dev.ebyrdeu.e_commerce_jakarta.repository.poduct;

import dev.ebyrdeu.e_commerce_jakarta.entity.Product;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

import static dev.ebyrdeu.e_commerce_jakarta.utils.Utils.isNotNull;


@ApplicationScoped
public class ProductRepositoryImpl implements ProductRepository {
    @PersistenceContext(unitName = "default")
    EntityManager em;

    @Override
    public List<Product> getAll() {
        var query = "select p from Product  p";
        return em.createQuery(query, Product.class).getResultList();
    }

    @Override
    public Product getOne(Product entity) {
        return em.find(Product.class, entity.id());
    }

    @Override
    @Transactional
    public Product create(Product entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    @Transactional
    public Product update(Product entity) {
        var existingEntity = em.find(Product.class, entity.id());

        if (existingEntity == null) {
            throw new NotFoundException("Product with id: " + entity.id() + " not found");
        }

        isNotNull(existingEntity::setName, entity.name());
        isNotNull(existingEntity::setDescription, entity.description());
        isNotNull(existingEntity::setPrice, entity.price());
        isNotNull(existingEntity::setStock, entity.stock());

        return entity;
    }

    @Override
    @Transactional
    public void remove(Product entity) {
        var existingEntity = em.find(Product.class, entity.id());

        if (existingEntity == null) {
            throw new NotFoundException("Product with id: " + entity.id() + " not found");
        }

        em.remove(existingEntity);
    }
}
