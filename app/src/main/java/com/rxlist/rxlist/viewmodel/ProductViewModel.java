package com.rxlist.rxlist.viewmodel;

import android.content.Context;
import android.widget.Toast;

import com.rxlist.rxlist.binding.IBooleanObservable;
import com.rxlist.rxlist.binding.IEvent;
import com.rxlist.rxlist.minterface.GetProductDataService;
import com.rxlist.rxlist.model.Product;
import com.rxlist.rxlist.model.ProductCall;
import com.rxlist.rxlist.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductViewModel {

    private Context _context;
    private Product _model;
    private boolean _loadingProductVisibility;
    private  IEvent _loadingProductEvent;

    public ProductViewModel(Context context) {
        _context = context;
        _model = null;
        _loadingProductEvent = null;
        _loadingProductVisibility = true;

        getProduct();
    }

    public String headline() {
        if (_model == null) {
            return "";
        }

        return _model.getHeadline();
    }

    public IBooleanObservable isLoadingProductVisible(){
        return new IBooleanObservable() {
            @Override
            public boolean value() {
                return _loadingProductVisibility;
            }

            @Override
            public IEvent changed() {
                _loadingProductEvent = ViewModelUtils.newEvent();

                return _loadingProductEvent;
            }
        };
    }

    private void getProduct() {
        GetProductDataService service = RetrofitInstance.getRetrofitInstance().create(GetProductDataService.class);
        Call<ProductCall> call = service.getProductData();
        call.enqueue(new Callback<ProductCall>() {
            @Override
            public void onResponse(Call<ProductCall> call, Response<ProductCall> response) {
                _model = response.body().getProductData();
                _loadingProductVisibility = false;
                _loadingProductEvent.changed();
            }

            @Override
            public void onFailure(Call<ProductCall> call, Throwable t) {
                Toast.makeText(_context, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}