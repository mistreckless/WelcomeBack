package com.welcome.server.repository;

import com.welcome.server.entity.Post;
import com.welcome.server.entity.User;

import java.util.List;

/**
 * Created by @mistreckless on 14.04.2017.!
 */
public interface PostRepositoryCustom {
    List<Post> getAllUserPosts(User user);
}
