package com.rxlist.rxlist.viewmodel;

import android.content.Context;
import android.widget.Toast;

import com.rxlist.rxlist.binding.IBooleanObservable;
import com.rxlist.rxlist.binding.IEvent;
import com.rxlist.rxlist.binding.IStringObservable;
import com.rxlist.rxlist.minterface.GetProductDataService;
import com.rxlist.rxlist.model.Product;
import com.rxlist.rxlist.model.ProductResult;
import com.rxlist.rxlist.model.Review;
import com.rxlist.rxlist.network.RetrofitInstance;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

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
    private IEvent _modelNewBestPriceEvent;
    private IEvent _modelReviewsEvent;

    public ProductViewModel(Context context) {
        _context = context;
        _model = null;
        _loadingProductEvent = null;
        _loadingProductVisibility = true;
        _modelHeadlineEvent = null;
        _modelImageUrlEvent = null;
        _modelNewBestPriceEvent = null;
        _modelReviewsEvent = null;

        getProduct();
    }

    public IStringObservable headline(){
        return new IStringObservable() {
            @Override
            public String value() {
                if (_model == null) {
                    return null;
                }

                return _model.getHeadline();
            }

            @Override
            public IEvent changed() {
                _modelHeadlineEvent = ViewModelUtils.newEvent();

                return _modelHeadlineEvent;
            }
        };
    }

    public IStringObservable newBestPrice() {
        return new IStringObservable() {
            @Override
            public String value() {
                if (_model == null) {
                    return null;
                }

                NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
                format.setCurrency(Currency.getInstance(Locale.FRANCE));
                return format.format(_model.getNewBestPrice());
            }

            @Override
            public IEvent changed() {
                _modelNewBestPriceEvent = ViewModelUtils.newEvent();

                return _modelNewBestPriceEvent;
            }
        };
    }

    public IEvent reviewsChanged() {
        _modelReviewsEvent = ViewModelUtils.newEvent();

        return _modelReviewsEvent;
    }

    public ArrayList<ReviewViewModel> reviews() {
        ArrayList<ReviewViewModel> reviews = new ArrayList<>();
        for (Review model: _model.getReviewList()) {
            reviews.add(new ReviewViewModel(_context, model));
        }

        return reviews;
    }

    public IStringObservable imageUrl() {
        return new IStringObservable() {
            @Override
            public String value() {
                if (_model == null) {
                    return null;
                }

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
                _modelNewBestPriceEvent.changed();
                _modelReviewsEvent.changed();
            }

            @Override
            public void onFailure(Call<ProductResult> call, Throwable t) {
                Toast.makeText(_context, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}