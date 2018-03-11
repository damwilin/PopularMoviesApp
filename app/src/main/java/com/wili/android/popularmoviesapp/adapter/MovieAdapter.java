package com.wili.android.popularmoviesapp.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wili.android.popularmoviesapp.R;
import com.wili.android.popularmoviesapp.repository.model.Movie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Damian on 24.02.2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> movieList;

    public MovieAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.movie_title);
        }
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        String title = movieList.get(position).getTitle();
        holder.textView.setText(title);
    }


    @Override
    public int getItemCount() {
        return movieList.size();
    }



}

