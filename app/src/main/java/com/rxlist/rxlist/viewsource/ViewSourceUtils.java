package com.rxlist.rxlist.viewsource;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class ViewSourceUtils {

    /**
     * Show soft keyboard.
     *
     * @param view View
     */
    public static void showSoftInput(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, 0);
        }
    }

    /**
     * Hide soft keyboard.
     *
     * @param view View
     */
    public static void hideSoftInput(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}