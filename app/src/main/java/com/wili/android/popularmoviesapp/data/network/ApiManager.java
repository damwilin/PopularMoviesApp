package com.wili.android.popularmoviesapp.data.network;

import com.wili.android.popularmoviesapp.data.model.Movie;

import retrofit2.Call;

/**
 * Created by Damian on 24.02.2018.
 */

public interface ApiManager {
    Call<MovieJSONResponse> getPopularMovieList();

    Call<MovieJSONResponse> getTopRatedMovieList();

    Call<Movie> getMovie(String id);

    Call<ReviewJSONResponse> getReviewList(String id);

    Call<VideoJSONResponse> getVideosList(String id);
}
