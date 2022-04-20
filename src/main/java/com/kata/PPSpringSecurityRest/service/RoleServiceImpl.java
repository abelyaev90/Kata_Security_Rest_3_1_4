package com.kata.PPSpringSecurityRest.service;


import com.kata.PPSpringSecurityRest.dao.RoleDao;
import com.kata.PPSpringSecurityRest.entitys.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> getAll() {
        return roleDao.getAll();
    }

    @Override
    public Role getById(Long id) {
        return roleDao.getById(id);
    }

    @Override
    public Set<Role> getByName(String[] roleName) {
        return roleDao.getByName(roleName);
    }

    @Override
    public void addRole(Role role) {
        roleDao.addRole(role);/////////
    }

    @Override
    public void deleteById(Long id) {
        roleDao.deleteById(id);
    }

}
