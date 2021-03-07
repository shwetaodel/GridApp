package com.example.gridapp;

public class ImageModel {

    String author;
    String post_url;

    public ImageModel(String author, String post_url) {
        this.author = author;
        this.post_url = post_url;
    }

    public String getAuthor() {
        return author;
    }

    public String getPost_url() {
        return post_url;
    }
}
