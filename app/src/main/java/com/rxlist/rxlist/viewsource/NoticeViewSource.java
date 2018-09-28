package com.rxlist.rxlist.viewsource;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.rxlist.rxlist.R;
import com.rxlist.rxlist.binding.IRawBinder;
import com.rxlist.rxlist.binding.IViewSource;
import com.rxlist.rxlist.binding.appliers.OnClickApplier;
import com.rxlist.rxlist.binding.appliers.TextApplier;
import com.rxlist.rxlist.viewmodel.NoticeViewModel;

public class NoticeViewSource implements IViewSource<NoticeViewModel> {

    @Override
    public View createView(LayoutInflater inflater, Context context) {
        return  inflater.inflate(R.layout.product_item_view, /*root: */null);
    }

    @Override
    public void bindValues(View createdView, IRawBinder rawBinder, NoticeViewModel viewModel) {
        rawBinder
                .bindApplier(new TextApplier((TextView) createdView.findViewById(R.id.txt_notice_title)), viewModel.title())
                .bindApplier(new TextApplier((TextView) createdView.findViewById(R.id.txt_notice_brief)), viewModel.brief())
                .bindApplier(new TextApplier((TextView) createdView.findViewById(R.id.txt_notice_file_path)), viewModel.filePath())
                .bindApplier(new OnClickApplier(createdView.findViewById(R.id.root_item)), viewModel.activateCommand());
    }
}