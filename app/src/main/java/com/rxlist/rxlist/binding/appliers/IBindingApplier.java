package com.rxlist.rxlist.binding.appliers;

public interface IBindingApplier<E>
{
    void initialize(E initialValue);
    void update(E value);
    void terminate();
}
