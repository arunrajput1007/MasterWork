package com.example.arun.masterwork.provider.movie;

import android.net.Uri;
import android.provider.BaseColumns;

import com.example.arun.masterwork.provider.MovieProvider;

/**
 * Movie Table containing movie related data
 */
public class MovieColumns implements BaseColumns {
    public static final String TABLE_NAME = "movie";
    public static final Uri CONTENT_URI = Uri.parse(MovieProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    /**
     * Movie Id
     */
    public static final String MOVIE_NO = "movie_no";

    /**
     * Movie Title
     */
    public static final String TITLE = "title";

    /**
     * Category
     */
    public static final String CATEGORY = "category";

    /**
     * Movie Poster
     */
    public static final String POSTER_PATH_URL = "poster_path_url";

    /**
     * Movie Poster local path
     */
    public static final String POSTER_PATH_LOCAL = "poster_path_local";

    /**
     * Movie Overview
     */
    public static final String OVERVIEW = "overview";

    /**
     * Movie Release Date
     */
    public static final String RELEASE_DATE = "release_date";

    /**
     * Is Adult or not
     */
    public static final String ADULT = "adult";

    /**
     * Movie Ratings
     */
    public static final String VOTE_AVERAGE = "vote_average";

    /**
     * Movie vote count
     */
    public static final String VOTE_COUNT = "vote_count";

    /**
     * Movie Popularity
     */
    public static final String POPULARITY = "popularity";

    /**
     * Movie Back Image Url
     */
    public static final String BACKDROP_PATH_URL = "backdrop_path_url";

    /**
     * Movie Back Image Local path
     */
    public static final String BACKDROP_PATH_LOCAL = "backdrop_path_local";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            MOVIE_NO,
            TITLE,
            CATEGORY,
            POSTER_PATH_URL,
            POSTER_PATH_LOCAL,
            OVERVIEW,
            RELEASE_DATE,
            ADULT,
            VOTE_AVERAGE,
            VOTE_COUNT,
            POPULARITY,
            BACKDROP_PATH_URL,
            BACKDROP_PATH_LOCAL
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(MOVIE_NO) || c.contains("." + MOVIE_NO)) return true;
            if (c.equals(TITLE) || c.contains("." + TITLE)) return true;
            if (c.equals(CATEGORY) || c.contains("." + CATEGORY)) return true;
            if (c.equals(POSTER_PATH_URL) || c.contains("." + POSTER_PATH_URL)) return true;
            if (c.equals(POSTER_PATH_LOCAL) || c.contains("." + POSTER_PATH_LOCAL)) return true;
            if (c.equals(OVERVIEW) || c.contains("." + OVERVIEW)) return true;
            if (c.equals(RELEASE_DATE) || c.contains("." + RELEASE_DATE)) return true;
            if (c.equals(ADULT) || c.contains("." + ADULT)) return true;
            if (c.equals(VOTE_AVERAGE) || c.contains("." + VOTE_AVERAGE)) return true;
            if (c.equals(VOTE_COUNT) || c.contains("." + VOTE_COUNT)) return true;
            if (c.equals(POPULARITY) || c.contains("." + POPULARITY)) return true;
            if (c.equals(BACKDROP_PATH_URL) || c.contains("." + BACKDROP_PATH_URL)) return true;
            if (c.equals(BACKDROP_PATH_LOCAL) || c.contains("." + BACKDROP_PATH_LOCAL)) return true;
        }
        return false;
    }

}
