package com.none.forum.DAOs;

import com.none.forum.Entities.Thread;
import com.none.forum.Entities.ThreadGroup;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ThreadDao extends AbstractJpaDao<Thread> {
    public ThreadDao() {this.setEntityClass(Thread.class);}
    public List<Thread> findByThreadGroup(ThreadGroup threadGroup) {
        return entityManager.createQuery("SELECT thread FROM Thread thread WHERE thread.threadGroup = :threadGroup", Thread.class)
                .setParameter("threadGroup", threadGroup).getResultList();
    }
}
