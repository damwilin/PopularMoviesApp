package com.wili.android.popularmoviesapp.repository.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Damian on 06.03.2018.
 */

public class MovieList {

    @SerializedName("results")
    @Expose
    public List<Movie> movieList;

    public List<Movie> getMovieList() {
        return movieList;
    }

    public MovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
}
