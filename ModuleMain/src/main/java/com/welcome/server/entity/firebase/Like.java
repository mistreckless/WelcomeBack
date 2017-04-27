package com.welcome.server.entity.firebase;

/**
 * Created by @mistreckless on 06.03.2017.!
 */
public class Like {
    private Author author;
    private String key;

    public Like(){}

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
