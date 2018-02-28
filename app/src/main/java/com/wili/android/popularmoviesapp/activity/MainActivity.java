package com.wili.android.popularmoviesapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wili.android.popularmoviesapp.R;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public void displayMovies(List<Movie> movieList) {

    }

    @Override
    public void displayNoMovies() {

    }
}
