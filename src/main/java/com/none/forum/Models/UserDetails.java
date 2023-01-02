package com.none.forum.Models;

import com.none.forum.Entities.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private final String ROLE_PREFIX = "ROLE_";
    private Long id;
    private String email;
    private String tag;
    private Boolean admin;
    private String firstName;
    private String lastName;
    private String password;
    private String profilePicture;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + "USER"));
        if (this.admin) {
            authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + "ADMIN"));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserDetails(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.tag = user.getTag();
        this.admin = user.isAdmin();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.password = user.getPassword();
        this.profilePicture = user.getProfilePicture();
    }

    public UserDetails() {}

}
