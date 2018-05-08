package com.wili.android.popularmoviesapp.data.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.wili.android.popularmoviesapp.data.model.Movie;

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
