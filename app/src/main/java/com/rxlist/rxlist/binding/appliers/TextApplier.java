package com.rxlist.rxlist.binding.appliers;

import android.widget.TextView;

public class TextApplier extends SimpleValueApplierBase<String>
{
    private final TextView _textView;

    public TextApplier(TextView textView)
    {
        _textView = textView;
    }

    public void update(String value)
    {
        _textView.setText(value);
    }
}