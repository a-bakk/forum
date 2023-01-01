package com.none.forum.Entities;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "posts")
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Date timestamp;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Long threadId;
    @Column
    private Long replyId;
}
