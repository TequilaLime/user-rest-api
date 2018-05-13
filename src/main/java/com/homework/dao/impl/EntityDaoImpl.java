package com.homework.dao.impl;

import com.homework.dao.EntityDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Repository
public class EntityDaoImpl<T extends Serializable> implements EntityDao<T> {

    @Autowired
    protected SessionFactory sessionFactory;

    protected Class<T> aClass;

    public void setClass(Class<T> aClass) {
        this.aClass = aClass;
    }

    @Override
    @Transactional
    public T save(T entity) {
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    @Override
    @Transactional
    public T update(T entity) {
        return (T) getCurrentSession().merge(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public T getById(Long id) {
        return sessionFactory.getCurrentSession().find(aClass, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> getAll() {
        return getCurrentSession().createQuery("from " + aClass.getName()).list();
    }

    @Override
    public void remove(T entity) {
        getCurrentSession().remove(entity);
    }


    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}