package com.example.riberdpuzzle.androidsamplepaging;

import android.arch.paging.DataSource;


public class RepoDataSourceFactory extends DataSource.Factory<Integer, Repo> {
    private final GitHubRepository gitHubRepository;

    public RepoDataSourceFactory(GitHubRepository gitHubRepository) {
        this.gitHubRepository = gitHubRepository;
    }

    @Override
    public DataSource<Integer, Repo> create() {
        return new PageKeyedRepoDataSource(gitHubRepository);
    }
}
