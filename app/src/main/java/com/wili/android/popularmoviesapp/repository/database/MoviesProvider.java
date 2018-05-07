package com.wili.android.popularmoviesapp.repository.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.wili.android.popularmoviesapp.repository.database.MoviesContract.MoviesEntry.COLUMN_NAME_MOVIE_ID;
import static com.wili.android.popularmoviesapp.repository.database.MoviesContract.MoviesEntry.CONTENT_URI;
import static com.wili.android.popularmoviesapp.repository.database.MoviesContract.MoviesEntry.TABLE_NAME;

public class MoviesProvider extends ContentProvider {

    private MoviesDbHelper moviesDbHelper;
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    public static final int CODE_MOVIE = 100;
    public static final int CODE_MOVIE_ID = 101;


    @Override
    public boolean onCreate() {
        moviesDbHelper = new MoviesDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final SQLiteDatabase writableDb = moviesDbHelper.getWritableDatabase();
        Uri returnUri;

        switch (sUriMatcher.match(uri)) {
            case CODE_MOVIE:
                long id = writableDb.insert(TABLE_NAME, null, values);
                if (id > 0) {
                    returnUri = ContentUris.withAppendedId(CONTENT_URI, id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unkown uri:" + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final SQLiteDatabase readableDb = moviesDbHelper.getReadableDatabase();
        Cursor cursor;

        switch (sUriMatcher.match(uri)) {
            case CODE_MOVIE:
                cursor = readableDb.query(
                        TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case CODE_MOVIE_ID:
                String id = uri.getLastPathSegment();
                String[] selectionArguments = new String[]{id};
                cursor = readableDb.query(
                        TABLE_NAME,
                        projection,
                        COLUMN_NAME_MOVIE_ID + " = ? ",
                        selectionArguments,
                        null,
                        null,
                        sortOrder
                );
                break;
            default:
                throw new UnsupportedOperationException("Unkown uri: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase writableDb = moviesDbHelper.getWritableDatabase();
        int numRowsDeleted;

        switch (sUriMatcher.match(uri)) {
            case CODE_MOVIE:
                numRowsDeleted = writableDb.delete(
                        TABLE_NAME,
                        selection,
                        selectionArgs
                );
                break;
            default:
                throw new UnsupportedOperationException("Unkown uri: " + uri);
        }
        return numRowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    public static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher((UriMatcher.NO_MATCH));
        final String authority = MoviesContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, MoviesContract.PATH_MOVIE, CODE_MOVIE);
        matcher.addURI(authority, MoviesContract.PATH_MOVIE + "/#", CODE_MOVIE_ID);

        return matcher;
    }
}
