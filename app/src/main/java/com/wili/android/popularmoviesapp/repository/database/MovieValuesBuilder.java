package com.wili.android.popularmoviesapp.repository.database;

import android.content.ContentValues;

import com.wili.android.popularmoviesapp.repository.model.Movie;

public class MovieValuesBuilder {

    public static ContentValues buildFrom(Movie movie) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MoviesContract.MoviesEntry.COLUMN_NAME_MOVIE_ID, movie.getMovieId());
        contentValues.put(MoviesContract.MoviesEntry.COLUMN_NAME_TITLE, movie.getTitle());
        contentValues.put(MoviesContract.MoviesEntry.COLUMN_NAME_POSTER_PATH, movie.getBasePosterPath());
        return contentValues;
    }
}
