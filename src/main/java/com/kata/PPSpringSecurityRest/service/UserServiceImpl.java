package com.kata.PPSpringSecurityRest.service;


import com.kata.PPSpringSecurityRest.dao.UserDao;
import com.kata.PPSpringSecurityRest.entitys.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final RoleService roleService;

    private final UserDao userDao;

    public UserServiceImpl(RoleService roleService, UserDao userDao) {
        this.roleService = roleService;
        this.userDao = userDao;
    }

    @Override
    public void addUser(User user, String[] roles) {
        user.setRoles(roleService.getByName(roles));
        userDao.addUser(user);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }


    @Override
    public void removeUser(Long id) {
        userDao.removeUser(id);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }


    @Override
    public User getByName(String name) {
        return userDao.getByName(name);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

}
