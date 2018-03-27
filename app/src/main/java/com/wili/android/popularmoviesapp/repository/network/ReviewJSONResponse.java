package com.wili.android.popularmoviesapp.repository.network;

import com.google.gson.annotations.SerializedName;
import com.wili.android.popularmoviesapp.repository.model.Review;

import java.util.List;

public class ReviewJSONResponse {
    @SerializedName("results")
    List<Review> reviewList;

    public List<Review> getReviewList() {
        return reviewList;
    }
}
