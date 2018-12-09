package com.example.riberdpuzzle.androidsamplepaging;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ApiGitHub {

    @GET("users/JakeWharton/repos?page={page_num}")
    Single<List<Repo>> getRepos(
            @Path("page_num") int pageNum);
}
