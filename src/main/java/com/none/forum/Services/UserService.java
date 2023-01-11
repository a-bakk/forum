package com.none.forum.Services;

import com.none.forum.DAOs.UserDao;
import com.none.forum.Entities.User;
import com.none.forum.Models.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDao dao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = dao.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with the following email address: " + username);
        }
        return new UserDetails(user);
    }

    public UserDetails getUserDetails(Long id) {
        return new UserDetails(dao.find(id));
    }

    public void deleteUser(UserDetails currentUser) {
        this.dao.delete(currentUser.getId());
    }

}
