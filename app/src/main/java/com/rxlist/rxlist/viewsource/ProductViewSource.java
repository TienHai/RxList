package com.rxlist.rxlist.viewsource;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.rxlist.rxlist.R;
import com.rxlist.rxlist.binding.IRawBinder;
import com.rxlist.rxlist.binding.IViewSource;
import com.rxlist.rxlist.binding.appliers.OnClickApplier;
import com.rxlist.rxlist.binding.appliers.TextApplier;
import com.rxlist.rxlist.viewmodel.ProductViewModel;

public class ProductViewSource implements IViewSource<ProductViewModel> {

    @Override
    public View createView(LayoutInflater inflater, Context context) {
        return inflater.inflate(R.layout.product_item_view, /*root: */null);
    }

    @Override
    public void bindValues(View createdView, IRawBinder rawBinder, ProductViewModel viewModel) {
        rawBinder
                .bindApplier(new TextApplier((TextView) createdView.findViewById(R.id.txt_product_headline)), viewModel.headline())
                .bindApplier(new TextApplier((TextView) createdView.findViewById(R.id.txt_product_caption)), viewModel.caption())
                .bindApplier(new TextApplier((TextView) createdView.findViewById(R.id.txt_product_topic)), viewModel.topic())
                .bindApplier(new TextApplier((TextView) createdView.findViewById(R.id.txt_product_reviews)), viewModel.reviews())
                .bindApplier(new OnClickApplier(createdView.findViewById(R.id.root_item)), viewModel.activateCommand());
    }
}