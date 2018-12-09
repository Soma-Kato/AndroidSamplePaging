package com.example.riberdpuzzle.androidsamplepaging;


import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class BaseIndicatorPagingAdapter<T> extends PagedListAdapter<T, RecyclerView.ViewHolder> {
    private final static int INDICATOR_SIZE = 1;

    protected final Context context;
    private boolean showIndicator = false;

    abstract @LayoutRes
    int getLoadingLayoutRes();

    BaseIndicatorPagingAdapter(@NonNull DiffUtil.ItemCallback<T> diffCallback, Context context) {
        super(diffCallback);
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IndicatorViewHolder(LayoutInflater.from(context).inflate(getLoadingLayoutRes(), parent, false));
    }

    public void setShowIndicator(boolean showIndicator) {
        this.showIndicator = showIndicator;
        if (showIndicator) {
            notifyItemInserted(getItemCount());
        } else {
            notifyItemRemoved(getItemCount());
        }
    }

    @Override
    public int getItemCount() {
        if (showIndicator) {
            return super.getItemCount() + INDICATOR_SIZE;
        } else {
            return super.getItemCount();
        }
    }

    class IndicatorViewHolder extends RecyclerView.ViewHolder {
        IndicatorViewHolder(View itemView) {
            super(itemView);
        }
    }
}