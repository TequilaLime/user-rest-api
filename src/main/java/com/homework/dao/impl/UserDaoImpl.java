package com.homework.dao.impl;

import com.homework.dao.UserDao;
import com.homework.entity.User;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDaoImpl extends EntityDaoImpl<User> implements UserDao {

    public UserDaoImpl() {
        super();
        setClass(User.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getByFirstName(String firstName) {
        Query query = getCurrentSession().createQuery("from " + aClass.getName() + " where firstName = :firstN");
        query.setParameter("firstN", firstName);
        return query.list();
    }
}
