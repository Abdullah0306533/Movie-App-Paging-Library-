package com.example.movieapppaginglibrary.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.LoadState;
import androidx.paging.LoadStateAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapppaginglibrary.R;
import com.example.movieapppaginglibrary.databinding.LoadStateItemBinding;

/**
 * Adapter class to handle the different load states (loading, error) in a RecyclerView.
 */
public class MoviesLoadStateAdapter extends LoadStateAdapter<MoviesLoadStateAdapter.LoadStateViewHolder> {

    private View.OnClickListener mRetryCallBack;

    /**
     * Constructor to initialize retry callback.
     *
     * @param mRetryCallBack OnClickListener for retry action.
     */
    public MoviesLoadStateAdapter(View.OnClickListener mRetryCallBack) {
        this.mRetryCallBack = mRetryCallBack;
    }

    @Override
    public void onBindViewHolder(@NonNull LoadStateViewHolder loadStateViewHolder, @NonNull LoadState loadState) {
        loadStateViewHolder.bind(loadState);
    }

    @NonNull
    @Override
    public LoadStateViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, @NonNull LoadState loadState) {
        return new LoadStateViewHolder(viewGroup, mRetryCallBack);
    }

    /**
     * ViewHolder class to display load state (loading, error) views.
     */
    public static class LoadStateViewHolder extends RecyclerView.ViewHolder {
        //final test
        private final ProgressBar mProgressBar;
        private final TextView mErrorMessage;
        private final Button mRetry;

        /**
         * Constructor to initialize load state views.
         *
         * @param parent        The ViewGroup into which the new View will be added.
         * @param retryCallBack OnClickListener for retry action.
         */
        public LoadStateViewHolder(@NonNull ViewGroup parent, @NonNull View.OnClickListener retryCallBack) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.load_state_item, parent, false));

            LoadStateItemBinding binding = LoadStateItemBinding.bind(itemView);
            mProgressBar = binding.progressBar;
            mErrorMessage = binding.errorMsg;
            mRetry = binding.retryButton;
            mRetry.setOnClickListener(retryCallBack);
        }

        /**
         * Binds the current load state to the view holder.
         *
         * @param loadState The load state to be bound.
         */
        public void bind(LoadState loadState) {
            if (loadState instanceof LoadState.Error) {
                LoadState.Error loadStateError = (LoadState.Error) loadState;
                mErrorMessage.setText(loadStateError.getError().getLocalizedMessage());
            }
            mProgressBar.setVisibility(
                    loadState instanceof LoadState.Loading ? View.VISIBLE : View.GONE
            );
            mRetry.setVisibility(
                    loadState instanceof LoadState.Error ? View.VISIBLE : View.GONE
            );
            mErrorMessage.setVisibility(
                    loadState instanceof LoadState.Error ? View.VISIBLE : View.GONE
            );
        }
    }
}
