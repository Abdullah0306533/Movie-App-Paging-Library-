package com.example.movieapppaginglibrary.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Represents a response from the movie API containing a list of movies and pagination details.
 */
public class MovieResponse {

    /**
     * The current page of the results.
     */
    @SerializedName("page")
    @Expose
    private int page;

    /**
     * The list of movies returned in the response.
     */
    @SerializedName("results")
    @Expose
    private List<Movie> movies = null;

    /**
     * The total number of results available.
     */
    @SerializedName("total_results")
    @Expose
    private int totalResults;

    /**
     * The total number of pages of results.
     */
    @SerializedName("total_pages")
    @Expose
    private int totalPages;

    /**
     * Gets the current page of the results.
     *
     * @return the current page number.
     */
    public int getPage() {
        return page;
    }

    /**
     * Sets the current page of the results.
     *
     * @param page the page number to set.
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * Gets the list of movies returned in the response.
     *
     * @return the list of movies.
     */
    public List<Movie> getMovies() {
        return movies;
    }

    /**
     * Sets the list of movies returned in the response.
     *
     * @param movies the list of movies to set.
     */
    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    /**
     * Gets the total number of results available.
     *
     * @return the total number of results.
     */
    public int getTotalResults() {
        return totalResults;
    }

    /**
     * Sets the total number of results available.
     *
     * @param totalResults the total number of results to set.
     */
    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    /**
     * Gets the total number of pages of results.
     *
     * @return the total number of pages.
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * Sets the total number of pages of results.
     *
     * @param totalPages the total number of pages to set.
     */
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
