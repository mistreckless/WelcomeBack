package com.welcome.server.service.impl;

import com.welcome.server.bot.FirebaseManager;
import com.welcome.server.entity.Post;
import com.welcome.server.entity.User;
import com.welcome.server.entity.firebase.Author;
import com.welcome.server.entity.firebase.Rating;
import com.welcome.server.helper.UserRequest;
import com.welcome.server.repository.PostRepository;
import com.welcome.server.service.PostService;
import com.welcome.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by @mistreckless on 07.03.2017.!
 */
@Service
public class PostServiceImpl implements PostService {
    private PostRepository repository;
    private UserService userService;
    private FirebaseManager firebaseManager;

    @Autowired
    public PostServiceImpl(PostRepository repository, @Lazy UserService userService, @Lazy FirebaseManager firebaseManager) {
        this.repository = repository;
        this.userService = userService;
        this.firebaseManager = firebaseManager;
    }


    @Override
    public void savePost(com.welcome.server.entity.firebase.Post firebasePost) {
        repository.save(generatePost(firebasePost, userService.getUserById(firebasePost.getAuthor().getuId())));
    }

    @Override
    public List<com.welcome.server.entity.firebase.Post> getNowUserPosts(UserRequest userRequest) {
        return firebaseManager.getAllNowPosts().values().stream()
                .filter(post -> post.getAuthor().getuId() == userRequest.getId())
                .collect(Collectors.toList());
    }

    @Override
    public List<com.welcome.server.entity.firebase.Post> getWillcomeUserPosts(UserRequest userRequest) {
        return firebaseManager.getAllNowPosts().values().stream()
                .filter(post -> post.getWillcomes() != null && post.getWillcomes().values().stream().filter(willcome -> willcome.getAuthor().getuId() == userRequest.getId()).count() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<com.welcome.server.entity.firebase.Post> getHistoryUserPosts(UserRequest userRequest) {
        List<Post> postList = repository.getAllUserPosts(userService.getUserById(userRequest.getId()));
        List<com.welcome.server.entity.firebase.Post> frPosts = new ArrayList<>(postList.size());
        frPosts.addAll(postList.stream().map(this::generateFirebasePost).collect(Collectors.toList()));
        return frPosts;
    }

    private Post generatePost(com.welcome.server.entity.firebase.Post firebasePost, User user) {
        Post post = new Post();
        post.setKey(firebasePost.getId());
        post.setAddress(firebasePost.getAddress());
        post.setCity(user.getCity());
        post.setContentRef(firebasePost.getContentRef());
        post.setContentType(firebasePost.getContentType());
        post.setPostType(firebasePost.getPostType());
        post.setCreateTime(firebasePost.getTime());
        post.setDeleteTime(firebasePost.getDeleteTime());
        post.setDescription(firebasePost.getDescription());
        post.setLat(firebasePost.getLat());
        post.setLon(firebasePost.getLon());
        post.setTags(firebasePost.getTags());
        post.setUser(user);
        post.setDressCode(firebasePost.isDressCode());
        return post;
    }

    private com.welcome.server.entity.firebase.Post generateFirebasePost(Post post) {
        Author author = new Author();
        User user = post.getUser();
        com.welcome.server.entity.Rating rating = user.getRating();
        author.setuId(user.getId());
        author.setName(user.getNickname());
        author.setThumbRef(user.getPhotoRef());
        author.setRating(new Rating(rating.getId(), rating.getLikeCount(), rating.getWillcomeCount(), rating.getPostCount(), rating.getVippostCount(),rating.getReportCount(),rating.getAdditionalPoints()));
        com.welcome.server.entity.firebase.Post frPost = new com.welcome.server.entity.firebase.Post();
        frPost.setId(post.getKey());
        frPost.setAuthor(author);
        frPost.setTags(post.getTags());
        frPost.setCity(post.getCity().getName());
        frPost.setCountry(post.getCity().getCountry().getName());
        frPost.setContentRef(post.getContentRef());
        frPost.setAddress(post.getAddress());
        frPost.setContentType(post.getContentType());
        frPost.setPostType(post.getPostType());
        frPost.setDeleteTime(post.getDeleteTime());
        frPost.setTime(post.getCreateTime());
        frPost.setLat(post.getLat());
        frPost.setLon(post.getLon());
        return frPost;
    }
}
