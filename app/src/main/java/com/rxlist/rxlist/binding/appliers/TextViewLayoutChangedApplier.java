package com.rxlist.rxlist.binding.appliers;
import android.view.View;
import android.widget.TextView;

import com.rxlist.rxlist.binding.ICommand;

public class TextViewLayoutChangedApplier implements IBindingApplier<ICommand>
{
    private final TextView _textView;
    private View.OnLayoutChangeListener _textListener;

    public TextViewLayoutChangedApplier(TextView view)
    {
        _textView = view;
    }

    public void initialize(ICommand command) {
        update(command);
    }

    public void update(final ICommand command)
    {
        _textListener = new View.OnLayoutChangeListener()
        {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                // its possible that the layout is not complete in which case
                // we will get all zero values for the positions, so ignore the event
                if (left == 0 && top == 0 && right == 0 && bottom == 0) {
                    return;
                }

                if (command.canExecute()) {
                    command.execute();
                }
            }
        };

        _textView.addOnLayoutChangeListener(_textListener);
    }

    public void terminate() {
        _textView.removeOnLayoutChangeListener(_textListener);
    }
}