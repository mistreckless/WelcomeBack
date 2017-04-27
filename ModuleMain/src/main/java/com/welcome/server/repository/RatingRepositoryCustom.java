package com.welcome.server.repository;

import com.welcome.server.entity.Rating;

/**
 * Created by @mistreckless on 15.01.2017.!
 */
public interface RatingRepositoryCustom {
    Rating findRatingByUserId(long userId);
}
