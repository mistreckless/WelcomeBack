package com.welcome.server.service.impl;

import com.welcome.server.entity.Rating;
import com.welcome.server.entity.firebase.Post;
import com.welcome.server.repository.RatingRepository;
import com.welcome.server.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by @mistreckless on 12.10.2016.!
 */
@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository repository;

    @Autowired
    public RatingServiceImpl(RatingRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Rating getRatingFromUserId(long userId) {
        return repository.findRatingByUserId(userId);
    }

    @Override
    @Transactional
    public com.welcome.server.entity.firebase.Rating getRating(long id) {
        return generateRatingResponse(repository.findOne(id));
    }

    @Override
    public com.welcome.server.entity.firebase.Rating updateRating(Post post, Post currentPost) {
        Rating rating=repository.findOne(post.getAuthor().getRating().getId());
        if (!post.getLikes().equals(currentPost.getLikes())){
            if (currentPost.getLikes()==null && post.getLikes()!=null)
                rating.setLikeCount(rating.getLikeCount()+1);
            else if (currentPost.getLikes()!=null && post.getLikes()==null)
                rating.setLikeCount(rating.getLikeCount()-1);
            else if (currentPost.getLikes()!=null && post.getLikes()!=null)
                rating.setLikeCount(rating.getLikeCount()+(post.getLikes().size()-currentPost.getLikes().size()));
        }
        if (!post.getWillcomes().equals(currentPost.getWillcomes())){
            if (currentPost.getWillcomes()==null && post.getWillcomes()!=null)
                rating.setWillcomeCount(rating.getWillcomeCount()+1);
            else if (currentPost.getWillcomes()!=null && post.getWillcomes()==null)
                rating.setWillcomeCount(rating.getWillcomeCount()-1);
            else if (currentPost.getWillcomes()!=null && post.getWillcomes()!=null)
                rating.setWillcomeCount(rating.getWillcomeCount()+(post.getWillcomes().size()-currentPost.getWillcomes().size()));
        }
        if (!post.getReports().equals(currentPost.getReports())){
            if (currentPost.getReports()==null &&  post.getReports()!=null)
                rating.setReportCount(rating.getReportCount()+1);
            else if (currentPost.getReports()!=null && post.getReports()==null)
                rating.setReportCount(rating.getReportCount()-1);
            else if (currentPost.getReports()!=null && post.getReports()!=null)
                rating.setReportCount(rating.getReportCount()+(post.getReports().size()-currentPost.getReports().size()));
        }
        return generateRatingResponse(repository.saveAndFlush(rating));
    }

    @Override
    public com.welcome.server.entity.firebase.Rating updateRating(Post post) {
        Rating rating=repository.findOne(post.getAuthor().getRating().getId());
        rating.setPostCount(rating.getPostCount()+1);
       return generateRatingResponse(repository.saveAndFlush(rating));
    }

    private com.welcome.server.entity.firebase.Rating generateRatingResponse(Rating rating) {
        return new com.welcome.server.entity.firebase.Rating(rating.getId(),rating.getLikeCount(),rating.getWillcomeCount(),
                rating.getPostCount(),rating.getVippostCount(),rating.getReportCount(),rating.getAdditionalPoints());
    }

}
