package com.wili.android.popularmoviesapp.repository.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.wili.android.popularmoviesapp.repository.model.Movie;

import java.util.List;

/**
 * Created by Damian on 11.03.2018.
 */

public class JSONResponse {
    @SerializedName("results")
    @Expose
    public List<Movie> results = null;

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
