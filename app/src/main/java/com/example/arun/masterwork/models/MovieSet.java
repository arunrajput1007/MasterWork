package com.example.arun.masterwork.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by arun on 19/12/15.
 */
public class MovieSet implements Parcelable {

    public static Creator<MovieSet> CREATOR = new Creator<MovieSet>() {
        @Override
        public MovieSet createFromParcel(Parcel source) {
            return new MovieSet(source);
        }

        @Override
        public MovieSet[] newArray(int size) {
            return new MovieSet[size];
        }
    };

    @SerializedName("page")
    private int currentPage;

    @SerializedName("results")
    private ArrayList<Movie> movieList;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("total_pages")
    private int totalPages;

    private MovieSet(Parcel in) {
        currentPage = in.readInt();
        movieList = in.createTypedArrayList(Movie.CREATOR);
        totalResults = in.readInt();
        totalPages = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(currentPage);
        dest.writeTypedList(movieList);
        dest.writeInt(totalResults);
        dest.writeInt(totalPages);
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public ArrayList<Movie> getMovieList() {
        return movieList;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
