package com.none.forum.Models;

import lombok.Data;

@Data
public class UserCreate {
    private String email;
    private String tag;
    private String firstName;
    private String lastName;
    private String password;
    private String passwordCheck;
}
