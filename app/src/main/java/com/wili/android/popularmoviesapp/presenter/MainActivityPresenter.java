package com.wili.android.popularmoviesapp.presenter;

import com.wili.android.popularmoviesapp.repository.MoviesRepository;
import com.wili.android.popularmoviesapp.repository.model.Movie;
import com.wili.android.popularmoviesapp.view.MainActivityView;

import java.util.List;

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
        List<Movie> movieList = repository.getPopularMovieList();
        if (!movieList.isEmpty()) {
            view.displayMovies(movieList);
        } else {
            view.displayNoMovies();
        }
    }

    public void loadTopRatedMovies() {
        List<Movie> movieList = repository.getTopRatedMovieList();
        if (!movieList.isEmpty()) {
            view.displayMovies(movieList);
        } else {
            view.displayNoMovies();
        }
    }
}
