package com.rxlist.rxlist.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductList {

    @SerializedName("result")
    private Result _result;

    private ArrayList<ProductItem> _productItemList;

    public ArrayList<ProductItem> getProductArrayList() {
        return _result.getProductArrayList();
    }

    public void setNoticeArrayList(ArrayList<ProductItem> productItemArrayList) {
        _result.setProductArrayList(productItemArrayList);
    }
}