package com.example.arun.masterwork.provider.movie;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.arun.masterwork.provider.base.BaseModel;

/**
 * Movie Table containing movie related data
 */
public interface MovieModel extends BaseModel {

    /**
     * Movie Id
     */
    int getMovieNo();

    /**
     * Movie Title
     * Cannot be {@code null}.
     */
    @NonNull
    String getTitle();

    /**
     * Category
     * Cannot be {@code null}.
     */
    @NonNull
    String getCategory();

    /**
     * Movie Poster
     * Cannot be {@code null}.
     */
    @NonNull
    String getPosterPathUrl();

    /**
     * Movie Poster local path
     * Can be {@code null}.
     */
    @Nullable
    String getPosterPathLocal();

    /**
     * Movie Overview
     * Cannot be {@code null}.
     */
    @NonNull
    String getOverview();

    /**
     * Movie Release Date
     * Cannot be {@code null}.
     */
    @NonNull
    String getReleaseDate();

    /**
     * Is Adult or not
     */
    boolean getAdult();

    /**
     * Movie Ratings
     */
    float getVoteAverage();

    /**
     * Movie vote count
     */
    int getVoteCount();

    /**
     * Movie Popularity
     */
    float getPopularity();

    /**
     * Movie Back Image Url
     * Cannot be {@code null}.
     */
    @NonNull
    String getBackdropPathUrl();

    /**
     * Movie Back Image Local path
     * Can be {@code null}.
     */
    @Nullable
    String getBackdropPathLocal();
}
