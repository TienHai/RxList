package com.rxlist.rxlist.viewsource;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.rxlist.rxlist.R;
import com.rxlist.rxlist.binding.IRawBinder;
import com.rxlist.rxlist.binding.IViewSource;
import com.rxlist.rxlist.binding.appliers.OnClickApplier;
import com.rxlist.rxlist.binding.appliers.RecyclerArrayApplier;
import com.rxlist.rxlist.binding.appliers.VisibilityApplier;
import com.rxlist.rxlist.model.Notice;
import com.rxlist.rxlist.viewmodel.NoticeListViewModel;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class NoticeListViewSource implements IViewSource<NoticeListViewModel> {

    public NoticeListViewSource() {
    }

    @Override
    public View createView(LayoutInflater inflater, Context context) {
        View createdView = inflater.inflate(R.layout.product_list_view, /*root: */null);
        RecyclerView recyclerView = createdView.findViewById(R.id.recycler_product_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(createdView.getContext());
        recyclerView.setLayoutManager(layoutManager);

        return createdView;
    }

    @Override
    public void bindValues(View createdView, IRawBinder rawBinder, final NoticeListViewModel viewModel) {
        rawBinder
                .bindApplier(new VisibilityApplier(createdView.findViewById(R.id.load_spinner)), viewModel.isLoadSpinnerVisible())
                .bindApplier(new VisibilityApplier(createdView.findViewById(R.id.empty_state)), viewModel.isEmptyStateVisible())
                .bindApplier(new OnClickApplier(createdView.findViewById(R.id.search_button)), viewModel.searchCommand())
                .bindApplier(new RecyclerArrayApplier((RecyclerView) createdView.findViewById(R.id.recycler_product_list), new NoticeViewSource()),
                        new Callable<ArrayList<Notice>>() {
                            @Override
                            public ArrayList<Notice> call() throws Exception {
                                return viewModel.noticeItems();
                            }
                        }, viewModel.noticeItemsChanged());
    }
}