package com.kata.PPSpringSecurityRest.service;

import com.kata.PPSpringSecurityRest.entitys.User;
import java.util.List;

public interface UserService {
    void addUser(User user, String[] roles);
    void addUser(User user);
    void removeUser(Long id);
    User getUserById(Long id);
    List<User> listUsers();
    User getByName(String name);
    void updateUser(User user);
}
