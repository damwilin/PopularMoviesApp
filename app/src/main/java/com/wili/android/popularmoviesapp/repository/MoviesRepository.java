package com.wili.android.popularmoviesapp.repository;

import com.wili.android.popularmoviesapp.repository.model.Movie;

import java.util.List;

/**
 * Created by Damian on 24.02.2018.
 */

public interface MoviesRepository {
    List<Movie> getMovies();
}
