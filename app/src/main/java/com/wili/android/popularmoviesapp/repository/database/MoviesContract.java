package com.wili.android.popularmoviesapp.repository.database;

import android.net.Uri;
import android.provider.BaseColumns;

public class MoviesContract {

    private MoviesContract() {
    }

    public static final String CONTENT_AUTHORITY = "com.wili.android.popularmoviesapp";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_MOVIE = "movie";

    public static final class MoviesEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_MOVIE)
                .build();

        public static final String TABLE_NAME = "movies";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_POSTER_PATH = "posterPath";
        public static final String COLUMN_NAME_MOVIE_ID = "id";
    }

}
