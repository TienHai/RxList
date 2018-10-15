package com.rxlist.rxlist.viewmodel;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.rxlist.rxlist.binding.IBooleanObservable;
import com.rxlist.rxlist.binding.ICommand;
import com.rxlist.rxlist.binding.IEvent;
import com.rxlist.rxlist.model.Review;

public class ReviewViewModel {

    private Context _context;
    private Review _model;
    private boolean _buttonMoreVisibility;
    private boolean _buttonLessVisibility;
    private IEvent _buttonMoreVisibilityEvent;
    private IEvent _buttonLessVisibilityEvent;
    private TextView _text;

    public ReviewViewModel(Context context, Review model) {
        _context = context;
        _model = model;
        _buttonMoreVisibility = true;
        _buttonLessVisibility = false;
        _buttonMoreVisibilityEvent = null;
        _buttonLessVisibilityEvent = null;
        _text = null;
    }

    public String title() {
        return _model.getTitle();
    }

    public Float note() {
        return (float) _model.getNote();
    }

    public String authorName() {
        return _model.getAuthorName();
    }

    public String description() {
        return _model.getDescription();
    }

    public IBooleanObservable isButtonMoreVisible(TextView text) {
        if (_text == null) {
            _text = text;
            _text.addOnLayoutChangeListener(textViewLayoutChangeListener());
        }

        return new IBooleanObservable() {
            @Override
            public boolean value() {
                return _buttonMoreVisibility;
            }

            @Override
            public IEvent changed() {
                return _buttonMoreVisibilityEvent;
            }
        };
    }

    public IBooleanObservable isButtonLessVisible(TextView text) {
        if (_text == null) {
            _text = text;
            _text.addOnLayoutChangeListener(textViewLayoutChangeListener());
        }

        return new IBooleanObservable() {
            @Override
            public boolean value() {
                return _buttonLessVisibility;
            }

            @Override
            public IEvent changed() {
                return _buttonLessVisibilityEvent;
            }
        };
    }

    private View.OnLayoutChangeListener textViewLayoutChangeListener() {
        _buttonMoreVisibilityEvent = ViewModelUtils.newEvent();
        _buttonLessVisibilityEvent = ViewModelUtils.newEvent();

        return new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight,
                                       int oldBottom) {
                // its possible that the layout is not complete in which case
                // we will get all zero values for the positions, so ignore the event
                if (left == 0 && top == 0 && right == 0 && bottom == 0) {
                    return;
                }
                heightTextView();
            }
        };
    }

    private void heightTextView() {
        if (_text.getLineCount() > _text.getMaxLines()) {
            _buttonMoreVisibility = false;
            _buttonLessVisibility = true;
        } else {
            _buttonMoreVisibility = true;
            _buttonLessVisibility = false;
        }
        _buttonMoreVisibilityEvent.changed();
        _buttonLessVisibilityEvent.changed();
    }
}