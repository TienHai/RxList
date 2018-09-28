package com.rxlist.rxlist.binding.appliers;

import android.support.v7.widget.RecyclerView;

import com.rxlist.rxlist.adapter.NoticeAdapter;
import com.rxlist.rxlist.model.Notice;
import com.rxlist.rxlist.viewsource.NoticeViewSource;

import java.util.ArrayList;

public class RecyclerArrayApplier implements IBindingApplier<ArrayList<Notice>> {

    private final RecyclerView _recycler;
    private final NoticeAdapter _adapter;

    public  RecyclerArrayApplier(RecyclerView recycler, NoticeViewSource noticeViewSource) {
        _recycler = recycler;
        _adapter = new NoticeAdapter(noticeViewSource);
    }

    @Override
    public void initialize(ArrayList<Notice> initialValue) {
        _recycler.setAdapter(_adapter);
        update(initialValue);
    }

    @Override
    public void update(ArrayList<Notice> value) {
        _adapter.updateItems(value);
    }

    @Override
    public void terminate() {
        _recycler.setAdapter(null);
    }
}