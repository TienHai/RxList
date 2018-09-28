package com.rxlist.rxlist;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.rxlist.rxlist.binding.RawBinder;
import com.rxlist.rxlist.viewmodel.NoticeListViewModel;
import com.rxlist.rxlist.viewsource.NoticeListViewSource;

public class MainActivity extends AppCompatActivity {

    private NoticeListViewSource _viewSource;
    private NoticeListViewModel _viewModel;

    MainActivity() {
        _viewSource = null;
        _viewModel = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public final void onStart() {
        super.onStart();
        _viewModel = createViewModel();
        RawBinder rawBinder = new RawBinder();
        _viewSource = new NoticeListViewSource();
        View createdView = _viewSource.createView(getLayoutInflater(), MainActivity.this);
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
    private NoticeListViewModel createViewModel() {
        NoticeListViewModel viewModel = new NoticeListViewModel(MainActivity.this);
        return viewModel;
    }
}