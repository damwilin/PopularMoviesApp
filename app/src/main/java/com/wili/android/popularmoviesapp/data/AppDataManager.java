package com.wili.android.popularmoviesapp.data;

import com.wili.android.popularmoviesapp.data.database.DbManager;
import com.wili.android.popularmoviesapp.data.model.Movie;
import com.wili.android.popularmoviesapp.data.network.ApiManager;
import com.wili.android.popularmoviesapp.data.network.MovieJSONResponse;
import com.wili.android.popularmoviesapp.data.network.ReviewJSONResponse;
import com.wili.android.popularmoviesapp.data.network.VideoJSONResponse;

import java.util.List;

import retrofit2.Call;

public class AppDataManager implements DataManager {
    private ApiManager apiManager;
    private DbManager dbManager;

    public AppDataManager(ApiManager apiManager, DbManager dbManager) {
        this.apiManager = apiManager;
        this.dbManager = dbManager;
    }

    @Override
    public void saveMovieToFavourite(Movie movie) {
        dbManager.saveMovieToFavourite(movie);
    }

    @Override
    public void deleteMovieFromFavourite(String id) {
        dbManager.deleteMovieFromFavourite(id);
    }

    @Override
    public List<Movie> getFavouriteMoviesList() {
        return dbManager.getFavouriteMoviesList();
    }

    @Override
    public boolean isFavourite(String id) {
        return dbManager.isFavourite(id);
    }

    @Override
    public Call<MovieJSONResponse> getPopularMovieList() {
        return apiManager.getPopularMovieList();
    }

    @Override
    public Call<MovieJSONResponse> getTopRatedMovieList() {
        return apiManager.getTopRatedMovieList();
    }

    @Override
    public Call<Movie> getMovie(String id) {
        return apiManager.getMovie(id);
    }

    @Override
    public Call<ReviewJSONResponse> getReviewList(String id) {
        return apiManager.getReviewList(id);
    }

    @Override
    public Call<VideoJSONResponse> getVideosList(String id) {
        return apiManager.getVideosList(id);
    }
}
