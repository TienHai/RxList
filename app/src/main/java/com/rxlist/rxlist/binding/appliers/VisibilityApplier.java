package com.rxlist.rxlist.binding.appliers;

import android.view.View;

public class VisibilityApplier extends SimpleValueApplierBase<Boolean> {

    private final View _view;

    public VisibilityApplier(View view) {
        _view = view;
    }

    @Override
    public void update(Boolean value) {
        _view.setVisibility(value ? View.VISIBLE : View.GONE);
    }
}