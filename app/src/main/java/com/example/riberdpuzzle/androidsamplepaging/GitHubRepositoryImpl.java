package com.example.riberdpuzzle.androidsamplepaging;


import java.util.List;

import io.reactivex.Single;

public class GitHubRepositoryImpl implements GitHubRepository {
    private final ApiGitHubClient apiGitHubClient;

    public GitHubRepositoryImpl(String endPoint) {
        this.apiGitHubClient = new ApiGitHubClient(endPoint);
    }

    @Override
    public Single<List<Repo>> getRepos(final int pageNum) {
        return apiGitHubClient.getRepos(pageNum);
    }
}
