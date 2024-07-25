package com.example.movieapppaginglibrary.api;

import com.example.movieapppaginglibrary.model.MovieResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("movies/popular")
    Single<MovieResponse> getMoviesByPage(@Query("api_key") int page);
}
