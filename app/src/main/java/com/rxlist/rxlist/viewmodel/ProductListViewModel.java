package com.rxlist.rxlist.viewmodel;

import android.content.Context;
import android.widget.Toast;

import com.rxlist.rxlist.R;
import com.rxlist.rxlist.binding.IBooleanObservable;
import com.rxlist.rxlist.binding.ICommand;
import com.rxlist.rxlist.binding.IEvent;
import com.rxlist.rxlist.minterface.GetProductDataService;
import com.rxlist.rxlist.model.ProductItem;
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
    private boolean _productListVisibility;
    private ArrayList<ProductItem> _productItemArrayList;
    private IEvent _noticeItemsChanged;
    private IEvent _loadSpinnerVisibilityEvent;
    private IEvent _emptyStateVisibilityEvent;
    private IEvent _productListVisibilityEvent;

    public ProductListViewModel(Context context) {
        _context = context;
        _emptyStateVisibility = true;
        _loadSpinnerVisibility = false;
        _productListVisibility = false;
        _loadSpinnerVisibilityEvent = null;
        _emptyStateVisibilityEvent = null;
        _productListVisibilityEvent = null;
    }

    public IBooleanObservable isLoadSpinnerVisible() {
        return new IBooleanObservable() {
            @Override
            public boolean value() {
                return _loadSpinnerVisibility;
            }

            @Override
            public IEvent changed() {
                _loadSpinnerVisibilityEvent = ViewModelUtils.newEvent();

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
                _emptyStateVisibilityEvent = ViewModelUtils.newEvent();

                return _emptyStateVisibilityEvent;
            }
        };
    }

    public IBooleanObservable isProductListVisible() {
        return new IBooleanObservable() {
            @Override
            public boolean value() {
                return _productListVisibility;
            }

            @Override
            public IEvent changed() {
                _productListVisibilityEvent = ViewModelUtils.newEvent();

                return _productListVisibilityEvent;
            }
        };
    }

    public ArrayList<ProductItem> ProductItems() {
        return _productItemArrayList;
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
                _productListVisibility = false;
                _emptyStateVisibilityEvent.changed();
                _loadSpinnerVisibilityEvent.changed();
                _productListVisibilityEvent.changed();
                getProducts();
            }
        };
    }

    public IEvent productItemsChanged() {
        _noticeItemsChanged = ViewModelUtils.newEvent();

        return _noticeItemsChanged;
    }

    private void getProducts() {
        GetProductDataService service = RetrofitInstance.getRetrofitInstance(_context.getResources().getString(R.string.URL_BASE)).create(GetProductDataService.class);
        Call<ProductList> call = service.getProductListData();
        call.enqueue(new Callback<ProductList>() {
            @Override
            public void onResponse(Call<ProductList> call, Response<ProductList> response) {
                _loadSpinnerVisibility = false;
                _productListVisibility = true;
                _productItemArrayList = response.body().getProductArrayList();
                _noticeItemsChanged.changed();
                _loadSpinnerVisibilityEvent.changed();
                _productListVisibilityEvent.changed();
            }

            @Override
            public void onFailure(Call<ProductList> call, Throwable t) {
                _emptyStateVisibility = true;
                _loadSpinnerVisibility = false;
                _productListVisibility = false;
                _emptyStateVisibilityEvent.changed();
                _loadSpinnerVisibilityEvent.changed();
                _productListVisibilityEvent.changed();
                Toast.makeText(_context, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}