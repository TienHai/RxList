package com.rxlist.rxlist.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.rxlist.rxlist.binding.IBooleanObservable;
import com.rxlist.rxlist.binding.ICallback;
import com.rxlist.rxlist.binding.ICommand;
import com.rxlist.rxlist.binding.IEvent;
import com.rxlist.rxlist.minterface.GetProductDataService;
import com.rxlist.rxlist.model.Product;
import com.rxlist.rxlist.model.ProductList;
import com.rxlist.rxlist.network.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListViewModel {

    private final Context _context;
    private boolean _loadSpinnerVisibility;
    private boolean _emptyStateVisibility;
    private ArrayList<Product> _productArrayList;
    private IEvent _noticeItemsChanged;
    private IEvent _loadSpinnerVisibilityEvent;
    private IEvent _emptyStateVisibilityEvent;

    public ProductListViewModel(Context context) {
        _context = context;
        _emptyStateVisibility = true;
        _loadSpinnerVisibility = false;
    }

    public IBooleanObservable isLoadSpinnerVisible() {
        return new IBooleanObservable() {
            @Override
            public boolean value() {
                return _loadSpinnerVisibility;
            }

            @Override
            public IEvent changed() {
                _loadSpinnerVisibilityEvent = newEvent();

                return _loadSpinnerVisibilityEvent;
            }
        };
    }

    public IBooleanObservable isEmptyStateVisible() {
        return new IBooleanObservable() {
            @Override
            public boolean value() {
                return _emptyStateVisibility;
            }

            @Override
            public IEvent changed() {
                _emptyStateVisibilityEvent = newEvent();

                return _emptyStateVisibilityEvent;
            }
        };
    }

    public ArrayList<Product> ProductItems() {
        return _productArrayList;
    }

    public ICommand searchCommand() {
        return new ICommand() {
            @Override
            public boolean canExecute() {
                return true;
            }

            @Override
            public void execute() {
                _emptyStateVisibility = false;
                _loadSpinnerVisibility = true;
                _emptyStateVisibilityEvent.changed();
                _loadSpinnerVisibilityEvent.changed();
                getProducts();
            }
        };
    }

    public IEvent ProductItemsChanged() {
        _noticeItemsChanged = newEvent();

        return _noticeItemsChanged;
    }

    private void getProducts() {
        GetProductDataService service = RetrofitInstance.getRetrofitInstance().create(GetProductDataService.class);
        Call<ProductList> call = service.getProductData();
        call.enqueue(new Callback<ProductList>() {
            @Override
            public void onResponse(Call<ProductList> call, Response<ProductList> response) {
                _loadSpinnerVisibility = false;
                _productArrayList = response.body().getProductArrayList();
                _noticeItemsChanged.changed();
                _loadSpinnerVisibilityEvent.changed();
            }

            @Override
            public void onFailure(Call<ProductList> call, Throwable t) {
                _emptyStateVisibility = true;
                _loadSpinnerVisibility = false;
                _emptyStateVisibilityEvent.changed();
                _loadSpinnerVisibilityEvent.changed();
                Toast.makeText(_context, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private IEvent newEvent() {
        return new IEvent() {

            private ICallback _callback;

            @Override
            public void subscribe(ICallback callback) {
                _callback = callback;
            }

            @Override
            public void changed() {
                _callback.execute();
            }
        };
    }
}