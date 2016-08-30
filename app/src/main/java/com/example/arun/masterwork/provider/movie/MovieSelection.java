package com.example.arun.masterwork.provider.movie;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.example.arun.masterwork.provider.base.AbstractSelection;

/**
 * Selection for the {@code movie} table.
 */
public class MovieSelection extends AbstractSelection<MovieSelection> {
    @Override
    protected Uri baseUri() {
        return MovieColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code MovieCursor} object, which is positioned before the first entry, or null.
     */
    public MovieCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new MovieCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public MovieCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code MovieCursor} object, which is positioned before the first entry, or null.
     */
    public MovieCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new MovieCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public MovieCursor query(Context context) {
        return query(context, null);
    }


    public MovieSelection id(long... value) {
        addEquals("movie." + MovieColumns._ID, toObjectArray(value));
        return this;
    }

    public MovieSelection idNot(long... value) {
        addNotEquals("movie." + MovieColumns._ID, toObjectArray(value));
        return this;
    }

    public MovieSelection orderById(boolean desc) {
        orderBy("movie." + MovieColumns._ID, desc);
        return this;
    }

    public MovieSelection orderById() {
        return orderById(false);
    }

    public MovieSelection movieNo(int... value) {
        addEquals(MovieColumns.MOVIE_NO, toObjectArray(value));
        return this;
    }

    public MovieSelection movieNoNot(int... value) {
        addNotEquals(MovieColumns.MOVIE_NO, toObjectArray(value));
        return this;
    }

    public MovieSelection movieNoGt(int value) {
        addGreaterThan(MovieColumns.MOVIE_NO, value);
        return this;
    }

    public MovieSelection movieNoGtEq(int value) {
        addGreaterThanOrEquals(MovieColumns.MOVIE_NO, value);
        return this;
    }

    public MovieSelection movieNoLt(int value) {
        addLessThan(MovieColumns.MOVIE_NO, value);
        return this;
    }

    public MovieSelection movieNoLtEq(int value) {
        addLessThanOrEquals(MovieColumns.MOVIE_NO, value);
        return this;
    }

    public MovieSelection orderByMovieNo(boolean desc) {
        orderBy(MovieColumns.MOVIE_NO, desc);
        return this;
    }

    public MovieSelection orderByMovieNo() {
        orderBy(MovieColumns.MOVIE_NO, false);
        return this;
    }

    public MovieSelection title(String... value) {
        addEquals(MovieColumns.TITLE, value);
        return this;
    }

    public MovieSelection titleNot(String... value) {
        addNotEquals(MovieColumns.TITLE, value);
        return this;
    }

    public MovieSelection titleLike(String... value) {
        addLike(MovieColumns.TITLE, value);
        return this;
    }

    public MovieSelection titleContains(String... value) {
        addContains(MovieColumns.TITLE, value);
        return this;
    }

    public MovieSelection titleStartsWith(String... value) {
        addStartsWith(MovieColumns.TITLE, value);
        return this;
    }

    public MovieSelection titleEndsWith(String... value) {
        addEndsWith(MovieColumns.TITLE, value);
        return this;
    }

    public MovieSelection orderByTitle(boolean desc) {
        orderBy(MovieColumns.TITLE, desc);
        return this;
    }

    public MovieSelection orderByTitle() {
        orderBy(MovieColumns.TITLE, false);
        return this;
    }

    public MovieSelection category(String... value) {
        addEquals(MovieColumns.CATEGORY, value);
        return this;
    }

    public MovieSelection categoryNot(String... value) {
        addNotEquals(MovieColumns.CATEGORY, value);
        return this;
    }

    public MovieSelection categoryLike(String... value) {
        addLike(MovieColumns.CATEGORY, value);
        return this;
    }

    public MovieSelection categoryContains(String... value) {
        addContains(MovieColumns.CATEGORY, value);
        return this;
    }

    public MovieSelection categoryStartsWith(String... value) {
        addStartsWith(MovieColumns.CATEGORY, value);
        return this;
    }

    public MovieSelection categoryEndsWith(String... value) {
        addEndsWith(MovieColumns.CATEGORY, value);
        return this;
    }

    public MovieSelection orderByCategory(boolean desc) {
        orderBy(MovieColumns.CATEGORY, desc);
        return this;
    }

    public MovieSelection orderByCategory() {
        orderBy(MovieColumns.CATEGORY, false);
        return this;
    }

    public MovieSelection posterPathUrl(String... value) {
        addEquals(MovieColumns.POSTER_PATH_URL, value);
        return this;
    }

    public MovieSelection posterPathUrlNot(String... value) {
        addNotEquals(MovieColumns.POSTER_PATH_URL, value);
        return this;
    }

    public MovieSelection posterPathUrlLike(String... value) {
        addLike(MovieColumns.POSTER_PATH_URL, value);
        return this;
    }

    public MovieSelection posterPathUrlContains(String... value) {
        addContains(MovieColumns.POSTER_PATH_URL, value);
        return this;
    }

    public MovieSelection posterPathUrlStartsWith(String... value) {
        addStartsWith(MovieColumns.POSTER_PATH_URL, value);
        return this;
    }

    public MovieSelection posterPathUrlEndsWith(String... value) {
        addEndsWith(MovieColumns.POSTER_PATH_URL, value);
        return this;
    }

    public MovieSelection orderByPosterPathUrl(boolean desc) {
        orderBy(MovieColumns.POSTER_PATH_URL, desc);
        return this;
    }

    public MovieSelection orderByPosterPathUrl() {
        orderBy(MovieColumns.POSTER_PATH_URL, false);
        return this;
    }

    public MovieSelection posterPathLocal(String... value) {
        addEquals(MovieColumns.POSTER_PATH_LOCAL, value);
        return this;
    }

    public MovieSelection posterPathLocalNot(String... value) {
        addNotEquals(MovieColumns.POSTER_PATH_LOCAL, value);
        return this;
    }

    public MovieSelection posterPathLocalLike(String... value) {
        addLike(MovieColumns.POSTER_PATH_LOCAL, value);
        return this;
    }

    public MovieSelection posterPathLocalContains(String... value) {
        addContains(MovieColumns.POSTER_PATH_LOCAL, value);
        return this;
    }

    public MovieSelection posterPathLocalStartsWith(String... value) {
        addStartsWith(MovieColumns.POSTER_PATH_LOCAL, value);
        return this;
    }

    public MovieSelection posterPathLocalEndsWith(String... value) {
        addEndsWith(MovieColumns.POSTER_PATH_LOCAL, value);
        return this;
    }

    public MovieSelection orderByPosterPathLocal(boolean desc) {
        orderBy(MovieColumns.POSTER_PATH_LOCAL, desc);
        return this;
    }

    public MovieSelection orderByPosterPathLocal() {
        orderBy(MovieColumns.POSTER_PATH_LOCAL, false);
        return this;
    }

    public MovieSelection overview(String... value) {
        addEquals(MovieColumns.OVERVIEW, value);
        return this;
    }

    public MovieSelection overviewNot(String... value) {
        addNotEquals(MovieColumns.OVERVIEW, value);
        return this;
    }

    public MovieSelection overviewLike(String... value) {
        addLike(MovieColumns.OVERVIEW, value);
        return this;
    }

    public MovieSelection overviewContains(String... value) {
        addContains(MovieColumns.OVERVIEW, value);
        return this;
    }

    public MovieSelection overviewStartsWith(String... value) {
        addStartsWith(MovieColumns.OVERVIEW, value);
        return this;
    }

    public MovieSelection overviewEndsWith(String... value) {
        addEndsWith(MovieColumns.OVERVIEW, value);
        return this;
    }

    public MovieSelection orderByOverview(boolean desc) {
        orderBy(MovieColumns.OVERVIEW, desc);
        return this;
    }

    public MovieSelection orderByOverview() {
        orderBy(MovieColumns.OVERVIEW, false);
        return this;
    }

    public MovieSelection releaseDate(String... value) {
        addEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public MovieSelection releaseDateNot(String... value) {
        addNotEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public MovieSelection releaseDateLike(String... value) {
        addLike(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public MovieSelection releaseDateContains(String... value) {
        addContains(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public MovieSelection releaseDateStartsWith(String... value) {
        addStartsWith(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public MovieSelection releaseDateEndsWith(String... value) {
        addEndsWith(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public MovieSelection orderByReleaseDate(boolean desc) {
        orderBy(MovieColumns.RELEASE_DATE, desc);
        return this;
    }

    public MovieSelection orderByReleaseDate() {
        orderBy(MovieColumns.RELEASE_DATE, false);
        return this;
    }

    public MovieSelection adult(boolean value) {
        addEquals(MovieColumns.ADULT, toObjectArray(value));
        return this;
    }

    public MovieSelection orderByAdult(boolean desc) {
        orderBy(MovieColumns.ADULT, desc);
        return this;
    }

    public MovieSelection orderByAdult() {
        orderBy(MovieColumns.ADULT, false);
        return this;
    }

    public MovieSelection voteAverage(float... value) {
        addEquals(MovieColumns.VOTE_AVERAGE, toObjectArray(value));
        return this;
    }

    public MovieSelection voteAverageNot(float... value) {
        addNotEquals(MovieColumns.VOTE_AVERAGE, toObjectArray(value));
        return this;
    }

    public MovieSelection voteAverageGt(float value) {
        addGreaterThan(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public MovieSelection voteAverageGtEq(float value) {
        addGreaterThanOrEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public MovieSelection voteAverageLt(float value) {
        addLessThan(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public MovieSelection voteAverageLtEq(float value) {
        addLessThanOrEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public MovieSelection orderByVoteAverage(boolean desc) {
        orderBy(MovieColumns.VOTE_AVERAGE, desc);
        return this;
    }

    public MovieSelection orderByVoteAverage() {
        orderBy(MovieColumns.VOTE_AVERAGE, false);
        return this;
    }

    public MovieSelection voteCount(int... value) {
        addEquals(MovieColumns.VOTE_COUNT, toObjectArray(value));
        return this;
    }

    public MovieSelection voteCountNot(int... value) {
        addNotEquals(MovieColumns.VOTE_COUNT, toObjectArray(value));
        return this;
    }

    public MovieSelection voteCountGt(int value) {
        addGreaterThan(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public MovieSelection voteCountGtEq(int value) {
        addGreaterThanOrEquals(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public MovieSelection voteCountLt(int value) {
        addLessThan(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public MovieSelection voteCountLtEq(int value) {
        addLessThanOrEquals(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public MovieSelection orderByVoteCount(boolean desc) {
        orderBy(MovieColumns.VOTE_COUNT, desc);
        return this;
    }

    public MovieSelection orderByVoteCount() {
        orderBy(MovieColumns.VOTE_COUNT, false);
        return this;
    }

    public MovieSelection popularity(float... value) {
        addEquals(MovieColumns.POPULARITY, toObjectArray(value));
        return this;
    }

    public MovieSelection popularityNot(float... value) {
        addNotEquals(MovieColumns.POPULARITY, toObjectArray(value));
        return this;
    }

    public MovieSelection popularityGt(float value) {
        addGreaterThan(MovieColumns.POPULARITY, value);
        return this;
    }

    public MovieSelection popularityGtEq(float value) {
        addGreaterThanOrEquals(MovieColumns.POPULARITY, value);
        return this;
    }

    public MovieSelection popularityLt(float value) {
        addLessThan(MovieColumns.POPULARITY, value);
        return this;
    }

    public MovieSelection popularityLtEq(float value) {
        addLessThanOrEquals(MovieColumns.POPULARITY, value);
        return this;
    }

    public MovieSelection orderByPopularity(boolean desc) {
        orderBy(MovieColumns.POPULARITY, desc);
        return this;
    }

    public MovieSelection orderByPopularity() {
        orderBy(MovieColumns.POPULARITY, false);
        return this;
    }

    public MovieSelection backdropPathUrl(String... value) {
        addEquals(MovieColumns.BACKDROP_PATH_URL, value);
        return this;
    }

    public MovieSelection backdropPathUrlNot(String... value) {
        addNotEquals(MovieColumns.BACKDROP_PATH_URL, value);
        return this;
    }

    public MovieSelection backdropPathUrlLike(String... value) {
        addLike(MovieColumns.BACKDROP_PATH_URL, value);
        return this;
    }

    public MovieSelection backdropPathUrlContains(String... value) {
        addContains(MovieColumns.BACKDROP_PATH_URL, value);
        return this;
    }

    public MovieSelection backdropPathUrlStartsWith(String... value) {
        addStartsWith(MovieColumns.BACKDROP_PATH_URL, value);
        return this;
    }

    public MovieSelection backdropPathUrlEndsWith(String... value) {
        addEndsWith(MovieColumns.BACKDROP_PATH_URL, value);
        return this;
    }

    public MovieSelection orderByBackdropPathUrl(boolean desc) {
        orderBy(MovieColumns.BACKDROP_PATH_URL, desc);
        return this;
    }

    public MovieSelection orderByBackdropPathUrl() {
        orderBy(MovieColumns.BACKDROP_PATH_URL, false);
        return this;
    }

    public MovieSelection backdropPathLocal(String... value) {
        addEquals(MovieColumns.BACKDROP_PATH_LOCAL, value);
        return this;
    }

    public MovieSelection backdropPathLocalNot(String... value) {
        addNotEquals(MovieColumns.BACKDROP_PATH_LOCAL, value);
        return this;
    }

    public MovieSelection backdropPathLocalLike(String... value) {
        addLike(MovieColumns.BACKDROP_PATH_LOCAL, value);
        return this;
    }

    public MovieSelection backdropPathLocalContains(String... value) {
        addContains(MovieColumns.BACKDROP_PATH_LOCAL, value);
        return this;
    }

    public MovieSelection backdropPathLocalStartsWith(String... value) {
        addStartsWith(MovieColumns.BACKDROP_PATH_LOCAL, value);
        return this;
    }

    public MovieSelection backdropPathLocalEndsWith(String... value) {
        addEndsWith(MovieColumns.BACKDROP_PATH_LOCAL, value);
        return this;
    }

    public MovieSelection orderByBackdropPathLocal(boolean desc) {
        orderBy(MovieColumns.BACKDROP_PATH_LOCAL, desc);
        return this;
    }

    public MovieSelection orderByBackdropPathLocal() {
        orderBy(MovieColumns.BACKDROP_PATH_LOCAL, false);
        return this;
    }
}
