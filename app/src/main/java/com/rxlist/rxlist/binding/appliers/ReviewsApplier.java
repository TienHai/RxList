package com.rxlist.rxlist.binding.appliers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rxlist.rxlist.binding.RawBinder;
import com.rxlist.rxlist.viewmodel.ReviewViewModel;
import com.rxlist.rxlist.viewsource.ReviewViewSource;

import java.util.ArrayList;

public class ReviewsApplier extends SimpleValueApplierBase<ArrayList<ReviewViewModel>> {

    private Context _context;
    private View _container;
    private ReviewViewSource _viewSource;

    public ReviewsApplier(Context context, View container, ReviewViewSource viewSource) {
        _context = context;
        _container = container;
        _viewSource = viewSource;
    }

    @Override
    public void update(ArrayList<ReviewViewModel> value) {
        LayoutInflater inflater = LayoutInflater.from(_context);

        for (final ReviewViewModel viewModel : value) {
            View createdView = _viewSource.createView(inflater, _context);
            RawBinder rawBinder = new RawBinder();
            ((ViewGroup) _container).addView(createdView);
            _viewSource.bindValues(createdView, rawBinder, viewModel);
        }
    }
}