package dev.ebyrdeu.e_commerce_jakarta.repository.category;

import dev.ebyrdeu.e_commerce_jakarta.entity.Category;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;


@ApplicationScoped
public class CategoryRepositoryImpl implements CategoryRepository {
    @PersistenceContext(unitName = "default")
    EntityManager em;

    @Override
    public List<Category> getAll() {
        var query = "SELECT c FROM Category c";
        return em.createQuery(query, Category.class).getResultList();
    }

    @Override
    public Optional<Category> getOne(Long id) {
        return Optional.ofNullable(em.find(Category.class, id));
    }

    @Override
    public Category create(Category entity) {
        em.persist(entity);

        return entity;
    }

    @Override
    public void update(Category entity) {
        em.merge(entity);
    }

    @Override
    public void remove(Category entity) {
        em.remove(entity);
    }
}
