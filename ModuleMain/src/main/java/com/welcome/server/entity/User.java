package com.welcome.server.entity;


import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.List;

/**
 * Created by @mistreckless on 10/3/16.!
 */
@Entity
@Table(name = "user")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nickname",length = 20, nullable = false, unique = true)
    private String nickname;

    @Column(name = "email")
    private String email;

    @Column(name = "photo_ref")
    private String photoRef;

    @Column(name = "imei",nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String imei;

    @Column(name = "token",length = 1000)
    private String token;

    @JsonManagedReference
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "rating_id",referencedColumnName = "id",unique = true,nullable = false)
    private Rating rating;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "id")
    @JsonBackReference
    private List<Post> posts;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "place_id",referencedColumnName = "id")
    private City city;

    public User(){}

    public Long getId() {
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

    @JsonIgnore
    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
