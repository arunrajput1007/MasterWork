package com.example.arun.masterwork.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.graphics.Palette;

import com.example.arun.masterwork.R;
import com.example.arun.masterwork.models.Movie;
import com.example.arun.masterwork.provider.movie.MovieColumns;

import java.io.FileInputStream;
import java.util.ArrayList;

/**
 * Created by arun on 19/3/16.
 */
public class Utility {

    private Context mContext = null;

    private static String firstTrailer = null;

    public Utility(Context mContext){
        this.mContext = mContext;
    }

    public Utility(){

    }

    public ArrayList<Movie> getMovieListFromCursor(Cursor cursor){
        ArrayList<Movie> movieArrayList = new ArrayList<>();
        Movie movie = null;
            while (cursor.moveToNext()) {
                movie = new Movie();
                movie.setId(cursor.getInt(cursor.getColumnIndex(MovieColumns.MOVIE_NO)));
                movie.setTitle(cursor.getString(cursor.getColumnIndex(MovieColumns.TITLE)));
                movie.setDescription(cursor.getString(cursor.getColumnIndex(MovieColumns.OVERVIEW)));
                movie.setReleaseDate(cursor.getString(cursor.getColumnIndex(MovieColumns.RELEASE_DATE)));
                movie.setRating(cursor.getFloat(cursor.getColumnIndex(MovieColumns.VOTE_AVERAGE)));
                movie.setVoteCount(cursor.getInt(cursor.getColumnIndex(MovieColumns.VOTE_COUNT)));
                movie.setPopularity(cursor.getFloat(cursor.getColumnIndex(MovieColumns.POPULARITY)));
                movie.setPosterLocalPath(cursor.getString(cursor.getColumnIndex(MovieColumns.POSTER_PATH_LOCAL)));
                movie.setBackImageLocalPath(cursor.getString(cursor.getColumnIndex(MovieColumns.BACKDROP_PATH_LOCAL)));
                movieArrayList.add(movie);
            }
        return movieArrayList;
    }

    public Bitmap getImageBitmap(String name){
        try{
            FileInputStream fis = mContext.openFileInput(name);
            Bitmap b = BitmapFactory.decodeStream(fis);
            fis.close();
            return b;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static int getTitleBarColor(Bitmap bitmap) {
        int color = R.color.colorPrimary;
        if (bitmap != null) {
            Palette p = Palette.from(bitmap).generate();
            Palette.Swatch vibrantSwatch = p.getVibrantSwatch();

            if (vibrantSwatch != null)
                color = vibrantSwatch.getRgb();
        }
        return color;
    }

    public static String getFirstTrailer() {
        return firstTrailer;
    }

    public static void setFirstTrailer(String firstTrailer) {
        Utility.firstTrailer = firstTrailer;
    }
}
