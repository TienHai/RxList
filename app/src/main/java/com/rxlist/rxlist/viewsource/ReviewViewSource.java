package com.rxlist.rxlist.viewsource;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.rxlist.rxlist.R;
import com.rxlist.rxlist.binding.IRawBinder;
import com.rxlist.rxlist.binding.IViewSource;
import com.rxlist.rxlist.binding.appliers.OnClickApplier;
import com.rxlist.rxlist.binding.appliers.RatingApplier;
import com.rxlist.rxlist.binding.appliers.TextApplier;
import com.rxlist.rxlist.binding.appliers.VisibilityApplier;
import com.rxlist.rxlist.viewmodel.ReviewViewModel;

public class ReviewViewSource implements IViewSource<ReviewViewModel> {

    @Override
    public View createView(LayoutInflater inflater, Context context) {
        return inflater.inflate(R.layout.product_review, /*root: */null);
    }

    @Override
    public void bindValues(final View createdView, IRawBinder rawBinder, final ReviewViewModel viewModel) {
        TextView text = createdView.findViewById(R.id.txt_review_description);

        rawBinder
                .bindApplier(new TextApplier((TextView) createdView.findViewById(R.id.txt_review_author_name)), viewModel.authorName())
                .bindApplier(new TextApplier((TextView) createdView.findViewById(R.id.txt_review_title)), viewModel.title())
                .bindApplier(new RatingApplier((RatingBar) createdView.findViewById(R.id.review_ratingbar)), viewModel.note())
                .bindApplier(new TextApplier(text), viewModel.description())
                .bindApplier(new VisibilityApplier(createdView.findViewById(R.id.btn_review_more)), viewModel.isButtonMoreVisible(text))
                .bindApplier(new VisibilityApplier(createdView.findViewById(R.id.btn_review_less)), viewModel.isButtonLessVisible())
                .bindApplier(new OnClickApplier(createdView.findViewById(R.id.btn_review_more)), viewModel.onButtonDescriptionMoreCommand(text))
                .bindApplier(new OnClickApplier(createdView.findViewById(R.id.btn_review_less)), viewModel.onButtonDescriptionLessCommand(text));
    }
}