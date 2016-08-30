package com.example.arun.masterwork.fragment;


import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arun.masterwork.R;
import com.example.arun.masterwork.activity.MainActivity;
import com.example.arun.masterwork.adapter.DetailRecyclerAdapter;
import com.example.arun.masterwork.adapter.Utility;
import com.example.arun.masterwork.provider.movie.MovieCursor;
import com.example.arun.masterwork.provider.movie.MovieSelection;
import com.example.arun.masterwork.provider.review.ReviewColumns;
import com.example.arun.masterwork.provider.review.ReviewCursor;
import com.example.arun.masterwork.provider.trailer.TrailerColumns;
import com.example.arun.masterwork.provider.trailer.TrailerCursor;

/**
 * Created by arun on 2/7/16.
 */

public class DetailFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private Integer movieDbId = 0;
    private boolean mTwoPane;
    private MovieCursor movieCursor = null;
    private Context mContext = null;
    private DetailRecyclerAdapter adapter = null;
    private TrailerCursor trailerCursor = null;
    private ReviewCursor reviewCursor = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();

        if (getArguments().containsKey("movie_db_id")) {
            movieDbId = (Integer) getArguments().get("movie_db_id");
        }
        if (getActivity() instanceof MainActivity)
            mTwoPane = true;
        movieCursor = new MovieSelection().id(movieDbId).query(mContext.getContentResolver());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_detail, container, false);
        if (movieCursor.moveToFirst()) {
            if (mTwoPane) {
                FrameLayout tabletFrame = (FrameLayout) view.findViewById(R.id.tablet_frame);
                View collapseView = inflater.inflate(R.layout.detail_collapse, tabletFrame, true);
                ImageView backimage = (ImageView) collapseView.findViewById(R.id.back_image);
                Bitmap backBitmap = Utility.getImageBitmap(movieCursor.getBackdropPathLocal(), mContext);
                backimage.setImageBitmap(backBitmap);
                TextView titleTv = (TextView) collapseView.findViewById(R.id.title_label);
                titleTv.setText(movieCursor.getTitle());
                titleTv.setBackgroundColor(Utility.getTitleBarColor(backBitmap));
            }
            ImageView posterImage = (ImageView) view.findViewById(R.id.poster_image);
            posterImage.setImageBitmap(Utility.getImageBitmap(movieCursor.getPosterPathLocal(), mContext));
            TextView releaseDate = (TextView) view.findViewById(R.id.release_date_tv);
            releaseDate.setText(movieCursor.getReleaseDate());
            TextView rating = (TextView) view.findViewById(R.id.rating_tv);
            rating.setText(Float.valueOf(movieCursor.getVoteAverage()).toString());
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.detail_dynamic_list);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager);
            adapter = new DetailRecyclerAdapter(mContext);
            adapter.setDescription(movieCursor.getOverview());
            recyclerView.setAdapter(adapter);
        }

        getActivity().getSupportLoaderManager().initLoader(0, null, this);
        getActivity().getSupportLoaderManager().initLoader(1, null, this);
        return view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (id == 0)
            return new CursorLoader(getContext(), TrailerColumns.CONTENT_URI, TrailerColumns.ALL_COLUMNS
                    , TrailerColumns.MOVIE_ID + "=?", new String[]{movieDbId.toString()}, null);
        if (id == 1)
            return new CursorLoader(getContext(), ReviewColumns.CONTENT_URI, ReviewColumns.ALL_COLUMNS
                    , ReviewColumns.MOVIE_ID + "=?", new String[]{movieDbId.toString()}, null);
        else
            return null;
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        /*if(loader.getId() == 0)
            adapter.setTrailerCursor(null);
        if(loader.getId() == 1)
            adapter.setReviewCursor(null);*/
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        int id = loader.getId();
        if (id == 0 && cursor.moveToFirst()) {
            trailerCursor = new TrailerCursor(cursor);
            adapter.setTrailerCursor(trailerCursor);
        }
        if (id == 1 && cursor.moveToFirst()) {
            reviewCursor = new ReviewCursor(cursor);
            adapter.setReviewCursor(reviewCursor);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (trailerCursor != null)
            trailerCursor.close();
        if (reviewCursor != null)
            reviewCursor.close();
    }
}
