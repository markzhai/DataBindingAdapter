package com.github.markzhai.recyclerview;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * @author markzhai on 16/3/18
 * @version 1.0.0
 */
public class BindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    protected final T mBinding;

    public BindingViewHolder(T binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public T getBinding() {
        return mBinding;
    }
}