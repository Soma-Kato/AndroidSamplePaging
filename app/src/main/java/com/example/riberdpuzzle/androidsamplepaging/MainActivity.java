package com.example.riberdpuzzle.androidsamplepaging;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LiveData<PagedList<Repo>> repoLiveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();
        setupDataSource();
        setupObserve();
    }

    private void setupViews() {
        recyclerView = findViewById(R.id.recycler_view);
        SamplePagingAdapter adapter = new SamplePagingAdapter(diff, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void setupDataSource() {
        GitHubRepository repository = new GitHubRepositoryImpl("https://api.github.com/");
        RepoDataSourceFactory factory = new RepoDataSourceFactory(repository);

        PagedList.Config config = new PagedList.Config.Builder()
                .setInitialLoadSizeHint(1)
                .setPageSize(1)
                .build();

        repoLiveData = new LivePagedListBuilder(factory, config).build();
    }

    private void setupObserve() {
        repoLiveData.observe(this, new Observer<PagedList<Repo>>() {
            @Override
            public void onChanged(@Nullable PagedList<Repo> repos) {
                SamplePagingAdapter adapter = (SamplePagingAdapter) recyclerView.getAdapter();
                adapter.submitList(repos);
            }
        });
    }

    private DiffUtil.ItemCallback<Repo> diff = new DiffUtil.ItemCallback<Repo>() {
        @Override
        public boolean areItemsTheSame(Repo oldItem, Repo newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Repo oldItem, Repo newItem) {
            return oldItem.getId() == newItem.getId();
        }
    };
}
