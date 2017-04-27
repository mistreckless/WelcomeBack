package com.welcome.server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

/**
 * Created by @mistreckless on 10/3/16.!
 */
@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName = "id",unique = true)
    private User user;

    @Column(name = "like_count",nullable = false)
    private long likeCount;

    @Column(name = "willcome_count",nullable = false)
    private long willcomeCount;

    @Column(name = "report_count",nullable = false)
    private long reportCount;

    @Column(name = "additional_points",nullable = false)
    private long additionalPoints;

    @Column(name = "post_count",nullable = false)
    private long postCount;

    @Column(name = "vippost_count",nullable = false)
    private long vippostCount;

    public Rating(){}

    public Rating(User user, long likeCount, long willcomeCount, long postCount, long vippostCount, long reportCount, long additionalPoints) {
        this.user = user;
        this.likeCount = likeCount;
        this.willcomeCount = willcomeCount;
        this.postCount = postCount;
        this.vippostCount = vippostCount;
        this.reportCount=reportCount;
        this.additionalPoints=additionalPoints;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(long likeCount) {
        this.likeCount = likeCount;
    }

    public long getWillcomeCount() {
        return willcomeCount;
    }

    public void setWillcomeCount(long willcomeCount) {
        this.willcomeCount = willcomeCount;
    }

    public long getReportCount() {
        return reportCount;
    }

    public void setReportCount(long reportCount) {
        this.reportCount = reportCount;
    }

    public long getAdditionalPoints() {
        return additionalPoints;
    }

    public void setAdditionalPoints(long additionalPoints) {
        this.additionalPoints = additionalPoints;
    }

    public long getPostCount() {
        return postCount;
    }

    public void setPostCount(long postCount) {
        this.postCount = postCount;
    }

    public long getVippostCount() {
        return vippostCount;
    }

    public void setVippostCount(long vippostCount) {
        this.vippostCount = vippostCount;
    }
}
