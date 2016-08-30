package com.example.arun.masterwork.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.arun.masterwork.R;
import com.example.arun.masterwork.activity.DetailActivity;
import com.example.arun.masterwork.activity.MainActivity;
import com.example.arun.masterwork.fragment.DetailFragment;
import com.example.arun.masterwork.provider.movie.MovieCursor;

/**
 * Created by arun on 31/5/16.
 */

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {

    private MovieCursor movieCursor = null;
    private Context mContext = null;
    private AppCompatActivity activity = null;
    private boolean mTwoPane;

    public MainRecyclerAdapter(@NonNull Context mContext, Cursor mCursor) {
        this.mContext = mContext;
        if (mCursor != null) {
            movieCursor = new MovieCursor(mCursor);
        }
        if (mContext instanceof MainActivity) {
            this.activity = (AppCompatActivity) mContext;
            if (activity.findViewById(R.id.movie_detail_container) != null) {
                mTwoPane = true;
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ImageView posterImage = new ImageView(parent.getContext());
        return new ViewHolder(posterImage);
    }

    @Override
    public long getItemId(int position) {
        movieCursor.moveToPosition(position);
        return movieCursor.getId();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        movieCursor.moveToPosition(position);
        String posterPath = movieCursor.getPosterPathLocal();
        holder.posterImage.setImageBitmap(Utility.getImageBitmap(posterPath, mContext));
        holder.posterImage.setAdjustViewBounds(true);
        holder.posterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int movieDbId = (int) getItemId(holder.getAdapterPosition());
                if (mTwoPane && activity instanceof MainActivity) {
                    DetailFragment fragment = new DetailFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("movie_db_id", movieDbId);
                    fragment.setArguments(bundle);
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.movie_detail_container, fragment).commit();
                } else if (!mTwoPane && activity instanceof MainActivity) {
                    activity.startActivity(new Intent(activity, DetailActivity.class).putExtra("movie_db_id", movieDbId));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (movieCursor != null)
            return movieCursor.getCount();
        return 0;
    }

    public void swapCursor(Cursor cursor) {
        if (cursor != null) {
            MovieCursor newMovieCursor = new MovieCursor(cursor);
            if (!newMovieCursor.equals(movieCursor))
                movieCursor = newMovieCursor;
            notifyDataSetChanged();
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView posterImage = null;

        ViewHolder(ImageView posterImage) {
            super(posterImage);
            this.posterImage = posterImage;
        }
    }
}
