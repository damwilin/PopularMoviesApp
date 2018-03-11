package com.wili.android.popularmoviesapp.repository.api;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Damian on 04.03.2018.
 */

public interface RequestInterface {

    @GET(ApiParam.POPULAR_PATH)
    Call<JSONResponse> getPopularMoviesJSON(@Query("api_key") String apiKey);

    @GET(ApiParam.TOP_RATED_PATH)
    Call<JSONResponse> getTopRatedMoviesJSON(@Query("api_key") String apiKey);

}
