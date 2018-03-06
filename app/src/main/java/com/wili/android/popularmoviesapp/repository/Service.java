package com.wili.android.popularmoviesapp.repository;

import com.wili.android.popularmoviesapp.repository.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Damian on 04.03.2018.
 */

public interface Service {

    @GET(ApiParam.POPULAR_PATH)
    Call<List<Movie>> getPopularMovies();

    @GET(ApiParam.TOP_RATED_PATH)
    Call<List<Movie>> getTopRatedMovies();
}
