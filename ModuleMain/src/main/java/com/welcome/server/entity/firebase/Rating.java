package com.welcome.server.entity.firebase;

/**
 * Created by @mistreckless on 06.03.2017.!
 */
public class Rating {
    private long id;
    private long likeCount;
    private long willcomeCount;
    private long postCount;
    private long vippostCount;
    private long reportCount;
    private long additionalPoints;

    public Rating(){}

    public Rating(long id, long likeCount, long willcomeCount, long postCount, long vippostCount,
                  long reportCount, long additionalPoints) {
        this.id = id;
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
}
