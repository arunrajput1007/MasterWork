package com.example.arun.masterwork.syncadapter;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import android.util.Log;

import com.example.arun.masterwork.provider.movie.MovieSelection;

/**
 * Created by arun on 28/8/16.
 */

public class SyncAdapter extends AbstractThreadedSyncAdapter {

    private ContentResolver mContentResolver;

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        mContentResolver = context.getContentResolver();
    }

    public SyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        mContentResolver = context.getContentResolver();
    }

    @Override
    public void onPerformSync(Account account, Bundle bundle, String authority, ContentProviderClient contentProviderClient, SyncResult syncResult) {
        Log.e("Syncadapter", "----------------syncperformed-------------------------");
        MovieSelection movieSelection = new MovieSelection();
        //movieSelection.category(category);
        movieSelection.delete(mContentResolver);
       /* MovieDataFetcher fetcher = new MovieDataFetcher(getContext());
        Utility utility = new Utility(getContext());

        String [] categories = {MovieConstant.MOVIE_CATEGORY_POPULAR, MovieConstant.MOVIE_CATEGORY_TOP_RATED};

        for (String category:categories) {
            List<Movie> networkMovieList = fetcher.fetchMovies(category);
            ArrayList<Movie> dbMovieList = utility.getMovieListByCategory(category);

            if(!utility.compareMovieList(networkMovieList,dbMovieList)){
                MovieSelection movieSelection = new MovieSelection();
                //movieSelection.category(category);
                movieSelection.delete(mContentResolver);
                //utility.insertMovieListInDb(networkMovieList,category);
            }
        }*/
    }
}
