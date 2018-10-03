package com.rxlist.rxlist.binding.appliers;

import android.support.v7.widget.RecyclerView;

import com.rxlist.rxlist.adapter.ProductListAdapter;
import com.rxlist.rxlist.model.Product;
import com.rxlist.rxlist.viewsource.ProductViewSource;

import java.util.ArrayList;

public class RecyclerArrayApplier implements IBindingApplier<ArrayList<Product>> {

    private final RecyclerView _recycler;
    private final ProductListAdapter _adapter;

    public  RecyclerArrayApplier(RecyclerView recycler, ProductViewSource productViewSource) {
        _recycler = recycler;
        _adapter = new ProductListAdapter(productViewSource);
    }

    @Override
    public void initialize(ArrayList<Product> initialValue) {
        _recycler.setAdapter(_adapter);
        update(initialValue);
    }

    @Override
    public void update(ArrayList<Product> value) {
        _adapter.updateItems(value);
    }

    @Override
    public void terminate() {
        _recycler.setAdapter(null);
    }
}