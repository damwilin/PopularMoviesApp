package com.wili.android.popularmoviesapp.activity.detail;

import android.util.Log;

import com.wili.android.popularmoviesapp.repository.MoviesRepository;
import com.wili.android.popularmoviesapp.repository.database.DbManager;
import com.wili.android.popularmoviesapp.repository.model.Movie;
import com.wili.android.popularmoviesapp.repository.network.ReviewJSONResponse;
import com.wili.android.popularmoviesapp.repository.network.VideoJSONResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Damian on 24.02.2018.
 */

public class DetailsActivityPresenter {
    private DetailsActivityView view;
    private MoviesRepository repository;
    private DbManager dbManager;

    public DetailsActivityPresenter(DetailsActivityView view, MoviesRepository repository, DbManager dbManager) {
        this.view = view;
        this.repository = repository;
        this.dbManager = dbManager;
    }

    public void loadMovieDetails(String id) {
        repository.getMovie(id).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                view.displayMovieDetails(response.body());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                view.displayNoDetails();
            }
        });
    }

    public void loadVideos(String id) {
        repository.getVideosList(id).enqueue(new Callback<VideoJSONResponse>() {
            @Override
            public void onResponse(Call<VideoJSONResponse> call, Response<VideoJSONResponse> response) {
                view.displayVideos(response.body().getVideosList());
            }

            @Override
            public void onFailure(Call<VideoJSONResponse> call, Throwable t) {
                view.displayNoVideos();
            }
        });
    }

    public void loadReviews(String id) {
        repository.getReviewList(id).enqueue(new Callback<ReviewJSONResponse>() {
            @Override
            public void onResponse(Call<ReviewJSONResponse> call, Response<ReviewJSONResponse> response) {
                view.displayReviews(response.body().getReviewList());
            }

            @Override
            public void onFailure(Call<ReviewJSONResponse> call, Throwable t) {
                view.displayNoReviews();
            }
        });
    }

    public void addToFavourites(String id) {
        repository.getMovie(id).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                dbManager.saveMovie(response.body());
                Log.d(DetailsActivity.class.getSimpleName(), "Added to favourites" + response.body().getTitle());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

                Log.d(DetailsActivity.class.getSimpleName(), "Error : Not added to favourites");
            }
        });
    }
}
