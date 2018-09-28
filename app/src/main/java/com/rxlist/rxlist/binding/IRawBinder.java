package com.rxlist.rxlist.binding;

import com.rxlist.rxlist.binding.appliers.IBindingApplier;

import java.util.concurrent.Callable;

public interface IRawBinder
{
    IRawBinder addUnbinder(IUnbinder unbinder);
    IRawBinder bindApplier(IBindingApplier<Void> applier);
    <E> IRawBinder bindApplier(IBindingApplier<E> applier, E element);
    <E> IRawBinder bindApplier(IBindingApplier<E> applier, Callable<E> retrieveElement, IEvent changed);
    IRawBinder bindApplier(IBindingApplier<Boolean> applier, IBooleanObservable observable);
}