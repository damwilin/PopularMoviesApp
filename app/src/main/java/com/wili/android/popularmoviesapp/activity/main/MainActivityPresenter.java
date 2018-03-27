package com.wili.android.popularmoviesapp.activity.main;

import com.wili.android.popularmoviesapp.repository.MoviesRepository;
import com.wili.android.popularmoviesapp.repository.network.MovieJSONResponse;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Damian on 24.02.2018.
 */

public class MainActivityPresenter {

    private MainActivityView view;
    private MoviesRepository repository;

    public MainActivityPresenter(MainActivityView view, MoviesRepository repository) {
        this.view = view;
        this.repository = repository;
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
        //TODO loadFavorites
    }
}
