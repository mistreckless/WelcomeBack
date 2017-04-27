package com.welcome.server.entity;

import com.welcome.server.util.Constance;
import com.welcome.server.util.StringListConverter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by @mistreckless on 07.03.2017.!
 */
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "post_key",nullable = false)
    private String key;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id",nullable = false)
    private User user;

    @Column(name = "create_time",nullable = false)
    private long createTime;

    @Column(name = "delete_time",nullable = false)
    private long deleteTime;

    @ManyToOne
    @JoinColumn(name = "city_id",referencedColumnName = "id", nullable = false)
    private City city;

    @Column(name = "address")
    private String address;

    @Column(name = "description")
    private String description;

    @Column(name = "dress_code")
    private boolean dressCode;

    @Column(name = "lat",  nullable = false)
    private double lat;

    @Column(name = "lon",nullable = false)
    private double lon;

    @Column(name = "post_type", nullable = false)
    private Constance.PostType postType;

    @Column(name = "content_type",nullable = false)
    private Constance.ContentType contentType;

    @Column(name = "content_ref", nullable = false)
    private String contentRef;

    @Column(name = "tags", length = 1000)
    @Convert(converter = StringListConverter.class)
    private List<String> tags;

    public Post(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(long deleteTime) {
        this.deleteTime = deleteTime;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDressCode() {
        return dressCode;
    }

    public void setDressCode(boolean dressCode) {
        this.dressCode = dressCode;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public Constance.PostType getPostType() {
        return postType;
    }

    public void setPostType(Constance.PostType postType) {
        this.postType = postType;
    }

    public Constance.ContentType getContentType() {
        return contentType;
    }

    public void setContentType(Constance.ContentType contentType) {
        this.contentType = contentType;
    }

    public String getContentRef() {
        return contentRef;
    }

    public void setContentRef(String contentRef) {
        this.contentRef = contentRef;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

}
