package com.wili.android.popularmoviesapp.data.database;

import com.wili.android.popularmoviesapp.data.model.Movie;

import java.util.List;

public interface DbManager {
    void saveMovieToFavourite(Movie movie);

    void deleteMovieFromFavourite(String id);

    List<Movie> getFavouriteMoviesList();

    boolean isFavourite(String id);
}
