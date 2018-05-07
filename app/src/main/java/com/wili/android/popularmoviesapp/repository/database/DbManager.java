package com.wili.android.popularmoviesapp.repository.database;

import com.wili.android.popularmoviesapp.repository.model.Movie;

import java.util.List;

public interface DbManager {
    void saveMovie(Movie movie);

    void deleteMovie(String movieId);

    List<Movie> getMoviesList();

    boolean isFavourite(String movieId);
}
