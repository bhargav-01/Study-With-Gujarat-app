package com.example.myapplication3;

public class Youtube_viedo {
    private String id;
    private  String title;
    private  String thumbnail;

    public Youtube_viedo(String title, String thumbnails, String id) {
        this.id=id;
        this.title=title;
        this.thumbnail=thumbnails;

    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
