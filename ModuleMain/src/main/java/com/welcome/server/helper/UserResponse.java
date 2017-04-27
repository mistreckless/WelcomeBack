package com.welcome.server.helper;

import com.welcome.server.entity.firebase.Rating;

/**
 * Created by @mistreckless on 14.04.2017.!
 */
public class UserResponse {
    private long id;
    private String nickname;
    private String email;
    private String photoRef;
    private Rating rating;
    private String city;
    private String country;

    public UserResponse(){}

    public UserResponse(long id, String nickname, String email, String photoRef, Rating rating, String city, String country) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.photoRef = photoRef;
        this.rating = rating;
        this.city = city;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoRef() {
        return photoRef;
    }

    public void setPhotoRef(String photoRef) {
        this.photoRef = photoRef;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
