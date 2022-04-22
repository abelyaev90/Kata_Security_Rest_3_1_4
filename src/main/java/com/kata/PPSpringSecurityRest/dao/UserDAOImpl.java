package com.kata.PPSpringSecurityRest.dao;

import com.kata.PPSpringSecurityRest.entitys.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addUser(User user) {
        em.persist(user);
    }


    @Override
    public void removeUser(Long id) {
        em.createQuery("DELETE FROM User u WHERE u.id = :id")
                .setParameter("id", id).executeUpdate();
    }

    @Override
    public User getUserById(Long id) {

        return em.find(User.class, id);
    }

    @Override
    public List<User> listUsers() {
        return em.createQuery("from User", User.class)
                .getResultList();
    }

    @Override
    public void updateUser(User userUpdater) {
        em.merge(userUpdater);
    }

    @Override
    public User getByName(String name) {
        TypedQuery<User> query = em
                .createQuery("select u from User u WHERE u.userName =:name", User.class);
                query.setParameter("name", name);
        return query.getSingleResult();
    }
}
