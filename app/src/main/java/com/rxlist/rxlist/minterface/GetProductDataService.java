package com.rxlist.rxlist.minterface;

import com.rxlist.rxlist.model.ProductResult;
import com.rxlist.rxlist.model.ProductList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetProductDataService {

    @GET("bins/k07iv/")
    Call<ProductList> getProductListData();

    @GET("bins/q0oqf")
    Call<ProductResult> getProductData();
}