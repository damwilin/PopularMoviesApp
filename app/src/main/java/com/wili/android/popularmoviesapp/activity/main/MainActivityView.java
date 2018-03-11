package com.wili.android.popularmoviesapp.activity.main;

import com.wili.android.popularmoviesapp.repository.model.Movie;

import java.util.List;

/**
 * Created by Damian on 24.02.2018.
 */

public interface MainActivityView {
    void displayMovies(List<Movie> movieList);

    void displayNoMovies();

    void showLoading();

    void hideLoading();
}
