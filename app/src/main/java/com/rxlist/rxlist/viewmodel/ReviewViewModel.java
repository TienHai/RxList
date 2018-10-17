package com.rxlist.rxlist.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rxlist.rxlist.R;
import com.rxlist.rxlist.binding.IBooleanObservable;
import com.rxlist.rxlist.binding.ICommand;
import com.rxlist.rxlist.binding.IEvent;
import com.rxlist.rxlist.model.Review;

import org.w3c.dom.Text;

public class ReviewViewModel {

    private Context _context;
    private Review _model;
    private boolean _buttonMoreVisibility;
    private boolean _buttonLessVisibility;
    private IEvent _buttonMoreVisibilityEvent;
    private IEvent _buttonLessVisibilityEvent;

    public ReviewViewModel(Context context, Review model) {
        _context = context;
        _model = model;
        _buttonMoreVisibility = true;
        _buttonLessVisibility = false;
        _buttonMoreVisibilityEvent = null;
        _buttonLessVisibilityEvent = null;
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

    public ICommand onButtonDescriptionMoreCommand(final TextView text) {
        return new ICommand() {
            @Override
            public boolean canExecute() {
                return true;
            }

            @Override
            public void execute() {
                text.setMaxLines(Integer.MAX_VALUE);
                _buttonMoreVisibility = false;
                _buttonLessVisibility = true;
                if (_buttonMoreVisibilityEvent != null) {
                    _buttonMoreVisibilityEvent.changed();
                    _buttonLessVisibilityEvent.changed();
                }
            }
        };
    }

    public ICommand onButtonDescriptionLessCommand(final TextView text) {
        return new ICommand() {
            @Override
            public boolean canExecute() {
                return true;
            }

            @Override
            public void execute() {
                text.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                text.setMaxLines(4);
                _buttonMoreVisibility = true;
                _buttonLessVisibility = false;
                if (_buttonMoreVisibilityEvent != null) {
                    _buttonMoreVisibilityEvent.changed();
                    _buttonLessVisibilityEvent.changed();
                }
            }
        };
    }


    public ICommand onTextLayoutChanged() {
        return new ICommand() {
            @Override
            public boolean canExecute() {
                return true;
            }

            @Override
            public void execute() {
                TextView text = ((Activity)_context).findViewById(R.id.txt_review_description);
                boolean isEllipsize = !((text.getLayout().getText().toString()).equalsIgnoreCase(text.getText().toString()));

                if(isEllipsize) {
                    _buttonMoreVisibility = true;
                    _buttonLessVisibility = false;
                } else {
                    _buttonMoreVisibility = false;
                    _buttonLessVisibility = true;
                }


                if (_buttonMoreVisibilityEvent != null) {
                    _buttonMoreVisibilityEvent.changed();
                    _buttonLessVisibilityEvent.changed();
                }
            }
        };
    }

    public IBooleanObservable isButtonMoreVisible() {
        return new IBooleanObservable() {
            @Override
            public boolean value() {
                return _buttonMoreVisibility;
            }

            @Override
            public IEvent changed() {
                _buttonMoreVisibilityEvent = ViewModelUtils.newEvent();

                return _buttonMoreVisibilityEvent;
            }
        };
    }

    public IBooleanObservable isButtonLessVisible() {
        return new IBooleanObservable() {
            @Override
            public boolean value() {
                return _buttonLessVisibility;
            }

            @Override
            public IEvent changed() {
                _buttonLessVisibilityEvent = ViewModelUtils.newEvent();

                return _buttonLessVisibilityEvent;
            }
        };
    }
}