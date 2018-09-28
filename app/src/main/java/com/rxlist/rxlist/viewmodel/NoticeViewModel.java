package com.rxlist.rxlist.viewmodel;

import android.content.Context;
import android.content.Intent;

import com.rxlist.rxlist.ItemActivity;
import com.rxlist.rxlist.binding.ICommand;
import com.rxlist.rxlist.model.Notice;

public class NoticeViewModel {

    private final Notice _model;
    private final Context _context;

    public NoticeViewModel(Context context, Notice model) {
        _context = context;
        _model = model;
    }

    public String title() {
        return _model.getTitle();
    }

    public String brief() {
        return _model.getBrief();
    }

    public String filePath() {
        return _model.getFileSource();
    }

    public ICommand activateCommand() {
        return new ICommand() {
            @Override
            public boolean canExecute() {
                return true;
            }

            @Override
            public void execute() {
                Intent intent = new Intent(_context, ItemActivity.class);
                _context.startActivity(intent);
            }
        };
    }
}