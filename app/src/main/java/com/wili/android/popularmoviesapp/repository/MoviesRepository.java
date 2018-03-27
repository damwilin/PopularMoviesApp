package com.wili.android.popularmoviesapp.repository;

import com.wili.android.popularmoviesapp.repository.model.Movie;
import com.wili.android.popularmoviesapp.repository.network.MovieJSONResponse;
import com.wili.android.popularmoviesapp.repository.network.ReviewJSONResponse;

import retrofit2.Call;

/**
 * Created by Damian on 24.02.2018.
 */

public interface MoviesRepository {
    Call<MovieJSONResponse> getPopularMovieList();

    Call<MovieJSONResponse> getTopRatedMovieList();

    Call<Movie> getMovie(String id);

    Call<ReviewJSONResponse> getReviewList(String id);
}
