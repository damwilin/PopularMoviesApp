package com.wili.android.popularmoviesapp.repository.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Damian on 06.03.2018.
 */

public class MovieList {

    @SerializedName("results")
    private List<Movie> movieList;

    public List<Movie> getMovieList() {
        return movieList;
    }
}
