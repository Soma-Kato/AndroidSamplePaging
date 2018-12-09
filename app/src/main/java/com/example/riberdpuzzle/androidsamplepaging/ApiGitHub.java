package com.example.riberdpuzzle.androidsamplepaging;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiGitHub {

    @GET("users/JakeWharton/repos")
    Single<List<Repo>> getRepos(
            @Query("num") String name);
}
