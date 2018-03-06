package com.wili.android.popularmoviesapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wili.android.popularmoviesapp.R;
import com.wili.android.popularmoviesapp.adapter.MovieAdapter;
import com.wili.android.popularmoviesapp.presenter.MainActivityPresenter;
import com.wili.android.popularmoviesapp.repository.ApiRepository;
import com.wili.android.popularmoviesapp.repository.MoviesRepository;
import com.wili.android.popularmoviesapp.repository.model.Movie;
import com.wili.android.popularmoviesapp.view.MainActivityView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityView {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.empty_view)
    TextView emptyView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter movieAdapter;
    private MainActivityPresenter presenter;

    @Override
    protected void onStart() {
        super.onStart();
        MoviesRepository moviesRepository = new ApiRepository();
        presenter = new MainActivityPresenter(this, moviesRepository);
        presenter.loadPopularMovies();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {
            case R.id.menu_popular:
                return true;
            case R.id.menu_top_rated:
                //change
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void displayMovies(List<Movie> movieList) {

    }

    @Override
    public void displayNoMovies() {

    }

    public void configureRecyclerView(List<Movie> movieList) {
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        movieAdapter = new MovieAdapter(null);
        recyclerView.setAdapter(movieAdapter);

    }

}
