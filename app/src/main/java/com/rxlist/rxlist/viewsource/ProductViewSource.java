package com.rxlist.rxlist.viewsource;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rxlist.rxlist.R;
import com.rxlist.rxlist.binding.IRawBinder;
import com.rxlist.rxlist.binding.IViewSource;
import com.rxlist.rxlist.binding.appliers.ImageApplier;
import com.rxlist.rxlist.binding.appliers.TextApplier;
import com.rxlist.rxlist.binding.appliers.VisibilityApplier;
import com.rxlist.rxlist.viewmodel.ProductViewModel;

public class ProductViewSource implements IViewSource<ProductViewModel> {

    @Override
    public View createView(LayoutInflater inflater, Context context) {
        return inflater.inflate(R.layout.product_view, /*root: */null);
    }

    @Override
    public void bindValues(View createdView, IRawBinder rawBinder, ProductViewModel viewModel) {
        rawBinder
                .bindApplier(new VisibilityApplier(createdView.findViewById(R.id.product_loading)), viewModel.isLoadingProductVisible())
                .bindApplier(new ImageApplier((ImageView) createdView.findViewById(R.id.img_product)), viewModel.imageUrl())
                .bindApplier(new TextApplier((TextView) createdView.findViewById(R.id.txt_product_headline)), viewModel.headline())
                .bindApplier(new TextApplier((TextView) createdView.findViewById(R.id.txt_product_newbestprice)), viewModel.newBestPrice());
    }
}