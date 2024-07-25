package com.example.movieapppaginglibrary.utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.movieapppaginglibrary.model.Movie;

/**
 * A comparator for comparing `Movie` items in a `RecyclerView`.
 * <p>
 * This class uses the DiffUtil.ItemCallback to efficiently update the list of movies
 * displayed in the `RecyclerView` by comparing items based on their ID and content.
 * </p>
 */
public class MovieComparator extends DiffUtil.ItemCallback<Movie> {

    /**
     * Determines whether two items represent the same movie.
     * <p>
     * This method checks if the IDs of the two movies are the same. If the IDs are equal,
     * it indicates that the two items are the same movie.
     * </p>
     *
     * @param oldItem the movie item previously displayed.
     * @param newItem the movie item currently displayed.
     * @return true if the items represent the same movie, false otherwise.
     */
    @Override
    public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
        return oldItem.getId() == newItem.getId();
    }

    /**
     * Determines whether the contents of two items are the same.
     * <p>
     * This method currently checks if the IDs of the two movies are the same.
     * For a more detailed comparison, you might also want to check other fields
     * of the `Movie` object to ensure that the contents are identical.
     * </p>
     *
     * @param oldItem the movie item previously displayed.
     * @param newItem the movie item currently displayed.
     * @return true if the contents of the items are the same, false otherwise.
     */
    @Override
    public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
        return oldItem.getId() == newItem.getId();
    }
}

