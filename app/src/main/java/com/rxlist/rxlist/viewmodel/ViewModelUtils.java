package com.rxlist.rxlist.viewmodel;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.rxlist.rxlist.binding.ICallback;
import com.rxlist.rxlist.binding.IEvent;

public class ViewModelUtils {

    public static IEvent newEvent() {
        return new IEvent() {

            private ICallback _callback;

            @Override
            public void subscribe(ICallback callback) {
                _callback = callback;
            }

            @Override
            public void changed() {
                if (_callback == null) {
                    return;
                }

                _callback.execute();
            }
        };
    }
}