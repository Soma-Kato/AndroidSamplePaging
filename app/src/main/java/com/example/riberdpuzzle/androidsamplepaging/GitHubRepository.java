package com.example.riberdpuzzle.androidsamplepaging;

import java.util.List;

import io.reactivex.Single;


public interface GitHubRepository {
    Single<List<Repo>> getRepos(final int pageNum);
}
