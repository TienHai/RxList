package com.rxlist.rxlist.binding;

public interface IEvent
{
    void subscribe(ICallback callback);
    void changed();
}