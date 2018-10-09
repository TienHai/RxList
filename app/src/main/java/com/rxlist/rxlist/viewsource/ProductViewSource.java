package com.rxlist.rxlist.viewsource;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rxlist.rxlist.R;
import com.rxlist.rxlist.binding.IRawBinder;
import com.rxlist.rxlist.binding.IViewSource;
import com.rxlist.rxlist.binding.appliers.ImageApplier;
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

        /*
        ViewPager viewPager = (ViewPager)  createdView.findViewById(R.id.img_product_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(createdView.getContext());
        viewPager.setAdapter(adapter);
        */

        return createdView;
    }

    @Override
    public void bindValues(View createdView, IRawBinder rawBinder, final ProductViewModel viewModel) {
        rawBinder
                .bindApplier(new VisibilityApplier(createdView.findViewById(R.id.product_loading)), viewModel.isLoadingProductVisible())
                .bindApplier(new ImageApplier((ImageView) createdView.findViewById(R.id.img_product)), viewModel.imageUrl())
                .bindApplier(new TextApplier((TextView) createdView.findViewById(R.id.txt_product_headline)), viewModel.headline())
                .bindApplier(new TextApplier((TextView) createdView.findViewById(R.id.txt_product_newbestprice)), viewModel.newBestPrice())
                .bindApplier(new ReviewsApplier(createdView.getContext(), createdView.findViewById(R.id.product_reviews), new ReviewViewSource()),
                        new Callable<ArrayList<ReviewViewModel>>() {
                            @Override
                            public ArrayList<ReviewViewModel> call() throws Exception {
                                return viewModel.reviews();
                            }
                        }, viewModel.reviewsChanged());
    }

    /*
    public class ViewPagerAdapter {
        public ViewPagerAdapter(Context context) {
            this.context = context;
            this.urls = urls;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View itemView = inflater.inflate(R.layout.viewpager_item, container,
                    false);
            // Locate the TextViews in viewpager_item.xml
            imgflag = (ImageView) itemView.findViewById(R.id.image);
            imgflag.setImageResource(urls[position]);
            ((ViewPager) container).addView(itemView);
            return itemView;
        }
    }
    */
}