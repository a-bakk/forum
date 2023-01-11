package com.none.forum.Services;

import com.none.forum.DAOs.UserDao;
import com.none.forum.Entities.User;
import com.none.forum.Models.UserCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserDao dao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void register(UserCreate newUser) {
        User user = new User();
        user.setEmail(newUser.getEmail());
        user.setTag(newUser.getTag());
        user.setAdmin(false);
        user.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        this.dao.create(user);
    }

    public User findUserByEmail(String email) {
        return this.dao.findByEmail(email);
    }

}
