package com.kata.PPSpringSecurityRest.dao;

import com.kata.PPSpringSecurityRest.entitys.User;
import java.util.List;

public interface UserDao {
    void addUser(User user);
    void removeUser(Long id);
    User getUserById(Long id);
    List<User> listUsers();
    void updateUser(User user);
    User getByName(String name);
}
