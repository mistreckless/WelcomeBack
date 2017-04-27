package com.welcome.server.bot;

import com.welcome.server.entity.firebase.Post;

/**
 * Created by @mistreckless on 07.03.2017.!
 */
public interface PostChangesListener {
    void postAdded(Post post);

    void postChanged(Post post);

    void postMoved(Post post);

    void postRemoved(Post post);
}
