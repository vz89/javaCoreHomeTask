package com.vz89.hometask.repository;

import java.util.List;

public interface GenericRepository<T,ID> {
    T getById(ID id);
    List<T> findAll();
    T save (T t);
    T update (T t);
    void deleteById(ID id);
}
