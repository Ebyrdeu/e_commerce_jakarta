package dev.ebyrdeu.e_commerce_jakarta.repository.category;

import dev.ebyrdeu.e_commerce_jakarta.entity.Category;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

import static dev.ebyrdeu.e_commerce_jakarta.utils.Utils.isNotNull;


@ApplicationScoped
public class CategoryRepositoryImpl implements CategoryRepositroy {
    @PersistenceContext(unitName = "default")
    EntityManager em;

    @Override
    public List<Category> getAll() {
        var query = "select c from  Category c";
        return em.createQuery(query, Category.class).getResultList();
    }

    @Override
    public Category getOne(Category entity) {
        return em.find(Category.class, entity.id());
    }

    @Override
    @Transactional
    public Category create(Category entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    @Transactional
    public Category update(Category entity) {
        var existingEntity = em.find(Category.class, entity.id());

        if (existingEntity == null) {
            throw new NotFoundException("Category with id: " + entity.id() + " not found");
        }

        isNotNull(existingEntity::setName, entity.name());
        isNotNull(existingEntity::setDescription, entity.description());

        em.merge(existingEntity);

        return existingEntity;
    }

    @Override
    @Transactional
    public void remove(Category entity) {
        var existingEntity = em.find(Category.class, entity.id());

        if (existingEntity == null) {
            throw new NotFoundException("Category with id: " + entity.id() + " not found");
        }

        em.remove(existingEntity);
    }
}
