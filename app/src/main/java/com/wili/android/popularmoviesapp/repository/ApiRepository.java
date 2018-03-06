package com.wili.android.popularmoviesapp.repository;

import com.wili.android.popularmoviesapp.repository.api.ApiSync;
import com.wili.android.popularmoviesapp.repository.model.Movie;

import java.io.IOException;
import java.util.List;

/**
 * Created by Damian on 06.03.2018.
 */

public class ApiRepository implements MoviesRepository {

    private ApiSync apiSync = new ApiSync();

    @Override
    public List<Movie> getPopularMovieList() throws IOException {
        return apiSync.getPopularMovies();
    }

    @Override
    public List<Movie> getTopRatedMovieList() throws IOException {
        return apiSync.getTopRatedMovies();
    }
}
