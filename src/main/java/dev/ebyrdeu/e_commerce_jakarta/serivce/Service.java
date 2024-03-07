package dev.ebyrdeu.e_commerce_jakarta.serivce;

import java.util.List;

public interface Service<T, D> {

    List<D> getAll();

    T getOne(long id);

    T create(D dto);

    void update(long id, D dto);

    void remove(long id);
}
