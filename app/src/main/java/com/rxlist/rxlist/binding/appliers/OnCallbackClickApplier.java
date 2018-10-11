package com.rxlist.rxlist.binding.appliers;

import android.view.View;

import com.rxlist.rxlist.binding.ICallback;

public class OnCallbackClickApplier implements IBindingApplier<ICallback> {
    private final View _view;

    public OnCallbackClickApplier(View view) {
        _view = view;
    }

    @Override
    public void initialize(ICallback initialCallback) {
        update(initialCallback);
    }

    @Override
    public void update(final ICallback callback) {
        _view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.execute();
            }
        });
    }

    @Override
    public void terminate() {
        _view.setOnClickListener(null);
    }
}