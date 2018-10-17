package com.rxlist.rxlist.binding.appliers;

public abstract class VoidApplierBase implements IBindingApplier<Void> {
    @Override
    public final void initialize(Void value) {
        initialize();
    }

    @Override
    public final void update(Void value) {
    }

    protected abstract void initialize();
}
