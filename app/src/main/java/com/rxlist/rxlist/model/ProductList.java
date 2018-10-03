package com.rxlist.rxlist.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductList {

    @SerializedName("result")
    private Result _result;

    private ArrayList<Product> _productList;

    public ArrayList<Product> getProductArrayList() {
        return _result.getProductArrayList();
    }

    public void setNoticeArrayList(ArrayList<Product> productArrayList) {
        _result.setProductArrayList(productArrayList);
    }
}