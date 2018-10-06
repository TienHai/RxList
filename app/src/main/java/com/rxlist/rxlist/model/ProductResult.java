package com.rxlist.rxlist.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductResult {

    @SerializedName("result")
    private Product _result;

    public ProductResult(Product result) {
        _result = result;
    }

    public Product getProductData() {
        return _result;
    }
}