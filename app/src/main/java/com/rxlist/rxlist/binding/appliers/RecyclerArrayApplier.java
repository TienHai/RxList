package com.rxlist.rxlist.binding.appliers;

import android.support.v7.widget.RecyclerView;

import com.rxlist.rxlist.adapter.ProductListAdapter;
import com.rxlist.rxlist.model.Product;
import com.rxlist.rxlist.viewsource.ProductItemViewSource;

import java.util.ArrayList;

public class RecyclerArrayApplier implements IBindingApplier<ArrayList<Product>> {

    private final RecyclerView _recycler;
    private final ProductListAdapter _adapter;

    public  RecyclerArrayApplier(RecyclerView recycler, ProductItemViewSource productItemViewSource) {
        _recycler = recycler;
        _adapter = new ProductListAdapter(productItemViewSource);
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