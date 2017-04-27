package com.welcome.server.service;

import com.welcome.server.entity.Rating;
import com.welcome.server.entity.firebase.Post;

/**
 * Created by @mistreckless on 12.10.2016.!
 */
public interface RatingService {

    Rating getRatingFromUserId(long userId);

    com.welcome.server.entity.firebase.Rating getRating(long id);

    com.welcome.server.entity.firebase.Rating updateRating(Post post, Post currentPost);

    com.welcome.server.entity.firebase.Rating updateRating(Post post);
}
