package com.none.forum.Controllers;

import com.none.forum.Entities.ThreadGroup;
import com.none.forum.Services.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/thread-groups")
public class ThreadGroupRestController {

    @Autowired
    private ForumService forumService;

    // GET http://localhost:8080/api/thread-groups
    @GetMapping
    public List<ThreadGroup> findAll() {
        return this.forumService.getAllThreadGroups();
    }

    // GET http://localhost:8080/api/thread-groups/2
    @GetMapping("/{id}")
    public ThreadGroup findById(@PathVariable("id") Long id) {
        return this.forumService.findThreadGroupById(id);
    }

    // POST http://localhost:8080/api/thread-groups
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ThreadGroup create(@RequestBody ThreadGroup threadGroup) {
        return this.forumService.createThreadGroup(threadGroup);
    }

    // PUT http://localhost:8080/api/thread-groups/1
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody ThreadGroup threadGroup, @PathVariable("id") Long id) {
        this.forumService.updateThreadGroup(threadGroup, id);
    }

    // DELETE http://localhost:8080/api/thread-groups/1
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.forumService.deleteThreadGroup(id);
    }

}
