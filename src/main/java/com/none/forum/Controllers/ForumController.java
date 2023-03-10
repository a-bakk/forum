package com.none.forum.Controllers;

import com.none.forum.Entities.Post;
import com.none.forum.Entities.Thread;
import com.none.forum.Entities.ThreadGroup;
import com.none.forum.Models.ThreadWithStats;
import com.none.forum.Models.UserDetails;
import com.none.forum.Services.ForumService;
import com.none.forum.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

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
        model.addAttribute("newThread", new Thread());
        model.addAttribute("authenticated", AuthController.isAuthenticated());
        model.addAttribute("active", "index");
        return "index";
    }

    @GetMapping("/thread/{id}")
    public String listPosts(Model model, @PathVariable(value = "id") Long id, @AuthenticationPrincipal UserDetails loggedUser) {
        Thread thread = this.forumService.findThreadById(id);
        List<Post> posts = this.forumService.listByThread(thread);
        model.addAttribute("posts", posts);
        model.addAttribute("thread", thread);
        model.addAttribute("newPost", new Post());
        model.addAttribute("uid", loggedUser == null ? null : loggedUser.getId());
        model.addAttribute("authenticated", AuthController.isAuthenticated());
        model.addAttribute("active", "thread");
        return "thread";
    }

    @PostMapping("/create_new_thread")
    public String newThread(@ModelAttribute Thread newThread, @RequestParam(value = "threadGroupId") Long threadGroupid,
                            RedirectAttributes redirectAttributes) {
        newThread.setHighlighted(false);
        newThread.setThreadGroup(this.forumService.findThreadGroupById(threadGroupid));
        this.forumService.createThread(newThread);
        redirectAttributes.addFlashAttribute("success", "Thread has been created successfully!");
        return "redirect:/index";
    }

    @PostMapping("/reply")
    public String replyToPost(@ModelAttribute Post newPost, @RequestParam(value = "userId") Long userId,
                              @RequestParam(value = "threadId") Long threadId, RedirectAttributes redirectAttributes) {
        newPost.setUser(this.userService.getUserById(userId));
        newPost.setThread(this.forumService.findThreadById(threadId));
        newPost.setTimestamp(new Date(System.currentTimeMillis()));
        this.forumService.createReply(newPost);
        redirectAttributes.addFlashAttribute("success", "Successfully replied to this thread.");
        return "redirect:/thread/" + threadId;
    }

}
