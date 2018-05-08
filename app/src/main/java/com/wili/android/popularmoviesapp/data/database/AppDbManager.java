package com.wili.android.popularmoviesapp.data.database;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;

import com.wili.android.popularmoviesapp.data.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class AppDbManager implements DbManager {
    private ContentResolver contentResolver;

    public AppDbManager(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    @Override
    public void saveMovieToFavourite(Movie movie) {
        ContentValues contentValues = MovieValuesBuilder.buildFrom(movie);
        contentResolver.insert(MoviesContract.MoviesEntry.CONTENT_URI, contentValues);
    }

    @Override
    public List<Movie> getFavouriteMoviesList() {
        List<Movie> moviesList = new ArrayList<>();
        Cursor cursor = contentResolver.query(MoviesContract.MoviesEntry.CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String title = cursor.getString(cursor.getColumnIndex(MoviesContract.MoviesEntry.COLUMN_NAME_TITLE));
                String id = cursor.getString(cursor.getColumnIndexOrThrow(MoviesContract.MoviesEntry.COLUMN_NAME_MOVIE_ID));
                String posterPath = cursor.getString(cursor.getColumnIndexOrThrow(MoviesContract.MoviesEntry.COLUMN_NAME_POSTER_PATH));
                Movie currMovie = new Movie();
                currMovie.setTitle(title);
                currMovie.setPosterPath(posterPath);
                currMovie.setMovieId(id);
                moviesList.add(currMovie);
            }
        }
        return moviesList;
    }

    @Override
    public void deleteMovieFromFavourite(String id) {
        String selection = MoviesContract.MoviesEntry.COLUMN_NAME_MOVIE_ID + " = ?";
        String[] selectionArgs = {id};
        contentResolver.delete(MoviesContract.MoviesEntry.CONTENT_URI, selection, selectionArgs);
    }

    @Override
    public boolean isFavourite(String id) {
        String selection = MoviesContract.MoviesEntry.COLUMN_NAME_MOVIE_ID + " = ? ";
        String[] selectionArgs = {id};
        String[] projection = {MoviesContract.MoviesEntry.COLUMN_NAME_MOVIE_ID};
        Cursor cursor = contentResolver.query(MoviesContract.MoviesEntry.CONTENT_URI, projection, selection, selectionArgs, null);
        return cursor.getCount() != 0;
    }
}
