package com.wili.android.popularmoviesapp.repository.api;

import android.util.Log;

import com.wili.android.popularmoviesapp.repository.model.Movie;
import com.wili.android.popularmoviesapp.repository.model.MovieList;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.wili.android.popularmoviesapp.repository.api.ApiParam.API_KEY;

/**
 * Created by Damian on 06.03.2018.
 */

public class ApiSync {
    private Retrofit retrofit;
    private ApiService apiService;
    private List<Movie> movieList;
    private OkHttpClient.Builder client;
    private static final String LOG_TAG = ApiSync.class.getSimpleName();

    public ApiSync() {
        configureRetrofit();
        createRetrofitClient();
    }

    private void configureRetrofit() {
        client = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        client.addInterceptor(interceptor);
        client.build();
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiParam.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void createRetrofitClient() {
        apiService = retrofit.create(ApiService.class);
    }


    public List<Movie> getPopularMovies() {
        final MovieList[] getResponse = new MovieList[1];
        Call<MovieList> call = apiService.getPopularMovies(API_KEY);
        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                getResponse[0] = response.body();
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {

            }
        });
        return getResponse[0].getMovieList();
    }

    public List<Movie> getTopRatedMovies() {
        try {
            movieList = apiService.getTopRatedMovies(API_KEY).execute().body().getMovieList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movieList;
    }


}
