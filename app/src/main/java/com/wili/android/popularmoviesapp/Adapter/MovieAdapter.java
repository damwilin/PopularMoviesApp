package com.wili.android.popularmoviesapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wili.android.popularmoviesapp.R;
import com.wili.android.popularmoviesapp.repository.model.Movie;

import java.util.List;

/**
 * Created by Damian on 24.02.2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private List<Movie> movieList;

    public MovieAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutForListItem = R.layout.movie_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutForListItem, parent, false);
        return new MovieViewHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        String title = movieList.get(position).getTitle();
        holder.title.setText(title);

    }


    @Override
    public int getItemCount() {
        return movieList.size();
    }

}

