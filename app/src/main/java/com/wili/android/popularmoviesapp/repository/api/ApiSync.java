package com.wili.android.popularmoviesapp.repository.api;

import android.content.res.Resources;
import android.widget.Toast;

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

    public List<Movie> getPopularMovies() {
        try {
            return this.apiService.getPopularMovies(ApiParam.API_KEY).execute().body().getMovieList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Movie> getTopRatedMovies() {
        try {
            return this.apiService.getTopRatedMovies(ApiParam.API_KEY).execute().body().getMovieList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
