package com.example.movieapppaginglibrary.adapters;

import static com.example.movieapppaginglibrary.utils.Utils.BASE_IMAGE_URL;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.example.movieapppaginglibrary.databinding.SingleMovieItemBinding;
import com.example.movieapppaginglibrary.model.Movie;

/**
 * Adapter class for displaying a list of movies in a RecyclerView using Paging 3 library.
 * This class extends PagingDataAdapter to support paginated loading of movie data.
 */
public class MoviesAdapter extends PagingDataAdapter<Movie, MoviesAdapter.ViewHolder> {

    /** Constant for the loading item view type. */
    public static final int LOADING_ITEM = 0;

    /** Constant for the movie item view type. */
    public static final int MOVIE_ITEM = 1;

    /** Glide RequestManager for loading images. */
    private final RequestManager glide;

    /**
     * Constructor for MoviesAdapter.
     *
     * @param diffCallback The DiffUtil.ItemCallback for calculating the difference between two non-null items.
     * @param glide        The Glide RequestManager for image loading.
     */
    public MoviesAdapter(@NonNull DiffUtil.ItemCallback<Movie> diffCallback, RequestManager glide) {
        super(diffCallback);
        this.glide = glide;
    }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent an item.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SingleMovieItemBinding binding = SingleMovieItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie currentMovie = getItem(position);
        if (currentMovie != null) {
            glide.load(BASE_IMAGE_URL + currentMovie.getPosterPath())
                    .into(holder.singleMovieItemBinding.imageViewMovie);
            holder.singleMovieItemBinding.textViewRating.setText(String.valueOf(currentMovie.getVoteAverage()));
        }
    }

    /**
     * Returns the view type of the item at position for the purposes of view recycling.
     *
     * @param position The position of the item within the adapter's data set.
     * @return The view type of the item at position.
     */
    @Override
    public int getItemViewType(int position) {
        return position == getItemCount() ? MOVIE_ITEM : LOADING_ITEM;
    }

    /**
     * ViewHolder class that represents the movie item view.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        /** Binding object for the single movie item layout. */
        private final SingleMovieItemBinding singleMovieItemBinding;

        /**
         * Constructor for ViewHolder.
         *
         * @param singleMovieItemBinding The binding object for the single movie item layout.
         */
        public ViewHolder(@NonNull SingleMovieItemBinding singleMovieItemBinding) {
            super(singleMovieItemBinding.getRoot());
            this.singleMovieItemBinding = singleMovieItemBinding;
        }
    }
}
