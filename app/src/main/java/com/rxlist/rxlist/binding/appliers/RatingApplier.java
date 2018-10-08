package com.rxlist.rxlist.binding.appliers;

import android.widget.RatingBar;

public class RatingApplier extends SimpleValueApplierBase<Float>
{
    private final RatingBar _ratingBar;

    public RatingApplier(RatingBar ratingBar)
    {
        _ratingBar = ratingBar;
    }

    public void update(Float value)
    {
        _ratingBar.setRating(value);
    }
}