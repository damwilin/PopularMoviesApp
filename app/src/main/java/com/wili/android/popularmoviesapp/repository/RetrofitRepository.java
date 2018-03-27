package com.wili.android.popularmoviesapp.repository;

import com.wili.android.popularmoviesapp.repository.model.Movie;
import com.wili.android.popularmoviesapp.repository.network.MovieJSONResponse;
import com.wili.android.popularmoviesapp.repository.network.RequestInterface;
import com.wili.android.popularmoviesapp.repository.network.RetrofitClient;
import com.wili.android.popularmoviesapp.repository.network.ReviewJSONResponse;

import retrofit2.Call;

import static com.wili.android.popularmoviesapp.repository.network.ApiParam.API_KEY;

/**
 * Created by Damian on 06.03.2018.
 */

public class RetrofitRepository implements MoviesRepository {
    private RequestInterface requestInterface;

    public RetrofitRepository() {
        this.requestInterface = RetrofitClient.getService(RequestInterface.class);
    }

    @Override
    public Call<MovieJSONResponse> getPopularMovieList() {
        return requestInterface.getPopularMoviesJSON(API_KEY);
    }

    @Override
    public Call<MovieJSONResponse> getTopRatedMovieList() {
        return requestInterface.getTopRatedMoviesJSON(API_KEY);
    }

    @Override
    public Call<Movie> getMovie(String id) {
        return requestInterface.getMovieJSON(id, API_KEY);
    }

    @Override
    public Call<ReviewJSONResponse> getReviewList(String id) {
        return requestInterface.getReviewJSON(id, API_KEY);
    }
}


