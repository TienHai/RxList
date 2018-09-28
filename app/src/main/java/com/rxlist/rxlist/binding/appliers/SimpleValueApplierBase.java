package com.rxlist.rxlist.binding.appliers;

public abstract class SimpleValueApplierBase<E> implements IBindingApplier<E>
{
    public final void initialize(E value)
    {
        update(value);
    }

    public final void terminate()
    {
    }
}
