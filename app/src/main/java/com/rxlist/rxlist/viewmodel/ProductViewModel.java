package com.rxlist.rxlist.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.rxlist.rxlist.binding.IBooleanObservable;
import com.rxlist.rxlist.binding.IEvent;
import com.rxlist.rxlist.binding.IStringObservable;
import com.rxlist.rxlist.minterface.GetProductDataService;
import com.rxlist.rxlist.model.Product;
import com.rxlist.rxlist.model.ProductResult;
import com.rxlist.rxlist.network.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductViewModel {

    private Context _context;
    private Product _model;
    private boolean _loadingProductVisibility;
    private IEvent _loadingProductEvent;
    private IEvent _modelHeadlineEvent;
    private IEvent _modelImageUrlEvent;

    public ProductViewModel(Context context) {
        _context = context;
        _model = null;
        _loadingProductEvent = null;
        _loadingProductVisibility = true;
        _modelHeadlineEvent = null;
        _modelImageUrlEvent = null;

        getProduct();
    }

    public IStringObservable headline(){
        return new IStringObservable() {
            @Override
            public String value() {
                return _model.getHeadline();
            }

            @Override
            public IEvent changed() {
                _modelHeadlineEvent = ViewModelUtils.newEvent();

                return _modelHeadlineEvent;
            }
        };
    }

    public IStringObservable imageUrl() {
        return new IStringObservable() {
            @Override
            public String value() {
                ArrayList<String> urls = _model.getImageUrls();
                if (urls.isEmpty())
                    return null;

                return urls.get(0);
            }

            @Override
            public IEvent changed() {
                _modelImageUrlEvent = ViewModelUtils.newEvent();

                return _modelImageUrlEvent;
            }
        };
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
        Call<ProductResult> call = service.getProductData();
        call.enqueue(new Callback<ProductResult>() {
            @Override
            public void onResponse(Call<ProductResult> call, Response<ProductResult> response) {
                _model = response.body().getProductData();
                _loadingProductVisibility = false;
                _loadingProductEvent.changed();
                _modelHeadlineEvent.changed();
                _modelImageUrlEvent.changed();
            }

            @Override
            public void onFailure(Call<ProductResult> call, Throwable t) {
                Toast.makeText(_context, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}