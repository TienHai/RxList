package com.rxlist.rxlist.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.media.Image;

import com.rxlist.rxlist.ItemActivity;
import com.rxlist.rxlist.binding.ICommand;
import com.rxlist.rxlist.model.Product;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

public class ProductViewModel {

    private final Product _model;
    private final Context _context;

    public ProductViewModel(Context context, Product model) {
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

    public String reviewsText() {
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