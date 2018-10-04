package com.rxlist.rxlist.binding.appliers;

import android.widget.ImageView;

import com.rxlist.rxlist.R;
import com.squareup.picasso.Picasso;

public class ImageApplier implements IBindingApplier<String>
{
    private final ImageView _view;

    public ImageApplier(ImageView view)
    {
        _view = view;
    }

    public void initialize(String url)
    {
        update(url);
    }

    public void update(final String url) {
        // Use Picasso to load the image
        // Temporarily have a placeholder in case it's slow to load
        Picasso.get()
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .into(_view);
    }

    public void terminate() {
    }
}