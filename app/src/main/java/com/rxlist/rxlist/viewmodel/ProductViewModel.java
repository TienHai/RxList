package com.rxlist.rxlist.viewmodel;

import android.content.Context;
import android.content.Intent;

import com.rxlist.rxlist.ItemActivity;
import com.rxlist.rxlist.binding.ICommand;
import com.rxlist.rxlist.model.Product;

public class ProductViewModel {

    private final Product _model;
    private final Context _context;

    public ProductViewModel(Context context, Product model) {
        _context = context;
        _model = model;
    }

    public String headline() {
        return _model.getHeadline();
    }

    public String caption() {
        return _model.getCaption();
    }

    public String topic() {
        return _model.getTopic();
    }

    public String reviews() {
        return "" + Math.round(_model.getReviewsAverageNote()) + "/5 (" + _model.getNbReviews() + " Avis)";
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
                _context.startActivity(intent);
            }
        };
    }
}