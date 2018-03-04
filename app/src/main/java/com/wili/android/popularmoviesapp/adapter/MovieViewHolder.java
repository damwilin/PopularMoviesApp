package com.wili.android.popularmoviesapp.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wili.android.popularmoviesapp.R;

/**
 * Created by Damian on 24.02.2018.
 */

public class MovieViewHolder extends RecyclerView.ViewHolder {
    private final TextView title;

    public MovieViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.movie_title);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }
}
