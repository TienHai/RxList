package com.rxlist.rxlist.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Result {

    @SerializedName("title")
    private String _title;

    @SerializedName("products")
    private ArrayList<ProductItem> _productItemList;

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = title;
    }

    public ArrayList<ProductItem> getProductArrayList() {
        return _productItemList;
    }

    public void setProductArrayList(ArrayList<ProductItem> productItemArrayList) {
        _productItemList = productItemArrayList;
    }
}