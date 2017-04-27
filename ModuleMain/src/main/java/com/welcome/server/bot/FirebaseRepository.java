package com.welcome.server.bot;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.welcome.server.entity.firebase.Post;
import com.welcome.server.entity.firebase.Rating;
import com.welcome.server.helper.rxfirebase.DataSnapshotMapper;
import com.welcome.server.helper.rxfirebase.RxFirebaseDatabase;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import rx.schedulers.Schedulers;

/**
 * Created by @mistreckless on 07.03.2017.!
 */
@Scope("prototype")
@Component
class FirebaseRepository {
    private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    private PostChangesListener listener;

    void setPostChangedListener(PostChangesListener listener) {
        this.listener = listener;
    }

    void listenPosts(String country, String city) {
        DatabaseReference ref = rootRef.child("posts").child(country).child(city);
        RxFirebaseDatabase.observeChildEvent(ref, DataSnapshotMapper.ofChildEvent(Post.class))
                .subscribeOn(Schedulers.io())
                .subscribe(postRxFirebaseChildEvent -> {
                    switch (postRxFirebaseChildEvent.getEventType()) {
                        case ADDED:
                            listener.postAdded(postRxFirebaseChildEvent.getValue());
                            break;
                        case CHANGED:
                            listener.postChanged(postRxFirebaseChildEvent.getValue());
                            break;
                        case MOVED:
                            listener.postMoved(postRxFirebaseChildEvent.getValue());
                            break;
                        case REMOVED:
                            listener.postRemoved(postRxFirebaseChildEvent.getValue());
                            break;
                    }
                });
    }

    void removePost(Post post) {
        rootRef.child("posts")
                .child(post.getCountry())
                .child(post.getCity())
                .child(post.getId())
                .removeValue();
    }

    void updateRating(Rating rating, Post post){
        rootRef.child("posts")
                .child(post.getCountry())
                .child(post.getCity())
                .child(post.getId())
                .child("author")
                .child("rating")
                .setValue(rating);

    }
}
