package com.wili.android.popularmoviesapp.repository.api;

import com.wili.android.popularmoviesapp.repository.model.Movie;

import java.io.IOException;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Damian on 06.03.2018.
 */

public class ApiSync {
    private Retrofit retrofit;
    private ApiService apiService;

    public ApiSync() {
        configureRetrofit();
        createRetrofitClient();
    }

    private void configureRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiParam.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void createRetrofitClient() {
        apiService = retrofit.create(ApiService.class);
    }

    public List<Movie> getPopularMovies() throws IOException {
        return this.apiService.getPopularMovies(ApiParam.API_KEY).execute().body().getMovieList();
    }

    public List<Movie> getTopRatedMovies() throws IOException {
        return this.apiService.getTopRatedMovies(ApiParam.API_KEY).execute().body().getMovieList();
    }

}
