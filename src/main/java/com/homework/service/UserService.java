package com.homework.service;

import com.homework.entity.User;

import java.util.List;

public interface UserService extends IService<User> {

    List<User> getByFirstName(String firstName);
}
