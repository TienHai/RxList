package com.rxlist.rxlist.binding.appliers;

import android.view.KeyEvent;
import android.widget.TextView;

import com.rxlist.rxlist.binding.ICallback;

public class OnEditorKeyboardActionApplier implements IBindingApplier<ICallback> {

    private TextView _textView;
    private int _editorAction;

    public OnEditorKeyboardActionApplier(TextView textView, int editorAction) {
        _textView = textView;
        _editorAction = editorAction;
    }

    @Override
    public void initialize(ICallback initialCallback) {
        update(initialCallback);
    }

    @Override
    public void update(final ICallback callback) {
        _textView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == _editorAction) {
                    callback.execute();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void terminate() {
        _textView.setOnEditorActionListener(null);
    }
}