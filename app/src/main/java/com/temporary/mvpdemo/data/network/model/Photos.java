package com.temporary.mvpdemo.data.network.model;

import com.google.gson.annotations.SerializedName;

public class Photos {
    @SerializedName("width")
    private int width;

    @SerializedName("height")
    private int height;

    @SerializedName("color")
    private String color;

    @SerializedName("alt_description")
    private String alt_description;

    @SerializedName("urls")
    private Urls urls;

    @SerializedName("likes")
    private int likes;

    public Photos(int width, int height, String color, String alt_description, Urls urls, int likes) {
        this.width = width;
        this.height = height;
        this.color = color;
        this.alt_description = alt_description;
        this.urls = urls;
        this.likes = likes;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAlt_description() {
        return alt_description;
    }

    public void setAlt_description(String alt_description) {
        this.alt_description = alt_description;
    }

    public Urls getUrls() {
        return urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
