package com.wili.android.popularmoviesapp.test;

import com.wili.android.popularmoviesapp.presenter.MainActivityPresenter;
import com.wili.android.popularmoviesapp.repository.MoviesRepository;
import com.wili.android.popularmoviesapp.repository.model.Movie;
import com.wili.android.popularmoviesapp.view.MainActivityView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Damian on 24.02.2018.
 */
public class MainActivityPresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    MoviesRepository repository;

    @Mock
    MainActivityView view;

    MainActivityPresenter presenter;
    private final List<Movie> MANY_MOVIE = Arrays.asList(new Movie(), new Movie(), new Movie());

    @Before
    public void setUp() {
        presenter = new MainActivityPresenter(view, repository);
    }

    @Test
    public void shouldPassMoviesToView() {
        // Mockito.when(repository.getPopularMovieList()).thenReturn(MANY_MOVIE);

        presenter.loadPopularMovies();

        Mockito.verify(view).displayMovies(MANY_MOVIE);
    }

    @Test
    public void shouldPassNoMoviesToView() {
        //Mockito.when(repository.getPopularMovieList()).thenReturn(Collections.EMPTY_LIST);

        presenter.loadPopularMovies();

        Mockito.verify(view).displayNoMovies();
    }

}