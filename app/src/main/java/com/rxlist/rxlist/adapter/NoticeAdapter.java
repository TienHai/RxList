package com.rxlist.rxlist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rxlist.rxlist.binding.RawBinder;
import com.rxlist.rxlist.model.Notice;
import com.rxlist.rxlist.viewmodel.NoticeViewModel;
import com.rxlist.rxlist.viewsource.NoticeViewSource;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder> {

    private ArrayList<Notice> _dataList;
    private NoticeViewSource _viewSource;
    private LayoutInflater _layoutInflater;

    public NoticeAdapter(NoticeViewSource viewSource) {
        _viewSource = viewSource;
        _dataList = new ArrayList<>();
        _layoutInflater = null;
    }

    public void updateItems(ArrayList<Notice> items) {
        if (items == null) {
            items = new ArrayList<>();
        }
        _dataList = items;
        notifyDataSetChanged();
    }

    @Override
    public NoticeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View createView = _viewSource.createView(getLayoutInflater(context), context);
        return new NoticeViewHolder(createView);
    }

    @Override
    public void onBindViewHolder(NoticeViewHolder holder, int position) {
        View view = holder.itemView;
        RawBinder rawBinder = new RawBinder();
        _viewSource.bindValues(view, rawBinder, new NoticeViewModel(view.getContext(), _dataList.get(position)));
    }

    @Override
    public int getItemCount() {
        return _dataList.size();
    }

    private LayoutInflater getLayoutInflater(Context context) {
        if (_layoutInflater == null) {
            _layoutInflater = LayoutInflater.from(context);
        }

        return _layoutInflater;
    }

    class NoticeViewHolder extends RecyclerView.ViewHolder {
        NoticeViewHolder(View itemView) {
            super(itemView);
        }
    }
}