package com.homework.dao;

import java.io.Serializable;
import java.util.List;

public interface EntityDao<T extends Serializable> {

    T save(T t);

    T update(T t);

    List<T> getAll();

    T getById(Long id);

    void remove(T t);

}
