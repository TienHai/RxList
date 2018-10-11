package com.rxlist.rxlist.binding.appliers;

import android.support.v4.view.ViewPager;

import com.rxlist.rxlist.adapter.ImageViewPagerAdapter;

import java.util.ArrayList;

public class ImageViewPagerArrayApplier implements IBindingApplier<ArrayList<String>> {

    private final ViewPager _viewPager;
    private final ImageViewPagerAdapter _adapter;

    public ImageViewPagerArrayApplier(ViewPager viewPager) {
        _viewPager = viewPager;
        _adapter = new ImageViewPagerAdapter(_viewPager.getContext());
    }

    @Override
    public void initialize(ArrayList<String> initialValue) {
        _viewPager.setAdapter(_adapter);
        update(initialValue);
    }

    @Override
    public void update(ArrayList<String> value) {
        _adapter.updateItems(value);

        // Fix: re set the adpter when change items, "notifyDataSetChanged" seem not do the job.
        _viewPager.setAdapter(_adapter);
    }

    @Override
    public void terminate() {
        _viewPager.setAdapter(null);
    }
}