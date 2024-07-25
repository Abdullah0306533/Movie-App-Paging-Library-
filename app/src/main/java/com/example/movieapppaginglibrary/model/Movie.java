package com.example.movieapppaginglibrary.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Represents a movie with its details.
 */
public class Movie {

    /**
     * Unique identifier for the movie.
     */
    @SerializedName("id")
    @Expose
    private int id;

    /**
     * Path to the movie's poster image.
     */
    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    /**
     * Average vote rating for the movie.
     */
    @SerializedName("vote_average")
    @Expose
    private double voteAverage;

    /**
     * Gets the unique identifier for the movie.
     *
     * @return the movie's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the movie.
     *
     * @param id the movie's ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the path to the movie's poster image.
     *
     * @return the path to the poster image.
     */
    public String getPosterPath() {
        return posterPath;
    }

    /**
     * Sets the path to the movie's poster image.
     *
     * @param posterPath the path to the poster image.
     */
    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    /**
     * Gets the average vote rating for the movie.
     *
     * @return the average vote rating.
     */
    public double getVoteAverage() {
        return voteAverage;
    }

    /**
     * Sets the average vote rating for the movie.
     *
     * @param voteAverage the average vote rating.
     */
    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    /**
     * Compares this movie to another object for equality.
     * <p>
     * This method currently only checks if the object is null or the same instance.
     * To fully implement equality comparison, consider comparing relevant fields.
     *
     * @param o the object to compare with.
     * @return true if the object is the same instance or false if the object is null.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        return false;
    }
}
