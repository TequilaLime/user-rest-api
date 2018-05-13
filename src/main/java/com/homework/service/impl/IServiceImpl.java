package com.homework.service.impl;

import com.homework.dao.EntityDao;
import com.homework.dao.impl.EntityDaoImpl;
import com.homework.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional
public class IServiceImpl<T extends Serializable> implements IService<T> {

    @Autowired
    EntityDaoImpl<T> entityDaoImpl;

    @Override
    @Transactional
    public T getById(Long id) {
        return getDao().getById(id);
    }

    @Override
    @Transactional
    public List<T> getAllRecords() {
        return getDao().getAll();
    }

    @Override
    @Transactional
    public T register(T record) {
        return getDao().save(record);
    }

    @Override
    @Transactional
    public T update(T record) {
        return getDao().update(record);
    }

    @Override
    @Transactional
    public void delete(T record) {
        getDao().remove(record);
    }

    @Override
    public EntityDao<T> getDao() {
        return entityDaoImpl;
    }

}
