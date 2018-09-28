package com.rxlist.rxlist.binding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public interface IViewSource<ViewModelType>
{
    View createView(LayoutInflater inflater, Context context);
    void bindValues(View createdView, IRawBinder rawBinder, ViewModelType item);
}
