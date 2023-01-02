package com.none.forum.Entities;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String tag;
    @Column(nullable = false)
    private boolean admin;
    @Column(nullable = false)
    private String password;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column(length = 64)
    private String profilePicture;
}
