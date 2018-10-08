package com.rxlist.rxlist.viewsource;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.rxlist.rxlist.R;
import com.rxlist.rxlist.binding.IRawBinder;
import com.rxlist.rxlist.binding.IViewSource;
import com.rxlist.rxlist.binding.appliers.RatingApplier;
import com.rxlist.rxlist.binding.appliers.TextApplier;
import com.rxlist.rxlist.viewmodel.ReviewViewModel;

public class ReviewViewSource implements IViewSource<ReviewViewModel> {

    @Override
    public View createView(LayoutInflater inflater, Context context) {
        return inflater.inflate(R.layout.product_review, /*root: */null);
    }

    @Override
    public void bindValues(View createdView, IRawBinder rawBinder, final ReviewViewModel viewModel) {
        rawBinder
                .bindApplier(new TextApplier((TextView) createdView.findViewById(R.id.txt_review_author_name)), viewModel.authorName())
                .bindApplier(new TextApplier((TextView) createdView.findViewById(R.id.txt_review_title)), viewModel.title())
                .bindApplier(new RatingApplier((RatingBar) createdView.findViewById(R.id.review_ratingbar)), viewModel.note())
                .bindApplier(new TextApplier((TextView) createdView.findViewById(R.id.txt_review_description)), viewModel.description());
    }
}