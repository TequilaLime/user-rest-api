package com.homework.dao;

import com.homework.entity.User;

import java.util.List;

public interface UserDao extends EntityDao<User> {

    List<User> getByFirstName(String userName);
}
