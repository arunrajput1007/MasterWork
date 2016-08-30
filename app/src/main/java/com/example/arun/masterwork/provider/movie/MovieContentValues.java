package com.example.arun.masterwork.provider.movie;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.arun.masterwork.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code movie} table.
 */
public class MovieContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return MovieColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable MovieSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable MovieSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Movie Id
     */
    public MovieContentValues putMovieNo(int value) {
        mContentValues.put(MovieColumns.MOVIE_NO, value);
        return this;
    }


    /**
     * Movie Title
     */
    public MovieContentValues putTitle(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("title must not be null");
        mContentValues.put(MovieColumns.TITLE, value);
        return this;
    }


    /**
     * Category
     */
    public MovieContentValues putCategory(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("category must not be null");
        mContentValues.put(MovieColumns.CATEGORY, value);
        return this;
    }


    /**
     * Movie Poster
     */
    public MovieContentValues putPosterPathUrl(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("posterPathUrl must not be null");
        mContentValues.put(MovieColumns.POSTER_PATH_URL, value);
        return this;
    }


    /**
     * Movie Poster local path
     */
    public MovieContentValues putPosterPathLocal(@Nullable String value) {
        mContentValues.put(MovieColumns.POSTER_PATH_LOCAL, value);
        return this;
    }

    public MovieContentValues putPosterPathLocalNull() {
        mContentValues.putNull(MovieColumns.POSTER_PATH_LOCAL);
        return this;
    }

    /**
     * Movie Overview
     */
    public MovieContentValues putOverview(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("overview must not be null");
        mContentValues.put(MovieColumns.OVERVIEW, value);
        return this;
    }


    /**
     * Movie Release Date
     */
    public MovieContentValues putReleaseDate(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("releaseDate must not be null");
        mContentValues.put(MovieColumns.RELEASE_DATE, value);
        return this;
    }


    /**
     * Is Adult or not
     */
    public MovieContentValues putAdult(boolean value) {
        mContentValues.put(MovieColumns.ADULT, value);
        return this;
    }


    /**
     * Movie Ratings
     */
    public MovieContentValues putVoteAverage(float value) {
        mContentValues.put(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }


    /**
     * Movie vote count
     */
    public MovieContentValues putVoteCount(int value) {
        mContentValues.put(MovieColumns.VOTE_COUNT, value);
        return this;
    }


    /**
     * Movie Popularity
     */
    public MovieContentValues putPopularity(float value) {
        mContentValues.put(MovieColumns.POPULARITY, value);
        return this;
    }


    /**
     * Movie Back Image Url
     */
    public MovieContentValues putBackdropPathUrl(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("backdropPathUrl must not be null");
        mContentValues.put(MovieColumns.BACKDROP_PATH_URL, value);
        return this;
    }


    /**
     * Movie Back Image Local path
     */
    public MovieContentValues putBackdropPathLocal(@Nullable String value) {
        mContentValues.put(MovieColumns.BACKDROP_PATH_LOCAL, value);
        return this;
    }

    public MovieContentValues putBackdropPathLocalNull() {
        mContentValues.putNull(MovieColumns.BACKDROP_PATH_LOCAL);
        return this;
    }
}
