package com.wili.android.popularmoviesapp.repository.network;

import com.wili.android.popularmoviesapp.repository.model.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.wili.android.popularmoviesapp.repository.network.ApiParam.API_PATH;
import static com.wili.android.popularmoviesapp.repository.network.ApiParam.MOVIE_PATH;
import static com.wili.android.popularmoviesapp.repository.network.ApiParam.POPULAR_PATH;
import static com.wili.android.popularmoviesapp.repository.network.ApiParam.TOP_RATED_PATH;

/**
 * Created by Damian on 04.03.2018.
 */

public interface RequestInterface {
    @GET(POPULAR_PATH)
    Call<JSONResponse> getPopularMoviesJSON(@Query("api_key") String apiKey);

    @GET(TOP_RATED_PATH)
    Call<JSONResponse> getTopRatedMoviesJSON(@Query("api_key") String apiKey);

    @GET(MOVIE_PATH + "{id}" + API_PATH)
    Call<Movie> getMovieJSON(@Path("id") String id, @Query("api_key") String apiKey);
}
