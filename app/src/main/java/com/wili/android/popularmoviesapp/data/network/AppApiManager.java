package com.wili.android.popularmoviesapp.data.network;

import com.wili.android.popularmoviesapp.data.model.Movie;

import retrofit2.Call;

import static com.wili.android.popularmoviesapp.data.network.ApiParam.API_KEY;

/**
 * Created by Damian on 06.03.2018.
 */

public class AppApiManager implements ApiManager {
    private RequestInterface requestInterface;

    public AppApiManager() {
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

    @Override
    public Call<VideoJSONResponse> getVideosList(String id) {
        return requestInterface.getVideosJSON(id, API_KEY);
    }
}


