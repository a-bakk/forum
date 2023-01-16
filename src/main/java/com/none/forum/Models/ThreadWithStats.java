package com.none.forum.Models;

import com.none.forum.Entities.Post;
import com.none.forum.Entities.ThreadGroup;
import lombok.Data;

@Data
public class ThreadWithStats {
    private Long id;
    private String name;
    private ThreadGroup threadGroup;
    private Boolean highlighted;
    private Integer replies;
    public ThreadWithStats(Long id, String name, ThreadGroup threadGroup, Boolean highlighted, Integer replies) {
        this.id = id;
        this.name = name;
        this.threadGroup = threadGroup;
        this.highlighted = highlighted;
        this.replies = replies;
    }
    public ThreadWithStats() {}
}
