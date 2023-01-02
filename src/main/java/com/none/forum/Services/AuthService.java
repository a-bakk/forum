package com.none.forum.Services;

import com.none.forum.DAOs.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserDao dao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //public User register(UserCreate User) {}

}
