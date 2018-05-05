package com.wili.android.popularmoviesapp.activity.detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wili.android.popularmoviesapp.R;
import com.wili.android.popularmoviesapp.adapter.ReviewAdapter;
import com.wili.android.popularmoviesapp.adapter.VideoAdapter;
import com.wili.android.popularmoviesapp.repository.MoviesRepository;
import com.wili.android.popularmoviesapp.repository.RetrofitRepository;
import com.wili.android.popularmoviesapp.repository.database.AppDbManager;
import com.wili.android.popularmoviesapp.repository.database.DbManager;
import com.wili.android.popularmoviesapp.repository.model.Movie;
import com.wili.android.popularmoviesapp.repository.model.Review;
import com.wili.android.popularmoviesapp.repository.model.Video;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity implements DetailsActivityView, VideoAdapter.VideoAdapterOnClickHandler {

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
    @BindView(R.id.background_image)
    ImageView backgroundImage;
    @BindView(R.id.reviews_recycler_view)
    RecyclerView reviewsRecyclerView;
    @BindView(R.id.videos_recycler_view)
    RecyclerView videosRecyclerView;
    @BindView(R.id.add_to_favourites)
    ImageView addToFavourites;

    private DetailsActivityPresenter presenter;
    private MoviesRepository repository;
    private DbManager dbManager;

    private RecyclerView.LayoutManager reviewsLayoutManager;
    private RecyclerView.Adapter reviewsAdapter;

    private RecyclerView.LayoutManager videosLayoutManager;
    private RecyclerView.Adapter videosAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        dbManager = new AppDbManager(getContentResolver());
        repository = new RetrofitRepository();
        presenter = new DetailsActivityPresenter(this, repository, dbManager);

        final String id = getIntent().getExtras().getString("id");
        presenter.loadMovieDetails(id);

        configureRecyclerView();
        presenter.loadVideos(id);
        presenter.loadReviews(id);

        addToFavourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addToFavourites(id);
            }
        });
    }

    @Override
    public void displayMovieDetails(Movie movie) {
        title.setText(movie.getTitle());
        releaseDate.setText(movie.getReleaseDate());
        voteAverage.setText(movie.getVoteAverage());
        plotSynopsis.setText(movie.getOverview());

        Picasso.get()
                .load(movie.getBackgroundImagePath())
                .placeholder(R.drawable.poster_placeholder)
                .error(R.drawable.ic_error_black_24dp)
                .into(backgroundImage);

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

    @Override
    public void displayReviews(List<Review> reviewList) {
        reviewsAdapter = new ReviewAdapter(reviewList);
        reviewsRecyclerView.setAdapter(reviewsAdapter);
    }

    @Override
    public void displayNoReviews() {

    }

    @Override
    public void displayVideos(List<Video> videosList) {
        videosAdapter = new VideoAdapter(videosList, this);
        videosRecyclerView.setAdapter(videosAdapter);
    }

    @Override
    public void displayNoVideos() {

    }

    private void configureRecyclerView() {
        reviewsLayoutManager = new LinearLayoutManager(this);
        reviewsRecyclerView.setLayoutManager(reviewsLayoutManager);
        reviewsRecyclerView.setHasFixedSize(true);

        videosLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        videosRecyclerView.setLayoutManager(videosLayoutManager);
        videosRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void onClick(String videoPath) {
        Log.d(DetailsActivity.class.getSimpleName(), videoPath);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoPath));
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
    }
}
