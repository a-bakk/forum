package com.none.forum.Services;

import com.none.forum.DAOs.PostDao;
import com.none.forum.DAOs.ThreadDao;
import com.none.forum.DAOs.ThreadGroupDao;
import com.none.forum.Entities.Post;
import com.none.forum.Entities.Thread;
import com.none.forum.Entities.ThreadGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForumService {
    private ThreadDao threadDao;
    private ThreadGroupDao threadGroupDao;
    private PostDao postDao;
    @Autowired
    public ForumService(ThreadDao threadDao, ThreadGroupDao threadGroupDao, PostDao postDao) {
        this.threadDao = threadDao;
        this.threadGroupDao = threadGroupDao;
        this.postDao = postDao;
    }
    public List<ThreadGroup> getAllThreadGroups() {
        return this.threadGroupDao.findAll();
    }
    public List<Thread> listThreadsByThreadGroup(ThreadGroup threadGroup) {
        return this.threadDao.findByThreadGroup(threadGroup);
    }
    public Integer countReplies(Thread thread) {
        return this.postDao.countReplies(thread);
    }
}
