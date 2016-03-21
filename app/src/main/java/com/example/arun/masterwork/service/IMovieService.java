package com.example.arun.masterwork.service;

import com.example.arun.masterwork.models.MovieSet;
import com.example.arun.masterwork.models.ReviewSet;
import com.example.arun.masterwork.models.TrailerSet;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by arun on 19/12/15.
 */
public interface IMovieService {
    @GET("/3/movie/{categories}")
    Call<MovieSet> getMovies(@Path("categories") String categories, @Query("api_key") String apiKey);

    @GET("/3/movie/{movieid}/videos")
    Call<TrailerSet> getTrailer(@Path("movieid") int movieId, @Query("api_key") String apiKey);

    @GET("/3/movie/{movieid}/reviews")
    Call<ReviewSet> getReviews(@Path("movieid") int movieId, @Query("api_key") String apiKey);
}
