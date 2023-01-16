package com.none.forum.Controllers;

import com.none.forum.Entities.Post;
import com.none.forum.Entities.Thread;
import com.none.forum.Entities.ThreadGroup;
import com.none.forum.Models.ThreadWithStats;
import com.none.forum.Services.ForumService;
import com.none.forum.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ForumController {

    private UserService userService;
    private ForumService forumService;

    @Autowired
    ForumController(UserService userService, ForumService forumService) {
        this.userService = userService;
        this.forumService = forumService;
    }

    @GetMapping("/index")
    public String listMainPage(Model model) {
        Map<ThreadGroup, List<ThreadWithStats>> threadGroupList = new HashMap<>();
        this.forumService.getAllThreadGroups().forEach(threadGroup -> {
            List<ThreadWithStats> threadsWithStats = new ArrayList<>();
            this.forumService.listThreadsByThreadGroup(threadGroup).forEach(thread -> {
                threadsWithStats.add(new ThreadWithStats(thread.getId(), thread.getName(), thread.getThreadGroup(), thread.getHighlighted(),
                        this.forumService.countReplies(thread)));
            });
            threadGroupList.put(threadGroup, threadsWithStats);
        });
        model.addAttribute("threadGroups", threadGroupList);
        model.addAttribute("active", "index");
        return "index";
    }

    @GetMapping("/thread/{id}")
    public String listPosts(Model model, @PathVariable(value = "id") Long id) {
        Thread thread = this.forumService.findThreadById(id);
        List<Post> posts = this.forumService.listByThread(thread);
        model.addAttribute("posts", posts);
        model.addAttribute("thread", thread);
        model.addAttribute("active", "thread");
        return "thread";
    }

}
