package com.wili.android.popularmoviesapp.test;

import com.wili.android.popularmoviesapp.repository.api.ApiSync;
import com.wili.android.popularmoviesapp.repository.model.Movie;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by Damian on 06.03.2018.
 */
public class ApiServiceSyncTest {

    ApiSync apiSync;
    List<Movie> movieList;
    static final int MOVIE_LIST_SIZE = 20;

    @Before
    public void setUp() {
        apiSync = new ApiSync();
    }

    @Test
    public void shouldGetPopularMovies() {
        movieList = apiSync.getPopularMovies();
        System.out.println(movieList.get(0).getTitle());
        Assert.assertEquals(movieList.size(), MOVIE_LIST_SIZE);
    }

    @Test
    public void shouldGetTopRatedMovies() {
        movieList = apiSync.getTopRatedMovies();
        System.out.println(movieList.get(0).getTitle());
        Assert.assertEquals(movieList.size(), MOVIE_LIST_SIZE);
    }

}