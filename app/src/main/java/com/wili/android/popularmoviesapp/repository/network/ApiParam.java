package com.wili.android.popularmoviesapp.repository.network;

import com.wili.android.popularmoviesapp.BuildConfig;

/**
 * Created by Damian on 05.03.2018.
 */

public class ApiParam {
    public static final String YT_IMAGE_URL = "https://img.youtube.com/vi/";
    public static final String YT_IMAGE_DEFAULT_QUALITY = "/default.jpg";


    public static final String YT_BASE_URL = "http://www.youtube.com/watch?v=";
    public static final String BASE_URL = "https://api.themoviedb.org";
    public static final String TOP_RATED_PATH = "3/movie/top_rated?api_key=";
    public static final String POPULAR_PATH = "3/movie/popular?api_key=";
    public static final String API_KEY = BuildConfig.THE_MOVIE_DB_API_KEY;
    public static final String MOVIE_PATH = "3/movie/";
    public static final String API_PATH = "?api_key=";
    public static final String REVIEWS_PATH = "/reviews";
    public static final String VIDEOS_PATH = "/videos";
    public static final String POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342";
}
