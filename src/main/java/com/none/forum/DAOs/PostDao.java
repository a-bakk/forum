package com.none.forum.DAOs;

import com.none.forum.Entities.Post;
import com.none.forum.Entities.Thread;
import com.none.forum.Entities.User;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class PostDao extends AbstractJpaDao<Post> {
    public PostDao() {this.setEntityClass(Post.class);}
    public Integer countReplies(Thread thread) {
        return entityManager.createQuery("SELECT post FROM Post post WHERE post.thread = :thread", Post.class)
                .setParameter("thread", thread).getResultList().size();
    }
}
