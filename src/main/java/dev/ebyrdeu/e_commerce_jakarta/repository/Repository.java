package dev.ebyrdeu.e_commerce_jakarta.repository;

import java.util.List;


public interface Repository<T> {

    List<T> getAll();

    T getOne(T entity);

    T create(T entity);

    T update(T entity);

    void remove(T entity);


}
