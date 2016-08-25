package com.github.markzhai.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.List;

/**
 * Base Data Binding RecyclerView Adapter.
 *
 * @author markzhai on 16/8/25
 */
public abstract class BaseViewAdapter<T> extends RecyclerView.Adapter<BindingViewHolder> {

    protected final LayoutInflater mLayoutInflater;

    protected List<T> mCollection;
    protected Presenter mPresenter;
    protected Decorator mDecorator;

    public interface Presenter {

    }

    public interface Decorator {
        void addDecorator(BindingViewHolder holder, int position);
    }

    public BaseViewAdapter(Context context) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        final Object item = mCollection.get(position);
        holder.getBinding().setVariable(BR.item, item);
        holder.getBinding().setVariable(BR.presenter, getPresenter());
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mCollection.size();
    }

    public void remove(int position) {
        mCollection.remove(position);
        notifyItemRemoved(position);
    }

    public void clear() {
        mCollection.clear();
        notifyDataSetChanged();
    }

    public void setDecorator(Decorator decorator) {
        mDecorator = decorator;
    }

    public void setPresenter(Presenter presenter) {
        mPresenter = presenter;
    }

    protected Presenter getPresenter() {
        return mPresenter;
    }
}
