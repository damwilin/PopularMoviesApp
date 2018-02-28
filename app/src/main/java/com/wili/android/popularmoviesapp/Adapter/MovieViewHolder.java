package com.wili.android.popularmoviesapp.Adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wili.android.popularmoviesapp.R;

/**
 * Created by Damian on 24.02.2018.
 */

public class MovieViewHolder extends RecyclerView.ViewHolder {
    public final TextView title;
    public final ImageView background;

    public MovieViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.movie_title);
        background = (ImageView) itemView.findViewById(R.id.background_image);
    }
}
