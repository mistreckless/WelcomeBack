package com.welcome.server.entity.firebase;

import java.io.Serializable;

/**
 * Created by @mistreckless on 06.03.2017.!
 */
public class Author implements Serializable{
    private long uId;
    private String name;
    private Rating rating;
    private String thumbRef;

    public Author(){}

    public long getuId() {
        return uId;
    }

    public void setuId(long uId) {
        this.uId = uId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getThumbRef() {
        return thumbRef;
    }

    public void setThumbRef(String thumbRef) {
        this.thumbRef = thumbRef;
    }
}
