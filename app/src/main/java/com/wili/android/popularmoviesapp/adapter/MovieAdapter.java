package com.wili.android.popularmoviesapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wili.android.popularmoviesapp.R;
import com.wili.android.popularmoviesapp.repository.model.Movie;

import java.util.List;

/**
 * Created by Damian on 24.02.2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> movieList;
    private final MovieAdapterOnClickHandler onClickHandler;

    public interface MovieAdapterOnClickHandler {
        void onClick(String movieId);
    }

    public MovieAdapter(List<Movie> movieList, MovieAdapterOnClickHandler onClickHandler) {
        this.movieList = movieList;
        this.onClickHandler = onClickHandler;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        ImageView imageView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.movie_title);
            this.imageView = itemView.findViewById(R.id.movie_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            String movieId = movieList.get(adapterPosition).getMovieId();
            onClickHandler.onClick(movieId);
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
        String posterPath = movieList.get(position).getPosterPath();
        holder.textView.setText(title);
        Picasso.get()
                .load(posterPath)
                .into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return movieList.size();
    }



}

