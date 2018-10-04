package com.rxlist.rxlist.model;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("id")
    private String _id;
    @SerializedName("clusterId")
    private String _clusterId;
    @SerializedName("summaryNewBestPrice")
    private Float _summaryNewBestPrice;
    @SerializedName("headline")
    private String _headline;

    public Product(String id, String clusterId, Float summaryNewBestPrice, String headline) {
        _id = id;
        _clusterId = clusterId;
        _summaryNewBestPrice = summaryNewBestPrice;
        _headline = headline;
    }

    public String getId() {
        return _id;
    }

    public String getClusterId() {
        return _clusterId;
    }

    public Float getSummaryNewBestPrice() {
        return  _summaryNewBestPrice;
    }

    public String getHeadline() {
        return _headline;
    }
}
