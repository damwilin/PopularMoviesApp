package com.wili.android.popularmoviesapp.activity.main;

import android.util.Log;

import com.wili.android.popularmoviesapp.repository.MoviesRepository;
import com.wili.android.popularmoviesapp.repository.database.DbManager;
import com.wili.android.popularmoviesapp.repository.model.Movie;
import com.wili.android.popularmoviesapp.repository.network.MovieJSONResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Damian on 24.02.2018.
 */

public class MainActivityPresenter {

    private MainActivityView view;
    private MoviesRepository repository;
    private DbManager dbManager;

    public MainActivityPresenter(MainActivityView view, MoviesRepository repository, DbManager dbManager) {
        this.view = view;
        this.repository = repository;
        this.dbManager = dbManager;

    }

    public void loadPopularMovies() {
        view.showLoading();
        repository.getPopularMovieList().enqueue(new retrofit2.Callback<MovieJSONResponse>() {
            @Override
            public void onResponse(Call<MovieJSONResponse> call, Response<MovieJSONResponse> response) {
                view.displayMovies(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieJSONResponse> call, Throwable t) {
                view.displayNoMovies();
            }
        });
    }

    public void loadTopRatedMovies() {
        view.showLoading();
        repository.getTopRatedMovieList().enqueue(new retrofit2.Callback<MovieJSONResponse>() {
            @Override
            public void onResponse(Call<MovieJSONResponse> call, Response<MovieJSONResponse> response) {
                view.displayMovies(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieJSONResponse> call, Throwable t) {
                view.displayNoMovies();
            }
        });
    }

    public void loadFavorites() {
        view.showLoading();
        List<Movie> movieList = dbManager.getMoviesList();
        Log.d(MainActivityPresenter.class.getSimpleName(), movieList.get(0).getPosterPath());
        view.displayMovies(movieList);
    }
}
