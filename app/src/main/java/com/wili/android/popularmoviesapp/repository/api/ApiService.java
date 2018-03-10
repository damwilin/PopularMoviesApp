package com.wili.android.popularmoviesapp.repository.api;

import com.wili.android.popularmoviesapp.repository.model.Movie;
import com.wili.android.popularmoviesapp.repository.model.MovieList;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Damian on 04.03.2018.
 */

public interface ApiService {

    @GET(ApiParam.POPULAR_PATH)
    Call<MovieList> getPopularMovies(@Query("api_key") String apiKey);

    @GET(ApiParam.TOP_RATED_PATH)
    Call<MovieList> getTopRatedMovies(@Query("api_key") String apiKey);

}
