package com.welcome.server.entity.firebase;

import java.io.Serializable;

/**
 * Created by @mistreckless on 06.03.2017.!
 */
public class Report implements Serializable{
    private Author author;
    private String key;

    public Report(){}

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
