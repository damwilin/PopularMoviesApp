package com.wili.android.popularmoviesapp.activity.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wili.android.popularmoviesapp.R;
import com.wili.android.popularmoviesapp.repository.MoviesRepository;
import com.wili.android.popularmoviesapp.repository.RetrofitRepository;
import com.wili.android.popularmoviesapp.repository.model.Movie;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity implements DetailsActivityView {

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

    private DetailsActivityPresenter presenter;
    private MoviesRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        repository = new RetrofitRepository();
        presenter = new DetailsActivityPresenter(this, repository);

        String id = getIntent().getExtras().getString("id");
        presenter.loadMovieDetails(id);

    }

    @Override
    public void displayMovieDetails(Movie movie) {
        title.setText(movie.getTitle());
        releaseDate.setText(movie.getReleaseDate());
        voteAverage.setText(movie.getVoteAverage());
        plotSynopsis.setText(movie.getOverview());

        Picasso.get()
                .load(movie.getPosterPath())
                .placeholder(R.drawable.poster_placeholder)
                .error(R.drawable.ic_error_black_24dp)
                .into(poster);
    }

    @Override
    public void displayNoDetails() {
        title.setText(R.string.empty_view);
    }
}
