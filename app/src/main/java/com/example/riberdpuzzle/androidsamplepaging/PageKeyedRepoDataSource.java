package com.example.riberdpuzzle.androidsamplepaging;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class PageKeyedRepoDataSource extends PageKeyedDataSource<Integer, Repo> {
    private final GitHubRepository gitHubRepository;

    public PageKeyedRepoDataSource(GitHubRepository gitHubRepository) {
        this.gitHubRepository = gitHubRepository;
    }

    @Override
    public void loadInitial(@NonNull final LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Repo> callback) {
        gitHubRepository.getRepos(1)
                .subscribeOn(Schedulers.newThread())
                .subscribeWith(new DisposableSingleObserver<List<Repo>>() {
                    @Override
                    public void onSuccess(List<Repo> repos) {
                        callback.onResult(repos, null, 2);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("", "");
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Repo> callback) {
        gitHubRepository.getRepos(params.key)
                .subscribeOn(Schedulers.newThread())
                .subscribeWith(new DisposableSingleObserver<List<Repo>>() {
                    @Override
                    public void onSuccess(List<Repo> repos) {
                        callback.onResult(repos, params.key - 1);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("", "");
                    }
                });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Repo> callback) {
        gitHubRepository.getRepos(params.key)
                .subscribeOn(Schedulers.newThread())
                .subscribeWith(new DisposableSingleObserver<List<Repo>>() {
                    @Override
                    public void onSuccess(List<Repo> repos) {
                        callback.onResult(repos, params.key + 1);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("", "");
                    }
                });
    }
}
