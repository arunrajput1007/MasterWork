package com.example.arun.masterwork.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by arun on 29/6/16.
 */

public class Trailer implements Parcelable, Databasable {

    static Creator<Trailer> CREATOR = new Creator<Trailer>() {
        @Override
        public Trailer createFromParcel(Parcel source) {
            return new Trailer(source);
        }

        @Override
        public Trailer[] newArray(int size) {
            return new Trailer[size];
        }
    };

    @SerializedName("key")
    private String key;
    @SerializedName("name")
    private String videoName;
    @SerializedName("type")
    private String type;

    private Trailer(Parcel in) {
        key = in.readString();
        videoName = in.readString();
        type = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(key);
        dest.writeString(videoName);
        dest.writeString(type);
    }

    public String getKey() {
        return key;
    }

    public String getVideoName() {
        return videoName;
    }

    public String getType() {
        return type;
    }
}
