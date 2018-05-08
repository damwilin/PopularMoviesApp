package com.wili.android.popularmoviesapp.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.wili.android.popularmoviesapp.data.database.MoviesContract.MoviesEntry.COLUMN_NAME_MOVIE_ID;
import static com.wili.android.popularmoviesapp.data.database.MoviesContract.MoviesEntry.COLUMN_NAME_POSTER_PATH;
import static com.wili.android.popularmoviesapp.data.database.MoviesContract.MoviesEntry.COLUMN_NAME_TITLE;
import static com.wili.android.popularmoviesapp.data.database.MoviesContract.MoviesEntry.TABLE_NAME;
import static com.wili.android.popularmoviesapp.data.database.MoviesContract.MoviesEntry._ID;

public class MoviesDbHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "movies.db";
    static final int DATABASE_VERSION = 1;

    public MoviesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MOVIE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME_MOVIE_ID + " TEXT NOT NULL, " +
                        COLUMN_NAME_TITLE + " TEXT NOT NULL, " +
                        COLUMN_NAME_POSTER_PATH + " TEXT NOT NULL" +
                        ");";

        db.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
