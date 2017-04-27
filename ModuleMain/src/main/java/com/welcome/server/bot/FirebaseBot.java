package com.welcome.server.bot;

import com.google.firebase.database.FirebaseDatabase;
import com.welcome.server.entity.firebase.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by @mistreckless on 07.03.2017.!
 */
class FirebaseBot {
    private static FirebaseBot ourInstance = new FirebaseBot();
    private Logger logger = LoggerFactory.getLogger("application");
    private Map<String, Post> posts = new HashMap<>();
    private boolean condition = true;

    public static FirebaseBot getInstance() {
        return ourInstance;
    }

    private FirebaseBot() {
    }

    void stop() {
        condition = false;
    }

    Map<String, Post> getPosts() {
        return posts;
    }

    void changePost(Post post){
        posts.put(post.getId(),post);
    }

    void addPost(Post post) {
        posts.put(post.getId(), post);
    }

    void removePost(String key) {
        posts.remove(key);
    }

    Post getPost(String key){
        return posts.get(key);
    }

    public void start() {
        new Thread(() -> {
            while (condition) {
                try {
                    Thread.sleep(60000);
                    logger.debug("Bot iteration with post size " + posts.size());
                    for (Post post :
                            posts.values()) {
                        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
                        long currentTime=System.currentTimeMillis();
                        Date date=new Date(post.getDeleteTime());
                        logger.debug("default gmt time " + new Date(currentTime) +
                                "  post time " + date);
                        if (currentTime >= date.getTime())
                            FirebaseDatabase.getInstance().getReference()
                                    .child("posts")
                                    .child(post.getCountry())
                                    .child(post.getCity())
                                    .child(post.getId())
                                    .removeValue();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }
}
