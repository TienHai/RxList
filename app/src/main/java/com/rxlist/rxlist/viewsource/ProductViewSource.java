package com.rxlist.rxlist.viewsource;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


import com.rxlist.rxlist.R;
import com.rxlist.rxlist.binding.IRawBinder;
import com.rxlist.rxlist.binding.IViewSource;
import com.rxlist.rxlist.binding.appliers.ImageViewPagerArrayApplier;
import com.rxlist.rxlist.binding.appliers.ReviewsApplier;
import com.rxlist.rxlist.binding.appliers.TextApplier;
import com.rxlist.rxlist.binding.appliers.VisibilityApplier;
import com.rxlist.rxlist.viewmodel.ProductViewModel;
import com.rxlist.rxlist.viewmodel.ReviewViewModel;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class ProductViewSource implements IViewSource<ProductViewModel> {

    @Override
    public View createView(LayoutInflater inflater, Context context) {
        View createdView = inflater.inflate(R.layout.product_view, /*root: */null);
        ViewPager pager = createdView.findViewById(R.id.img_product_pager);
        TabLayout tabLayout = createdView.findViewById(R.id.img_product_tab);
        tabLayout.setupWithViewPager(pager, true);

        return createdView;
    }

    @Override
    public void bindValues(View createdView, IRawBinder rawBinder, final ProductViewModel viewModel) {
        rawBinder
                .bindApplier(new VisibilityApplier(createdView.findViewById(R.id.product_loading)), viewModel.isLoadingProductVisible())
                .bindApplier(new TextApplier((TextView) createdView.findViewById(R.id.txt_product_headline)), viewModel.headline())
                .bindApplier(new TextApplier((TextView) createdView.findViewById(R.id.txt_product_newbestprice)), viewModel.newBestPrice())
                .bindApplier(new ReviewsApplier(createdView.getContext(), createdView.findViewById(R.id.product_reviews), new ReviewViewSource()),
                        new Callable<ArrayList<ReviewViewModel>>() {
                            @Override
                            public ArrayList<ReviewViewModel> call() throws Exception {
                                return viewModel.reviews();
                            }
                        }, viewModel.reviewsChanged())
                .bindApplier(new ImageViewPagerArrayApplier((ViewPager) createdView.findViewById(R.id.img_product_pager)),
                        new Callable<ArrayList<String>>() {
                            @Override
                            public ArrayList<String> call() throws Exception {
                                return viewModel.imagesUrls();
                            }
                        }, viewModel.imagesUrlsChanged());
    }
}