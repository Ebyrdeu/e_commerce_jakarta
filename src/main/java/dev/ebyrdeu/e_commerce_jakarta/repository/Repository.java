package dev.ebyrdeu.e_commerce_jakarta.repository;

import java.util.List;
import java.util.Optional;


public interface Repository<T> {

    List<T> getAll();

    Optional<T> getOne(Long id);

    T create(T entity);

    void update(T entity);

    void remove(T entity);


}
