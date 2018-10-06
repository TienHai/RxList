package com.rxlist.rxlist.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Product {

    @SerializedName("id")
    private String _id;
    @SerializedName("clusterId")
    private String _clusterId;
    @SerializedName("summaryNewBestPrice")
    private Float _summaryNewBestPrice;
    @SerializedName("headline")
    private String _headline;
    @SerializedName("imagesUrls")
    private ArrayList<String> _imagesUrls;

    public Product(String id, String clusterId, Float summaryNewBestPrice, String headline,
                   ArrayList<String> imagesUrls) {
        _id = id;
        _clusterId = clusterId;
        _summaryNewBestPrice = summaryNewBestPrice;
        _headline = headline;
        _imagesUrls = imagesUrls;
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

    public Float getSummaryNewBestPrice() {
        return  _summaryNewBestPrice;
    }

    public void setSummaryNewBestPrice(Float value) {
        _summaryNewBestPrice = value;
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
}
