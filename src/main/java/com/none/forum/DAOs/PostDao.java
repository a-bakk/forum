package com.none.forum.DAOs;

import com.none.forum.Entities.Post;
import com.none.forum.Entities.Thread;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostDao extends AbstractJpaDao<Post> {
    public PostDao() {this.setEntityClass(Post.class);}
    public Integer countReplies(Thread thread) {
        return entityManager.createQuery("SELECT post FROM Post post WHERE post.thread = :thread", Post.class)
                .setParameter("thread", thread).getResultList().size();
    }
    public List<Post> findByThread(Thread thread) {
        return entityManager.createQuery("SELECT post FROM Post post WHERE post.thread = :thread", Post.class)
                .setParameter("thread", thread).getResultList();
    }
}
