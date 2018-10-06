package com.rxlist.rxlist.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Product {

    @SerializedName("id")
    private String _id;
    @SerializedName("clusterId")
    private String _clusterId;
    @SerializedName("newBestPrice")
    private Float _newBestPrice;
    @SerializedName("headline")
    private String _headline;
    @SerializedName("imagesUrls")
    private ArrayList<String> _imagesUrls;
    @SerializedName("reviews")
    private ArrayList<Review> _reviews;

    public Product(String id, String clusterId, Float newBestPrice, String headline,
                   ArrayList<String> imagesUrls, ArrayList<Review> reviews) {
        _id = id;
        _clusterId = clusterId;
        _newBestPrice = newBestPrice;
        _headline = headline;
        _imagesUrls = imagesUrls;
        _reviews = reviews;
    }

    public String getId() {
        return _id;
    }

    public void setId(String value) {
        _id = value;
    }

    public String getClusterId() {
        return _clusterId;
    }

    public void setClusterId(String value) {
        _clusterId = value;
    }

    public Float getNewBestPrice() {
        return _newBestPrice;
    }

    public void setNewBestPrice(Float value) {
        _newBestPrice = value;
    }

    public String getHeadline() {
        return _headline;
    }

    public void setHeadline(String value) {
        _headline = value;
    }

    public ArrayList<String> getImageUrls() {
        return _imagesUrls;
    }

    public void setImageUrls(ArrayList<String> imageUrls) {
        _imagesUrls = imageUrls;
    }

    public ArrayList<Review> getReviewList() {
        return _reviews;
    }

    public void setReviewList(ArrayList<Review> reviews) {
        _reviews = reviews;
    }
}
