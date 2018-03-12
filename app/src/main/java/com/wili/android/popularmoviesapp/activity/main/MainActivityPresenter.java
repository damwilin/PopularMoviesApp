package com.wili.android.popularmoviesapp.activity.main;

import com.wili.android.popularmoviesapp.repository.MoviesRepository;
import com.wili.android.popularmoviesapp.repository.network.JSONResponse;

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
        repository.getPopularMovieList().enqueue(new retrofit2.Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                view.displayMovies(response.body().getResults());
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                view.displayNoMovies();
            }
        });
    }

    public void loadTopRatedMovies() {
        view.showLoading();
        repository.getTopRatedMovieList().enqueue(new retrofit2.Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                view.displayMovies(response.body().getResults());
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                view.displayNoMovies();
            }
        });
    }
}
