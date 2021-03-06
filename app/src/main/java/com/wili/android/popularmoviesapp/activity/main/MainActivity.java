package com.wili.android.popularmoviesapp.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wili.android.popularmoviesapp.R;
import com.wili.android.popularmoviesapp.activity.detail.DetailsActivity;
import com.wili.android.popularmoviesapp.adapter.MovieAdapter;
import com.wili.android.popularmoviesapp.adapter.MovieAdapter.MovieAdapterOnClickHandler;
import com.wili.android.popularmoviesapp.data.AppDataManager;
import com.wili.android.popularmoviesapp.data.DataManager;
import com.wili.android.popularmoviesapp.data.database.AppDbManager;
import com.wili.android.popularmoviesapp.data.database.DbManager;
import com.wili.android.popularmoviesapp.data.model.Movie;
import com.wili.android.popularmoviesapp.data.network.ApiManager;
import com.wili.android.popularmoviesapp.data.network.AppApiManager;
import com.wili.android.popularmoviesapp.utils.CategoryConstants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.wili.android.popularmoviesapp.R.string.empty_view;

public class MainActivity extends AppCompatActivity implements MainActivityView, MovieAdapterOnClickHandler {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.empty_view)
    TextView emptyView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter movieAdapter;

    private MainActivityPresenter presenter;
    private ApiManager apiManager;
    private DbManager dbManager;
    private DataManager dataManager;

    private static final String LAYOUT_STATE = "recycler_state";
    private static final String CATEGORY = "category";
    private int currCategory;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        dbManager = new AppDbManager(getContentResolver());
        apiManager = new AppApiManager();
        dataManager = new AppDataManager(apiManager, dbManager);

        presenter = new MainActivityPresenter(this, dataManager);

        initMovieRecyclerView();
        configureBottomNavigationView();


        if (savedInstanceState != null) {
            Parcelable savedLayoutState = savedInstanceState.getParcelable(LAYOUT_STATE);
            if (savedLayoutState != null)
                layoutManager.onRestoreInstanceState(savedLayoutState);
            currCategory = savedInstanceState.getInt(CATEGORY);
            switch (currCategory) {
                case CategoryConstants.FAVOURITE:
                    presenter.loadFavorites();
                    break;
                case CategoryConstants.POPULAR:
                    presenter.loadPopularMovies();
                    break;
                case CategoryConstants.TOP_RATED:
                    presenter.loadTopRatedMovies();
                    break;
            }

        } else
            presenter.loadTopRatedMovies();
    }

    private void initMovieRecyclerView() {
        layoutManager = new GridLayoutManager(this, numberOfColumns());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }

    private int numberOfColumns() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int widthDivider = 600;
        int width = displayMetrics.widthPixels;
        int nColumns = width / widthDivider;
        if (nColumns < 2) return 2;
        return nColumns;
    }

    private void configureBottomNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_popular:
                        presenter.loadPopularMovies();
                        currCategory = CategoryConstants.POPULAR;
                        return true;
                    case R.id.menu_top_rated:
                        presenter.loadTopRatedMovies();
                        currCategory = CategoryConstants.TOP_RATED;
                        return true;
                    case R.id.menu_favourites:
                        presenter.loadFavorites();
                        currCategory = CategoryConstants.FAVOURITE;
                        return true;
                    default:
                        return true;
                }
            }
        });
    }

    @Override
    public void displayMovies(List<Movie> movieList) {
        progressBar.setVisibility(GONE);
        emptyView.setVisibility(GONE);
        recyclerView.setVisibility(VISIBLE);
        movieAdapter = new MovieAdapter(movieList, this);
        recyclerView.setAdapter(movieAdapter);
    }

    @Override
    public void displayNoMovies() {
        progressBar.setVisibility(GONE);
        recyclerView.setVisibility(GONE);
        emptyView.setVisibility(VISIBLE);
        emptyView.setText(empty_view);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(VISIBLE);
    }

    @Override
    public void onClick(String movieId) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("id", movieId);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(LAYOUT_STATE, layoutManager.onSaveInstanceState());
        outState.putInt(CATEGORY, currCategory);
    }
}
