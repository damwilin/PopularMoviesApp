package com.wili.android.popularmoviesapp.repository.database;

import android.provider.BaseColumns;

public class MoviesContract {

    private MoviesContract() {
    }

    public static final class MoviesEntry implements BaseColumns {

        public static final String TABLE_NAME = "movies";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_POSTER_PATH = "posterPath";
        public static final String COLUMN_NAME_MOVIE_ID = "id";
    }

}