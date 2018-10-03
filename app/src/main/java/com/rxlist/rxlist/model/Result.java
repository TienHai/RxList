package com.rxlist.rxlist.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Result {

    @SerializedName("title")
    private String _title;

    @SerializedName("products")
    private ArrayList<Product> _productList;

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = title;
    }

    public ArrayList<Product> getProductArrayList() {
        return _productList;
    }

    public void setProductArrayList(ArrayList<Product> productArrayList) {
        _productList = productArrayList;
    }
}