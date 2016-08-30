package com.example.arun.masterwork.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by arun on 29/6/16.
 */

public class TrailerSet implements Parcelable {

    static Creator<TrailerSet> CREATOR = new Creator<TrailerSet>() {
        @Override
        public TrailerSet createFromParcel(Parcel source) {
            return new TrailerSet(source);
        }

        @Override
        public TrailerSet[] newArray(int size) {
            return new TrailerSet[size];
        }
    };

    @SerializedName("id")
    private int movieId;
    @SerializedName("results")
    private ArrayList<Trailer> trailerList;

    private TrailerSet(Parcel in) {
        movieId = in.readInt();
        trailerList = in.createTypedArrayList(Trailer.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(movieId);
        dest.writeTypedList(trailerList);
    }

    public int getMovieId() {
        return movieId;
    }

    public ArrayList<Trailer> getTrailerList() {
        return trailerList;
    }
}
