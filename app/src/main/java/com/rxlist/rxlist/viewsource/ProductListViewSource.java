package com.rxlist.rxlist.viewsource;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.rxlist.rxlist.R;
import com.rxlist.rxlist.binding.ICallback;
import com.rxlist.rxlist.binding.IRawBinder;
import com.rxlist.rxlist.binding.IViewSource;
import com.rxlist.rxlist.binding.appliers.OnClickApplier;
import com.rxlist.rxlist.binding.appliers.OnEditorKeyboardActionApplier;
import com.rxlist.rxlist.binding.appliers.RecyclerArrayApplier;
import com.rxlist.rxlist.binding.appliers.VisibilityApplier;
import com.rxlist.rxlist.model.ProductItem;
import com.rxlist.rxlist.viewmodel.ProductListViewModel;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class ProductListViewSource implements IViewSource<ProductListViewModel> {

    public ProductListViewSource() {
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
    public void bindValues(View createdView, IRawBinder rawBinder, final ProductListViewModel viewModel) {
        final TextView searchString = createdView.findViewById(R.id.search_string);

        rawBinder
                .bindApplier(new VisibilityApplier(createdView.findViewById(R.id.load_spinner)), viewModel.isLoadSpinnerVisible())
                .bindApplier(new VisibilityApplier(createdView.findViewById(R.id.empty_state)), viewModel.isEmptyStateVisible())
                .bindApplier(new OnClickApplier(createdView.findViewById(R.id.search_button)), viewModel.searchCommand())
                .bindApplier(new RecyclerArrayApplier((RecyclerView) createdView.findViewById(R.id.recycler_product_list), new ProductItemViewSource()),
                        new Callable<ArrayList<ProductItem>>() {
                            @Override
                            public ArrayList<ProductItem> call() throws Exception {
                                return viewModel.ProductItems();
                            }
                        }, viewModel.ProductItemsChanged())
                .bindApplier(
                        new OnEditorKeyboardActionApplier((TextView) createdView.findViewById(R.id.search_string), EditorInfo.IME_ACTION_SEARCH),
                        new ICallback() {
                            @Override
                            public void execute() {
                                if (searchString.getText().toString().isEmpty()) {
                                    return;
                                }

                                if (viewModel.searchCommand().canExecute()) {
                                    // Hide soft input
                                    ViewSourceUtils.hideSoftInput(searchString);

                                    // Search the string
                                    viewModel.searchCommand().execute();

                                    // Move cursor to end of text
                                    ((EditText) searchString).setSelection(searchString.getText().length());
                                }
                            }
                        });
    }
}