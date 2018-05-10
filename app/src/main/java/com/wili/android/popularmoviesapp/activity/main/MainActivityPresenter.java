package com.wili.android.popularmoviesapp.activity.main;

import com.wili.android.popularmoviesapp.data.DataManager;
import com.wili.android.popularmoviesapp.data.model.Movie;
import com.wili.android.popularmoviesapp.data.network.MovieJSONResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Damian on 24.02.2018.
 */

public class MainActivityPresenter {

    private MainActivityView view;
    private DataManager dataManager;


    public MainActivityPresenter(MainActivityView view, DataManager dataManager) {
        this.view = view;
        this.dataManager = dataManager;
    }

    public void loadPopularMovies() {
        view.showLoading();
        dataManager.getPopularMovieList().enqueue(new retrofit2.Callback<MovieJSONResponse>() {
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
        dataManager.getTopRatedMovieList().enqueue(new retrofit2.Callback<MovieJSONResponse>() {
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
        List<Movie> movieList = dataManager.getFavouriteMoviesList();
        if (movieList.isEmpty()) {
            view.displayNoMovies();
        } else {
            view.displayMovies(movieList);
        }
    }
}
