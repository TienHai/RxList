package com.rxlist.rxlist;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.rxlist.rxlist.binding.RawBinder;
import com.rxlist.rxlist.model.ProductItem;
import com.rxlist.rxlist.viewmodel.ProductViewModel;
import com.rxlist.rxlist.viewsource.ProductViewSource;

public class ItemActivity extends AppCompatActivity {

    private ProductViewSource _viewSource;
    private ProductViewModel _viewModel;
    private ProductItem _model;

    ItemActivity() {
        _viewSource = null;
        _viewModel = null;
        _model = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Intent intent = getIntent();
        _model = (ProductItem) intent.getSerializableExtra("ProductItem");
    }

    @Override
    public final void onStart() {
        super.onStart();
        _viewModel = createViewModel();
        RawBinder rawBinder = new RawBinder();
        _viewSource = new ProductViewSource();
        View createdView = _viewSource.createView(getLayoutInflater(), ItemActivity.this);
        FrameLayout frameLayout = findViewById(R.id.root_container);
        frameLayout.addView(createdView);
        _viewSource.bindValues(createdView, rawBinder, _viewModel);

        /*_unbinder = rawBinder.build();*/
    }

    @Override
    public final void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public final void onResume() {
        super.onResume();
    }

    @Override
    public final void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        /*
        _unbinder.unbind();
        _unbinder = null;
        */
        _viewModel = null;
        _viewSource = null;
        super.onStop();
    }

    /** */
    private ProductViewModel createViewModel() {
        ProductViewModel viewModel = new ProductViewModel(_model);
        return viewModel;
    }
}