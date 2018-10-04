package com.rxlist.rxlist.viewmodel;

import android.content.Context;
import android.content.Intent;

import com.rxlist.rxlist.ItemActivity;
import com.rxlist.rxlist.binding.ICommand;
import com.rxlist.rxlist.model.Product;

public class ProductItemViewModel extends ProductViewModel {

    private final Product _model;
    private final Context _context;

    public ProductItemViewModel(Context context, Product model) {
        super(model);
        _context = context;
        _model = model;
    }

    public ICommand activateCommand() {
        return new ICommand() {
            @Override
            public boolean canExecute() {
                return true;
            }

            @Override
            public void execute() {
                Intent intent = new Intent(_context, ItemActivity.class);
                intent.putExtra("Product", _model);
                _context.startActivity(intent);
            }
        };
    }
}