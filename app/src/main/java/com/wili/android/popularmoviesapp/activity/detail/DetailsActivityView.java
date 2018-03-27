package com.wili.android.popularmoviesapp.activity.detail;

import com.wili.android.popularmoviesapp.repository.model.Movie;
import com.wili.android.popularmoviesapp.repository.model.Review;

import java.util.List;

/**
 * Created by Damian on 24.02.2018.
 */

public interface DetailsActivityView {
    void displayMovieDetails(Movie movie);

    void displayNoDetails();

    void displayReviews(List<Review> reviewList);

    void displayNoReviews();
}
