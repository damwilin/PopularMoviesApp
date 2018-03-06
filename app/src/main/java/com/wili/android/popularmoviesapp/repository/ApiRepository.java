package com.wili.android.popularmoviesapp.repository;

import com.wili.android.popularmoviesapp.repository.api.ApiSync;
import com.wili.android.popularmoviesapp.repository.model.Movie;

import java.util.List;

/**
 * Created by Damian on 06.03.2018.
 */

public class ApiRepository implements MoviesRepository {

    private ApiSync apiSync = new ApiSync();

    @Override
    public List<Movie> getPopularMovieList() {
        return apiSync.getPopularMovies();
    }

    @Override
    public List<Movie> getTopRatedMovieList() {
        return apiSync.getTopRatedMovies();
    }
}
