package com.wili.android.popularmoviesapp.activity.detail;

import com.wili.android.popularmoviesapp.repository.MoviesRepository;
import com.wili.android.popularmoviesapp.repository.model.Movie;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Damian on 24.02.2018.
 */

public class DetailsActivityPresenter {
    private DetailsActivityView view;
    private MoviesRepository repository;

    public DetailsActivityPresenter(DetailsActivityView view, MoviesRepository repository) {
        this.view = view;
        this.repository = repository;

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
}
