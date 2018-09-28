package com.rxlist.rxlist.binding.appliers;

import android.view.View;

import com.rxlist.rxlist.binding.ICommand;

public class OnClickApplier implements IBindingApplier<ICommand>
{
    private final View _view;

    public OnClickApplier(View view)
    {
        _view = view;
    }

    public void initialize(ICommand command)
    {
        update(command);
    }

    public void update(final ICommand command)
    {
        _view.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (command.canExecute())
                {
                    command.execute();
                }
            }
        });
    }

    public void terminate()
    {
        _view.setOnClickListener(null);
    }
}