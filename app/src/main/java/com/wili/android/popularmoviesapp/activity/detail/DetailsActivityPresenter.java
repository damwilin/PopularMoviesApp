package com.wili.android.popularmoviesapp.activity.detail;

import android.util.Log;

import com.wili.android.popularmoviesapp.data.DataManager;
import com.wili.android.popularmoviesapp.data.model.Movie;
import com.wili.android.popularmoviesapp.data.model.Review;
import com.wili.android.popularmoviesapp.data.network.ReviewJSONResponse;
import com.wili.android.popularmoviesapp.data.network.VideoJSONResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Damian on 24.02.2018.
 */

public class DetailsActivityPresenter {
    private DetailsActivityView view;
    private DataManager dataManager;

    public DetailsActivityPresenter(DetailsActivityView view, DataManager dataManager) {
        this.view = view;
        this.dataManager = dataManager;
    }

    public void loadMovieDetails(String id) {
        dataManager.getMovie(id).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                view.displayMovieDetails(response.body());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                view.displayNoDetails();
            }
        });
    }

    public void loadVideos(String id) {
        dataManager.getVideosList(id).enqueue(new Callback<VideoJSONResponse>() {
            @Override
            public void onResponse(Call<VideoJSONResponse> call, Response<VideoJSONResponse> response) {
                view.displayVideos(response.body().getVideosList());
            }

            @Override
            public void onFailure(Call<VideoJSONResponse> call, Throwable t) {
                view.displayNoVideos();
            }
        });
    }

    public void loadReviews(String id) {
        dataManager.getReviewList(id).enqueue(new Callback<ReviewJSONResponse>() {
            @Override
            public void onResponse(Call<ReviewJSONResponse> call, Response<ReviewJSONResponse> response) {
                List<Review> reviewsList = response.body().getReviewList();
                if (reviewsList.isEmpty()) {
                    view.displayNoReviews();
                } else {
                    view.displayReviews(response.body().getReviewList());
                }
            }

            @Override
            public void onFailure(Call<ReviewJSONResponse> call, Throwable t) {
                view.displayNoReviews();
            }
        });
    }

    private void addToFavourites(String id) {
        dataManager.getMovie(id).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                dataManager.saveMovieToFavourite(response.body());
                view.displayToastAddedToFavourites();
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                view.displayToastAddedToFavouritesError();
            }
        });
    }

    private void deleteFromFavourites(String id) {
        dataManager.deleteMovieFromFavourite(id);
        view.displayToastDeletedFromFavourites();
    }

    public void switchFavourite(String id) {
        if (!dataManager.isFavourite(id)) {
            addToFavourites(id);
            view.displayFavourite();
            Log.d(DetailsActivityPresenter.class.getSimpleName(), "ADDED to favourites: " + id);
        } else {
            deleteFromFavourites(id);
            view.displayNoFavourite();
            Log.d(DetailsActivityPresenter.class.getSimpleName(), "DELETED from favourites: " + id);
        }
    }

    public void loadFavouriteIcon(String id) {
        if (dataManager.isFavourite(id))
            view.displayFavourite();
        else
            view.displayNoFavourite();
    }
}
