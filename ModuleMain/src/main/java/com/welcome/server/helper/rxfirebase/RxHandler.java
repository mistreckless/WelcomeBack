package com.welcome.server.helper.rxfirebase;


import com.google.firebase.internal.NonNull;
import com.google.firebase.tasks.OnCompleteListener;
import com.google.firebase.tasks.OnFailureListener;
import com.google.firebase.tasks.OnSuccessListener;
import com.google.firebase.tasks.Task;
import rx.Subscriber;

/**
 * Created by @mistreckless on 24/05/2016.!
 */
public class RxHandler<T> implements OnSuccessListener<T>, OnFailureListener, OnCompleteListener<T> {

    private final Subscriber<? super T> subscriber;

    private RxHandler(Subscriber<? super T> observer) {
        this.subscriber = observer;
    }

    public static <T> void assignOnTask(Subscriber<? super T> observer, Task<T> task) {
        RxHandler handler = new RxHandler(observer);
        task.addOnSuccessListener(handler);
        task.addOnFailureListener(handler);
        try {
            task.addOnCompleteListener(handler);
        } catch (Throwable t) {
            // ignore
        }
    }

    @Override
    public void onSuccess(T res) {
        if (!subscriber.isUnsubscribed()) {
            subscriber.onNext(res);
        }
    }

    @Override
    public void onComplete(@NonNull Task<T> task) {
        if (!subscriber.isUnsubscribed()) {
            subscriber.onCompleted();
        }
    }

    @Override
    public void onFailure(@NonNull Exception e) {
        if (!subscriber.isUnsubscribed()) {
            subscriber.onError(e);
        }
    }
}
