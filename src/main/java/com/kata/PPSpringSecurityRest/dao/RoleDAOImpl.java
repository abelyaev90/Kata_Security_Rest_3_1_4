package com.kata.PPSpringSecurityRest.dao;

import com.kata.PPSpringSecurityRest.entitys.Role;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleDAOImpl implements RoleDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Role> getAll() {
        return em.createQuery("select r from Role r", Role.class).
                getResultList();
    }

    @Override
    public Role getById(Long id) {
        return em.createQuery("select r from Role r WHERE r.id = :id", Role.class).
                setParameter("id", id).getSingleResult();
    }

    @Override
    public Set<Role> getByName(String [] roleName) {
        return em.createQuery("select r from Role r WHERE r.roleName in (:name)", Role.class).
                setParameter("name", Arrays.asList(roleName)).getResultList().stream().collect(Collectors.toSet());
    }

    @Override
    public void addRole(Role role) {
        em.merge(role);
    }

    @Override
    public void deleteById(Long id) {
        Role role = em.find(Role.class, id);
        if (role != null) {
            em.remove(role);
        }
    }

    @Override
    public Role findByName(String name) {
        return null;
    }
}
