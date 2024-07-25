package com.example.movieapppaginglibrary.moviedatasource;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxPagingSource;

import com.example.movieapppaginglibrary.api.APiClient;
import com.example.movieapppaginglibrary.model.Movie;
import com.example.movieapppaginglibrary.model.MovieResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

/**
 * PagingSource implementation for loading movie data from a remote source using RxJava3.
 * This class extends RxPagingSource to provide paginated data loading for the Paging 3 library.
 */
public class MoviePagingSource extends RxPagingSource<Integer, Movie> {

    /**
     * Loads a single page of data.
     *
     * @param loadParams The parameters for loading data, including the key and load size.
     * @return A Single that emits a LoadResult containing the loaded data.
     */
    @NonNull
    @Override
    public Single<LoadResult<Integer, Movie>> loadSingle(@NonNull LoadParams<Integer> loadParams) {
        try {
            // Determine the page number to load. Default to 0 if the key is null.
            int page = loadParams.getKey() != null ? loadParams.getKey() : 0;

            // Make an API call to get movies by page, map the response to a list of movies,
            // and create a LoadResult object.
            return APiClient.getApiInterface()
                    .getMoviesByPage(page)
                    .map(MovieResponse::getMovies)
                    .map(movies -> toLoadResult(movies, page))
                    .onErrorReturn(LoadResult.Error::new); // Handle errors by returning a LoadResult.Error.
        } catch (Exception e) {
            // Return a LoadResult.Error if an exception occurs.
            return Single.just(new LoadResult.Error<>(e));
        }
    }

    /**
     * Returns the key for the page to be loaded after invalidation.
     *
     * @param pagingState The state containing loaded pages and the last accessed position.
     * @return The key for the next page to load, or null if the default key should be used.
     */
    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, Movie> pagingState) {
        return null; // Returning null indicates that the default key should be used.
    }

    /**
     * Converts a list of movies and the current page number into a LoadResult object.
     *
     * @param movies The list of movies to be loaded.
     * @param page The current page number.
     * @return A LoadResult object containing the loaded data and keys for the previous and next pages.
     */
    private LoadResult<Integer, Movie> toLoadResult(List<Movie> movies, int page) {
        return new LoadResult.Page(
                movies, // The list of movies.
                page == 1 ? null : page - 1, // Previous page key, or null if this is the first page.
                page + 1 // Next page key.
        );
    }
}
