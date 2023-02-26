package com.none.forum.DAOs;

import com.none.forum.Entities.User;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao extends AbstractJpaDao<User> {

    List<User> db = new ArrayList<>();

    public UserDao() {
        this.setEntityClass(User.class);
    }

    public User findByEmail(String email) {
        User result;
        try {
            result = entityManager.createQuery("SELECT u from User u WHERE u.email = :email", User.class).setParameter("email", email).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return result;
    }

}
