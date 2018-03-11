package com.wili.android.popularmoviesapp.repository;

import com.wili.android.popularmoviesapp.repository.api.JSONResponse;
import com.wili.android.popularmoviesapp.repository.api.RequestInterface;
import com.wili.android.popularmoviesapp.repository.api.RetrofitClient;

import retrofit2.Call;

import static com.wili.android.popularmoviesapp.repository.api.ApiParam.API_KEY;

/**
 * Created by Damian on 06.03.2018.
 */

public class RetrofitRepository implements MoviesRepository {
    private RequestInterface requestInterface;

    public RetrofitRepository() {
        this.requestInterface = RetrofitClient.getService(RequestInterface.class);
    }

    @Override
    public Call<JSONResponse> getPopularMovieList() {
        return requestInterface.getPopularMoviesJSON(API_KEY);
    }

    @Override
    public Call<JSONResponse> getTopRatedMovieList() {
        return requestInterface.getTopRatedMoviesJSON(API_KEY);
    }
}


