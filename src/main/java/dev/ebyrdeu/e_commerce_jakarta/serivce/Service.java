package dev.ebyrdeu.e_commerce_jakarta.serivce;

public interface Service<T, D, D2> {

    D2 getAll();

    T getOne(long id);

    T create(D dto);

    T update(long id, D dto);

    void remove(long id);
}
