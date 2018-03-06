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

    private List<Movie> movieListItems;

    public MovieAdapter(List<Movie> movieListItems) {
        this.movieListItems = movieListItems;
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
        String title = movieListItems.get(position).getTitle();
        holder.movieTitle.setText(title);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return movieListItems.size();
    }

    public void setMovieListItems(List<Movie> movieListItems) {
        this.movieListItems = movieListItems;
        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_view)
        CardView cardView;
        @BindView(R.id.movie_title)
        TextView movieTitle;
        @BindView(R.id.movie_image)
        ImageView movieImage;

        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }
}

