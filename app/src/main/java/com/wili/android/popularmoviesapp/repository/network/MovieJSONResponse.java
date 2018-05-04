package com.wili.android.popularmoviesapp.repository.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.wili.android.popularmoviesapp.repository.model.Movie;

import java.util.List;

/**
 * Created by Damian on 11.03.2018.
 */

public class MovieJSONResponse {
    @SerializedName("results")
    @Expose
    public List<Movie> results;
    public List<Movie> getResults() {
        return results;
    }
}