package com.example.movieapppaginglibrary.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Custom `RecyclerView.ItemDecoration` to add spacing between items in a grid layout.
 * <p>
 * This class adds spacing around grid items, with an option to include spacing on the edges
 * of the grid. It helps in creating evenly spaced grid items with proper margins.
 * </p>
 */
public class GridSpace extends RecyclerView.ItemDecoration {

    private final int spanCount;  // Number of columns in the grid
    private final int spacing;    // Spacing between items
    private final boolean includeEdge; // Whether to include edge spacing

    /**
     * Constructor for `GridSpace`.
     *
     * @param spanCount the number of columns in the grid.
     * @param spacing the spacing between grid items.
     * @param includeEdge whether to include spacing on the edges of the grid.
     */
    public GridSpace(int spanCount, int spacing, boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    /**
     * Calculates the offsets for each item in the grid.
     * <p>
     * This method sets the appropriate spacing for the item based on its position in the grid,
     * the total number of columns, and whether edge spacing is included.
     * </p>
     *
     * @param outRect the output rectangle to specify offsets for the item.
     * @param view the view for which to calculate the offsets.
     * @param parent the parent RecyclerView containing the item.
     * @param state the current state of the RecyclerView.
     */
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // Position of the item
        int column = position % spanCount; // Column of the item

        if (includeEdge) {
            // Calculate spacing including edge
            outRect.left = spacing - column * spacing / spanCount; // Left edge spacing
            outRect.right = (column + 1) * spacing / spanCount; // Right edge spacing

            if (position < spanCount) { // Top edge spacing
                outRect.top = spacing;
            }
            outRect.bottom = spacing; // Bottom edge spacing
        } else {
            // Calculate spacing excluding edge
            outRect.left = column * spacing / spanCount; // Left spacing
            outRect.right = spacing - (column + 1) * spacing / spanCount; // Right spacing
            if (position >= spanCount) { // Top spacing for items below the top row
                outRect.top = spacing;
            }
        }
    }
}
