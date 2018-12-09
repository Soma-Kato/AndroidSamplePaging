package com.example.riberdpuzzle.androidsamplepaging;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class SamplePagingAdapter extends PagedListAdapter<Repo, RecyclerView.ViewHolder> {
    private final Context context;

    protected SamplePagingAdapter(@NonNull DiffUtil.ItemCallback<Repo> diffCallback, Context context) {
        super(diffCallback);
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_repo, parent, false);
        return new SampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SampleViewHolder) {
            SampleViewHolder sampleViewHolder = (SampleViewHolder) holder;
            Repo repo = getItem(position);
            sampleViewHolder.bind(repo);
        }
    }

    static class SampleViewHolder extends RecyclerView.ViewHolder {
        private TextView idTextView;
        private TextView nameTextView;

        public SampleViewHolder(View itemView) {
            super(itemView);
            idTextView = itemView.findViewById(R.id.repo_id);
            nameTextView = itemView.findViewById(R.id.repo_name);
        }

        public void bind(Repo repo) {
            idTextView.setText(String.valueOf(repo.getId()));
            nameTextView.setText(repo.getName());
        }
    }
}
