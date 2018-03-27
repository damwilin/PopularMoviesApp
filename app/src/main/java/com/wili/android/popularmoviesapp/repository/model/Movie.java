package com.wili.android.popularmoviesapp.repository.model;

import com.google.gson.annotations.SerializedName;

import static com.wili.android.popularmoviesapp.repository.network.ApiParam.POSTER_BASE_URL;

/**
 * Created by Damian on 24.02.2018.
 */

public class Movie {
    @SerializedName("title")
    private String title;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("overview")
    private String overview;
    @SerializedName("vote_average")
    private String voteAverage;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("id")
    private String movieId;
    @SerializedName("backdrop_path")
    private String backgroundImagePath;

    private boolean isFavourite;

    public Movie(String title, String posterPath, String overview, String voteAverage, String releaseDate, String movieI, String backgroundImagePath) {
        this.title = title;
        this.posterPath = posterPath;
        this.overview = overview;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
        this.movieId = movieId;
        this.posterPath = posterPath;
        this.backgroundImagePath = backgroundImagePath;
    }

    public Movie() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return POSTER_BASE_URL + posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setIsFavourite(boolean mFavourite) {
        this.isFavourite = mFavourite;
    }

    public boolean getIsFavourite() {
        return isFavourite;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getBackgroundImagePath() {
        return POSTER_BASE_URL + backgroundImagePath;
    }
}
