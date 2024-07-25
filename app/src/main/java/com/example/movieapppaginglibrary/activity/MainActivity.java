package com.example.movieapppaginglibrary.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.RequestManager;
import com.example.movieapppaginglibrary.R;
import com.example.movieapppaginglibrary.adapters.MoviesAdapter;
import com.example.movieapppaginglibrary.adapters.MoviesLoadStateAdapter;
import com.example.movieapppaginglibrary.databinding.ActivityMainBinding;
import com.example.movieapppaginglibrary.utils.GridSpace;
import com.example.movieapppaginglibrary.utils.MovieComparator;
import com.example.movieapppaginglibrary.utils.Utils;
import com.example.movieapppaginglibrary.viewmodel.MovieViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * MainActivity that displays a list of movies using paging and handles different load states.
 */
@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private MovieViewModel mainActivityViewModel;
    private ActivityMainBinding binding;
    private MoviesAdapter moviesAdapter;

    @Inject
    RequestManager requestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Inflate the layout
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        // Check API key validity
        if (Utils.API_KEY == null || Utils.API_KEY.isEmpty()) {
            Toast.makeText(this, "Error in API Key", Toast.LENGTH_SHORT).show();
        }

        // Initialize MoviesAdapter
        moviesAdapter = new MoviesAdapter(new MovieComparator(), requestManager);

        // Obtain ViewModel
        mainActivityViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        // Initialize RecyclerView and Adapter
        initRecyclerviewAndAdapter();

        // Subscribe to paging data
        mainActivityViewModel.moviePagingDataFlowable.subscribe(moviePagingData -> {
            moviesAdapter.submitData(getLifecycle(), moviePagingData);
        });
    }

    /**
     * Initializes the RecyclerView and sets up the adapter with a LoadState footer.
     */
    private void initRecyclerviewAndAdapter() {
        // Create GridLayoutManager with 2 columns
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);

        // Set LayoutManager and ItemDecoration
        binding.recyclerViewMovies.setLayoutManager(gridLayoutManager);
        binding.recyclerViewMovies.addItemDecoration(new GridSpace(2, 20, true));

        // Set Adapter with LoadState footer
        binding.recyclerViewMovies.setAdapter(
                moviesAdapter.withLoadStateFooter(
                        new MoviesLoadStateAdapter(view -> {
                            moviesAdapter.retry();
                        })
                )
        );

        // Set SpanSizeLookup to handle span size for loading items
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return moviesAdapter.getItemViewType(position) == MoviesAdapter.LOADING_ITEM ? 1 : 2;
            }
        });
    }
}
