package com.wili.android.popularmoviesapp.repository.network;

import com.wili.android.popularmoviesapp.BuildConfig;

/**
 * Created by Damian on 05.03.2018.
 */

public class ApiParam {
    public static final String BASE_URL = "https://api.themoviedb.org";
    public static final String TOP_RATED_PATH = "3/movie/top_rated?api_key=";
    public static final String POPULAR_PATH = "3/movie/popular?api_key=";
    public static final String API_KEY = BuildConfig.THE_MOVIE_DB_API_KEY;

    public static final String MOVIE_PATH = "3/movie/";
    public static final String API_PATH = "?api_key=";

    public static final String POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342";
}
