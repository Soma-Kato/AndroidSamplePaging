package com.example.riberdpuzzle.androidsamplepaging;

import java.util.List;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


public class ApiGitHubClient {

    private final Retrofit retrofit;

    public ApiGitHubClient(String endPoint) {
        retrofit = new Retrofit.Builder()
                .baseUrl(endPoint)
                .client(new OkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public Single<List<Repo>> getRepos(final int pageNum) {
        return retrofit
                .create(ApiGitHub.class)
                .getRepos(pageNum);
    }
}
