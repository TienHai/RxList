package com.rxlist.rxlist.binding;

import com.rxlist.rxlist.binding.appliers.IBindingApplier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class RawBinder implements IRawBinder {

    private List<IUnbinder> _unbinders;

    public RawBinder()
    {
        _unbinders = new ArrayList<IUnbinder>();
    }

    @Override
    public IRawBinder addUnbinder(IUnbinder unbinder)
    {
        _unbinders.add(unbinder);
        return this;
    }

    @Override
    public IRawBinder bindApplier(IBindingApplier<Void> applier)
    {
        addUnbinder(new ApplyerTerminationUnbinder<Void>(applier));
        applier.initialize(null);
        return this;
    }

    @Override
    public <E> IRawBinder bindApplier(IBindingApplier<E> applier, E element)
    {
        addUnbinder(new ApplyerTerminationUnbinder<E>(applier));
        applier.initialize(element);
        return this;
    }

    @Override
    public <E> IRawBinder bindApplier(final IBindingApplier<E> applier, final Callable<E> retrieveElement, IEvent changed) {
        addUnbinder(new ApplyerTerminationUnbinder<E>(applier));

        ICallback callback = new ICallback() {
            @Override
            public void execute() {
                try {
                    applier.update(retrieveElement.call());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        try {
            applier.initialize(retrieveElement.call());
        } catch (Exception e) {
            e.printStackTrace();
        }

        changed.subscribe(callback);

        return this;
    }

    @Override
    public IRawBinder bindApplier(IBindingApplier<Boolean> applier, final IBooleanObservable observable) {
        return bindApplier(
                applier,
                new Callable<Boolean>() {
                    @Override
                    public Boolean call()
                    {
                        return observable.value();
                    }
                },
                observable.changed());
    }

    private static class ApplyerTerminationUnbinder<E> implements IUnbinder 
    {
        private IBindingApplier<E> _applier;

        public ApplyerTerminationUnbinder(IBindingApplier<E> applier)
        {
            _applier = applier;
        }

        public void unbind()
        {
            if (_applier != null) {
                IBindingApplier<E> applier = _applier;
                _applier = null;
                applier.terminate();
            }
        }
    }
}
