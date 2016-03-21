package com.example.arun.masterwork.DBHandler;

import android.content.Context;

/**
 * Created by arun on 3/3/16.
 */
public class FetchMovieOverDb {
    private final String TAG = "FetchMovieOverDb";
    private Context mContext = null;
    private DbManager dbManager = null;

    public FetchMovieOverDb(Context mContext) {
        this.mContext = mContext;
    }

    /*public void setAdapter(BaseAdapter adapter) {
        if (adapter instanceof GridAdapter)
            this.gridAdapter = (GridAdapter) adapter;
        else
            Log.e(TAG, "Adapter is not of type GridAdapter : Class cast exception");
    }*/

    public void fetchMovies(String categoryInDb) {

    }

    public void fetchMovieTrailer(int movieId/*, int movie_id*/) {

    }

    public void fetchMovieReviews(int movieId/*, int movie_id*/) {

    }
}
