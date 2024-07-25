package com.example.movieapppaginglibrary.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.example.movieapppaginglibrary.model.Movie;
import com.example.movieapppaginglibrary.moviedatasource.MoviePagingSource;

import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.CoroutineScope;

/**
 * ViewModel for managing movie data with paging support.
 * This ViewModel uses the Paging 3 library to load data in chunks, or "pages",
 * which helps to reduce memory consumption and enables smooth scrolling.
 */
public class MovieViewModel extends ViewModel {

    // Flowable to hold paging data for movies
    public Flowable<PagingData<Movie>> moviePagingDataFlowable;

    /**
     * Constructor for MovieViewModel.
     * Calls the init method to set up the paging configuration.
     */
    public MovieViewModel() {
        init();
    }

    /**
     * Initializes the Paging configuration and Flowable.
     * This method sets up the PagingSource, Pager, and Flowable,
     * and caches the Flowable within the ViewModel's CoroutineScope.
     */
    private void init() {
        // Create an instance of MoviePagingSource, which will handle
        // the actual data loading and provide the data to the Pager.
        MoviePagingSource moviePagingSource = new MoviePagingSource();

        // Configure the Pager with paging settings.
        // The Pager is responsible for handling the data source and
        // providing the data to the UI in chunks (pages).
        Pager<Integer, Movie> pager = new Pager<>(
                new PagingConfig(
                        20, // Page size: the number of items loaded at once.
                        20, // Prefetch distance: how far ahead to load data.
                        false, // Enable placeholders: whether to enable placeholders for unloaded items.
                        20 * 499 // Maximum page size: the maximum number of items to load.
                ),
                () -> moviePagingSource // Provide the PagingSource: the data source for paging.
        );

        // Create a Flowable for PagingData.
        // Flowable is a type of reactive stream in RxJava that emits a series of data items.
        moviePagingDataFlowable = PagingRx.getFlowable(pager);

        // Get CoroutineScope for the ViewModel.
        // CoroutineScope is used to manage the lifecycle of coroutines.
        CoroutineScope coroutineScope = ViewModelKt.getViewModelScope(this);

        // Cache the Flowable within the ViewModel's CoroutineScope.
        // Caching ensures that the data remains consistent and survives configuration changes,
        // such as screen rotations.
        PagingRx.cachedIn(moviePagingDataFlowable, coroutineScope);
    }
}
