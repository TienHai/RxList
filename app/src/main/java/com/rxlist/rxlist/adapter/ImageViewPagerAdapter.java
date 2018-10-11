package com.rxlist.rxlist.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rxlist.rxlist.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageViewPagerAdapter extends PagerAdapter {

    private Context _context;
    private ArrayList<String> _imagesUrls;

    public ImageViewPagerAdapter(Context context) {
        _context = context;
        _imagesUrls = new ArrayList<>();
    }

    public void updateItems(ArrayList<String> items) {
        if (items == null) {
            items = new ArrayList<>();
        }
        _imagesUrls = items;
        notifyDataSetChanged();
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        LayoutInflater inflater = LayoutInflater.from(_context);
        ViewGroup itemView = (ViewGroup) inflater.inflate(R.layout.viewpager_item, collection,false);
        collection.addView(itemView);

        ImageView imageView = itemView.findViewById(R.id.img_product);
        String imageUrl = _imagesUrls.get(position);
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);

        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup collection, int position, @NonNull Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return _imagesUrls.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}