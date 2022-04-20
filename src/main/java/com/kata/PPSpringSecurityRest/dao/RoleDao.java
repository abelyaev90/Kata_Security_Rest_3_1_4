package com.kata.PPSpringSecurityRest.dao;

import com.kata.PPSpringSecurityRest.entitys.Role;
import java.util.List;
import java.util.Set;

//для чего нужна Роль? Какой есть функционал? При добавлении пользователя - добавляется роль.
// При проверки прав доступа достается список ролей, нужно будет находить по id роль и сравнивать ее с id пользователя.

public interface RoleDao {
    List<Role> getAll();
    Role getById(Long id);
    Set<Role> getByName(String[] roleName);
    void addRole(Role role);
    void deleteById(Long id);
    Role findByName(String name);
}

