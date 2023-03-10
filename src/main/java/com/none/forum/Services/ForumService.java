package com.none.forum.Services;

import com.none.forum.DAOs.PostDao;
import com.none.forum.DAOs.ThreadDao;
import com.none.forum.DAOs.ThreadGroupDao;
import com.none.forum.Entities.Post;
import com.none.forum.Entities.Thread;
import com.none.forum.Entities.ThreadGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<Post> listByThread(Thread thread) {
        return this.postDao.findByThread(thread);
    }
    public Thread findThreadById(Long id) {
        return this.threadDao.find(id);
    }
    public List<Thread> getAllThreads() {
        return this.threadDao.findAll();
    }
    public ThreadGroup findThreadGroupById(Long id) {
        return this.threadGroupDao.find(id);
    }
    public void createThread(Thread thread) {
        this.threadDao.create(thread);
    }
    public void createReply(Post post) {
        this.postDao.create(post);
    }

    public ThreadGroup createThreadGroup(ThreadGroup threadGroup) {
        return threadGroupDao.create(threadGroup);
    }

    public ThreadGroup updateThreadGroup(ThreadGroup threadGroup, Long id) {
        ThreadGroup curr = threadGroupDao.find(id);
        curr.setName(threadGroup.getName());
        return threadGroupDao.update(curr);
    }

    public void deleteThreadGroup(Long id) {
        this.threadGroupDao.delete(id);
    }
}
