package com.welcome.server.service;

import com.welcome.server.entity.firebase.Post;
import com.welcome.server.helper.UserRequest;

import java.util.List;

/**
 * Created by @mistreckless on 07.03.2017.!
 */
public interface PostService {
    void savePost(Post firebasePost);

    List<Post> getNowUserPosts(UserRequest userRequest);

    List<Post> getWillcomeUserPosts(UserRequest userRequest);

    List<Post> getHistoryUserPosts(UserRequest userRequest);
}
