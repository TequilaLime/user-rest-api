package com.homework.service;

import com.homework.dao.impl.UserDaoImpl;
import com.homework.entity.User;
import com.homework.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;
    @Mock
    UserDaoImpl userDao;
    @Mock
    User user;

    @Test
    public void saveTest() {
        when(userService.register(user)).thenReturn(user);
        User savedUser = userService.register(user);
        verify(userDao, times(1)).save(user);
        assertThat(savedUser, is(equalTo(user)));
    }

    @Test
    public void updateTest() {
        Integer age = 30;
        User returnedUser = new User();
        returnedUser.setUserName("SomeUserName");
        returnedUser.setAge(age);

        when(userService.update(returnedUser)).thenReturn(returnedUser);
        User updatedUser = userService.update(returnedUser);
        verify(userDao, times(1)).update(returnedUser);

        assertEquals(updatedUser.getUserName(), "SomeUserName");
        assertEquals(updatedUser.getAge(), age);

    }

    @Test
    public void getAllTest() {
        List<User> usersList = new ArrayList();
        usersList.add(new User());
        usersList.add(new User());
        usersList.add(new User());
        when(userService.getAllRecords()).thenReturn(usersList);

        List<User> usersLisFromDao = userService.getAllRecords();
        verify(userDao, times(1)).getAll();
        assertEquals(usersLisFromDao.size(), 3);

    }

    @Test
    public void getByIdTest() {
        User user = new User();
        user.setId(1L);
        user.setUserName("TestUserName");
        when(userService.getById(1L)).thenReturn(user);

        User retrievedUser = userService.getById(1L);
        verify(userDao, times(1)).getById(1L);
        assertEquals(retrievedUser.getId(), user.getId());
        assertEquals(retrievedUser.getUserName(), user.getUserName());
    }

    @Test
    public void removeTest() {
        userService.delete(user);
        verify(userDao, times(1)).remove(user);
    }

    @Test
    public void getByFirstNameTest() {
        User userDan = new User();
        userDan.setFirstName("Daniyar");
        when(userService.getByFirstName("Daniyar")).thenReturn(Collections.singletonList(userDan));

        List<User> retrievedUser = userService.getByFirstName("Daniyar");
        verify(userDao, times(1)).getByFirstName("Daniyar");
        assertEquals(retrievedUser.size(), 1);
        assertEquals(retrievedUser.get(0).getFirstName(), "Daniyar");
    }
}
