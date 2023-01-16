package com.none.forum.DAOs;

import com.none.forum.Entities.ThreadGroup;
import org.springframework.stereotype.Repository;

@Repository
public class ThreadGroupDao extends AbstractJpaDao<ThreadGroup> {
    public ThreadGroupDao() {this.setEntityClass(ThreadGroup.class);}
}
