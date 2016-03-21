package com.example.arun.masterwork.provider.movie;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.arun.masterwork.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code movie} table.
 */
public class MovieCursor extends AbstractCursor implements MovieModel {
    public MovieCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(MovieColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Movie Id
     */
    public int getMovieNo() {
        Integer res = getIntegerOrNull(MovieColumns.MOVIE_NO);
        if (res == null)
            throw new NullPointerException("The value of 'movie_no' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Movie Title
     * Cannot be {@code null}.
     */
    @NonNull
    public String getTitle() {
        String res = getStringOrNull(MovieColumns.TITLE);
        if (res == null)
            throw new NullPointerException("The value of 'title' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Movie Poster
     * Cannot be {@code null}.
     */
    @NonNull
    public String getPosterPathUrl() {
        String res = getStringOrNull(MovieColumns.POSTER_PATH_URL);
        if (res == null)
            throw new NullPointerException("The value of 'poster_path_url' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Movie Poster local path
     * Can be {@code null}.
     */
    @Nullable
    public String getPosterPathLocal() {
        String res = getStringOrNull(MovieColumns.POSTER_PATH_LOCAL);
        return res;
    }

    /**
     * Movie Overview
     * Cannot be {@code null}.
     */
    @NonNull
    public String getOverview() {
        String res = getStringOrNull(MovieColumns.OVERVIEW);
        if (res == null)
            throw new NullPointerException("The value of 'overview' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Movie Release Date
     * Cannot be {@code null}.
     */
    @NonNull
    public String getReleaseDate() {
        String res = getStringOrNull(MovieColumns.RELEASE_DATE);
        if (res == null)
            throw new NullPointerException("The value of 'release_date' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Is Adult or not
     */
    public boolean getAdult() {
        Boolean res = getBooleanOrNull(MovieColumns.ADULT);
        if (res == null)
            throw new NullPointerException("The value of 'adult' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Movie Ratings
     */
    public float getVoteAverage() {
        Float res = getFloatOrNull(MovieColumns.VOTE_AVERAGE);
        if (res == null)
            throw new NullPointerException("The value of 'vote_average' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Movie vote count
     */
    public int getVoteCount() {
        Integer res = getIntegerOrNull(MovieColumns.VOTE_COUNT);
        if (res == null)
            throw new NullPointerException("The value of 'vote_count' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Movie Popularity
     */
    public float getPopularity() {
        Float res = getFloatOrNull(MovieColumns.POPULARITY);
        if (res == null)
            throw new NullPointerException("The value of 'popularity' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Movie Back Image Url
     * Cannot be {@code null}.
     */
    @NonNull
    public String getBackdropPathUrl() {
        String res = getStringOrNull(MovieColumns.BACKDROP_PATH_URL);
        if (res == null)
            throw new NullPointerException("The value of 'backdrop_path_url' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Movie Back Image Local path
     * Can be {@code null}.
     */
    @Nullable
    public String getBackdropPathLocal() {
        String res = getStringOrNull(MovieColumns.BACKDROP_PATH_LOCAL);
        return res;
    }

    /**
     * Movie Category
     * Cannot be {@code null}.
     */
    @NonNull
    public String getCategory() {
        String res = getStringOrNull(MovieColumns.CATEGORY);
        if (res == null)
            throw new NullPointerException("The value of 'category' in the database was null, which is not allowed according to the model definition");
        return res;
    }
}
