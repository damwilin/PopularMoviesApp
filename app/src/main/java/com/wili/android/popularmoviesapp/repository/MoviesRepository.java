package com.wili.android.popularmoviesapp.repository;

import com.wili.android.popularmoviesapp.repository.network.JSONResponse;

import retrofit2.Call;

/**
 * Created by Damian on 24.02.2018.
 */

public interface MoviesRepository {
    Call<JSONResponse> getPopularMovieList();

    Call<JSONResponse> getTopRatedMovieList();
}
