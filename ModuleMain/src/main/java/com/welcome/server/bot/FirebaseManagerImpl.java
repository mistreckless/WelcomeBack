package com.welcome.server.bot;

import com.welcome.server.entity.firebase.Post;
import com.welcome.server.service.CityService;
import com.welcome.server.service.PostService;
import com.welcome.server.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by @mistreckless on 07.03.2017.!
 */
@Component
public class FirebaseManagerImpl implements FirebaseManager {
    private final PostService postService;
    private final CityService cityService;
    private final RatingService ratingService;
    private FirebaseRepository firebaseRepository;

    @Autowired
    public FirebaseManagerImpl(PostService postService, FirebaseRepository firebaseRepository,
                               @Lazy CityService cityService, @Lazy RatingService ratingService) {
        this.ratingService = ratingService;
        this.postService = postService;
        this.cityService = cityService;
        this.firebaseRepository = firebaseRepository;
        this.firebaseRepository.setPostChangedListener(postChangesListener);
    }

    @Override
    public void addNewPlace(String country, String city) {
        firebaseRepository.listenPosts(country, city);
    }

    @Override
    public Map<String, Post> getAllNowPosts() {
        return FirebaseBot.getInstance().getPosts();
    }

    private PostChangesListener postChangesListener = new PostChangesListener() {

        @Override
        public void postAdded(Post post) {
            //add to current cache
            FirebaseBot.getInstance().addPost(post);
            firebaseRepository.updateRating(ratingService.updateRating(post), post);
        }

        @Override
        public void postChanged(Post post) {
            //check updates
            Post currentPost = FirebaseBot.getInstance().getPost(post.getId());
            if (!currentPost.getLikes().equals(post.getLikes()) ||
                    !currentPost.getWillcomes().equals(post.getWillcomes()) ||
                    !currentPost.getReports().equals(post.getReports()))
                firebaseRepository.updateRating(ratingService.updateRating(post, currentPost), post);


            //check delete
            if (post.getReports() != null && post.getReports().size() * 100 / cityService.getUserCount(post.getCity()) >= 10)
                firebaseRepository.removePost(post);
            else
                FirebaseBot.getInstance().changePost(post);
        }

        @Override
        public void postMoved(Post post) {
            //no idea
        }

        @Override
        public void postRemoved(Post post) {
            //remove from current cache
            FirebaseBot.getInstance().removePost(post.getId());
            //save to archive
            postService.savePost(post);
        }
    };

}
