package com.wili.android.popularmoviesapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wili.android.popularmoviesapp.R;
import com.wili.android.popularmoviesapp.adapter.MovieAdapter;
import com.wili.android.popularmoviesapp.presenter.MainActivityPresenter;
import com.wili.android.popularmoviesapp.repository.RetrofitRepository;
import com.wili.android.popularmoviesapp.repository.model.Movie;
import com.wili.android.popularmoviesapp.view.MainActivityView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;
import static com.wili.android.popularmoviesapp.R.string.empty_view;

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
    private RetrofitRepository repository;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        repository = new RetrofitRepository();
        presenter = new MainActivityPresenter(this, repository);

        configureRecyclerView();

        presenter.loadTopRatedMovies();
    }

    private void configureRecyclerView() {
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
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
                presenter.loadPopularMovies();
                return true;
            case R.id.menu_top_rated:
                presenter.loadTopRatedMovies();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void displayMovies(List<Movie> movieList) {
        progressBar.setVisibility(GONE);
        emptyView.setVisibility(GONE);
        movieAdapter = new MovieAdapter(movieList);
        recyclerView.setAdapter(movieAdapter);
    }

    @Override
    public void displayNoMovies() {
        progressBar.setVisibility(GONE);
        emptyView.setText(empty_view);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(GONE);
    }
}
