package com.welcome.server.entity.firebase;

import com.welcome.server.util.Constance;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by @mistreckless on 06.03.2017.!
 */
public class Post implements Serializable {
    private String id;
    private Author author;
    private long time;
    private String city;
    private String address;
    private String description;
    private boolean dressCode;
    private double lat;
    private double lon;
    private Constance.PostType postType;
    private Constance.ContentType contentType;
    private String contentRef;
    private double offset;
    private long deleteTime;
    private List<String> tags;
    private String country;
    private Map<String,Like> likes;
    private Map<String,Willcome> willcomes;
    private Map<String,Report> reports;
    private Map<String,Comment> comments;

    public Post(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
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

    public double getOffset() {
        return offset;
    }

    public void setOffset(double offset) {
        this.offset = offset;
    }

    public long getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(long deleteTime) {
        this.deleteTime = deleteTime;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Map<String, Like> getLikes() {
        return likes;
    }

    public void setLikes(Map<String, Like> likes) {
        this.likes = likes;
    }

    public Map<String, Willcome> getWillcomes() {
        return willcomes;
    }

    public void setWillcomes(Map<String, Willcome> willcomes) {
        this.willcomes = willcomes;
    }

    public Map<String, Report> getReports() {
        return reports;
    }

    public void setReports(Map<String, Report> reports) {
        this.reports = reports;
    }

    public Map<String, Comment> getComments() {
        return comments;
    }

    public void setComments(Map<String, Comment> comments) {
        this.comments = comments;
    }
}
