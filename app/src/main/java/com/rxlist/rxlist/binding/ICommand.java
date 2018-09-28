package com.rxlist.rxlist.binding;

public interface ICommand
{
    boolean canExecute();
    void execute();
}
