package com.homework.service;

import com.homework.dao.EntityDao;

import java.io.Serializable;
import java.util.List;

public interface IService<T extends Serializable> {

    T getById(Long id);

    List<T> getAllRecords();

    T register(T entity);

    T update(T entity);

    void delete(T entity);

    EntityDao<T> getDao();
}