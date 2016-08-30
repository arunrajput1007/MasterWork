package com.example.arun.masterwork.loaders;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.example.arun.masterwork.provider.movie.MovieColumns;

/**
 * Created by arun on 15/5/16.
 */
public class MovieLoader implements LoaderCallbacks<Cursor> {

    private boolean isFavoriteButtonClicked;
    private Context mContext = null;

    public MovieLoader(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (id == 1)
            isFavoriteButtonClicked = true;
        return new CursorLoader(mContext, MovieColumns.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (isFavoriteButtonClicked) {
            /*Utility utility = new Utility();
            ArrayList<Movie> movieList = utility.getMovieListFromCursor(cursor);*/
            //movieGridAdapter.updateData(movieList);
            isFavoriteButtonClicked = false;
        }
    }
}
