package com.welcome.server.bot;

import com.welcome.server.entity.firebase.Post;

import java.util.Map;

/**
 * Created by @mistreckless on 05.03.2017.!
 */
public interface FirebaseManager {

    void addNewPlace(String country, String city);

    Map<String,Post> getAllNowPosts();
}
