package com.rxlist.rxlist.viewmodel;

import android.content.Context;

import com.rxlist.rxlist.model.Review;

public class ReviewViewModel {

    private Context _context;
    private Review _model;

    public ReviewViewModel(Context context, Review model) {
        _context = context;
        _model = model;
    }

    public String title() {
        return _model.getTitle();
    }

    public Float note() {
        return (float) _model.getNote();
    }

    public String authorName() {
        return _model.getAuthorName();
    }

    public String description() {
        return _model.getDescription();
    }

}