package com.rxlist.rxlist.viewsource;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.rxlist.rxlist.R;
import com.rxlist.rxlist.binding.ICallback;
import com.rxlist.rxlist.binding.IRawBinder;
import com.rxlist.rxlist.binding.IViewSource;
import com.rxlist.rxlist.binding.appliers.OnCallbackClickApplier;
import com.rxlist.rxlist.binding.appliers.RatingApplier;
import com.rxlist.rxlist.binding.appliers.TextApplier;
import com.rxlist.rxlist.viewmodel.ReviewViewModel;

public class ReviewViewSource implements IViewSource<ReviewViewModel> {

    @Override
    public View createView(LayoutInflater inflater, Context context) {
        return inflater.inflate(R.layout.product_review, /*root: */null);
    }

    @Override
    public void bindValues(final View createdView, IRawBinder rawBinder, final ReviewViewModel viewModel) {
        final TextView text = createdView.findViewById(R.id.txt_review_description);

        ICallback callbackMore = new ICallback() {
            @Override
            public void execute() {
                int height = text.getMeasuredHeight();
                text.setMaxLines(Integer.MAX_VALUE);
                ObjectAnimator animation = textViewAnimation(text, height);
                animation.start();
            }
        };

        ICallback callbackLess = new ICallback() {
            @Override
            public void execute() {
                int height = text.getMeasuredHeight();
                text.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                text.setMaxLines(4);
                ObjectAnimator animation = textViewAnimation(text, height);
                animation.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);

                        // Fix: redraw correctly textView with "..." at end.
                        text.setMaxLines(4);
                        text.setEllipsize(TextUtils.TruncateAt.END);
                    }
                });
                animation.start();
            }
        };

        rawBinder
                .bindApplier(new TextApplier((TextView) createdView.findViewById(R.id.txt_review_author_name)), viewModel.authorName())
                .bindApplier(new TextApplier((TextView) createdView.findViewById(R.id.txt_review_title)), viewModel.title())
                .bindApplier(new RatingApplier((RatingBar) createdView.findViewById(R.id.review_ratingbar)), viewModel.note())
                .bindApplier(new TextApplier((TextView) createdView.findViewById(R.id.txt_review_description)), viewModel.description())
                .bindApplier(new OnCallbackClickApplier(createdView.findViewById(R.id.btn_review_more)), callbackMore)
                .bindApplier(new OnCallbackClickApplier(createdView.findViewById(R.id.btn_review_less)), callbackLess);
    }

    private ObjectAnimator textViewAnimation(TextView text, int originHeight) {
        text.measure(
                View.MeasureSpec.makeMeasureSpec(text.getMeasuredWidth(), View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        final int newHeight = text.getMeasuredHeight();
        ObjectAnimator animation = ObjectAnimator.ofInt(text, "height", originHeight, newHeight);
        animation.setDuration(250);

        return animation;
    }
}