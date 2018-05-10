package com.wili.android.popularmoviesapp.activity.detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.wili.android.popularmoviesapp.R;
import com.wili.android.popularmoviesapp.adapter.ReviewAdapter;
import com.wili.android.popularmoviesapp.adapter.VideoAdapter;
import com.wili.android.popularmoviesapp.data.AppDataManager;
import com.wili.android.popularmoviesapp.data.DataManager;
import com.wili.android.popularmoviesapp.data.database.AppDbManager;
import com.wili.android.popularmoviesapp.data.database.DbManager;
import com.wili.android.popularmoviesapp.data.model.Movie;
import com.wili.android.popularmoviesapp.data.model.Review;
import com.wili.android.popularmoviesapp.data.model.Video;
import com.wili.android.popularmoviesapp.data.network.ApiManager;
import com.wili.android.popularmoviesapp.data.network.AppApiManager;

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
    private ApiManager apiManager;
    private DbManager dbManager;
    private DataManager dataManager;

    private RecyclerView.LayoutManager reviewsLayoutManager;
    private RecyclerView.Adapter reviewsAdapter;

    private RecyclerView.LayoutManager videosLayoutManager;
    private RecyclerView.Adapter videosAdapter;

    private static final String REVIEWS_LAYOUT = "reviews_layout";
    private static final String VIDEOS_LAYOUT = "videos_layout";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        dbManager = new AppDbManager(getContentResolver());
        apiManager = new AppApiManager();
        dataManager = new AppDataManager(apiManager, dbManager);

        presenter = new DetailsActivityPresenter(this, dataManager);

        final String id = getIntent().getExtras().getString("id");

        presenter.loadMovieDetails(id);
        presenter.loadFavouriteIcon(id);

        initVideosRecyclerView();
        presenter.loadVideos(id);

        initReviewsRecyclerView();
        presenter.loadReviews(id);

        addToFavourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.switchFavourite(id);
            }
        });

        if (savedInstanceState != null) {
            Parcelable savedReviewsLayoutState = savedInstanceState.getParcelable(REVIEWS_LAYOUT);
            Parcelable savedVideosLayoutState = savedInstanceState.getParcelable(VIDEOS_LAYOUT);
            if (savedReviewsLayoutState != null)
                reviewsLayoutManager.onRestoreInstanceState(savedInstanceState);
            if (savedVideosLayoutState != null)
                videosLayoutManager.onRestoreInstanceState(savedVideosLayoutState);

        }
    }

    @Override
    public void displayMovieDetails(Movie movie) {
        addToFavourites.setVisibility(View.VISIBLE);
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
        addToFavourites.setVisibility(View.GONE);
    }

    @Override
    public void displayReviews(List<Review> reviewList) {
        reviewsRecyclerView.setVisibility(View.VISIBLE);
        reviewsAdapter = new ReviewAdapter(reviewList);
        reviewsRecyclerView.setAdapter(reviewsAdapter);
    }

    @Override
    public void displayNoReviews() {
        reviewsRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void displayVideos(List<Video> videosList) {
        videosRecyclerView.setVisibility(View.VISIBLE);
        videosAdapter = new VideoAdapter(videosList, this);
        videosRecyclerView.setAdapter(videosAdapter);
    }

    @Override
    public void displayNoVideos() {
        videosRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void displayFavourite() {
        addToFavourites.setImageResource(R.drawable.ic_favorite_true_24dp);
    }

    @Override
    public void displayNoFavourite() {
        addToFavourites.setImageResource(R.drawable.ic_favorite_false_24dp);
    }

    @Override
    public void displayToastAddedToFavourites() {
        displayToast("Added to favourites");
    }

    @Override
    public void displayToastDeletedFromFavourites() {
        displayToast("Deleted from favourites");
    }

    @Override
    public void displayToastAddedToFavouritesError() {
        displayToast("Error. Movie not added to favourites");
    }

    private void displayToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    private void initReviewsRecyclerView() {
        reviewsLayoutManager = new LinearLayoutManager(this);
        reviewsRecyclerView.setLayoutManager(reviewsLayoutManager);
        reviewsRecyclerView.setHasFixedSize(true);
    }

    private void initVideosRecyclerView() {
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(REVIEWS_LAYOUT, reviewsLayoutManager.onSaveInstanceState());
        outState.putParcelable(VIDEOS_LAYOUT, videosLayoutManager.onSaveInstanceState());
    }
}
