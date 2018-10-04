package com.rxlist.rxlist.viewmodel;

import com.rxlist.rxlist.model.ProductItem;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

public class ProductViewModel {

    private final ProductItem _model;

    public ProductViewModel(ProductItem model) {
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
}