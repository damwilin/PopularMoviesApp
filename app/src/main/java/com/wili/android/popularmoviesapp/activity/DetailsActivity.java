package com.wili.android.popularmoviesapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.wili.android.popularmoviesapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.movie_title)
    TextView title;
    @BindView(R.id.movie_poster)
    ImageView poster;
    @BindView(R.id.movie_release_date)
    TextView releaseDate;
    @BindView(R.id.movie_vote_average)
    TextView voteAverage;
    @BindView(R.id.movie_plot_synopsis)
    TextView plotSynopsis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
    }
}
