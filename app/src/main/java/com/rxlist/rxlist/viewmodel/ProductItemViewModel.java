package com.rxlist.rxlist.viewmodel;

import android.content.Context;
import android.content.Intent;

import com.rxlist.rxlist.ItemActivity;
import com.rxlist.rxlist.binding.ICommand;
import com.rxlist.rxlist.model.ProductItem;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

public class ProductItemViewModel {

    private final ProductItem _model;
    private final Context _context;

    public ProductItemViewModel(Context context, ProductItem model) {
        _context = context;
        _model = model;
    }

    public String newBestPriceText() {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
        format.setCurrency(Currency.getInstance(Locale.FRANCE));
        return format.format(_model.getNewBestPrice());
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

    public String imageUrl() {
        ArrayList<String> urls = _model.getImageUrls();
        if (urls.isEmpty())
            return null;

        return urls.get(0);
    }

    public float reviewsAverageNote() {
        return _model.getReviewsAverageNote();
    }

    public String reviewsText() {
        return "(" + _model.getNbReviews() + " Avis)";
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