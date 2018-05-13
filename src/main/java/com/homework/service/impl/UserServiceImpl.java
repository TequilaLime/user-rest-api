package com.homework.service.impl;

import com.homework.dao.UserDao;
import com.homework.entity.User;
import com.homework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl extends IServiceImpl<User> implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDao getDao() {
        return userDao;
    }

    @Override
    @Transactional
    public List<User> getByFirstName(String firstName) {
        return getDao().getByFirstName(firstName);
    }
}
