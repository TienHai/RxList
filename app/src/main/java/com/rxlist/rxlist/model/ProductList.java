package com.rxlist.rxlist.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductList {

    @SerializedName("result")
    private Result _result;

    public ProductList(Result result) {
        _result = result;
    }

    public ArrayList<ProductItem> getProductArrayList() {
        return _result.getProductArrayList();
    }

    public void setNoticeArrayList(ArrayList<ProductItem> productItemArrayList) {
        _result.setProductArrayList(productItemArrayList);
    }

    private class Result {

        @SerializedName("products")
        private ArrayList<ProductItem> _productItemList;

        public Result(ArrayList<ProductItem> productItemList) {
            _productItemList = productItemList;
        }

        public ArrayList<ProductItem> getProductArrayList() {
            return _productItemList;
        }

        public void setProductArrayList(ArrayList<ProductItem> productItemArrayList) {
            _productItemList = productItemArrayList;
        }
    }
}