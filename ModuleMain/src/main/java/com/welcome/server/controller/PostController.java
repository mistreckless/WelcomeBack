package com.welcome.server.controller;

import com.welcome.server.entity.firebase.Post;
import com.welcome.server.helper.UserRequest;
import com.welcome.server.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by mistreckless on 13.02.2017.!
 */
@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "/getNowPosts", method = RequestMethod.POST)
    @ResponseBody
    public List<Post> getNowPosts(@RequestBody UserRequest userRequest) {
        return postService.getNowUserPosts(userRequest);
    }

    @RequestMapping(value = "/getWillcomePosts", method = RequestMethod.POST)
    @ResponseBody
    public List<Post> getWillcomePosts(@RequestBody UserRequest userRequest) {
        return postService.getWillcomeUserPosts(userRequest);
    }

    @RequestMapping(value = "/getHistoryPosts", method = RequestMethod.POST)
    @ResponseBody
    public List<Post> getHistoryPosts(@RequestBody UserRequest userRequest) {
        return postService.getHistoryUserPosts(userRequest);
    }

}
